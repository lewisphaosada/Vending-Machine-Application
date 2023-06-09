package com.techelevator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private VendingMachine vendingMachine;
    private int currentDollarAmount = 0;
    LocalDate myDate = LocalDate.now();
    LocalTime myTime = LocalTime.now();

    public Transaction(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public void dispenseProduct(String slotID) {
        if (!vendingMachine.getQuantityRemainingIfValidID(slotID).equals("Not Found") && !vendingMachine.getQuantityRemainingIfValidID(slotID).equals("0")) {
            //TODO
            //If a customer selects a valid product, it's dispensed to the customer.
            //        - Dispensing an item prints the item name, cost, and the money
            //        remaining. Dispensing also returns a message:
            //          - All chip items print "Crunch Crunch, Yum!"
            //          - All candy items print "Munch Munch, Yum!"
            //          - All drink items print "Glug Glug, Yum!"
            //          - All gum items print "Chew Chew, Yum!"
            //        - After the machine dispenses the product, the machine must update its balance
            //        accordingly and return the customer to the Purchase menu.
            System.out.println(slotID + " You need to dispense an item");
        } else if (vendingMachine.getQuantityRemainingIfValidID(slotID).equals("0"))
            System.out.println("Sold out, please choose another item");
        else
            System.out.println("Slot ID does not exist");
    }

    public void depositDollarAmount(int addDollarAmount) {
        this.currentDollarAmount += addDollarAmount;
    }

    public int getCurrentDollarAmount() {
        return currentDollarAmount;
    }

    public void setCurrentDollarAmount(int setTo) {
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
    private void createLogFile(){

    }

}
