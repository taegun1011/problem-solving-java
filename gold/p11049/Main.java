package gold.p11049;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
			arr[i + 1] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N + 1][N + 1];

		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], (int) 1e9);
			dp[i][i + 1] = 0;
		}

		for (int i = 2; i <= N; i++) {
			for (int j = 0; i + j <= N; j++) {
				int l = j;
				int r = i + j;
				for (int k = l + 1; k < r; k++) {
					dp[l][r] = Integer.min(dp[l][r], dp[l][k] + dp[k][r] + arr[l] * arr[k] * arr[r]);
				}
			}
		}

		System.out.println(dp[0][N]);
	}
}
