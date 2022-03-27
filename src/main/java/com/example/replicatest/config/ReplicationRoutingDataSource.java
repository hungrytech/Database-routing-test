package com.example.replicatest.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    private static final String READ = "read";

    private static final String WRITE = "write";

    private final ReadOnlyDataSourceCycle<String> readOnlyDataSourceCycle;

    public ReplicationRoutingDataSource() {
        this.readOnlyDataSourceCycle = new ReadOnlyDataSourceCycle<>();
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        List<String> readOnlyDataSourceLookupKeys = targetDataSources.keySet()
                .stream()
                .map(String::valueOf)
                .filter(lookupKey -> lookupKey.contains(READ))
                .collect(Collectors.toList());
        readOnlyDataSourceCycle.setReadOnlyDataSourceLookupKeys(readOnlyDataSourceLookupKeys);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ?
                readOnlyDataSourceCycle.getRadOnlyDataSourceLookupKey()
                : WRITE;
    }
}
