package gold.p1937;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int re_dp(int cr, int cc, int[][] graph, int[][] dp) {
		if (dp[cr][cc] != 0)
			return dp[cr][cc];

		int N = graph.length;
		int ret = 0;
		for (int i = 0; i < 4; i++) {
			int nr = cr + dir[i][0];
			int nc = cc + dir[i][1];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;

			if (graph[nr][nc] > graph[cr][cc])
				ret = Integer.max(ret, re_dp(nr, nc, graph, dp));
		}

		return dp[cr][cc] = ret + 1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				graph[i][j] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N][N];

		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				re_dp(i, j, graph, dp);
				ans = Integer.max(ans, dp[i][j]);
			}
		}

		System.out.println(ans);
	}

}
