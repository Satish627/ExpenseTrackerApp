package com.example.expensetrackerapp.ui.ViewTransaction;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.example.expensetrackerapp.Callback.CallBack;
import com.example.expensetrackerapp.model.Transaction;
import com.example.expensetrackerapp.repository.Transaction.TransactionsRepository;
import com.example.expensetrackerapp.repository.Transaction.TransactionsRepositoryImpl;

import java.util.ArrayList;

public class ViewTransactionViewModel extends AndroidViewModel implements CallBack {
    TransactionsRepository transactionsRepository;

    public ViewTransactionViewModel(@NonNull Application application) {
        super(application);
        transactionsRepository = new TransactionsRepositoryImpl(application,this);
    }
    public MutableLiveData<ArrayList<Transaction>> getAllTransactions(){
       return transactionsRepository.getTransactions();
    }
    @Override
    public void getMessage(String message) {
        Toast.makeText(getApplication(),message,Toast.LENGTH_SHORT).show();

    }
}
