package vn.funix.fx13483.java.asm03.models;

import vn.funix.fx13483.java.asm02.models.Account;
import vn.funix.fx13483.java.asm03.Asm03;
import vn.funix.fx13483.java.asm03.ReportService;
import vn.funix.fx13483.java.asm03.Withdraw;

import java.text.DecimalFormat;

public class LoansAccount extends Account implements ReportService, Withdraw {
    private static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private static final double LOAN_ACCOUNT_MAX_BALANCE = 100000000.0;
    private static final double LOAN_ACCOUNT_MIN_BALANCE = 50000.0;

    public static DecimalFormat formatter = new DecimalFormat("###,###đ");

    private String typeAccount = "LOAN";
    private final Transaction[] transactionList = new Transaction[100];
    private int indexOfTransaction = 0;

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    private static double balanceBeforeWithdrawing = 0.0; //dung de luu gia tri balance truoc khi rut

    @Override
    public void log(double amount) {
        System.out.println("+--------+-------------------+--------+");
        System.out.printf("%30s%n", "BIEN LAI GIAO DICH LOAN");
        System.out.printf("NGAY G/D: %28s%n", Asm03.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s%n", getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", formatter.format(amount));
        System.out.printf("SO DU: %31s%n", formatter.format(getBalance()));
        System.out.printf("PHI + VAT: %27s%n", formatter.format(amount * getTransactionFee(balanceBeforeWithdrawing)));
        System.out.println("+--------+-------------------+--------+");
    }

    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            balanceBeforeWithdrawing = getBalance();
            setBalance(getBalance() - amount - amount * getTransactionFee(getBalance()));
            log(amount);
            addTransaction(amount, true, transactionList[indexOfTransaction] = new Transaction());
            indexOfTransaction++;
            return true;
        } else {
            addTransaction(amount, false, transactionList[indexOfTransaction] = new Transaction());
            indexOfTransaction++;
            System.out.println("Han muc khong duoc qua gioi han 100,000,000d");
            System.out.println("Han muc con lai sau khi rut khong duoc nho hon 50,000d");
            return false;
        }
    }

    //Dieu kien: Han muc con lai sau khi rut khong duoc nho hon 50,000d
    @Override
    public boolean isAccepted(double amount) {
        return amount <= getBalance() - LOAN_ACCOUNT_MIN_BALANCE - amount * getTransactionFee(getBalance());
    }

    public boolean withdrawForPremiumAccount(double amount) {
        if (isAcceptedForPremiumAccount(amount)) {
            balanceBeforeWithdrawing = getBalance();
            setBalance(getBalance() - amount - amount * getTransactionFee(getBalance()));
            log(amount);
            addTransaction(amount, true, transactionList[indexOfTransaction] = new Transaction());
            indexOfTransaction++;
            return true;
        } else {
            addTransaction(amount, false, transactionList[indexOfTransaction] = new Transaction());
            indexOfTransaction++;
            System.out.println("Han muc khong duoc qua gioi han 100,000,000d");
            System.out.println("Han muc con lai sau khi rut khong duoc nho hon 50,000d");
            return false;
        }
    }

    //Dieu kien: Han muc con lai sau khi rut khong duoc nho hon 50,000d
    public boolean isAcceptedForPremiumAccount(double amount) {
        return amount <= getBalance() - LOAN_ACCOUNT_MIN_BALANCE - amount * getTransactionFee(getBalance());
    }

    public double getTransactionFee(double balance) {
        if (isPremium(balance)) {
            return LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE;
        }
        return LOAN_ACCOUNT_WITHDRAW_FEE;
    }

}
