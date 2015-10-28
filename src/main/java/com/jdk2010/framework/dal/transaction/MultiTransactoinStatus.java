package com.jdk2010.framework.dal.transaction;

import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class MultiTransactoinStatus implements TransactionStatus {

	@Override
	public Object createSavepoint() throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rollbackToSavepoint(Object savepoint)
			throws TransactionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void releaseSavepoint(Object savepoint) throws TransactionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isNewTransaction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasSavepoint() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRollbackOnly() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRollbackOnly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCompleted() {
		// TODO Auto-generated method stub
		return false;
	}

}
