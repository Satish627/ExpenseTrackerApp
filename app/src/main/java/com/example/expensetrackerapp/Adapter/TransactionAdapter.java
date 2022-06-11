package com.example.expensetrackerapp.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.expensetrackerapp.R;
import com.example.expensetrackerapp.model.Transaction;
import com.example.expensetrackerapp.ui.EditTransaction.UpdateTransactions;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private ArrayList<Transaction> transactions;


    //Create the view by converting single list item form xml into view objects and setting it in viewholder
    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_item, parent, false);
        return new ViewHolder(view);
    }

    //Binds view with data from database i.e. setting data from the data source on each relevant view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.transactionName.setText(transactions.get(position).getName());
        holder.amount.setText(transactions.get(position).getAmount());
        holder.note.setText(transactions.get(position).getNote());
        String type = transactions.get(position).getType();
        if (type.equals("Expense") ) {
            holder.transactionType.setBackgroundResource(R.drawable.red_circle);
        }
        else {
            holder.transactionType.setBackgroundResource(R.drawable.green_circle);
        }
    }

    //number  of elements
    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setTransactionsItems(ArrayList<Transaction> transactionItems) {
        transactions = transactionItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView transactionName;
        TextView amount;
        TextView note;
        View transactionType;
        Button delBtn, editBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            transactionName = itemView.findViewById(R.id.name);
            amount = itemView.findViewById(R.id.amount);
            note = itemView.findViewById(R.id.notes);
            transactionType = itemView.findViewById(R.id.view);
            delBtn = itemView.findViewById(R.id.deleteBtn);
            editBtn = itemView.findViewById(R.id.editBtn);


            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), UpdateTransactions.class);
                    intent.putExtra("name", transactionName.getText().toString());
                    intent.putExtra("amount", amount.getText().toString());
                    intent.putExtra("note", note.getText().toString());
                    intent.putExtra("type", transactions.get(getAdapterPosition()).getType());
                    v.getContext().startActivity(intent);
                }
            });
        }

    }


}
