package collectionInterfaces;

public interface KeyValueTree<K, V> {

    void add(K key, V value);

    boolean isEmpty();

    boolean keyExists(K key);

    void removeItem(K key);

    int getSize();

    V getValue(K key);

}
