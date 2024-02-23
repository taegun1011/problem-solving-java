package gold.p17281;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[][] score;

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static boolean np(int[] arr) {
		int N = 9;
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

	static int calc(int[] hitter) {
		int order = 0;
		int ret = 0;

		for (int inning = 0; inning < score.length; inning++) {
			int out = 0;
			int base = 0; // 비트마스킹
			while (out < 3) {
				int result = score[inning][hitter[order]];
				if (result == 0)
					out++;
				else {
					base <<= result;
					base |= 1 << (result - 1);

					// 1111000
					for (int bit = 8; bit <= 64 && base >= bit; bit <<= 1)
						ret += (base & bit) / bit;
					base &= 0b111;
				}
				order = (order + 1) % 9;
			}
		}

		return ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		score = new int[N][9];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < 9; j++)
				score[i][j] = input.charAt(j * 2) - '0';
		}

		int[] arr = new int[9];
		for (int i = 0; i < 9; i++)
			arr[i] = i;

		// 1. 모든 순열을 만들고 arr[3] = 0인 경우만 처리하기 -> 시간 초과
		// 2. arr[3] = 0인 모든 순열을 만들기

		int ans = 0;
		do {
			if (arr[3] == 0)
				ans = Integer.max(ans, calc(arr));
		} while (np(arr));

		System.out.println(ans);
	}
}
