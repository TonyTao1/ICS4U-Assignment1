package assignment1;

import java.io.*;
import java.util.Scanner;

public class Unit1AssignmentPartB {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        // open the file to scan the input
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            input = decode(input);
            // decoding by using the method "decode"
            System.out.println(input);
        }
        sc.close();
    }

    public static String decode(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            // scan the string char by char
            if (input.charAt(i) == '%') {
                // if the i character of the string is %, then the word behind % will be the hex
                // which we need to decode
                if (i + 3 > input.length()) {
                    // if less than 2 chars are behind %
                    output += input.substring(i);
                    return output;
                }
                // to see if it is legal

                String num = input.substring(i + 1, i + 3);
                // take out the two letter string from the first char after % to the second char
                // after the % to decode
                char firstChar = num.charAt(0);
                char secondChar = num.charAt(1);
                if (firstChar > 'F' || secondChar > 'F') {
                    output += '%' + num;
                    i += 2;
                    continue;
                }
                // to see if is out of range
                int dec = Integer.parseInt(num, 16);
                // decode by using parseInt

                if (dec < 32) {
					// if not printable char
                    output += '%' + num;
                    i += 2;
                    continue;
                }
                // to check if the output is printable
                char converted = (char) dec;
                output += converted;
                i += 2;
                // skip the hex in case the for loop put the same thing into the output twice
            } else {
                output += input.charAt(i);
            }
        }
        return output;
    }

}
