package com.example.expensetrackerapp.repository.Transaction;

import androidx.lifecycle.MutableLiveData;


import com.example.expensetrackerapp.model.Transaction;

import java.util.ArrayList;

public interface TransactionsRepository {
    void addTransaction(Transaction transaction);
    MutableLiveData<ArrayList<Transaction>> getTransactions();
    void removeTransaction(String name);
    void updateTransaction(Transaction transaction);

}
