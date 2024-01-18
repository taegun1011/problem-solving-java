package silver.p1914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();

//	public static void power_of_2(int n) {
//		int[] arr = new int[100];
//		arr[0] = 1;
//		int first_digit = 0;
//
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j <= first_digit; j++) {
//				arr[j] *= 2;
//			}
//
//			for (int j = 0; j <= first_digit; j++) {
//				arr[j + 1] += arr[j] / 10;
//				arr[j] %= 10;
//			}
//			if (arr[first_digit + 1] > 0)
//				first_digit++;
//		}
//
//		arr[0] -= 1;
//		for (int i = first_digit; i >= 0; i--)
//			System.out.print(arr[i]);
//		System.out.println();
//	}

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

//		power_of_2(N);

		if (N <= 20) {
			recursion(N, 1, 3);
			System.out.println(sb);
		}
	}
}
