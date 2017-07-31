package collections;

import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.function.BiFunction;

import collectionInterfaces.List;

public class BinaryHeap<V> implements List<V> {

    private static final int DEFAULT_SIZE = 100;
    private final Class<V> type;
    private BiFunction<V, V, Integer> compare;
    private V values[];
    private int size;
    private OrderProperty orderProperty;

    public enum OrderProperty{
        MIN_HEAP,
        MAX_HEAP;

    }


    public BinaryHeap(int size, OrderProperty orderProperty, Class<V> type) {
        this.size = size;
        this.orderProperty = orderProperty;
        this.values = (V[])new Object[size];
        if (Comparable.class.isAssignableFrom(type)) {
            this.compare =
        }
        if (orderProperty.equals(OrderProperty.MIN_HEAP)){
            this.compare = V::
        }
    }


    public BinaryHeap() {
        this(DEFAULT_SIZE, OrderProperty.MIN_HEAP)
    }

    @Override
    public void addItem(V value) {

    }

    @Override
    public V removeItem(V value) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public V get(int i) {
        return null;
    }

    @Override
    public V remove(int i) {
        return null;
    }

}
