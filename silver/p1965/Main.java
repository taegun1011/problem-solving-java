package silver.p1965;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] box;
	static int[] dp;

	static int func(int idx) {
		if (idx == n - 1) {
			return dp[idx] = 1;
		}

		if (dp[idx] != 0) {
			return dp[idx];
		}

		if (idx + 1 >= n) {
			return dp[idx];
		}

		dp[idx] = 0;
		if (box[idx] < box[idx + 1])
			dp[idx] = Math.max(dp[idx], 1 + func(idx + 1));
		else
			dp[idx] = Math.max(dp[idx], func(idx + 1));

		return dp[idx];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		box = new int[n];
		dp = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(func(0));
	}
}