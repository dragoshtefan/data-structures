import java.util.Set;

public interface Map<K, V> {

    boolean isEmpty();

    int size();

    boolean containsKey();

    boolean containsValue(V object);

    V get(K key);

    V put(K key, V val);

    V remove(K key);

    Set<K> keySet();

    interface Entry<K, V> {

        K getKey();

        V getValue();

        V setValue(V value);

    }
}
