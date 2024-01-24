package silver.p6182;

import java.util.Scanner;

public class Main {
	public static int recursion(int N, int K) {
		int a = (N - K) / 2, b = a + K;
		if (a <= 0 || a + b != N) {
			return 1;
		}

		return recursion(a, K) + recursion(b, K);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(), K = sc.nextInt();

		System.out.println(recursion(N, K));
	}
}
