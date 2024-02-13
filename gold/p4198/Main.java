package gold.p4198;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int lb(int[] arr, int num) {
		int s = 0, e = arr.length - 1, mid;
		while (s <= e) {
			mid = (s + e) / 2;
			if (num <= arr[mid])
				e = mid - 1;
			else
				s = mid + 1;
		}

		return s;
	}

	public static void main(String[] args) throws Exception {
		// 각 위치에서의 LIS와 LDS 길이의 합
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(br.readLine());

		int ans = 0;
		for (int i = 0; i < arr.length; i++)
			ans = Integer.max(ans, LISLDS(arr, i, 1) + LISLDS(arr, i, 2) - 1);

		System.out.println(ans);
	}

	static int LISLDS(int[] a, int i, int type) {
		int[] arr = Arrays.copyOfRange(a, i, a.length);
		int N = arr.length;
		int[] dp = new int[N + 1];

		if (type == 1) {
			Arrays.fill(dp, (int) 1e9);
			dp[0] = a[i];
		} else {
			for (int j = 0; j < arr.length; j++)
				arr[j] *= -1;
			dp[0] = -a[i];
		}

		for (int j = 0; j < N; j++) {
			int idx = lb(dp, arr[j]);
			if (idx > 0)
				dp[idx] = arr[j];
		}

		for (int j = N; j >= 0; j--)
			if (dp[j] != (type == 1 ? (int) 1e9 : 0))
				return j + 1;

		return 1;
	}
}
