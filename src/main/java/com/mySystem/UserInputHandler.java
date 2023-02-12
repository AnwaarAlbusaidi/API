package com.mySystem;

import java.util.Scanner;

public class UserInputHandler {

    private Scanner userInput = new Scanner(System.in);

    /**
     * Input: None Output: String value Description: This method prompts the user to
     * enter a string value and returns the input as a string.
     * @return returns the input as a string
     */

    public String getUserChoiceString() {
        String choice = null;
        while (choice == null || choice.trim().isEmpty()) {
            System.out.println("Enter your string in term of (place name,country name): ");
            choice = userInput.nextLine();
        }
        return choice;
    }
}
