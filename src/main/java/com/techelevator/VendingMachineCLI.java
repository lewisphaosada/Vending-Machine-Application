package com.techelevator;

import com.techelevator.view.Menu;


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

    public VendingMachineCLI(Menu menu, VendingMachine vendingMachine) {
        this.menu = menu;
        this.vendingMachine = vendingMachine;
    }

    public void run() {
        boolean stayInPurchaseMenu = false;
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            switch (choice) {
                case MAIN_MENU_OPTION_DISPLAY_ITEMS:
                    vendingMachine.displayContents();
                    break;
                case MAIN_MENU_OPTION_PURCHASE:
                    int currentMoney = 0;
                    System.out.println("\nCurrent Money Provided: " + currentMoney);
                    stayInPurchaseMenu = true;
                    while (stayInPurchaseMenu) {
                        String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
                        switch (purchaseChoice) {
                            case PURCHASE_MENU_OPTION_FEED_MONEY:
                                System.out.println("You chose to Feed Money");
                                ++currentMoney;
                                break;
                            case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
                                System.out.println("You chose to Select Product");
                                break;
                            case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
                                System.out.println("You chose to Finish Transaction");
                                stayInPurchaseMenu = false;
                                break;
                            default:
                                System.out.println("Not an Option");
                        }
                        System.out.println("\nCurrent Money Provided: " + currentMoney);
                    }
                    break;
                case MAIN_MENU_OPTION_EXIT:
                    System.out.println("You chose to Exit");
                    break;
                default:
                    System.out.println("Not an Option");
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachine vendingMachine = new VendingMachine();
        VendingMachineCLI cli = new VendingMachineCLI(menu, vendingMachine);

        cli.run();
    }


}
