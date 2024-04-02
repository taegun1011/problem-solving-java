package gold.p27435;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	final static int MOD = 998244353;

	public static void main(String[] args) throws Exception {
		int[][] matrix = { { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 1, 0 } };
		int[] a = { 2, 2, 1, 1, 1 };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (TC-- > 0) {
			long N = Long.parseLong(br.readLine());

			long ans = 0;
			if (N <= 5)
				ans = a[5 - (int) N];
			else {
				int[][] res = pow(matrix, N - 5);
				for (int i = 0; i < 5; i++) {
					ans = ans + a[i] * res[0][i];
					ans %= MOD;
				}
			}

			sb.append(ans).append('\n');
		}

		System.out.println(sb);
	}

	private static int[][] pow(int[][] m, long p) {
		int[][] ret = identity();

		while (p > 0) {
			if (p % 2 == 1)
				ret = mul(ret, m);
			m = mul(m, m);
			p /= 2;
		}

		return ret;
	}

	private static int[][] mul(int[][] a, int[][] b) {
		int[][] c = new int[5][5];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					c[i][j] += (1l * a[i][k] * b[k][j]) % MOD;
					c[i][j] %= MOD;
				}
			}
		}

		return c;
	}

	private static int[][] identity() {
		return new int[][] { { 1, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 1 } };
	}
}
