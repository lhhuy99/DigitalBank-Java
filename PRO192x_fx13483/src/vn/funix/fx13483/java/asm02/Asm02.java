package vn.funix.fx13483.java.asm02;

import vn.funix.fx13483.java.asm02.models.Account;
import vn.funix.fx13483.java.asm02.models.Bank;
import vn.funix.fx13483.java.asm02.models.Customer;

import java.util.Scanner;

public class Asm02 {
    private static final Bank bank = new Bank();
    public static Customer[] customer = new Customer[10];
    public static Account[] account = new Account[50];
    public static final String AUTHOR = "FX13483";
    public static final String VERSION = "v2.0.0";
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        displayMenu();
        checkChosenFunction();
    }

    public static void displayMenu() {
        System.out.println("+--------+-------------------+--------+");
        System.out.println("| NGAN HANG SO | " + AUTHOR + "@" + VERSION + "       |");
        System.out.println("+--------+-------------------+--------+");
    }

    public static void displayFunction() {
        System.out.println(" 1. Them khach hang");
        System.out.println(" 2. Them tai khoan cho khach hang");
        System.out.println(" 3. Hien thi danh sach khach hang");
        System.out.println(" 4. Tim theo CCCD");
        System.out.println(" 5. Tim theo ten khach hang");
        System.out.println(" 0. Thoat");
        System.out.println("+--------+-------------------+--------+");
    }

    public static int chooseFunction() {
        int chosenNum = 6;
        boolean doWhileCondition = true;
        do {
            //try catch de nhap dung cac so chuc nang
            try {
                System.out.print("Chuc nang: ");
                String text = sc.nextLine();
                chosenNum = Integer.parseInt(text);
                switch (chosenNum) {
                    case 1, 2, 3, 4, 5, 0 -> doWhileCondition = false;
                    default -> System.out.println("Vui long nhap dung so chuc nang");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long chi nhap so");
            }
        } while (doWhileCondition);
        return chosenNum;
    }

    public static void checkChosenFunction() {
        int indexOfNewCustomerArray = 0; //Index cua mang newCustomer
        int indexOfNewAccount = 0;
        do {
            displayFunction();
            int chosenNum = chooseFunction();
            switch (chosenNum) {
                case 0 -> System.exit(0);
                case 1 -> {
                    bank.addCustomer(customer[indexOfNewCustomerArray] = new Customer());
                    indexOfNewCustomerArray++;
                }
                case 2 -> {
                    String customerId;
                    do {
                        customerId = bank.setCustomerId();
                        if (!bank.isCustomerExisted(customerId)) {
                            System.out.println("Khach hang khong ton tai. Vui long nhap lai CCCD");
                        }
                    } while (!bank.isCustomerExisted(customerId));
                    bank.addAccount(customerId, account[indexOfNewAccount] = new Account());
                    indexOfNewAccount++;
                }
                case 3 -> bank.displayInformationAllCustomers();
                case 4 -> {
                    String customerIdForSearch;
                    customerIdForSearch = sc.nextLine();
//                    do {
//                        customerIdForSearch = bank.setCustomerId();
//                        if (!bank.isCustomerExisted(customerIdForSearch)) {
//                            System.out.println("Khach hang khong ton tai. Vui long nhap lai CCCD");
//                        }
//                    } while (!bank.isCustomerExisted(customerIdForSearch));
                    bank.searchCustomerByCustomerId(customerIdForSearch);
                }
                case 5 -> {
                    String nameForSearch = enterNameForSearch();
                    bank.searchCustomerByName(nameForSearch);
                }
            }
        } while (true);
    }

    public static String enterNameForSearch() {
        System.out.println("Nhap ten khach hang: ");
        return sc.nextLine();
    }
}
