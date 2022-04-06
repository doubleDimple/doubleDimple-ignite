package com.doubledimple.ignite.ignitecache.dtx;


import org.apache.ignite.Ignite;
import org.apache.ignite.transactions.Transaction;
import org.apache.ignite.transactions.TransactionConcurrency;
import org.apache.ignite.transactions.TransactionIsolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IgniteDisTransTemplate {


    @Autowired
    private Ignite ignite;


    public void disTrans(TransactionConcurrency transactionConcurrency, TransactionIsolation transactionIsolation,IgniteTransService igniteTransService){

        Transaction transaction = null;
        try{
            transaction = ignite.transactions().txStart(transactionConcurrency, transactionIsolation);

            //todo do handler you business
            igniteTransService.loadDisTrans();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (null != transaction) transaction.rollback();
        } finally {
            if (null != transaction) transaction.close();
        }
    }
}
