package vn.funix.fx13483.java.asm03.models;

import java.util.UUID;

public class Transaction {
    private final String id;
    private String accountNumber;
    private double amount;
    private String time;
    private Boolean status;

    public Transaction() {
        this.id = String.valueOf(UUID.randomUUID());
    }

    public String getId() {
        return id;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
