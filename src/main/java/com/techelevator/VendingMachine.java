package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VendingMachine {

    public static void main(String[] args) throws FileNotFoundException {

        File newFile = new File("vendingmachine.csv");
        Scanner sc = new Scanner(newFile);

        System.out.printf("%4s %-20s %-10s", "Slot", "Item", "Price");

        System.out.println();

        while (sc.hasNextLine()) {
            String inLine = sc.nextLine();

            String[] inLineArray = inLine.split("\\|");
            double price = Double.parseDouble(inLineArray[2]);

            System.out.printf("%-4s %-20s %-10.2f", inLineArray[0], inLineArray[1], price);
            System.out.println();
        }
    }
}
