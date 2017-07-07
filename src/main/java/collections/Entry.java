package collections;

import java.io.Serializable;

import collectionInterfaces.Map;

public class Entry<C, W> implements Map.Entry<C, W>, Serializable {

    private C key;
    private W value;

    Entry(C key, W value){
        this.key = key;
        this.value = value;
    }

    @Override
    public C getKey() {
        return key;
    }

    @Override
    public W getValue() {
        return value;
    }

    @Override
    public W setValue(Object value) {
        return null;
    }


}