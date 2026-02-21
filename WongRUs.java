package assginment1;

import java.io.*;
import java.util.Scanner;

public class WongRUs {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfToy = 0;
        double purchaseTotalCostBeforeTax = 0;

        int mostExpensiveItemQuantity = 0;
        String mostExpensiveItemName = "";
        double mostExpensiveItemTotalCost = Double.MIN_VALUE;

        // using \t which is tab, equivalent to 4 chars
        String summary = """
				---------------------------------------------------------------------------------------------
				| Here is a summary of your purchases at Wong \"R\" Us:\t\t\t\t\t\t\t\t\t\t|
				|\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|
				| ITEM \t\t\t\t\t\t\t\t\t\tUNIT COST\t\tQUANTITY\t\tTOTAL COST\t\t|
				| ----\t\t\t\t\t\t\t\t\t\t---------\t\t--------\t\t----------\t\t|
				""";

        do {
            numOfToy++;
            // prompt for toy name, any string could work, but must be less than 30 chars and not empty
            String toyName = promptForToyName(sc, numOfToy);

            // prompt for quantity, must be an integer
            // must not use nextInt(), must use nextLine() and parse it to an integer, and must check if it's a positive integer first
            int quantity = promptForQuantity(sc);

            // prompt for cost, must be a double
            // must not use nextDouble(), must use nextLine() and parse it to a double, and must check if it's a positive double first
            double unitCost = promptForPrice(sc);

            // print the total price, which is quantity * cost
            double itemTotalPrice = quantity * unitCost;
            if (itemTotalPrice > mostExpensiveItemTotalCost) {
                mostExpensiveItemQuantity = quantity;
                mostExpensiveItemName = toyName;
                mostExpensiveItemTotalCost = itemTotalPrice;
            }
            purchaseTotalCostBeforeTax += itemTotalPrice;
            System.out.printf("The cost for %d %s @ $%.2f each is $%.2f\n", quantity, toyName, unitCost, itemTotalPrice);

            // add the toy to the summary, using \t to align the columns, and using String.format to format the string
            summary += String.format("| %-42s$%-15.2f%-16d$%-14.2f |\n", toyName, unitCost, quantity, itemTotalPrice);

            // ask continue or not, and keep asking until it's y or n <- must be do-while loop
        } while (promptForContinuation(sc) == 'y');

        double tax = Math.round(purchaseTotalCostBeforeTax * 0.13 * 100) / 100.0;

        summary += "| ------------------------------------------------------------------------------------      |\n";
        summary += String.format("| Total Cost                                                                $%-15.2f|\n", purchaseTotalCostBeforeTax);
        summary += String.format("| Tax                                                                       $%-15.2f|\n", tax);
        summary += "| ------------------------------------------------------------------------------------      |\n";
        summary += String.format("| Final Cost                                                                $%-15.2f|\n", purchaseTotalCostBeforeTax + tax);
        summary += String.format("|%-91s|\n", " ");
        summary += String.format("| Total # of items bought: %-65d|\n", numOfToy);
        summary += String.format("| Most expensive item: %d %s for $%-28.2f|\n", mostExpensiveItemQuantity, mostExpensiveItemName, mostExpensiveItemTotalCost);
        summary += "---------------------------------------------------------------------------------------------";

        printEndingMessage();
        savingSummary(summary);

        sc.close();
    }

    // helper method of savingSummary
    // save receipt to summary.txt, printf "\t"
    private static void savingSummary(String summary) {
        try {
            FileWriter fw = new FileWriter("summary.txt");
            fw.write(summary + "\n");
            fw.close();
            System.out.println("(Your receipt is saved in receipt.txt.)");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the summary.");
        }
    }

    // return true if input is a valid quantity (positive integer), otherwise return false
    private static boolean isValidateQuantity(String input) {
        try {
            int quantity = Integer.parseInt(input);
            return quantity > 0 && quantity <= 9999;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // keep asking for quantity until it's valid, then return the quantity as an integer
    private static int promptForQuantity(Scanner sc) {
        String input;
        do {
            System.out.print("How many of this toy are you buying?: ");
            input = sc.nextLine();
        } while (!isValidateQuantity(input));
        return Integer.parseInt(input);
    }

    // return true if input is a valid price (positive double), otherwise return false
    private static boolean isValidatePrice(String input) {
        try {
            double price = Double.parseDouble(input);
            return price > 0 && price <= 99999.99;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // keep asking for price until it's valid, then return the price as a double
    private static double promptForPrice(Scanner sc) {
        String input;
        do {
            System.out.print("Please enter the cost of this toy: $");
            input = sc.nextLine();
        } while (!isValidatePrice(input));
        return Math.round(Double.parseDouble(input) * 100) / 100.0;
    }

    // return true if input is a valid toy name (less than 30 chars and not empty), otherwise return false
    private static boolean isValidateToyName(String input) {
        return input.length() > 0 && input.length() <= 30;
    }

    // keep asking for toy name that is less than 30 chars until it's valid, then return the toy name as a string
    private static String promptForToyName(Scanner sc, int numOfToy) {
        String input;
        do {
            System.out.print("Please enter the name of the toy #" + numOfToy + ": ");
            input = sc.nextLine();
        } while (!isValidateToyName(input));
        return input;
    }

    private static char promptForContinuation(Scanner sc) {
        String input;
        do {
            System.out.print("Are you buying anymore toys? (y/n): ");
            input = sc.nextLine().toLowerCase();
            System.out.println();
        } while (!input.equals("y") && !input.equals("n"));
        return input.charAt(0);
    }

    private static void printEndingMessage() {
        System.out.println("Thank you for shopping at Wong \"R\" Us!");
    }
}
