package gold.p2225;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int MOD = 1000000000;

	static int[][] dp = new int[201][201];

	static int re_dp(int sum, int left) {
		if (left == 0)
			return sum > 0 ? 0 : 1;
		if (dp[sum][left] != -1)
			return dp[sum][left];
		long ret = 0;
		for (int i = 0; i <= sum; i++) {
			ret += re_dp(sum - i, left - 1);
			ret %= MOD;
		}

		return dp[sum][left] = (int) ret;
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 201; i++)
			Arrays.fill(dp[i], -1);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		System.out.println(re_dp(N, K));
	}
}
