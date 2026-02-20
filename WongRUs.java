package assignment1;
import java.util.Scanner;
public class WongRUs {

		final private static Scanner sc  = new Scanner(System.in);
	public static void main(String[] args) {

		int numOfToy = 1;
		do {
			// prompt for toy name, any string could work
			System.out.print("Please enter the name of the toy #" + numOfToy + ": ");
			String toyName = sc.nextLine();

			// prompt for quantity, must be an integer
			// must not use nextInt(), must use nextLine() and parse it to an integer, and must check if it's a positive integer first
			int quantity = promptForQuantity();

			// prompt for cost, must be a double
			// must not use nextDouble(), must use nextLine() and parse it to a double, and must check if it's a positive double first
			double cost = promptForPrice();

			// print the total price, which is quantity * cost
			double totalPrice = quantity * cost;
			System.out.printf("The cost for %d %s @ $%.2f each is $%.2f\n", quantity, toyName, cost, totalPrice);

			// ask continue or not, and keep asking until it's y or n <- must be do-while loop
		}while(promptForContinuation() == 'y');

		printEndingMessage();
		// savingSummary

		sc.close();
	}

	// helper method of savingSummary
	// save receipt to summary.txt, printf "\t"

	// return true if input is a valid quantity (positive integer), otherwise return false
	private static boolean isValidateQuantity(String input) {
		try {
			int quantity = Integer.parseInt(input);
			return quantity > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// keep asking for quantity until it's valid, then return the quantity as an integer
	private static int promptForQuantity() {
		String input;
		do {
			System.out.print("How many of this toy are tyou buying?: ");
			input = sc.nextLine();
		} while (!isValidateQuantity(input));
		return Integer.parseInt(input);
	}

	// return true if input is a valid price (positive double), otherwise return false
	private static boolean isValidatePrice(String input) {
		try {
			double price = Double.parseDouble(input);
			return price > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// keep asking for price until it's valid, then return the price as a double
	private static double promptForPrice() {
		String input;
		do {
			System.out.print("Please enter the cost of this toy: ");
			input = sc.nextLine();
		} while (!isValidatePrice(input));
		return Double.parseDouble(input);
	}

	private static char promptForContinuation() {
		String input;
		do {
			System.out.print("Are you buying anymore toys? (y/n): ");
			input = sc.nextLine().toLowerCase();
		} while (!input.equals("y") && !input.equals("n"));
		return input.charAt(0);
	}
	
	private static void printEndingMessage() {
		System.out.println("Thank you for shopping at Wong \"R\" Us!");
	}
}
