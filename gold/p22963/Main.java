package gold.p22963;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	// dp[i][j] : i까지 j번 증가했을 때 LIS 길이
	// static int[][] dp = new int[200001][4];
	static int[] dp = new int[200001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		Arrays.fill(dp, 1);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] <= arr[i])
					dp[i] = Integer.max(dp[j] + 1, dp[i]);
			}
		}

		for (int i = 0; i < N; i++)
			System.out.println(dp[i]);
	}
}
