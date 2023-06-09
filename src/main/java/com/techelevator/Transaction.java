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
