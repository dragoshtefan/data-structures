public interface Map<K, V> {

    boolean isEmpty();

    int size();

    boolean containsKey();

    boolean containsValue();

    V get(K key);

    V put(K key, V obj);

    V remove(K key);
}
