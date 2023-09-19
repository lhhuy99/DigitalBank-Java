package vn.funix.fx13483.java.asm02.models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private final List<Account> accounts; //Danh sach cac account cua customer

    public Customer() {
        this.accounts = new ArrayList<>();
    }

    public static DecimalFormat formatter = new DecimalFormat("###,###đ");

    public List<Account> getAccounts() {
        return accounts;
    }

    public String isPremium() {
        for (Account account : accounts) {
            if (account.isPremium(account.getBalance())) {
                return "Premium";
            }
        }
        return "Normal";
    }

    public void addAccount(Account newAccount) {
        System.out.println("Nhap ma STK gom 6 chu so: ");
        newAccount.setAccountNumber();
        for (Account account : accounts) {
            if (newAccount.getAccountNumber().equals(account.getAccountNumber())) { //Kiem tra CCCD nguoi dung nhap co trong he thong chua
                System.out.println("Ma STK da dang ky.");
                return;
            }
        }
        System.out.println("Nhap so du: ");
        newAccount.setBalance();
        this.accounts.add(newAccount);
    }


    public double getBalance() {
        double totalBalance = 0.0;
        for (Account account : accounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    public void displayInformation() {
        System.out.println(getCustomerId() + "  |        " + getName() + " | " + isPremium() + " |  " + formatter.format(getBalance()));
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + "     " + accounts.get(i).getAccountNumber() + "  |                    " + formatter.format(accounts.get(i).getBalance()));
        }
    }

    public boolean isAccountNumberExisted(String accountNumber) {
        for (Account account : accounts) {
            if (accountNumber.equals(account.getAccountNumber())) {
                return true;
            }
        }
        return false;
    }

}
