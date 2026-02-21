package assignment1;
import java.io.*;
import java.util.Scanner;
public class Unit1AssignmentPartC {
	public static void main(String []args)throws IOException {
		Scanner sc = new Scanner(new File("input2.txt"));
		int caseNum = sc.nextInt();
		for(int i = 1; i <= caseNum; i++) {
			int[] input = new int[sc.nextInt()];
			for(int j = 0; j < input.length; j++) {
				input[j] = sc.nextInt();
			}
			int max = findSum(input);
			System.out.println("Case #" + i + ": " + max);
		}
		sc.close();
	}
	public static int findSum(int[] numArray) {
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < numArray.length; i++) {
			int sum = 0;
			for(int j = i; j < numArray.length; j++) {
				sum += numArray[j];
				if(max < sum) {
					max = sum;
				}
			}
		}
		return max;
	}

}
