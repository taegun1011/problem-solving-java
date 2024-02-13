package gold.p2533;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] Edges;
	static int[][] dp = new int[1000001][2];

	static int re_dp(int cur, int type, int prev) {
		// 트리를 이용한 top-down dp

		if (dp[cur][type] != -1)
			return dp[cur][type];
		int ret = type;

		for (int nxt : Edges[cur]) {
			if (nxt == prev)
				continue;
			if (type == 0)
				ret += re_dp(nxt, 1, cur);
			else
				ret += Integer.min(re_dp(nxt, 0, cur), re_dp(nxt, 1, cur));
		}

		return dp[cur][type] = ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Edges = new List[N + 1];
		for (int i = 1; i <= N; i++)
			Edges[i] = new LinkedList<>();

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			Edges[u].add(v);

			// 일단은 무향 간선으로 받음
			Edges[v].add(u);
		}

		for (int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], -1);

		System.out.println(Integer.min(re_dp(1, 0, -1), re_dp(1, 1, -1)));
	}
}
