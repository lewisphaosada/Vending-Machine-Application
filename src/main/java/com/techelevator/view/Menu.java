package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private PrintWriter out;
    private Scanner in;

    public Menu(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        return choice;
    }

    private void displayMenuOptions(Object[] options) {
        out.println();
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }

    //Checks to see if user input a valid deposit amount
    public int depositErrorCheck(String prompt, String prompt2, int lengthRestriction, List<Integer> values) {
        int userIntegerInput = 0;
        int value = 0;
        boolean tryCatchFlag;
        do {
            if (String.valueOf(userIntegerInput).length() > lengthRestriction)
                System.out.println("Invalid input. Please ensure you're entering an integer that is <= " + lengthRestriction);
            tryCatchFlag = false;
            System.out.println("\n" + prompt);
            System.out.print(prompt2);
            try {
                userIntegerInput = in.nextInt();
                int integerInput = userIntegerInput;
                //Checks to see if the user input an acceptable amount, if so, set value to the accepted user input value.
                value = values.stream().filter(amount -> amount == integerInput).findFirst().orElseThrow(() -> new InputMismatchException("Input Invalid"));
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                tryCatchFlag = true;
            }
            in.nextLine();
        } while (String.valueOf(userIntegerInput).length() > lengthRestriction || tryCatchFlag);
        return value;
    }

    //Checks the Users Input on String types and restricts the length.
    public String errorCheckString(String prompt, String prompt2, int lengthRestriction) {
        String userStringInput = "";
        do {
            if (userStringInput.length() > lengthRestriction)
                System.out.println("Input length is greater than " + lengthRestriction + ". Please try again.");
            System.out.println("\n" + prompt);
            System.out.print(prompt2);
            userStringInput = in.nextLine();
        } while (userStringInput.length() > lengthRestriction);
        return userStringInput;
    }
}
