package gold.p28467;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N][N];
		long[][] sum = new long[N][N];

		for (int i = 0; i < N; i++) {
			dp[i][i] = arr[i];
			Arrays.fill(sum[i], (long) 1e18);
			sum[i][i] = 0;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 0; i + j < N; j++) {
				int l = j;
				int r = i + j;
				for (int k = l; k < r; k++) {
					long temp = sum[l][k] + sum[k + 1][r] + dp[l][k] + dp[k + 1][r];
					if (temp < sum[l][r]) {
						sum[l][r] = temp;
						dp[l][r] = Integer.max(dp[l][k], dp[k + 1][r]);
					}
				}
			}
		}

		System.out.println(sum[0][N - 1]);
	}
}
