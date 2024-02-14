package silver.p11051;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[][] dp = new int[1001][1001];

	static final int divisor = 10007;

	static int re_dp(int n, int r) {
		if (r == 0 || n == r)
			return 1;
		if (r == 1 || n - r == 1)
			return n;
		if (dp[n][r] != -1)
			return dp[n][r];

		int ret = re_dp(n - 1, r - 1) + re_dp(n - 1, r);
		return dp[n][r] = ret % divisor;
	}

	public static void main(String[] args) {
		for (int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], -1);

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(), K = sc.nextInt();

		System.out.println(re_dp(N, K));
	}
}
