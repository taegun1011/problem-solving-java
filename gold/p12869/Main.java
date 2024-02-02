package gold.p12869;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] damage = { { 1, 3, 9 }, { 1, 9, 3 }, { 3, 1, 9 }, { 3, 9, 1 }, { 9, 1, 3 }, { 9, 3, 1 } };

	static int[][][] dp = new int[61][61][61];

	static int solve(int a, int b, int c) {
		if (a == 0 && b == 0 && c == 0)
			return 0;
		if (dp[a][b][c] != -1)
			return dp[a][b][c];

		int[] hp = { a, b, c };
		int ret = Integer.MAX_VALUE;
		for (int i = 0; i < 6; i++) {
			int[] left = new int[3];
			for (int j = 0; j < 3; j++) {
				left[j] = Integer.max(hp[j] - damage[i][j], 0);
			}
			ret = Integer.min(ret, solve(left[0], left[1], left[2]) + 1);
		}
		return (dp[a][b][c] = ret);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;
		int c = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;

		for (int[][] h : dp)
			for (int[] r : h)
				Arrays.fill(r, -1);

		System.out.println(solve(a, b, c));
	}
}
