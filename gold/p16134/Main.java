package gold.p16134;

import java.util.Scanner;

public class Main {
	static long divisor = 1000000007;

	static long perm(int A, int B) {
		long ret = 1;
		for (int i = 0; i < B; i++)
			ret = ret * (A - i) % divisor;

		return ret;
	}

	static long pow(long a, long n) {
		long ret = 1;
		while (n > 0) {
			if (n % 2 == 1)
				ret = ret * a % divisor;
			a = a * a % divisor;
			n /= 2;
		}

		return ret;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int R = sc.nextInt();

		// nPr
		long nPr = perm(N, R);
		// rPr
		long rPr = perm(R, R);
		// nPr * rPr^-1 % divisor
		System.out.println(nPr * pow(rPr, divisor - 2) % divisor);
	}
}
