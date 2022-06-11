package com.example.expensetrackerapp.repository.Transaction;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.expensetrackerapp.Callback.CallBack;
import com.example.expensetrackerapp.model.Transaction;
import com.example.expensetrackerapp.repository.FirebaseConfig;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionsRepositoryImpl extends LiveData<ArrayList<Transaction>> implements TransactionsRepository, CallBack {
    private DatabaseReference db;
    private final Application app;
    private CallBack callBack;
    private MutableLiveData<ArrayList<Transaction>> transactions;
    int sumExpense, sumIncome;

    public TransactionsRepositoryImpl(Application app, CallBack callBack) {
        this.app = app;
        this.callBack = callBack;
        db = FirebaseConfig.getDatabaseReference();
        transactions = new MutableLiveData<>();
    }

    //adding data to firebase realtime db
    @Override
    public void addTransaction(Transaction transaction) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", transaction.getName());
        map.put("amount", transaction.getAmount());
        map.put("note", transaction.getNote());
        map.put("type", transaction.getType());

        DatabaseReference databaseReference = db.child("Transactions").child(transaction.getName());
        databaseReference.setValue(map);
        callBack.getMessage("Transaction added");
    }

    @Override
    public MutableLiveData<ArrayList<Transaction>> getTransactions() {
        getAllTransactions();
        return transactions;
    }

    private void getAllTransactions() {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        DatabaseReference reference = db.child("Transactions");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    HashMap  map = (HashMap) child.getValue();
                    Transaction transaction = new Transaction(map.get("name").toString(), map.get("amount").toString(), map.get("note").toString(), map.get("type").toString());
               /*  int amount = Integer.parseInt((String) map.get("amount"));
                    if (map.get("type").equals("Expense")){
                        sumExpense = sumExpense + amount;
                    }
                    else {
                        sumIncome = sumIncome + amount;
                    }*/

                   Log.d(TAG, "Transactions: " + transaction);

                  transactionList.add(transaction);
                }
                transactions.setValue(transactionList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }

        });
    }

    @Override
    public void removeTransaction(String name) {
        DatabaseReference reference = db.child("Transactions/"+name);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // remove the value at reference
             dataSnapshot.getRef().removeValue();
                Toast.makeText(app,"Transaction removed",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(app,"Transaction removal cancelled",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        HashMap<String, Object> Transaction = new HashMap<>();
        Transaction.put("name", transaction.getName());
        Transaction.put("amount", transaction.getAmount());
        Transaction.put("note", transaction.getNote());
        Transaction.put("type", transaction.getType());

      db.child("Transactions/"+transaction.getName()).updateChildren(Transaction)
              .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(app,"Data updated",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(app,"Data failed to update",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void getMessage(String message) {

    }
}
