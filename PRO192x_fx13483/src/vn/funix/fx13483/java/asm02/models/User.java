package vn.funix.fx13483.java.asm02.models;

import java.util.Scanner;

public class User {
    private String name;
    private String customerId;

    public static Scanner sc = new Scanner(System.in);

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId() {
        long checkException = -1;   //Dung de parse du lieu String sang long de check exception
        boolean checkCustomerId = true;
        String customerId = "a";
        do {
            try {
                customerId = sc.nextLine();
                checkException = Long.parseLong(customerId); //check exception
                if (customerId.length() != 12) {
                    System.out.println("So can cuoc cong dan phai chinh xac 12 so");
                    continue;
                }
                checkCustomerId = false;
            } catch (NumberFormatException e) {
                System.out.println("So can cuoc cong dan khong hop le. Vui long nhap lai");
            }
        } while (checkCustomerId);
        this.customerId = customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
