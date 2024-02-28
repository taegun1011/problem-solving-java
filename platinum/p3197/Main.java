package platinum.p3197;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static int M;
	static int lr1 = -1, lc1 = -1, lr2 = -1, lc2 = -1;
	private static char[][] map;

	static class Pair {
		int r;
		int c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private static void floodFill(int sr, int sc, boolean[][] visited) {
		visited[sr][sc] = true;
		Queue<Pair> Q = new ArrayDeque<>();
		Q.add(new Pair(sr, sc));

		while (!Q.isEmpty()) {
			Pair cur = Q.poll();
			int cr = cur.r, cc = cur.c;
			for (int i = 0; i < 4; i++) {
				int nr = cr + dir[i][0];
				int nc = cc + dir[i][1];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (visited[nr][nc])
					continue;
				if (map[nr][nc] != 'X') {
					visited[nr][nc] = true;
					Q.add(new Pair(nr, nc));
				}
			}
		}
	}

	static int[] p;

	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static boolean check(int[][] dist, int limit) {
		Queue<Pair> Q = new ArrayDeque<>();
		Q.add(new Pair(lr1, lc1));

		boolean[][] visited = new boolean[N][M];
		visited[lr1][lc1] = true;

		while (!Q.isEmpty()) {
			Pair cur = Q.poll();

			int cr = cur.r, cc = cur.c, cd = dist[cr][cc];
			if (cr == lr2 && cc == lc2)
				return true;

			if (cd > limit)
				continue;

			for (int i = 0; i < 4; i++) {
				int nr = cr + dir[i][0];
				int nc = cc + dir[i][1];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (visited[nr][nc])
					continue;

				visited[nr][nc] = true;
				Q.add(new Pair(nr, nc));
			}
		}

		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		p = new int[N * M];
		for (int i = 0; i < p.length; i++)
			p[i] = i;

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				if ((map[i][j] = input.charAt(j)) == 'L' && lr1 == -1) {
					lr1 = i;
					lc1 = j;
				} else if (map[i][j] == 'L' && lr2 == -1) {
					lr2 = i;
					lc2 = j;
				}
			}
		}

		Queue<Pair> Q = new ArrayDeque<>();

		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 'X' && !visited[i][j]) {
					Q.add(new Pair(i, j));
					floodFill(i, j, visited);
				}
			}
		}

		int[][] dist = getDistance(Q);

		// NNNNYYYY
		int s = 0, e = 1500, m;
		while (s <= e) {
			m = (s + e) / 2;
			if (check(dist, m))
				e = m - 1;
			else
				s = m + 1;
		}

		System.out.println(s);
	}

	private static int[][] getDistance(Queue<Pair> Root) {
		int[][] dist = new int[N][M];
		for (int i = 0; i < N; i++)
			Arrays.fill(dist[i], (int) 1e9);

		Queue<Pair> Q = new ArrayDeque<>();
		Q.addAll(Root);

		for (Pair root : Q)
			dist[root.r][root.c] = 0;

		while (!Q.isEmpty()) {
			Pair cur = Q.poll();

			int cr = cur.r, cc = cur.c, cd = dist[cr][cc];

			for (int i = 0; i < 4; i++) {
				int nr = cr + dir[i][0];
				int nc = cc + dir[i][1];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if (map[nr][nc] != 'X') {
					if (dist[nr][nc] <= cd)
						continue;
					dist[nr][nc] = cd;
				} else {
					if (dist[nr][nc] <= cd + 1)
						continue;
					dist[nr][nc] = cd + 1;
				}

				Q.add(new Pair(nr, nc));
			}
		}

		return dist;
	}
}
