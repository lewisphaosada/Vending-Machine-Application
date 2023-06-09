package com.techelevator;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private VendingMachine vendingMachine;
    private int currentDollarAmount = 0;
    LocalDate myDate = LocalDate.now();
    LocalTime myTime = LocalTime.now();

    public Transaction(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    public void dispenseProduct(String slotID){
        if(!vendingMachine.getQuantityRemainingIfValidID(slotID).equals("Slot ID does not exist") && !vendingMachine.getQuantityRemainingIfValidID(slotID).equals("0")){
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
        else if(vendingMachine.getQuantityRemainingIfValidID(slotID).equals("Slot ID does not exist"))
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


}
