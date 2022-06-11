package com.example.expensetrackerapp.ui.ViewTransaction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.expensetrackerapp.Adapter.TransactionAdapter;
import com.example.expensetrackerapp.R;
import com.example.expensetrackerapp.model.Transaction;

import java.util.ArrayList;


public class ViewTransactionFragment extends Fragment {
    RecyclerView recyclerView;
    ViewTransactionViewModel transactionVM;
    TransactionAdapter adapter;
    TextView income, expense, total;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_view_transaction, container, false);
        transactionVM = new ViewModelProvider(this).get(ViewTransactionViewModel.class);

        income = view.findViewById(R.id.incomeTv);
        expense = view.findViewById(R.id.expenseTv);
        total = view.findViewById(R.id.totalTv);

        recyclerView = view.findViewById(R.id.rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        adapter = new TransactionAdapter();

        getAllTransactions();
        return view;
    }

    private void getAllTransactions() {
        transactionVM.getAllTransactions().observe(getViewLifecycleOwner(), new Observer<ArrayList<Transaction>>() {
            @Override
            public void onChanged(ArrayList<Transaction> transactions) {
                adapter.setTransactionsItems(transactions);
                recyclerView.setAdapter(adapter);

            }
        });
    }

}