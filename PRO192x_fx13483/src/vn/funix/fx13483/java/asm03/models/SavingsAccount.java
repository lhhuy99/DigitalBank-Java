package vn.funix.fx13483.java.asm03.models;

import vn.funix.fx13483.java.asm02.models.Account;
import vn.funix.fx13483.java.asm03.Asm03;
import vn.funix.fx13483.java.asm03.ReportService;
import vn.funix.fx13483.java.asm03.Withdraw;

import java.text.DecimalFormat;

public class SavingsAccount extends Account implements ReportService, Withdraw {
    private static final double SAVING_ACCOUNT_MAX_WITHDRAW = 5000000.0;
    private static final double SAVING_ACCOUNT_MIN_BALANCE = 50000.0;
    public static DecimalFormat formatter = new DecimalFormat("###,###đ");
    private String typeAccount = "SAVINGS";
    private final Transaction[] transactionList = new Transaction[100];
    private int indexOfTransaction = 0;

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    @Override
    public void log(double amount) {
        System.out.println("+--------+-------------------+--------+");
        System.out.printf("%30s%n", "BIEN LAI GIAO DICH SAVINGS");
        System.out.printf("NGAY G/D: %28s%n", Asm03.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s%n", getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", formatter.format(amount));
        System.out.printf("SO DU: %31s%n", formatter.format(getBalance()));
        System.out.printf("PHI + VAT: %27s%n", "0đ");
        System.out.println("+--------+-------------------+--------+");
    }

    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            setBalance(getBalance() - amount);
            log(amount);
            addTransaction(amount, true, transactionList[indexOfTransaction] = new Transaction());
            indexOfTransaction++;
            return true;
        } else {
            addTransaction(amount, false, transactionList[indexOfTransaction] = new Transaction());
            indexOfTransaction++;
            System.out.println("So tien rut phai lon hon hoac bang 50,000d");
            System.out.println("So tien 1 lan rut khong qua 5,000,000d doi voi tai khoan thuong");
            System.out.println("So du sau khi rut phai lon hon hoac bang 50,000d");
            System.out.println("So tien rut phai la boi so cua 10,000d");
            return false;
        }
    }

    //Dieu kien:
    //So tien rut phai lon hon hoac bang 50,000d
    //So tien 1 lan rut khong qua 5,000,000d doi voi tai khoan thuong
    //So du sau khi rut phai lon hon hoac bang 50,000d
    //So tien rut phai la boi so cua 10,000d
    @Override
    public boolean isAccepted(double amount) {
        return (amount >= SAVING_ACCOUNT_MIN_BALANCE) && (amount <= SAVING_ACCOUNT_MAX_WITHDRAW) && (getBalance() - amount >= SAVING_ACCOUNT_MIN_BALANCE) && (amount % 10000 == 0);
    }

    public boolean withdrawForPremiumAccount(double amount) {
        if (isAcceptedForPremiumAccount(amount)) {
            setBalance(getBalance() - amount);
            log(amount);
            addTransaction(amount, true, transactionList[indexOfTransaction] = new Transaction());
            indexOfTransaction++;
            return true;
        } else {
            addTransaction(amount, false, transactionList[indexOfTransaction] = new Transaction());
            indexOfTransaction++;
            System.out.println("So tien rut phai lon hon hoac bang 50,000d");
            System.out.println("So du sau khi rut phai lon hon hoac bang 50,000d");
            System.out.println("So tien rut phai la boi so cua 10,000d");
            return false;
        }
    }

    //Dieu kien:
    //So tien rut phai lon hon hoac bang 50,000d
    //So du sau khi rut phai lon hon hoac bang 50,000d
    //So tien rut phai la boi so cua 10,000d
    public boolean isAcceptedForPremiumAccount(double amount) {
        return (amount >= SAVING_ACCOUNT_MIN_BALANCE) && (getBalance() - amount >= SAVING_ACCOUNT_MIN_BALANCE) && (amount % 10000 == 0);
    }

}
