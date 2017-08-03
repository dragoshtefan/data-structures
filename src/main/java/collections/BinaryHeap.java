package collections;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/*
    - heap is a data structure used to sort values. It is very good at fetching min or max value, depending on its implementation
    - it is backed up by an array (or arrayList).
    - To obtain the array representation of a tree, we use LEVEL ORDER traversal. Level order means we read from left to right,
        top to bottom, one level of the tree at a time. Therefore, we can say this about the array backing it:
        - root is stored in array[0]
        - parent of node array[i] is at array[(i-1)/2]
        - left child is at array[2*i+1]
        - right child is at array[2*i+2]
    - MIN_HEAP property : node value is higher or equal to its parents value; min of tree at root
    - MAX_HEAP property: node value is lower or equal to its parents value; max of tree at root
    -fetching min or max takes O(1) while set, delete, deleteTop are  done in O(logN) time
 */

public class BinaryHeap<V extends Comparable<? super V>> {

    private BiFunction<V, V, Boolean> compareFunc;
    private List<V> values;
    private int size;
    private final OrderProperty orderProperty;

    public enum OrderProperty {
        MIN_HEAP,
        MAX_HEAP;
    }


    public BinaryHeap(OrderProperty order) {
        this.size = 0;
        this.orderProperty = order;
        this.values = new ArrayList<V>();
        BiFunction<V, V, Integer> compareAux = V::compareTo;
        if (orderProperty.equals(OrderProperty.MIN_HEAP))
            compareFunc = (v1, v2) -> compareAux.apply(v1, v2).compareTo(0) > 0;
        else
            compareFunc = (v1, v2) -> compareAux.apply(v1, v2).compareTo(0) < 0;
    }


    public BinaryHeap() {
        this(OrderProperty.MIN_HEAP);
    }

    public V getTop() {
        return values.get(0);
    }

    public V removeTop(){
        V top = values.get(0);
        values.remove(0);
        eqilibrateTree(values);
        return top;
    }

    public boolean insertValue(V value) {
        values.add(value);
        eqilibrateTree(values);
        return true;
    }

    private void eqilibrateTree(List<V> subList) {
        if (subList.size() == 1)
            return;
        int pos = subList.size() - 1;
        int ppos = (pos - 1) / 2;
        if (!compareFunc.apply(subList.get(pos), subList.get(ppos))) {
            V aux = values.get(pos);
            values.set(pos, values.get(ppos));
            values.set(ppos, aux);
            eqilibrateTree(subList.subList(0, ppos + 1));
        }
    }


    public boolean deleteValue(V value) {

    }

    public int size() {
        return values.size();
    }

    public V get(int i) {
        return values.get(i);
    }

    public V remove(int i) {
        return null;
    }

}
