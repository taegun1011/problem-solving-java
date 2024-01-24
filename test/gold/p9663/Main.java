package gold.p9663;

import java.util.Scanner;

public class Main {
	static int N;

	public static int placeQueen(int cr, int[] y_arr) {
		if (cr > N) {
			return 1;
		}

		int ret = 0;

		for (int cc = 1; cc <= N; cc++) {
			boolean flag = true;
			for (int i = 0; i < cr - 1; i++) {
				int pr = i + 1;
				int pc = y_arr[i];

				if (pc == cc || pr - pc == cr - cc || pr + pc == cr + cc) {
					flag = false;
					continue;
				}

			}
			if (flag) {
				y_arr[cr - 1] = cc;
				ret += placeQueen(cr + 1, y_arr);
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		int[] y_arr = new int[N];

		System.out.println(placeQueen(1, y_arr));

	}
}
