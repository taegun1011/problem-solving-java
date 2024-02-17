package gold.p28071;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		// dp[i][j][k] : i번째 사탕까지, j개가 남았을 때, 나머지가 k, 최댓값
		int[][][] dp = new int[N + 1][M + 1][K];
//		for (int i = 0; i <= N; i++)
//			for (int j = 0; j <= M; j++)
//				Arrays.fill(dp[i][j], -1);
//		dp[0][M][0] = 0;

		// 4중 반복문...??
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= M; j++) {
				for (int k = 0; k < K; k++) {
					int temp = dp[i][M - j][k] + arr[i] * j;
					dp[i + 1][M - j][temp % K] = Integer.max(dp[i + 1][M - j][temp % K], temp);
				}
			}
		}

		int ans = 0;
		for (int i = 0; i <= M; i++)
			ans = Integer.max(ans, dp[N][i][0]);

		System.out.println(ans);
	}
}
