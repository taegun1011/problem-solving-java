package bronze.p2747;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		System.out.println(fibo(N));
	}

	private static int fibo(int n) {
		int f1 = 0, f2 = 1;
		for (int i = 1; i < n; i++) {
			int temp = f1 + f2;
			f1 = f2;
			f2 = temp;
		}
		return f2;
	}
}
