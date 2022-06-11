package com.example.expensetrackerapp.ui.EditTransaction;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;


import com.example.expensetrackerapp.Callback.CallBack;
import com.example.expensetrackerapp.model.Transaction;
import com.example.expensetrackerapp.repository.Transaction.TransactionsRepository;
import com.example.expensetrackerapp.repository.Transaction.TransactionsRepositoryImpl;

public class UpdateTransactionsViewModel extends AndroidViewModel implements CallBack {

    private TransactionsRepository transactionsRepository;

    public UpdateTransactionsViewModel(Application app) {
        super(app);
        transactionsRepository = new TransactionsRepositoryImpl(app,this);
    }

    public void updateTransaction(Transaction transaction){
        transactionsRepository.updateTransaction(transaction);
    }
    public void removeTransaction(String name){
        transactionsRepository.removeTransaction(name);
    }
    @Override
    public void getMessage(String message) {
    }
}
