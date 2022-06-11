package com.example.expensetrackerapp.ui.AddTransaction;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.expensetrackerapp.R;
import com.example.expensetrackerapp.model.Transaction;
import com.example.expensetrackerapp.ui.MainActivity;


public class AddTransactionsFragment extends Fragment {
    EditText transaction_name, amount, note;
    Button saveBtn;
    CheckBox incomeCheckbox, expenseCheckBox;
    String type = " ";
    AddTransactionsViewModel viewModel;
    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_transactions, container, false);

        transaction_name = view.findViewById(R.id.name);
        amount = view.findViewById(R.id.amount);
        saveBtn = view.findViewById(R.id.saveBtn);
        note = view.findViewById(R.id.note);
        incomeCheckbox = view.findViewById(R.id.income);
        expenseCheckBox = view.findViewById(R.id.expense);
        linearLayout = view.findViewById(R.id.linearLayout);

        //it will call viewModel.onCleared() properly at the right time by component activity
        viewModel = new ViewModelProvider(this).get(AddTransactionsViewModel.class);

        incomeCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Income";
                incomeCheckbox.setChecked(true);
                expenseCheckBox.setChecked(false);
            }
        });
        expenseCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Expense";
                expenseCheckBox.setChecked(true);
                incomeCheckbox.setChecked(false);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = transaction_name.getText().toString();
                String Amount = amount.getText().toString();
                String Note = note.getText().toString();
                String CheckboxValue = type;

                if (Name.isEmpty()) {
                    transaction_name.setError("Enter the transactions name");
                    return;
                }
                if (Amount.isEmpty()) {
                    amount.setError("Enter the amount");
                    return;
                } else {
                    Transaction transaction = new Transaction();
                    transaction.setName(Name);
                    transaction.setAmount(Amount);
                    transaction.setNote(Note);
                    transaction.setType(CheckboxValue);
                    viewModel.addTransactions(transaction);
                    goToViewTransactionFragment();
                }
            }
        });
        return view;
    }

    private void goToViewTransactionFragment() {
        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        startActivity(intent);

    }
}