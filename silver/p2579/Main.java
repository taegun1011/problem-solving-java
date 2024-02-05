package silver.p2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N == 1) {
			System.out.println(br.readLine());
			return;
		}

		int[] cost = new int[N + 1];
		for (int i = 1; i <= N; i++)
			cost[i] = Integer.parseInt(br.readLine());

		int[][] dp = new int[304][2];
		dp[1][0] = cost[1];
		dp[2][1] = cost[2];
		dp[2][0] = cost[1] + cost[2];
		for (int i = 3; i <= N; i++) {
			dp[i][0] = dp[i - 1][1] + cost[i];
			dp[i][1] = Integer.max(dp[i - 2][0], dp[i - 2][1]) + cost[i];
		}

		System.out.println(Integer.max(dp[N][0], dp[N][1]));
	}
}
