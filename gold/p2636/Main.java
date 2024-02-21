package gold.p2636;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pair {
		int r;
		int c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static Queue<Pair> Q;
	static boolean[][] visited;

	static int N;
	static int M;

	static int[][] map;

	static void DFS(int cr, int cc) {
		visited[cr][cc] = true;

		for (int i = 0; i < 4; i++) {
			int nr = cr + dir[i][0];
			int nc = cc + dir[i][1];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				continue;
			if (visited[nr][nc])
				continue;
			if (map[nr][nc] == 1) {
				visited[nr][nc] = true;
				Q.add(new Pair(nr, nc));
			} else
				DFS(nr, nc);

		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		Q = new ArrayDeque<>();

		int time = 0;
		int amount = 0;
		int ans;
		do {
			time++;
			ans = amount;
			amount = 0;

			visited = new boolean[N][M];
			DFS(0, 0);

			while (!Q.isEmpty()) {
				Pair cur = Q.poll();
				map[cur.r][cur.c] = 0;
				amount++;
			}
		} while (amount != 0);
		System.out.println(time - 1);
		System.out.println(ans);
	}
}
