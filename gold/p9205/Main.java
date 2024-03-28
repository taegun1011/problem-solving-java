package gold.p9205;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (tc-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] x = new int[N + 2], y = new int[N + 2];
			for (int i = 0; i < N + 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				x[i] = Integer.parseInt(st.nextToken());
				y[i] = Integer.parseInt(st.nextToken());
			}

			boolean[][] connected = new boolean[N + 2][N + 2];
			for (int i = 0; i < N + 2; i++)
				for (int j = 0; j < N + 2; j++)
					connected[i][j] = isLowerThan1000(x[i], y[i], x[j], y[j]);

			for (int k = 0; k < N + 2; k++)
				for (int i = 0; i < N + 2; i++)
					for (int j = 0; j < N + 2; j++)
						connected[i][j] |= (connected[i][k] && connected[k][j]);
			sb.append(connected[0][N + 1] ? "happy\n" : "sad\n");
		}
		System.out.println(sb);
	}

	private static boolean isLowerThan1000(int x1, int y1, int x2, int y2) {
		return Math.abs(x2 - x1) + Math.abs(y2 - y1) <= 1000;
	}
}
