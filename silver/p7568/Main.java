package silver.p7568;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] w_arr = new int[N];
		int[] h_arr = new int[N];
		int[] rank = new int[N];

		for (int i = 0; i < N; i++) {
			w_arr[i] = sc.nextInt();
			h_arr[i] = sc.nextInt();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (w_arr[i] < w_arr[j] && h_arr[i] < h_arr[j]) {
					rank[i]++;
				}

			}
		}
		for (int r : rank)
			System.out.println(r + 1);
	}
}
