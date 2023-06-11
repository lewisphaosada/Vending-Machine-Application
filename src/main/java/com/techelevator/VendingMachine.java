package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    private boolean firstRun = true;
    private Map<String, Item> items = new HashMap<>();

    public void displayContents() {
        //If initial run, populate with starting values.
        if (firstRun)
            loadFromFile();

        System.out.printf("%-6s %-20s %-10s %-20s", "Slot", "Item", "Price", "Quantity");
        System.out.println();
        for (Item entry : items.values()) {
            System.out.printf("%-6s %-20s %-10.2f %-1s", entry.getSlot(), entry.getDescription(), entry.getPrice(), entry.getQuantity() > 0 ? entry.getQuantity() : "Sold Out");
            System.out.println();
        }
    }

    private void loadFromFile() {
        File newFile = new File("./data/vendingmachine.csv");
        try (Scanner sc = new Scanner(newFile)) {
            while (sc.hasNextLine()) {
                String inLine = sc.nextLine();
                String[] line = inLine.split("\\|");
                Item item = new Item(line[0], line[1], Double.parseDouble(line[2]), line[3], 5);
                items.put(item.getSlot(), item);
            }
            firstRun = false;
        } catch (FileNotFoundException e) {
            System.err.println("No such file");
        }
    }

    public Map<String, Item> getItems() {
        return items;
    }
}

