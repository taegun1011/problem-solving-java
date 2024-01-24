package gold.p13302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int MAX = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N + 1][41];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], i == 0 ? 0 : MAX);
		}

		boolean[] absent = new boolean[N];

		if (M > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				absent[Integer.parseInt(st.nextToken()) - 1] = true;
			}
		}

		for (int i = 1; i <= N; i++) {

			for (int j = 0; j <= Integer.max(i / 3, i / 5 * 2); j++) {

				if (absent[i - 1])
					dp[i][j] = dp[i - 1][j];

				dp[i][j] = Integer.min(dp[i][j], dp[i - 1][j] + 10000);

				if (i >= 3 && j >= 1)
					dp[i][j] = Integer.min(dp[i][j], dp[i - 3][j - 1] + 25000);

				if (i >= 5 && j >= 2)
					dp[i][j] = Integer.min(dp[i][j], dp[i - 5][j - 2] + 37000);

				if (j <= 37 && dp[i - 1][j + 3] != 0)
					dp[i][j] = Integer.min(dp[i][j], dp[i - 1][j + 3]);
			}

		}

		int min = MAX;
		for (int x : dp[N]) {
			min = Integer.min(min, x);
		}
		System.out.println(min);
	}
}
