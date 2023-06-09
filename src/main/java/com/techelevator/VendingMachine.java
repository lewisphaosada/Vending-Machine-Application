package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    private boolean firstRun = true;
    private List<String> withoutSplit = new ArrayList<>();

    public void displayContents() {
        //If initial run, populate with starting values.
        if (firstRun)
            loadFromFile();

        System.out.printf("%-6s %-20s %-10s %-20s", "Slot", "Item", "Price", "Quantity");
        System.out.println();
        for (String entry : withoutSplit) {
            List<String> newList = new ArrayList<>(List.of(entry.split("\\|")));
            if (Integer.parseInt(newList.get(4)) < 1) {
                newList.set(4, "Sold Out");
            }
            double price = Double.parseDouble(newList.get(2));
            System.out.printf("%-6s %-20s %-10.2f %-1s", newList.get(0), newList.get(1), price, newList.get(4));
            System.out.println();
        }
    }

    private void loadFromFile() {
        File newFile = new File("vendingmachine.csv");
        try (Scanner sc = new Scanner(newFile)) {
            while (sc.hasNextLine()) {
                String inLine = sc.nextLine();
                withoutSplit.add(inLine + "|5");
            }
            firstRun = false;
        } catch (FileNotFoundException e) {
            System.err.println("No such file");
        }
    }

    public void setQuantityRemaining(String identifier, int quantityRemaining) {
        for (int i = 0; i < withoutSplit.size(); i++) {
            List<String> newList = new ArrayList<>(List.of(withoutSplit.get(i).split("\\|")));
            if (newList.get(0).equals(identifier)) {
                newList.set(4, String.valueOf(quantityRemaining));
                withoutSplit.set(i, newList.get(0) + "|" + newList.get(1) + "|" + newList.get(2) + "|" + newList.get(3) + "|" + newList.get(4));
                return;
            }
        }
    }

    //Checks to see if the ID exist, if so, it will return the quantity that remains.
    public String getQuantityRemainingIfValidID(String identifier) {
        for (String s : withoutSplit) {
            List<String> newList = new ArrayList<>(List.of(s.split("\\|")));
            if (newList.get(0).equals(identifier)) {
                return newList.get(4);
            }
        }
        return "Not Found";
    }

    public List<String> getWithoutSplit() {
        return withoutSplit;
    }
}

