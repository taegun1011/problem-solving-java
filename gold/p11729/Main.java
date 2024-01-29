package gold.p11729;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void recursion(int n, int from, int to) {
		if (n == 1) {
			sb.append(from + " " + to + '\n');
			return;
		}
		int left = 6 - from - to;
		recursion(n - 1, from, left);
		recursion(1, from, to);
		recursion(n - 1, left, to);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		System.out.println((int) Math.pow(2, N) - 1);

		recursion(N, 1, 3);
		System.out.println(sb);
	}
}
