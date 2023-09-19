package vn.funix.fx13483.java.asm02.models;

import vn.funix.fx13483.java.asm03.Asm03;
import vn.funix.fx13483.java.asm03.models.Transaction;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {
    private String accountNumber;
    private double balance;
    private static final List<Transaction> transactions = new ArrayList<>(); //Danh sach lich su giao dich
    public static DecimalFormat formatter = new DecimalFormat("###,###đ");
    public static Scanner sc = new Scanner(System.in);

    public Account() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber() {
        long checkException = -1;   //Dung de parse du lieu String sang long de check exception
        boolean checkAccountNumber = true;
        String accountNumber = "a";
        do {
            try {
                accountNumber = sc.nextLine();
                if (accountNumber.equalsIgnoreCase("no")) {
                    System.exit(0);
                }
                checkException = Long.parseLong(accountNumber); //check exception
                if (accountNumber.length() != 6) {
                    System.out.println("Ma STK phai chinh xac 6 so. Vui long nhap lai hoac nhap No de thoat chuong trinh");
                    continue;
                }
                checkAccountNumber = false;
            } catch (NumberFormatException e) {
                System.out.println("Ma STK khong hop le. Vui long nhap lai hoac nhap No de thoat chuong trinh ");
            }
        } while (checkAccountNumber);
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance() {
        boolean checkBalance = true;
        double balance = 1.0;
        String balanceStringForCheckException;
        do {
            try {
                balanceStringForCheckException = sc.nextLine();
                if (balanceStringForCheckException.equalsIgnoreCase("no")) {
                    System.exit(0);
                }
                balance = Double.parseDouble(balanceStringForCheckException);
                if (balance < 50000.0) {
                    System.out.println("Vui long nhap so tien tu 50000 tro len hoac nhap No de thoat chuong trinh");
                    continue;
                }
                checkBalance = false;
            } catch (NumberFormatException e) {
                System.out.println("Chi nhap so. Vui long nhap lai hoac nhap No de thoat chuong trinh");
            }
        } while (checkBalance);
        this.balance = balance;
    }

    public boolean isPremium(double balance) {
        return balance >= 10000000.0;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addTransaction(double amount, Boolean status, Transaction transaction) {
        transaction.setAccountNumber(getAccountNumber());
        transaction.setAmount(amount);
        transaction.setTime(Asm03.getDateTime());
        transaction.setStatus(status);
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void displayTransactionInformation() {
        for (Transaction transaction : transactions) {
            if (transaction.getStatus()) {
                System.out.printf("%s |   %9s | %s%n", transaction.getAccountNumber(), formatter.format(transaction.getAmount()), transaction.getTime());
            }
        }
    }
}
