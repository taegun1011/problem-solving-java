package bronze.p3009;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] points = new int[3][2];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				points[i][j] = sc.nextInt();
			}
		}

		int y = 0, x = 0;
		for (int i = 0; i < 3; i++) {
			y ^= points[i][0];
			x ^= points[i][1];
		}
		System.out.println(y + " " + x);
	}
}
