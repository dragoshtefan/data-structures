package collectionInterfaces;

import java.util.Set;

import exceptions.DuplicateValueException;
import exceptions.NullValueException;

public interface Map<K extends Comparable<K>, V> {

    boolean isEmpty();

    int size();

    boolean containsKey(K key);

    boolean containsValue(V object);

    V get(K key);

    V put(K key, V val) throws DuplicateValueException, NullValueException;

    V remove(K key);

    Set<K> keySet();

    interface Entry<K, V> {

        K getKey();

        V getValue();

        V setValue(V value);

    }
}
