package com.techelevator;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Transaction {
    private VendingMachine vendingMachine;
    private double currentDollarAmount = 0;
    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
    LocalDate myDate = LocalDate.now();
    LocalTime myTime = LocalTime.now();
    Map<String, String> messageBasedOnItem = new HashMap<String, String>() {{
        put("chip", "Crunch Crunch, Yum!");
        put("candy", "Munch Munch, Yum!");
        put("drink", "Glug Glug, Yum!");
        put("gum", "Chew Chew, Yum!");
    }};

    public Transaction(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
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
            System.out.println("\nItem: " + vendingMachine.getItems().get(slotID).getDescription() + "\n" +
                    "Cost: " + formatter.format(vendingMachine.getItems().get(slotID).getPrice()) + "\n" +
                    "Money Remaining: " + formatter.format(getCurrentDollarAmount()));
            //Decrement the quantity by 1
            vendingMachine.getItems().get(slotID).setQuantity(vendingMachine.getItems().get(slotID).getQuantity() - 1);
            //Message to be printed, key is assigned by getting the item's category and retrieving its value
            key = vendingMachine.getItems().get(slotID).getCategory();
            return messageBasedOnItem.get(key.toLowerCase());
        }else
            return "Not Enough Money. Please deposit " + formatter.format((vendingMachine.getItems().get(slotID).getPrice() - getCurrentDollarAmount()));

    }

    public void depositDollarAmount(double addDollarAmount) {
        this.currentDollarAmount += addDollarAmount;
    }

    public double getCurrentDollarAmount() {
        return currentDollarAmount;
    }

    public void setCurrentDollarAmount(double setTo) {
        if(setTo >= 0)
            this.currentDollarAmount = setTo;
    }

    //TODO
    //The vending machine logs all transactions to prevent theft from the vending machine.
    //   - Each purchase must generate a line in a file called `Log.txt`.
    //   - The lines must follow the format shown in the following example.
    //       - The first dollar amount is the amount deposited, spent, or given as change.
    //       - The second dollar amount is the new balance.
    //        ```
    //        01/01/2019 12:00:00 PM FEED MONEY: $5.00 $5.00
    //        01/01/2019 12:00:15 PM FEED MONEY: $5.00 $10.00
    //        01/01/2019 12:00:20 PM Crunchie B4 $1.75 $8.25
    //        01/01/2019 12:01:25 PM Cowtales B2 $1.50 $6.75
    //        01/01/2019 12:01:35 PM GIVE CHANGE: $6.75 $0.00
    private void createLogFile() {

    }

}
