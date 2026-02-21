package assignment1;

import java.io.*;
import java.util.Scanner;

public class Unit1AssignmentPartC {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input2.txt"));

        int caseNum = isValidCount(sc.next());
        if (caseNum == -1) {
            sc.close();
            return;
        }

        test_cases:
        for (int i = 1; i <= caseNum; i++) {
            int arrLength = isValidCount(sc.next());
            if (arrLength == -1) {
                if (i < caseNum) {
                    sc.nextLine();
                    sc.nextLine();
                }
                continue;
            }
            int[] input = new int[arrLength];

            for (int j = 0; j < input.length; j++) {
                try {
                    int num = Integer.parseInt(sc.next());
                    input[j] = num;
                } catch (NumberFormatException e) {
                    if (i < caseNum) {
                        sc.nextLine();
                        sc.nextLine();
                    }
                    continue test_cases;
                }
            }
            int max = findSum(input);
            System.out.println("Case #" + i + ": " + max);
        }
        sc.close();
    }

    public static int findSum(int[] numArray) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < numArray.length; i++) {
            int sum = 0;
            for (int j = i; j < numArray.length; j++) {
                sum += numArray[j];
                if (max < sum) {
                    max = sum;
                }
            }
        }
        return max;
    }

    public static int isValidCount(String str) {
        try {
            int count = Integer.parseInt(str);
            if (count > 0) {
                return count;
            } else {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
