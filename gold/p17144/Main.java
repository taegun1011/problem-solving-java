package gold.p17144;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static class Pair {
		int r;
		int c;

		public Pair(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	private static int[][] map;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int R, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		ArrayList<Pair> AL = new ArrayList<>();

		int u = 0, d = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					if (u == 0) {
						u = i;
						d = i + 1;
					}
				} else if (map[i][j] > 0)
					AL.add(new Pair(i, j));
			}
		}

		while (T-- > 0) {
			AL = spread(AL);

			move(u, d);
		}

		int sum = 0;
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				if (map[i][j] > 0)
					sum += map[i][j];

		System.out.println(sum);
	}

	private static void move(int u, int d) {
		int cr = u, cc = 0;
		int nr = cr, nc = cc;
		int last = 0;
		while (cr != u || cc != 0) {
			if (cr != 0 && cc != C - 1) {
				cc++;
			} else if (cr != 0 && cc == C - 1) {
				cr--;
			} else if (cr == 0 && cc != 0) {
				cc--;
			} else {
				cr++;
			}

			int temp = map[cr][cc];
			map[cr][cc]
		}
	}

	private static ArrayList<Pair> spread(ArrayList<Pair> AL) {
		int[][] newMap = new int[R][C];

		for (Pair p : AL) {
			int cr = p.r, cc = p.c;
			int amount = map[cr][cc] / 5;
			for (int i = 0; i < 4; i++) {
				int nr = cr + dir[i][0];
				int nc = cc + dir[i][1];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == -1)
					continue;
				newMap[nr][nc] += amount;
				map[cr][cc] -= amount;
			}
			newMap[cr][cc] += map[cr][cc];
		}

		map = newMap;

		ArrayList<Pair> newAL = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0)
					newAL.add(new Pair(i, j));
			}
		}
		return newAL;
	}
}
