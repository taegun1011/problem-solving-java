package platinum.p1019;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static long[] cnt = new long[10];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		count(Integer.parseInt(br.readLine()));

		for (long x : cnt)
			System.out.print(x + " ");
	}

	private static void count(long n) {
		long divisor = 1;

		while (divisor <= n) {
			// 0은 따로 처리
			long tail = n / divisor % 10;

			if (divisor * 10 <= n) {
				if (tail == 0) {
					cnt[0] += (n / (divisor * 10) - 1) * divisor + (n % divisor + 1);
				} else
					cnt[0] += (n / (divisor * 10)) * divisor;
			}

			for (int i = 1; i <= 9; i++) {
				if (i < tail) {
					cnt[i] += (n / (divisor * 10) + 1) * divisor;
				} else if (i == tail) {
					cnt[i] += (n / (divisor * 10) * divisor + n % divisor + 1);
				} else {
					cnt[i] += (n / (divisor * 10)) * divisor;
				}
			}
			divisor *= 10;
		}
	}
}
