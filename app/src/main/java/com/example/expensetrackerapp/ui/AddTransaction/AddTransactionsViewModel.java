package com.example.expensetrackerapp.ui.AddTransaction;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.expensetrackerapp.Callback.CallBack;
import com.example.expensetrackerapp.model.Transaction;
import com.example.expensetrackerapp.repository.Transaction.TransactionsRepository;
import com.example.expensetrackerapp.repository.Transaction.TransactionsRepositoryImpl;

public class AddTransactionsViewModel extends AndroidViewModel implements CallBack {

   private TransactionsRepository transactionsRepository;

    public AddTransactionsViewModel(@NonNull Application application) {
        super(application);
        transactionsRepository = new TransactionsRepositoryImpl(application,this);
    }

    public void addTransactions(Transaction transaction){
        transactionsRepository.addTransaction(transaction);
    }

    @Override
    public void getMessage(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_SHORT).show();
    }
}
