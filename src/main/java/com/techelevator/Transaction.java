package com.techelevator;

public class Transaction {
    private VendingMachine vendingMachine;
    private int currentDollarAmount = 0;

    public Transaction(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    public void addDollarAmount(int addDollarAmount) {
        this.currentDollarAmount += addDollarAmount;
    }

    public int getCurrentDollarAmount() {
        return currentDollarAmount;
    }

    public void setCurrentDollarAmount(int setTo) {
        this.currentDollarAmount = setTo;
    }


}
