package gold.p28071;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		// dp[i][j][k] = x => i번째 사탕을 골라서 j개가 남았을 때 나머지가 k인 경우의 최댓값
		int[][][] dp = new int[301][301][301];
		for (int i = 0; i < dp.length; i++)
			for (int j = 0; j < dp.length; j++)
				Arrays.fill(dp[i][j], -1);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		dp[0][M][0] = 0;

		for (int i = 0; i < N; i++) {
			for (int j = M; j >= 0; j--) {
				for (int k = 0; k < K; k++) {
					if (dp[i][j][k] >= 0)
						for (int l = 0; l <= j; l++) {
							int sum = dp[i][j][k] + arr[i] * l;
							dp[i + 1][j - l][sum % K] = Integer.max(dp[i + 1][j - l][sum % K], sum);
						}
				}
			}
		}

		int ans = 0;
		for (int i = 0; i <= M; i++)
			ans = Integer.max(ans, dp[N][i][0]);
		System.out.println(ans);
	}
}
