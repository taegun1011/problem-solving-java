package gold.p12865;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RecurDp {
	static int[][] dp;
	static int N;
	static int[] W, V;

	static int dp(int i, int j) {
		if (j < 0)
			return (int) -1e9;
		if (i == N)
			return 0;
		if (dp[i][j] != -1)
			return dp[i][j];
		int ret = Integer.max(dp(i + 1, j), dp(i + 1, j - W[i]) + V[i]);
		return dp[i][j] = ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		dp = new int[N][K + 1];
		for (int i = 0; i < N; i++)
			Arrays.fill(dp[i], -1);

		W = new int[N];
		V = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(dp(0, K));
	}
}
