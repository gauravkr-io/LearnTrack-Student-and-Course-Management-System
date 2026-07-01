package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.util.InputValidator;

import java.util.Scanner;

public final class ConsoleInput {

    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleInput() {
    }

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine();
    }

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = SCANNER.nextLine().trim();
            int result = InputValidator.parsePositiveInt(input);
            if (result > 0) {
                return result;
            }
            System.out.println("  [Error] Please enter a valid positive number.");
        }
    }

    public static int readMenuChoice(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = SCANNER.nextLine().trim();
            if (InputValidator.isNullOrBlank(input)) {
                System.out.println("  [Error] Please enter a valid choice.");
                continue;
            }
            try {
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    return value;
                }
            } catch (NumberFormatException exception) {
                // fall through
            }
            System.out.println("  [Error] Please enter a valid choice.");
        }
    }

    public static void close() {
        SCANNER.close();
    }
}
