package gold.p2749;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	final static int MOD = 1000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long N = Long.parseLong(br.readLine());
		int[][] matrix = { { 1, 1 }, { 1, 0 } };

		System.out.println(pow(matrix, N - 1)[0][0]);
	}

	private static int[][] pow(int[][] m, long l) {
		int[][] ret = identity();

		while (l > 0) {
			if (l % 2 == 1)
				ret = mul(ret, m);
			m = mul(m, m);
			l >>= 1;
		}

		return ret;
	}

	private static int[][] identity() {
		int[][] ret = { { 1, 0 }, { 0, 1 } };
		return ret;
	}

	private static int[][] mul(int[][] a, int[][] b) {
		int[][] c = new int[2][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					c[i][j] += (1l * a[i][k] * b[k][j]) % MOD;
					c[i][j] %= MOD;
				}
			}
		}

		return c;
	}
}
