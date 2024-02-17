package gold.p12865;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LoopDp {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N + 1][K + 1];
		int[] W = new int[N + 1], V = new int[N + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = K; j >= 0; j--) {
				if (j >= W[i])
					dp[i + 1][j - W[i]] = Integer.max(dp[i + 1][j - W[i]], dp[i][j] + V[i]);
				dp[i + 1][j] = Integer.max(dp[i + 1][j], dp[i][j]);
			}
		}

		int max = 0;
		for (int x : dp[N])
			max = Integer.max(max, x);
		System.out.println(max);
	}
}
