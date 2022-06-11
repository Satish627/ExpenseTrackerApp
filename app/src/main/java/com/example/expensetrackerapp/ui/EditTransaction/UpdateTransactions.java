package com.example.expensetrackerapp.ui.EditTransaction;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.expensetrackerapp.R;
import com.example.expensetrackerapp.model.Transaction;
import com.example.expensetrackerapp.ui.MainActivity;

public class UpdateTransactions extends AppCompatActivity {

    UpdateTransactionsViewModel viewModel;
    String newType = " ";
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_transactions);

        EditText Name = findViewById(R.id.name);
        EditText Amount = findViewById(R.id.amount);
        EditText Note = findViewById(R.id.note);
        Button Delete_Btn = findViewById(R.id.deleteBtn);
        Button Update_Btn = findViewById(R.id.updateBtn);
        CheckBox incomeCheckbox = findViewById(R.id.income);
        CheckBox expenseCheckbox = findViewById(R.id.expense);
        layout = findViewById(R.id.layout);

        viewModel = new ViewModelProvider(this).get(UpdateTransactionsViewModel.class);

        //receiving intent as a bundle
        Bundle bundle = getIntent().getExtras();
        Name.setText(bundle.getString("name"));
        Amount.setText(bundle.getString("amount"));
        Note.setText(bundle.getString("note"));
        String type = bundle.getString("type");

        switch (type){
            case "Income":
                newType = "Income";
                incomeCheckbox.setChecked(true);
            case "Expense":
                newType = "Expense";
                expenseCheckbox.setChecked(true);
        }
        incomeCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newType = "Income";
                incomeCheckbox.setChecked(true);
                expenseCheckbox.setChecked(false);
            }
        });
        expenseCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newType = "Expense";
                expenseCheckbox.setChecked(true);
                incomeCheckbox.setChecked(false);
            }
        });


        Update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String amount = Amount.getText().toString();
                String note = Note.getText().toString();


                if (name.isEmpty()) {
                    Name.setError("Enter the transactions name");
                    return;
                }
                if (amount.isEmpty()) {
                    Amount.setError("Enter the amount");
                    return;
                } else {
                    Transaction transaction = new Transaction();
                    transaction.setName(name);
                    transaction.setAmount(amount);
                    transaction.setNote(note);
                    transaction.setType(type);
                    viewModel.updateTransaction(transaction);
                    goToHomePage();
                }
            }
        });
        Delete_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data cannot be undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = Name.getText().toString();
                        viewModel.removeTransaction(name);
                        goToHomePage();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(UpdateTransactions.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    private void goToHomePage() {
        Intent intent = new Intent(UpdateTransactions.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}