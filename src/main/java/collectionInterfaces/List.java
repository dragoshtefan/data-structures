package collectionInterfaces;

public interface List<V> {

    void addItem(V value);

    V removeItem(V value);

    int size();;

    V get(int i);

    V remove(int i);


}
