package silver.p9461;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(padoban(N)).append('\n');
		}

		System.out.println(sb);
	}

	private static long padoban(int n) {
		long[] dp = new long[101];
		dp[1] = dp[2] = dp[3] = 1;
		dp[4] = dp[5] = 2;
		for (int i = 6; i <= n; i++)
			dp[i] = dp[i - 1] + dp[i - 5];

		return dp[n];
	}
}
