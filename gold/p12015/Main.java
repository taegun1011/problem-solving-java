package gold.p12015;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int lb(int[] arr, int num) {
		int s = 0, e = arr.length - 1, mid;
		// NNNNNYYYYY
		while (s < e) {
			mid = (s + e) / 2;
			if (num > arr[mid])
				s = mid + 1;
			else
				e = mid;
		}

		return s;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int[] dp = new int[N + 1];
		Arrays.fill(dp, (int) 1e9);
		dp[0] = 0;

		int ans = 0;
		for (int i = 0; i < N; i++) {
			int idx = lb(dp, arr[i]);
			dp[idx] = arr[i];
			ans = Integer.max(ans, idx);
		}

		System.out.println(ans);
	}
}
