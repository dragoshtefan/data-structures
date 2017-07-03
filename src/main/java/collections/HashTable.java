package collections;
import collectionInterfaces.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HashTable <K,V> implements Serializable, Map<K, V> {

    private static final float DEFAULT_FILL_FACTOR = 0.7f;
    private static final int DEFAULT_START_SIZE = 10;

    private BinarySearchTree<Map.Entry<K, V>>[] buckets;
    private float fillFactor;
    private int size;


    public HashTable(int size){
        this(size, DEFAULT_FILL_FACTOR);
    }

    public HashTable(int size, float fillFactor) {
        this.size = size;
        this.fillFactor = fillFactor;
     //   this.buckets = new BinarySearchTree()[];
        for (BinarySearchTree bucket : this.buckets) {
   //         bucket = new
        }
    }

    public HashTable(){
        this(DEFAULT_START_SIZE, DEFAULT_FILL_FACTOR);
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }

    public boolean containsKey() {
        return false;
    }

    public boolean containsValue(V object) {
        return false;
    }

    public V get(K key) {
        return null;
    }

    public V put(K key, V val) {
        return null;
    }

    public V remove(K key) {
        return null;
    }

    public Set<K> keySet() {
        return null;
    }

    class Entry<K, V> implements Map.Entry, Serializable {

        private K key;
        private V value;

        Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(Object value) {
            return null;
        }
    }

}
