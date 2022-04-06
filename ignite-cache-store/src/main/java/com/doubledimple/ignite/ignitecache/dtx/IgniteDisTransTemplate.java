package com.doubledimple.ignite.ignitecache.dtx;


import org.apache.ignite.Ignite;
import org.apache.ignite.transactions.Transaction;
import org.apache.ignite.transactions.TransactionConcurrency;
import org.apache.ignite.transactions.TransactionIsolation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IgniteDisTransTemplate {

    private static final Logger LOGGER  = LoggerFactory.getLogger(IgniteDisTransTemplate.class);

    @Autowired
    private Ignite ignite;


    public void disTrans(TransactionConcurrency transactionConcurrency, TransactionIsolation transactionIsolation,IgniteTransService igniteTransService){

        LOGGER.info("ignite transactional start.....");
        Transaction transaction = null;

        try{
            transaction = ignite.transactions().txStart(transactionConcurrency, transactionIsolation);

            //load business
            igniteTransService.loadDisTrans();

            transaction.commit();

            LOGGER.info("ignite transaction commit success");
        } catch (Exception e) {
            LOGGER.error("business is error,current transactional is rollback....");
            LOGGER.error("rollback reason: [{}]",e.getMessage());
            e.printStackTrace();
            if (null != transaction) transaction.rollback();
        } finally {
            if (null != transaction){
                transaction.close();
                LOGGER.info("ignite transaction closed");
            }
        }
    }
}
