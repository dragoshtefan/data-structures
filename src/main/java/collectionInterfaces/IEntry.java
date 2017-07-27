package collectionInterfaces;

public interface IEntry<K, V> {

    K getKey();

    V getValue();

    V setValue(V value);

}
