package com.techelevator;

import com.techelevator.view.Menu;

import java.util.ArrayList;
import java.util.List;


public class VendingMachineCLI {
    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION};
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    private Menu menu;
    private VendingMachine vendingMachine;
    private Transaction transaction;

    public VendingMachineCLI(Menu menu, VendingMachine vendingMachine, Transaction transaction) {
        this.menu = menu;
        this.vendingMachine = vendingMachine;
        this.transaction = transaction;
    }

    public void run() {
        boolean stayInPurchaseMenu;
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            switch (choice) {
                case MAIN_MENU_OPTION_DISPLAY_ITEMS:
                    vendingMachine.displayContents();
                    break;
                case MAIN_MENU_OPTION_PURCHASE:
                    System.out.println("\nCurrent Money Deposited: " + transaction.getCurrentDollarAmount());
                    stayInPurchaseMenu = true;
                    while (stayInPurchaseMenu) {
                        String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
                        switch (purchaseChoice) {
                            case PURCHASE_MENU_OPTION_FEED_MONEY:
                                transaction.depositDollarAmount(
                                        menu.depositErrorCheck("How much would you like to Deposit?",
                                                "Only Accept (1, 5, 10, 20) Whole Dollar Amounts >>> ",
                                                2, new ArrayList<>(List.of(1, 5, 10, 20))));
                                break;
                            case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
                                vendingMachine.displayContents();
                                transaction.dispenseProduct(
                                        menu.errorCheckString("Please enter the Slot ID",
                                                "Purchase --> ", 2));
                                //TODO Finish
                                //Dispensing an item prints the item name, cost, and the money remaining
                                break;
                            case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
                                System.out.println("You chose to Finish Transaction");
                                transaction.setCurrentDollarAmount(0);
                                stayInPurchaseMenu = false;
                                //TODO
                                //Selecting "(3) Finish Transaction" allows the customer to complete the
                                //    transaction and receive any remaining change.
                                //        - The machine returns the customer's money using nickels, dimes, and quarters
                                //        (using the smallest amount of coins possible).
                                //        - The machine's current balance updates to $0 remaining.
                                break;
                            default:
                                System.out.println("Not an Option");
                        }
                        System.out.println("\nCurrent Money Deposited: " + transaction.getCurrentDollarAmount());
                    }
                    break;
                case MAIN_MENU_OPTION_EXIT:
                    System.out.println("Thank you, come again!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Not an Option");
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachine vendingMachine = new VendingMachine();
        Transaction transaction = new Transaction(vendingMachine);
        VendingMachineCLI cli = new VendingMachineCLI(menu, vendingMachine, transaction);

        cli.run();
    }


}
