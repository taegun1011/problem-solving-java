package gold.p2086;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	final static int MOD = 1000000000;

	static long[][] matrix = { { 2, 0, -1 }, { 1, 0, 0 }, { 0, 1, 0 } };

	static long[][] identity() {
		return new long[][] { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
	}

	static int[] a = { 4, 2, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken()), B = Long.parseLong(st.nextToken());

		System.out.println((sum(B) - sum(A - 1) + MOD) % MOD);
	}

	private static long sum(long N) {
		if (N <= 0)
			return 0;
		if (N <= 3)
			return a[3 - (int) N];

		long[][] result = pow(matrix, N - 3);

		long ret = 0;
		for (int i = 0; i < 3; i++) {
			ret += result[0][i] * a[i];
			ret = (ret + MOD) % MOD;
		}

		return ret;
	}

	private static long[][] pow(long[][] matrix, long l) {
		if (l == 0)
			return identity();

		if (l % 2 == 1)
			return mul(pow(matrix, l - 1), matrix);
		long[][] res = pow(matrix, l / 2);
		return mul(res, res);
	}

	private static long[][] mul(long[][] a, long[][] b) {
		long[][] c = new long[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					c[i][j] += a[i][k] * b[k][j];
					c[i][j] = (c[i][j] + MOD) % MOD;
				}
			}
		}

		return c;
	}
}
