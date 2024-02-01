package gold.p30407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, H, D, K;
	static int[] dmg;

	// 턴, 거리, 놀래키기 여부
	static int[][][] dp = new int[20][65][3];

	public static void main(String[] args) throws IOException {
		for (int[][] board : dp) {
			for (int[] row : board)
				Arrays.fill(row, -1);
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dmg = new int[N + 1];
		for (int i = 1; i <= N; i++)
			dmg[i] = Integer.parseInt(br.readLine());

	}
}