package com.example.expensetrackerapp.model;

public class Transaction {
    private int id;
    private String name;
    private String amount;
    private String note;
    private String type;

    public Transaction() {
    }

    public Transaction(String name, String amount, String note, String type) {
        this.name = name;
        this.amount = amount;
        this.note = note;
        this.type = type;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", note='" + note + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
