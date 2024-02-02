package silver.p1359;

import java.util.Scanner;

public class Main {
	public static int fact(int N) {
		if (N <= 1)
			return 1;
		return fact(N - 1) * N;
	}

	public static int comb(int N, int M) {
		return fact(N) / fact(M) / fact(N - M);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(), M = sc.nextInt(), K = sc.nextInt();

		double result = 0.0;
		for (int i = K; i <= M; i++) {
			result += 1.0 * comb(M, i) * comb(N - M, M - i);
		}
		result /= comb(N, M);

		System.out.println(result);
	}
}
