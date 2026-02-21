package assignment1;
import java.io.*;
import java.util.Scanner;

public class Unit1AssignmentPartB {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new File("input.txt"));
		while (sc.hasNextLine()) {
			String input = sc.nextLine();
			input = decode(input);
			System.out.println(input);
		}
		sc.close();
		
	}
	public static String decode(String input) {
		String output = "";
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '%') {
				String num = input.substring(i+1, i+3);
				int dec = Integer.parseInt(num, 16);
				char converted =(char) dec;
				output += converted;
				i += 2;
			}
			else {
				output += input.charAt(i);
			}
		}
		return output;
	}

}
