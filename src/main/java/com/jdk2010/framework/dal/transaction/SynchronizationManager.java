package com.jdk2010.framework.dal.transaction;

public interface SynchronizationManager {
	void initSynchronization();
	boolean isSynchronizationActive();
	void clearSynchronization();
}
