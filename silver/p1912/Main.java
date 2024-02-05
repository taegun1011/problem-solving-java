package silver.p1912;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[100001];
		int ans = arr[1];
		for (int i = 1; i <= N; i++) {
			dp[i] = Integer.max(dp[i - 1] + arr[i], arr[i]);
			ans = Integer.max(dp[i], ans);
		}

		System.out.println(ans);
	}
}
