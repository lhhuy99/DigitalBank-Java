package vn.funix.fx13483.java.asm03.models;

import vn.funix.fx13483.java.asm02.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class DigitalCustomer extends Customer {
    private final List<SavingsAccount> savingsAccounts;
    private final List<LoansAccount> loansAccounts;

    public DigitalCustomer() {
        this.savingsAccounts = new ArrayList<>();
        this.loansAccounts = new ArrayList<>();
    }

    public void addSavingsAccount(SavingsAccount newSavingsAccount) {
        System.out.println("Nhap ma STK gom 6 chu so: ");
        newSavingsAccount.setAccountNumber();
        if (isAccountNumberExisted(newSavingsAccount.getAccountNumber())) {
            System.out.println("Ma STK da dang ky.");
            return;
        }
        System.out.println("Nhap so du: ");
        newSavingsAccount.setBalance();
        this.savingsAccounts.add(newSavingsAccount);
    }

    public void addLoanAccount(LoansAccount newLoanAccount) {
        System.out.println("Nhap ma STK gom 6 chu so: ");
        newLoanAccount.setAccountNumber();
        if (isAccountNumberExisted(newLoanAccount.getAccountNumber())) {
            System.out.println("Ma STK da dang ky.");
            return;
        }
        newLoanAccount.setBalance(100000000.0); //Han muc 100,000,000d
        this.loansAccounts.add(newLoanAccount);
    }

    @Override
    public String isPremium() {
        for (SavingsAccount savingsAccount : savingsAccounts) {
            if (savingsAccount.isPremium(savingsAccount.getBalance())) {
                return "Premium";
            }
        }

        for (LoansAccount loansAccount : loansAccounts) {
            if (loansAccount.isPremium(loansAccount.getBalance())) {
                return "Premium";
            }
        }
        return "Normal";
    }

    @Override
    public void displayInformation() {
        int orderNumber = 1;
        System.out.printf("%12s |      %8s |  %7s |   %15s%n", getCustomerId(), getName(), isPremium(), formatter.format(getBalance()));
        for (int i = 0; i < savingsAccounts.size(); i++, orderNumber++) {
            System.out.printf("%d%11s |      %8s |%29s%n", orderNumber, savingsAccounts.get(i).getAccountNumber(), savingsAccounts.get(i).getTypeAccount(), formatter.format(savingsAccounts.get(i).getBalance()));
        }

        for (int i = 0; i < loansAccounts.size(); i++, orderNumber++) {
            System.out.printf("%d%11s |      %8s |%29s%n", orderNumber, loansAccounts.get(i).getAccountNumber(), loansAccounts.get(i).getTypeAccount(), formatter.format(loansAccounts.get(i).getBalance()));
        }
    }

    @Override
    public boolean isAccountNumberExisted(String accountNumber) {
        for (SavingsAccount savingsAccount : savingsAccounts) {
            if (accountNumber.equals(savingsAccount.getAccountNumber())) {
                return true;
            }
        }
        for (LoansAccount loansAccount : loansAccounts) {
            if (accountNumber.equals(loansAccount.getAccountNumber())) {
                return true;
            }
        }
        return false;
    }

    public void withdraw(String accountNumber, double amount) {
        for (SavingsAccount savingsAccount : savingsAccounts) {
            if (accountNumber.equals(savingsAccount.getAccountNumber())) {
                if (savingsAccount.isPremium(savingsAccount.getBalance())) {
                    savingsAccount.withdrawForPremiumAccount(amount);
                } else {
                    savingsAccount.withdraw(amount);
                }
                return;
            }
        }
        for (LoansAccount loansAccount : loansAccounts) {
            if (accountNumber.equals(loansAccount.getAccountNumber())) {
                if (loansAccount.isPremium(loansAccount.getBalance())) {
                    loansAccount.withdrawForPremiumAccount(amount);
                } else {
                    loansAccount.withdraw(amount);
                }
                return;
            }
        }
    }

    @Override
    public double getBalance() {
        double totalBalance = 0.0;
        for (SavingsAccount savingsAccount : savingsAccounts) {
            totalBalance += savingsAccount.getBalance();
        }
        for (LoansAccount loansAccount : loansAccounts) {
            totalBalance += loansAccount.getBalance();
        }
        return totalBalance;
    }
}
