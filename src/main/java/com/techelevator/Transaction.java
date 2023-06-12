package com.techelevator;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Transaction {
    h2Server dataBase = new h2Server();
    private VendingMachine vendingMachine;
    private double currentDollarAmount = 0;
    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

    Map<String, String> messageBasedOnItem = new HashMap<String, String>() {{
        put("chip", "Crunch Crunch, Yum!");
        put("candy", "Munch Munch, Yum!");
        put("drink", "Glug Glug, Yum!");
        put("gum", "Chew Chew, Yum!");
    }};

    public Transaction(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        dataBase.createAndInitializeDatabase();
    }

    public String dispenseProduct(String slotID) {
        String key = "";
        if (!vendingMachine.getItems().containsKey(slotID))
            return "Slot ID does not exist";
        else if (vendingMachine.getItems().get(slotID).getQuantity() < 1)
            return "Sold out, please choose another item";
        else if (getCurrentDollarAmount() - vendingMachine.getItems().get(slotID).getPrice() > 0) {
            //Subtract price from balance
            setCurrentDollarAmount(getCurrentDollarAmount() - vendingMachine.getItems().get(slotID).getPrice());
            //Dispense Output
            System.out.println("\nItem: " + vendingMachine.getItems().get(slotID).getDescription() +
                    "\nCost: " + formatter.format(vendingMachine.getItems().get(slotID).getPrice()) +
                    "\nMoney Remaining: " + formatter.format(getCurrentDollarAmount()));
            //Decrement the quantity by 1
            vendingMachine.getItems().get(slotID).setQuantity(vendingMachine.getItems().get(slotID).getQuantity() - 1);
            //*****BONUS**** Add sale to sale table ****
            dataBase.insertSale(vendingMachine.getItems().get(slotID));
            //*****BONUS**** Increment the total amount sold ****
            dataBase.incrementTotalSold(slotID, 1);
            //Writing to log file.
            log.info(vendingMachine.getItems().get(slotID).getDescription() + ": " +
                    formatter.format(vendingMachine.getItems().get(slotID).getPrice()) + " " +
                    formatter.format(getCurrentDollarAmount()));
            //Message to be printed, key is assigned by getting the item's category and retrieving its value
            key = vendingMachine.getItems().get(slotID).getCategory();
            return messageBasedOnItem.get(key.toLowerCase());
        } else
            return "Not Enough Money. Please deposit " +
                    formatter.format((vendingMachine.getItems().get(slotID).getPrice() - getCurrentDollarAmount()));
    }

    public void depositDollarAmount(double addDollarAmount) {
        this.currentDollarAmount += addDollarAmount;
        log.info("FEED MONEY: " + formatter.format(addDollarAmount) + " " + formatter.format(currentDollarAmount));
    }

    public void giveChange() {
        int nickles = 0, dimes = 0, quarters = 0;
        double change = getCurrentDollarAmount();
        log.info("GIVE CHANGE: " + formatter.format(change) + " " + formatter.format(0));
        quarters = (int) (change / 0.25);
        change = change % 0.25;
        dimes = (int) (change / 0.10);
        change = change % 0.10;
        nickles = (int) (change / 0.05);
        System.out.println("\nPlease take your change: " +
                "\nQuarters: " + quarters +
                "\nDimes: " + dimes +
                "\nNickels: " + nickles);
    }

    public double getCurrentDollarAmount() {
        return currentDollarAmount;
    }

    public void setCurrentDollarAmount(double setTo) {
        if (setTo >= 0)
            this.currentDollarAmount = setTo;
    }
}
