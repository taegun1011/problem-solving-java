package gold.p7579;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] m = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			m[i] = Integer.parseInt(st.nextToken());

		int[] c = new int[N];
		st = new StringTokenizer(br.readLine());

		int sum = 0;
		for (int i = 0; i < N; i++) {
			c[i] = Integer.parseInt(st.nextToken());
			sum += c[i];
		}

		int[] dp = new int[10001];
		for (int i = 0; i < N; i++)
			for (int j = sum; j >= c[i]; j--)
				dp[j] = Integer.max(dp[j], dp[j - c[i]] + m[i]);

		int ans = 0;
		for (; ans < sum; ans++) {
			if (dp[ans] >= M)
				break;
		}
		System.out.println(ans);
	}
}
