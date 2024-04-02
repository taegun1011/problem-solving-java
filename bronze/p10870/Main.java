package bronze.p10870;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		System.out.println(fibo(N));
	}

	static int fibo(int N) {
		if (N <= 1)
			return N;
		return fibo(N - 1) + fibo(N - 2);
	}
}
