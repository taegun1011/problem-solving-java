package gold.p6987;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static boolean np(int[] arr) {
		int N = arr.length;
		for (int i = N - 1; i > 0; i--) {
			if (arr[i - 1] < arr[i]) {
				for (int j = N - 1; j >= i; j--) {
					if (arr[i - 1] < arr[j]) {
						swap(arr, i - 1, j);
						break;
					}
				}
				for (int s = i, e = N - 1; s < e; s++, e--)
					swap(arr, s, e);
				return true;
			}
		}

		return false;
	}

	static boolean recursion(int N, int[][] match, int[][] score) {
		if (N == 6) {
			int[][] temp = new int[6][3];
			for (int i = 0; i < 6; i++)
				temp[i] = Arrays.copyOf(score[i], score[i].length);
			return calc(match, temp);
		}

		int[] enemy = new int[6 - N - 1];
		for (int i = 0; i < enemy.length; i++)
			enemy[i] = N + i + 1;

		boolean ret = false;

		do {
			match[N] = enemy;
			ret |= recursion(N + 1, match, score);
		} while (np(enemy));

		return ret;
	}

	static boolean calc(int[][] match, int[][] score) {
		for (int i = 0; i < 6; i++) {
			int win = score[i][0];
			int draw = score[i][1];
			int lose = score[i][2];
			int idx = 0;
			while (win-- > 0) {
				int enemy = match[i][idx++];
				score[enemy][2]--;
				if (score[enemy][2] < 0)
					return false;
			}
			while (draw-- > 0) {
				int enemy = match[i][idx++];
				score[enemy][1]--;
				if (score[enemy][1] < 0)
					return false;
			}
			while (lose-- > 0) {
				int enemy = match[i][idx++];
				score[enemy][0]--;
				if (score[enemy][0] < 0)
					return false;
			}
		}

		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < 4; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			boolean flag = true;

			int[][] arr = new int[6][3];
			for (int i = 0; i < 6; i++) {
				int sum = 0;
				for (int j = 0; j < 3; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] > 5)
						flag = false;
					sum += arr[i][j];
				}
				if (sum != 5) {
					flag = false;
					break;
				}
			}

			if (!flag) {
				sb.append(0 + " ");
				continue;
			}

			sb.append(recursion(0, new int[6][], arr) ? 1 : 0).append(' ');
		}
		System.out.println(sb);
	}
}
