package vn.funix.fx13483.java.asm03.models;

import vn.funix.fx13483.java.asm02.models.Bank;

import java.util.ArrayList;
import java.util.List;

public class DigitalBank extends Bank {
    private final List<DigitalCustomer> digitalCustomers;

    public DigitalBank() {
        this.digitalCustomers = new ArrayList<>();
    }

    public DigitalCustomer getCustomerById(String customerId) {
        for (DigitalCustomer digitalCustomer : digitalCustomers) {
            if (customerId.equals(digitalCustomer.getCustomerId())) {
                return digitalCustomer;
            }
        }
        return null;
    }

    public void addCustomer(String customerId, String name, DigitalCustomer newCustomer) {
        newCustomer.setName(name);
        newCustomer.setCustomerId(customerId);
        digitalCustomers.add(newCustomer);
    }

    public void addSavingsAccount(String customerId, SavingsAccount newSavingsAccount) {
        for (DigitalCustomer digitalCustomer : digitalCustomers) {
            if (customerId.equals(digitalCustomer.getCustomerId())) {
                digitalCustomer.addSavingsAccount(newSavingsAccount);
            } else {
                System.out.println("Khach hang khong ton tai.");
            }
        }
    }

    public void addLoanAccount(String customerId, LoansAccount newLoanAccount) {
        for (DigitalCustomer digitalCustomer : digitalCustomers) {
            if (customerId.equals(digitalCustomer.getCustomerId())) {
                digitalCustomer.addLoanAccount(newLoanAccount);
            } else {
                System.out.println("Khach hang khong ton tai.");
            }
        }
    }

    @Override
    public boolean isCustomerExisted(String customerId) {
        for (DigitalCustomer digitalCustomer : digitalCustomers) {
            if (customerId.equals(digitalCustomer.getCustomerId())) {
                return true;
            }
        }
        return false;
    }

    public void withdraw(String customerId, String accountNumber, double amount) {
        for (DigitalCustomer digitalCustomer : digitalCustomers) {
            if (customerId.equals(digitalCustomer.getCustomerId())) {
                digitalCustomer.withdraw(accountNumber, amount);
            }
        }
    }
}
