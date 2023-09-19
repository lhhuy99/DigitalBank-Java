package vn.funix.fx13483.java.asm02.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Bank {
    private final String id;
    private final List<Customer> customers; //Danh sach cac customer. Moi 1 customer gom: ten, id, danh sach cac account cua customer do
    public static Scanner sc = new Scanner(System.in);

    public Bank() {
        this.id = String.valueOf(UUID.randomUUID());
        this.customers = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void addCustomer(Customer newCustomer) {
        System.out.println("Nhap ten khach hang: ");
        newCustomer.setName(sc.nextLine());
        System.out.println("Nhap so CCCD: ");
        boolean isCustomerIdExisted;
        //do while nham cho nguoi dung nhap lai khi nhap CCCD da co trong he thong
        do {
            isCustomerIdExisted = false;
            newCustomer.setCustomerId();
            for (Customer customer : customers) {
                if (newCustomer.getCustomerId().equals(customer.getCustomerId())) { //Kiem tra CCCD nguoi dung nhap co trong he thong chua
                    System.out.println("CCCD da dang ky. Vui long nhap CCCD khac");
                    isCustomerIdExisted = true;
                    break;
                }
            }
        } while (isCustomerIdExisted);
        customers.add(newCustomer);
    }

    //them tai khoan SAVINGS
    public void addAccount(String customerId, Account newAccount) {
        for (Customer customer : customers) {
            if (customerId.equals(customer.getCustomerId())) {
                customer.addAccount(newAccount); //addAccount cua class Customer
            }
        }
    }


    public String setCustomerId() {
        System.out.println("Nhap CCCD khach hang: ");
        long checkException = -1;   //Dung de parse du lieu String sang long de check exception
        boolean checkCustomerId = true;
        String customerId = "a";
        do {
            try {
                customerId = sc.nextLine();
                if (customerId.equalsIgnoreCase("no")) {
                    System.exit(0);
                }
                checkException = Long.parseLong(customerId); //check exception
                if (customerId.length() != 12) {
                    System.out.println("So can cuoc cong dan phai chinh xac 12 so");
                    continue;
                }
                checkCustomerId = false;
            } catch (NumberFormatException e) {
                System.out.println("So can cuoc cong dan khong hop le. Vui long nhap lai hoac nhap No de thoat");
            }
        } while (checkCustomerId);
        return customerId;
    }

    public boolean isCustomerExisted(String customerId) {
        for (Customer customer : customers) {
            if (customerId.equals(customer.getCustomerId())) {
                return true;
            }
        }
        return false;
    }

    public void displayInformationAllCustomers() {
        for (Customer customer : customers) { //Duyet mang
            customer.displayInformation();  //Moi phan tu hien thi qua displayInformation()
        }
    }

    public void searchCustomerByCustomerId(String customerIdForSearch) {
        for (Customer customer : customers) {
//            if (customerIdForSearch.equals(customer.getCustomerId())) {
//                customer.displayInformation();
//            }
            if (customer.getCustomerId().contains(customerIdForSearch)) {
                customer.displayInformation();
            }
        }
    }

    public void searchCustomerByName(String nameForSearch) {
        for (Customer customer : customers) {
            if (customer.getName().contains(nameForSearch)) {
                customer.displayInformation();
            }
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }

}
