package com.jdk2010.framework.dal.transaction;

import org.springframework.transaction.support.TransactionSynchronizationManager;

public class DefaultSynchronizationManager implements SynchronizationManager {
    @Override
    public void initSynchronization() {
        TransactionSynchronizationManager.initSynchronization();
    }

    @Override
    public boolean isSynchronizationActive() {
        return TransactionSynchronizationManager.isSynchronizationActive();
    }

    @Override
    public void clearSynchronization() {
        TransactionSynchronizationManager.clear();
    }
}
