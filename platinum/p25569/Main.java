package platinum.p25569;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int divisor = 1000000007;

	static long[] fact;

	static long pow(long a, int n) {
		long ret = 1;
		while (n > 0) {
			if (n % 2 == 1)
				ret = ret * a % divisor;
			a = a * a % divisor;
			n /= 2;
		}

		return ret;
	}

	static long perm(int a, int b) {
		return fact[a] * pow(fact[a - b], divisor - 2) % divisor;
	}

	static long comb(int a, int b) {
		return perm(a, b) * pow(perm(b, b), divisor - 2) % divisor;
	}

	public static void main(String[] args) throws Exception {
		// for(int i=1;i<=min(a,b);i++) sum += (a C i * b C i)

		// Vandermonde's identity -> 위 식을 (a+b) C min - 1로 나타낼 수 있다.
		// 1을 빼주는 이유 -> i가 1부터 시작하므로 aC0*bC0은 제외

		fact = new long[600001];
		fact[0] = 1;
		for (int i = 1; i < fact.length; i++)
			fact[i] = fact[i - 1] * i % divisor;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long ans = 1;
		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int min = Integer.min(a, b);
			long sum = comb(a + b, min) - 1;
			ans = ans * sum % divisor;
		}

		System.out.println(ans);
	}
}
