package vn.funix.fx13483.java.asm03;

import vn.funix.fx13483.java.asm02.models.Account;
import vn.funix.fx13483.java.asm03.models.DigitalBank;
import vn.funix.fx13483.java.asm03.models.DigitalCustomer;
import vn.funix.fx13483.java.asm03.models.LoansAccount;
import vn.funix.fx13483.java.asm03.models.SavingsAccount;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Asm03 {
    public static final String AUTHOR = "FX13483";
    public static final String VERSION = "v3.0.0";
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final Scanner sc = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final DigitalCustomer activeCustomer = new DigitalCustomer(); //Chi tao 1 doi tuong khach hang tren hang so cho truoc
    private static final Account accountUseToDisplayTransaction = new Account(); //Dung de goi method hien thi lich su giao dich
    private static final String CUSTOMER_ID = "002193000004";
    private static final String CUSTOMER_NAME = "ASM03";
    private static int indexOfNewSavingsAccount = 0;
    private static int indexOfNewLoanAccount = 0;
    private static final SavingsAccount[] savingAccountList = new SavingsAccount[20];
    private static final LoansAccount[] loanAccountList = new LoansAccount[20];

    public static void main(String[] args) {
        activeBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME, activeCustomer); // Khoi tao khach hang theo hang so cho truoc
        displayMenu();
        checkChosenFunction();
    }

    public static void displayMenu() {
        System.out.println("+--------+-------------------+--------+");
        System.out.println("| NGAN HANG SO | " + AUTHOR + "@" + VERSION + "       |");
        System.out.println("+--------+-------------------+--------+");
    }

    public static void displayFunction() {
        System.out.println(" 1. Thong tin khach hang");
        System.out.println(" 2. Them tai khoan Savings");
        System.out.println(" 3. Them tai khoan Loan");
        System.out.println(" 4. Rut tien");
        System.out.println(" 5. Lich su giao dich");
        System.out.println(" 0. Thoat");
        System.out.println("+--------+-------------------+--------+");
    }

    public static void showCustomer() {
        DigitalCustomer digitalCustomer = activeBank.getCustomerById(CUSTOMER_ID);
        if (digitalCustomer != null) {
            digitalCustomer.displayInformation();
        }
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
        do {
            displayFunction();
            int chosenNum = chooseFunction();
            switch (chosenNum) {
                case 0 -> System.exit(EXIT_COMMAND_CODE);
                case 1 -> showCustomer();
                case 2 -> {
                    activeBank.addSavingsAccount(CUSTOMER_ID, savingAccountList[indexOfNewSavingsAccount] = new SavingsAccount());
                    indexOfNewSavingsAccount++;
                }
                case 3 -> {
                    activeBank.addLoanAccount(CUSTOMER_ID, loanAccountList[indexOfNewLoanAccount] = new LoansAccount());
                    indexOfNewLoanAccount++;
                }
                case 4 -> {
                    if (!(activeBank.isCustomerExisted(CUSTOMER_ID))) {
                        System.out.println("Khach hang khong ton tai");
                        break;
                    }
                    System.out.println("Nhap ma STK:");
                    String accountNumber = enterAccountNumber();
                    if (!(activeCustomer.isAccountNumberExisted(accountNumber))) {
                        System.out.println("Tai khoan khong ton tai");
                        break;
                    }
                    System.out.println("Nhap so tien can rut:");
                    double amountWithdrawn = enterAAmountWithdrawOfAccount();
                    activeBank.withdraw(CUSTOMER_ID, accountNumber, amountWithdrawn);

                }
                case 5 -> {
                    System.out.println("+--------+-------------------+--------+");
                    System.out.println("| LICH SU GIAO DICH                   |");
                    System.out.println("+--------+-------------------+--------+");
                    showCustomer();
                    accountUseToDisplayTransaction.displayTransactionInformation();
                    System.out.println("+--------+-------------------+--------+");
                }
            }
        } while (true);
    }

    public static double enterAAmountWithdrawOfAccount() {
        boolean checkEnterAmount = true;
        double enterAmount = 1.0;
        String enterAmountStringForCheckException;
        do {
            try {
                enterAmountStringForCheckException = sc.nextLine();
                if (enterAmountStringForCheckException.equalsIgnoreCase("no")) {
                    checkChosenFunction();
                }
                enterAmount = Double.parseDouble(enterAmountStringForCheckException);
                checkEnterAmount = false;
            } catch (NumberFormatException e) {
                System.out.println("Chi nhap so. Vui long nhap lai hoac nhap No de quay lai");
            }
        } while (checkEnterAmount);
        return enterAmount;
    }


    public static String enterAccountNumber() {
        long checkException = -1;   //Dung de parse du lieu String sang long de check exception
        boolean checkAccountNumber = true;
        String accountNumber = "a";
        do {
            try {
                accountNumber = sc.nextLine();
                if (accountNumber.equalsIgnoreCase("no")) {
                    checkChosenFunction();
                }
                checkException = Long.parseLong(accountNumber); //check exception
                if (accountNumber.length() != 6) {
                    System.out.println("Ma STK phai chinh xac 6 so. Vui long nhap lai hoac nhap No de quay lai");
                    continue;
                }
                checkAccountNumber = false;
            } catch (NumberFormatException e) {
                System.out.println("Ma STK khong hop le. Vui long nhap lai hoac nhap No de quay lai ");
            }
        } while (checkAccountNumber);
        return accountNumber;
    }

    public static String getDateTime() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }
}
