package gold.p21870;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	static int getGCD(int[] arr, int l, int size) {
		int ret = arr[l];
		for (int i = 1; i < size; i++)
			ret = gcd(ret, arr[l + i]);

		return ret;
	}

	static int recursion(int[] arr, int l, int size) {
		if (size == 1)
			return arr[l];

		int result1 = getGCD(arr, l, size / 2) + recursion(arr, l + size / 2, size - size / 2);
		int result2 = recursion(arr, l, size / 2) + getGCD(arr, l + size / 2, size - size / 2);

		return Integer.max(result1, result2);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < S.length; i++)
			S[i] = Integer.parseInt(st.nextToken());

		System.out.println(recursion(S, 0, N));
	}
}
