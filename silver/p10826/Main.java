package silver.p10826;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		System.out.println(fibo(N));
	}

	private static BigInteger fibo(int n) {
		if (n == 0)
			return BigInteger.ZERO;

		BigInteger f1 = BigInteger.ZERO, f2 = BigInteger.ONE;
		for (int i = 1; i < n; i++) {
			BigInteger temp = f1.add(f2);
			f1 = f2;
			f2 = temp;
		}
		return f2;
	}
}