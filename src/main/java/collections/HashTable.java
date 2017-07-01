package collections;
import collectionInterfaces.Map;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

public class HashTable <K,V> implements Serializable, Map<K, V> {

    private Map.Entry<K, V>[] entries;

    public HashTable(){
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

}
