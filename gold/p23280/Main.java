package gold.p23280;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int A;
	static int B;

	// 재귀 -> A 위치, B 위치

	// 숫자간의 거리 -> 가로 : -1 mod 3의 차, 세로 : -1 / 3의 차
	static int getDistance(int a, int b) {
		return Math.abs((a + 2) % 3 - (b + 2) % 3) + Math.abs((a - 1) / 3 - (b - 1) / 3);
	}

	static int[][] dist = new int[13][13];

	static int[][][] dp = new int[10001][13][13];
	static int[] arr;

	static int re_dp(int idx, int apos, int bpos) {
		if (idx == N)
			return 0;
		if (dp[idx][apos][bpos] != -1)
			return dp[idx][apos][bpos];
		// A 옮기기, B 옮기기
		int a = re_dp(idx + 1, arr[idx], bpos) + dist[apos][arr[idx]] + A;
		int b = re_dp(idx + 1, apos, arr[idx]) + dist[bpos][arr[idx]] + B;
		int ret = Integer.min(a, b);

		return dp[idx][apos][bpos] = ret;
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < dp.length; i++)
			for (int j = 0; j < dp[0].length; j++)
				Arrays.fill(dp[i][j], -1);

		for (int i = 1; i <= 12; i++)
			for (int j = 1; j <= 12; j++)
				dist[i][j] = getDistance(i, j);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		System.out.println(re_dp(0, 1, 3));
	}
}
