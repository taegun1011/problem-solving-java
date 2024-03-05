package gold.p1509;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//각 구간의 팰린드롬 여부를 저장해둔다
//top down

public class Main {
	static void set(int[][] dp, String str, int s, int e) {
		if (e - s == 1) {
			dp[s][e] = str.charAt(s) == str.charAt(e) ? 1 : 0;
			return;
		}
		if (dp[s][e] != -1)
			return;
		set(dp, str, s, e - 1);
		set(dp, str, s + 1, e);
		set(dp, str, s + 1, e - 1);
		dp[s][e] = str.charAt(s) == str.charAt(e) ? dp[s + 1][e - 1] : 0;
	}

	// dp[s][e] : s부터 e까지의 팰린드롬 분할의 최솟값
	static int re_dp(int[][] P, int[][] dp, int s, int e) {
		if (P[s][e] == 1)
			return 1;
		if (dp[s][e] != -1)
			return dp[s][e];
		int ret = e - s + 1;
		for (int i = s; i < e; i++)
			if (P[s][i] == 1)
				ret = Integer.min(ret, re_dp(P, dp, i + 1, e) + 1);

		return dp[s][e] = ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		int N = str.length();
		int[][] P = new int[N][N], cnt = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(P[i], -1);
			Arrays.fill(cnt[i], -1);
			P[i][i] = 1;
		}
		set(P, str, 0, N - 1);

		System.out.println(re_dp(P, cnt, 0, N - 1));
	}
}
