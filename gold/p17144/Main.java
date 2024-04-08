package gold.p17144;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
				}
			}
		}

		while (T-- > 0) {
			spread();

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
		int cr = u, cc = 1;

		int last = 0;
		// 위쪽
		while (cc < C)
			last = cover(cr, cc++, last);

		cr--;
		cc = C - 1;
		while (cr >= 0)
			last = cover(cr--, cc, last);

		cr = 0;
		cc = C - 2;
		while (cc >= 0)
			last = cover(cr, cc--, last);

		cr = 1;
		cc = 0;
		while (cr < u)
			last = cover(cr++, cc, last);

		map[u][0] = -1;

		// 아래쪽
		cr = d;
		cc = 1;

		last = 0;
		while (cc < C)
			last = cover(cr, cc++, last);

		cr++;
		cc = C - 1;
		while (cr < R)
			last = cover(cr++, cc, last);

		cr = R - 1;
		cc--;
		while (cc >= 0)
			last = cover(cr, cc--, last);

		cr--;
		cc = 0;
		while (cr > d)
			last = cover(cr--, cc, last);

		map[d][0] = -1;
	}

	private static int cover(int cr, int cc, int last) {
		int temp = map[cr][cc];
		map[cr][cc] = last;
		return temp;
	}

	private static void spread() {
		int[][] newMap = new int[R][C];

		for (int cr = 0; cr < R; cr++) {
			for (int cc = 0; cc < C; cc++) {
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
		}

		map = newMap;
	}
}
