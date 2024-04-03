package gold.p14891;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] magnetic;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		magnetic = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++)
				magnetic[i][j] = str.charAt(j) - '0';
		}
		int K = Integer.parseInt(br.readLine());

		while (K-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()) - 1, dir = Integer.parseInt(st.nextToken());

			// i부터 좌우로 확인하면서
			select(i, dir);
		}

		int score = 0;
		for (int i = 0; i < 4; i++)
			score += magnetic[i][0] * (1 << i);

		System.out.println(score);
	}

	private static void select(int i, int dir) {
		// 0 : 선택 안됨, 1 : 시계방향으로, -1 : 반시계 방향으로
		int[] choice = new int[4];

		choice[i] = dir;

		int l = i, r = i;
		while (l >= 1 && magnetic[l][6] != magnetic[l - 1][2]) {
			choice[l - 1] = -choice[l];
			l--;
		}

		while (r <= 2 && magnetic[r][2] != magnetic[r + 1][6]) {
			choice[r + 1] = -choice[r];
			r++;
		}

		rotate(choice);
	}

	private static void rotate(int[] choice) {
		for (int i = 0; i < 4; i++) {
			int[] newMagnetic = new int[8];

			for (int j = 0; j < 8; j++)
				newMagnetic[j] = magnetic[i][(j - choice[i] + 8) % 8];

			magnetic[i] = Arrays.copyOf(newMagnetic, 8);
		}
	}
}
