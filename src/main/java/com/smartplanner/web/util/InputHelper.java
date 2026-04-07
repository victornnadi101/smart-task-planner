package com.smartplanner.web.util;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * ===================================================
 * CLASS: InputHelper
 * ===================================================
 * PURPOSE
 * -------
 * Provides reusable methods for safe user input handling.
 * *
 * RESPONSIBILITIES
 * ----------------
 * - Validate integer input
 * - Validate string input
 * - Validate date input
 * *
 * DESIGN NOTES
 * ------------
 * - Prevents program crashes from invalid input
 * - Keeps Main class clean and readable
 * - Promotes code reuse and maintainability
 */
public class InputHelper {
    // Reads a valid integer from the user.
    public static int readInt(Scanner input, String message) {
        int value;
        while (true) {
            System.out.print(message);
            if(input.hasNextInt()) {
                value = input.nextInt();
                input.nextLine(); // clear buffer
                return value;
            } else {
                System.out.println("\nInvalid input! Please enter a number.");
                input.nextLine(); // discard invalid input
            }
        }
    }

    // Reads a valid string from the user.
    public static String readString(Scanner input, String message) {
        System.out.print(message);
        return input.nextLine();
    }

    // Reads a valid date in YYYY-MM-DD format.
    public static LocalDate readDate(Scanner input, String message) {
        LocalDate date = null;
        while(date == null) {
            System.out.print(message);
            String inputDate = input.nextLine();

            try {
                date = LocalDate.parse(inputDate);
            } catch (Exception ex) {
                System.out.println("\nInvalid date format! Use YYY-MM-DD.");
            }
        }
        return date;
    }

}
