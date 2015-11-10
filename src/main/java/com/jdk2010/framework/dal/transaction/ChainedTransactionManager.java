package com.jdk2010.framework.dal.transaction;

/**
 * 出处
 * https://github.com/spring-projects/spring-data-graph/tree/master/spring-data-neo4j/src/main/java/org/springframework/data/neo4j
 * 
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.HeuristicCompletionException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ChainedTransactionManager implements PlatformTransactionManager, InitializingBean {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<PlatformTransactionManager> transactionManagers;

    public List<PlatformTransactionManager> getTransactionManagers() {
        return transactionManagers;
    }

    public void setTransactionManagers(List<PlatformTransactionManager> transactionManagers) {
        this.transactionManagers = transactionManagers;
    }

    @Override
    public MultiTransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {

        MultiTransactionStatus mts = new MultiTransactionStatus(transactionManagers.get(0)/* First TM is main TM */);

        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.initSynchronization();
            mts.setNewSynchonization();
        }

        for (PlatformTransactionManager transactionManager : transactionManagers) {
            mts.registerTransactionManager(definition, transactionManager);
        }

        return mts;
    }

    @Override
    public void commit(TransactionStatus status) throws TransactionException {

        MultiTransactionStatus multiTransactionStatus = (MultiTransactionStatus) status;

        boolean commit = true;
        Exception commitException = null;
        PlatformTransactionManager commitExceptionTransactionManager = null;

        for (PlatformTransactionManager transactionManager : transactionManagers) {
            if (commit) {
                try {
                    multiTransactionStatus.commit(transactionManager);
                } catch (Exception ex) {
                    commit = false;
                    commitException = ex;
                    commitExceptionTransactionManager = transactionManager;
                }
            } else {
                try {
                    multiTransactionStatus.rollback(transactionManager);
                } catch (Exception ex) {
                    logger.error("Rollback exception (after commit) (" + transactionManager + ") " + ex.getMessage());
                }
            }
        }

        if (multiTransactionStatus.isNewSynchonization()) {
            TransactionSynchronizationManager.clearSynchronization();
        }

        if (commitException != null) {
            boolean firstTransactionManagerFailed = commitExceptionTransactionManager == getLastTransactionManager();
            int transactionState = firstTransactionManagerFailed ? HeuristicCompletionException.STATE_ROLLED_BACK
                    : HeuristicCompletionException.STATE_MIXED;
            throw new HeuristicCompletionException(transactionState, commitException);
        }

    }

    @Override
    public void rollback(TransactionStatus status) throws TransactionException {

        Exception rollbackException = null;
        PlatformTransactionManager rollbackExceptionTransactionManager = null;

        MultiTransactionStatus multiTransactionStatus = (MultiTransactionStatus) status;

        for (PlatformTransactionManager transactionManager :transactionManagers) {
            try {
                multiTransactionStatus.rollback(transactionManager);
            } catch (Exception ex) {
                if (rollbackException == null) {
                    rollbackException = ex;
                    rollbackExceptionTransactionManager = transactionManager;
                } else {
                    logger.error("Rollback exception (" + transactionManager + ") " + ex.getMessage());
                }
            }
        }

        if (multiTransactionStatus.isNewSynchonization()) {
            TransactionSynchronizationManager.clearSynchronization();
        }

        if (rollbackException != null) {
            throw new UnexpectedRollbackException("Rollback exception, originated at ("
                    + rollbackExceptionTransactionManager + ") " + rollbackException.getMessage(), rollbackException);
        }
    }


    private PlatformTransactionManager getLastTransactionManager() {
        return transactionManagers.get(lastTransactionManagerIndex());
    }

    private int lastTransactionManagerIndex() {
        return transactionManagers.size() - 1;
    }

    public static void main(String[] args) {
        // BeanFactory factory = new
        // ClassPathXmlApplicationContext("spring/applicationContext2.xml");
        // PlatformTransactionManager
        // manater1=(DataSourceTransactionManager)factory.getBean("transactionManager1");
        // PlatformTransactionManager
        // manater2=(DataSourceTransactionManager)factory.getBean("transactionManager2");
        // PlatformTransactionManager
        // manater3=(DataSourceTransactionManager)factory.getBean("transactionManager3");
        // ChainedTransactionManager manager4 =new
        // ChainedTransactionManager(manater1,manater2,manater3);
        // TransactionStatus transactionStatus1 = manager4.getTransaction(new
        // DefaultTransactionDefinition());
        // try {
        // IStudentDao studentDao = factory.getBean(IStudentDao.class);
        // studentDao.save();
        // manager4.commit(transactionStatus1);
        // }catch(Exception e){
        // manager4.rollback(transactionStatus1);
        // e.printStackTrace();
        // }
        // 测试代码
        // }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("初始化：" + transactionManagers.size() + "个数据源");
    }
}
