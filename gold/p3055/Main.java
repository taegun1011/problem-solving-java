package gold.p3055;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	final static int INF = (int) 1e9;

	static class Pair {
		int r;
		int c;

		public Pair(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	private static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		char[][] board = new char[R][C];
		int[][] dist = new int[R][C], water = new int[R][C];
		for (int i = 0; i < R; i++) {
			Arrays.fill(dist[i], INF);
			Arrays.fill(water[i], INF);
		}

		Queue<Pair> Q1 = new ArrayDeque<>(), Q2 = new ArrayDeque<>();

		for (int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (board[i][j] == '*') {
					Q1.add(new Pair(i, j));
					water[i][j] = 0;
				} else if (board[i][j] == 'S') {
					Q2.add(new Pair(i, j));
					dist[i][j] = 0;
				}

			}
		}

		while (!Q1.isEmpty()) {
			Pair cur = Q1.poll();

			int cr = cur.r, cc = cur.c;
			for (int i = 0; i < 4; i++) {
				int nr = dir[i][0] + cr;
				int nc = dir[i][1] + cc;

				if (nr < 0 || nr >= R || nc < 0 || nc >= C)
					continue;
				if (board[nr][nc] == 'X' || board[nr][nc] == 'D')
					continue;
				if (water[nr][nc] > water[cr][cc] + 1) {
					water[nr][nc] = water[cr][cc] + 1;
					Q1.add(new Pair(nr, nc));
				}
			}
		}

		boolean found = false;
		while (!Q2.isEmpty()) {
			Pair cur = Q2.poll();

			int cr = cur.r, cc = cur.c;
			if (board[cr][cc] == 'D') {
				found = true;
				System.out.println(dist[cr][cc]);
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nr = dir[i][0] + cr;
				int nc = dir[i][1] + cc;

				if (nr < 0 || nr >= R || nc < 0 || nc >= C)
					continue;
				if (board[nr][nc] == 'X')
					continue;
				int nd = dist[cr][cc] + 1;
				if (water[nr][nc] <= nd)
					continue;

				if (dist[nr][nc] > nd) {
					dist[nr][nc] = nd;
					Q2.add(new Pair(nr, nc));
				}
			}
		}

		if (!found)
			System.out.println("KAKTUS");
	}
}
