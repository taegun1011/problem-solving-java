package silver.p1780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int[] cnt = new int[] { 0, 0, 0 }; // -1, 0, 1;

	public static void recursion(int sr, int sc, int size) {
		int first_color = arr[sr][sc];
		if (size == 1) {
			cnt[first_color + 1]++;
			return;
		}

		for (int i = sr; i < sr + size; i++) {
			for (int j = sc; j < sc + size; j++) {
				if (arr[i][j] != first_color) {
					size /= 3;
					for (int ii = 0; ii < 3; ii++) {
						for (int jj = 0; jj < 3; jj++) {
							recursion(sr + ii * size, sc + jj * size, size);
						}
					}
					return;
				}
			}
		}

		cnt[first_color + 1]++;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		recursion(0, 0, n);

		for (int i : cnt) {
			System.out.println(i);
		}
	}
}
