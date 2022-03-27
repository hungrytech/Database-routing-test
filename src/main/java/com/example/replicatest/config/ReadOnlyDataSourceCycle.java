package com.example.replicatest.config;

import java.util.Collections;
import java.util.List;

public class ReadOnlyDataSourceCycle <T> {

    private List<T> readOnlyDataSourceLookupKeys;

    private int index;

    public ReadOnlyDataSourceCycle() {
        this.index = 0;
    }

    public void setReadOnlyDataSourceLookupKeys(List<T> readOnlyDataSourceLookupKeys) {
        this.readOnlyDataSourceLookupKeys = Collections.unmodifiableList(readOnlyDataSourceLookupKeys);
    }

    public T getRadOnlyDataSourceLookupKey() {
        if (index + 1 >= readOnlyDataSourceLookupKeys.size()) {
            index = -1;
        }
        return readOnlyDataSourceLookupKeys.get(++index);
    }
}
