package gold.p17136;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static final int size = 10;
	static final int INF = (int) 1e9;
	static int[][] arr;
	static int[] left = { 5, 5, 5, 5, 5 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		arr = new int[size][size];
		for (int i = 0; i < size; i++) {
			String row = br.readLine();
			for (int j = 0; j < size; j++)
				arr[i][j] = row.charAt(j * 2) - '0';
		}

		int ans = recursion(0, 0);
		System.out.println(ans == INF ? -1 : ans);
	}

	private static int recursion(int cr, int cc) {

		int nr = cr, nc = cc;
		boolean found = false;
		for (; nr < size; nr++) {
			for (nc = 0; nc < size; nc++) {
				if (nr == cr && nc < cc)
					continue;
				if (arr[nr][nc] == 1) {
					found = true;
					break;
				}
			}
			if (found)
				break;
		}

		if (!found)
			return 0;

		int ret = INF;

		for (int p = 1; p <= 5; p++) {
			if (left[p - 1] == 0)
				continue;
			if (check(nr, nc, p)) {
				paste(nr, nc, p);
				ret = Integer.min(ret, recursion(nr, nc) + 1);
				remove(nr, nc, p);
			}
		}

		return ret;
	}

	private static boolean check(int sr, int sc, int len) {
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (sr + i >= size || sc + j >= size)
					return false;
				if (arr[sr + i][sc + j] == 0)
					return false;
			}
		}

		return true;
	}

	private static void paste(int sr, int sc, int len) {
		left[len - 1]--;
		for (int i = 0; i < len; i++)
			for (int j = 0; j < len; j++)
				arr[sr + i][sc + j] = 0;
	}

	private static void remove(int sr, int sc, int len) {
		left[len - 1]++;

		for (int i = 0; i < len; i++)
			for (int j = 0; j < len; j++) {
				if (sr + i >= size || sc + j >= size)
					continue;
				arr[sr + i][sc + j] = 1;
			}
	}
}
