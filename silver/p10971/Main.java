package silver.p10971;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int INF = (int) 1e9;

	static int[][] dp;

	static int N;
	static int[][] W;

	static int re_dp(int start, int cur, int field) {
		if (field + 1 == (1 << N))
			return W[cur][start] == 0 ? INF : W[cur][start];
		if (dp[cur][field] == INF)
			return INF;
		if (dp[cur][field] != -1)
			return dp[cur][field];
		int ret = INF;
		for (int nxt = 0; nxt < N; nxt++) {
			if ((field & (1 << nxt)) != 0)
				continue;
			if (W[cur][nxt] == 0)
				continue;
			int sum = re_dp(start, nxt, field + (1 << nxt)) + W[cur][nxt];
			ret = Integer.min(ret, sum);
		}

		return dp[cur][field] = ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				W[i][j] = Integer.parseInt(st.nextToken());
		}

		// 마지막 도시,방문한 도시들
		dp = new int[N][1 << N];

		int ans = INF;
		for (int start = 0; start < N; start++) {
			for (int i = 0; i < N; i++)
				Arrays.fill(dp[i], -1);
			ans = Integer.min(ans, re_dp(start, start, 1 << start));
		}

		System.out.println(ans);
	}
}
