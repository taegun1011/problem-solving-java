package silver.p10974;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void recursion(int cur, int max, int[] arr, int field) {
		if (cur == max) {
			for (int i : arr)
				sb.append(i + " ");
			sb.append('\n');
			return;
		}

		int[] temp = Arrays.copyOf(arr, max);

		for (int i = 1; i <= max; i++) {
			if ((field & (1 << i)) != 0)
				continue;
			temp[cur] = i;
			recursion(cur + 1, max, temp, field | (1 << i));
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		recursion(0, N, new int[N], 0);
		System.out.println(sb);
	}
}
