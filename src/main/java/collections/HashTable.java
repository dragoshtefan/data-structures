package collections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import collectionInterfaces.Map;
import com.google.common.collect.ImmutableSet;

public class HashTable <K extends Comparable< K>,V> implements Serializable, Map<K, V> {

    private static final float DEFAULT_FILL_FACTOR = 0.7f;
    private static final int DEFAULT_START_SIZE = 10;

    private List<BinarySearchTree<K, V>> buckets;
    private float fillFactor;
    private int bucketNo;
    private int noElem;


    public HashTable(int bucketNo){

            this(bucketNo, DEFAULT_FILL_FACTOR);
    }

    public HashTable(int bucketNo, float fillFactor) {
        this.bucketNo = bucketNo;
        this.fillFactor = fillFactor;
        this.buckets = new ArrayList<BinarySearchTree<K, V>>(bucketNo);
        for (int i = 0; i < bucketNo; i++) {
            buckets.set(i, new BinarySearchTree<>());
        }
        noElem = 0;
    }

    public HashTable(){
        this(DEFAULT_START_SIZE, DEFAULT_FILL_FACTOR);
    }

    public boolean isEmpty() {
        return bucketNo == 0;
    }

    public int size() {
        return bucketNo;
    }

    @Override
    public boolean containsKey(K key) {
        int hash = key.hashCode() % this.bucketNo;
        BinarySearchTree<K,V> bucket = buckets.get(hash);
        if (bucket == null || bucket.isEmpty()) {
            return false;
        }
        for (Entry<K, V> entry : bucket.getAsList()) {
            if (entry.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V object) {
        for (BinarySearchTree<K,V> bucket : buckets) {
            for (Entry<K, V> entry : bucket.getAsList()){
                if (entry.getValue().equals(object)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int hash = key.hashCode() % bucketNo;
        BinarySearchTree<K,V> bucket = buckets.get(hash);
        return bucket.getValue(key);
    }

    @Override
    public V put(K key, V val) {
        int hash = key.hashCode() % this.size();
        if (buckets.get(hash).getSize() / this.size() > this.fillFactor){
            this.doubleCapacity();
            hash = key.hashCode() % this.size();
        }
        buckets.get(hash).add(key, val);
        return val;
    }

    private void doubleCapacity() {
        List<BinarySearchTree<K, V>> newBuckets = new ArrayList<>(bucketNo);
        int newSize = bucketNo * 2;
        for (int i = 0; i < newSize; i++) {
            newBuckets.set(i, new BinarySearchTree<>());
        }
        for (int i = 0; i < bucketNo; i++){
            for (Entry<K, V> entry : buckets.get(i).getAsList()) {
                int newHash = entry.getKey().hashCode() % newSize;
                newBuckets.get(newHash).add(entry.getKey(), entry.getValue());
            }
        }
        this.buckets = newBuckets;
        this.bucketNo = newSize;
    }

    @Override
    public V remove(K key) {
        int hash = key.hashCode() % bucketNo;
        V value = buckets.get(hash).getValue(key);
        buckets.get(hash).removeItem(key);
        return value;
    }

    @Override
    public Set<K> keySet() {
        final Set<K> keys = new HashSet<K>();
        for (int i = 0; i < bucketNo; i++) {
            keys.addAll(buckets.get(i).getAsList().stream().map(Entry::getKey).collect(Collectors.toList()));
        }
        return ImmutableSet.copyOf(keys);
    }

}
