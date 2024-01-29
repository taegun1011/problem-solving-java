package silver.p15650;

import java.util.Scanner;

public class Main {
	static int N;

	public static void recursion(int start, int left, StringBuilder sb) {
		if (left == 0) {
			System.out.println(sb);
			return;
		}
		for (int i = start + 1; i <= N; i++) {
			StringBuilder temp = new StringBuilder(sb);
			recursion(i, left - 1, temp.append(i).append(' '));
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int M = sc.nextInt();

		recursion(0, M, new StringBuilder());
	}
}
