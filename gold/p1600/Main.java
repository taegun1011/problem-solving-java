package gold.p1600;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] dir = { { -1, 0 }, { -2, 1 }, { -1, 2 }, { 0, 1 }, { 1, 2 }, { 2, 1 }, { 1, 0 }, { 2, -1 },
			{ 1, -2 }, { 0, -1 }, { -1, -2 }, { -2, -1 } };

	static class Tuple {
		int f;
		int r;
		int c;

		public Tuple(int f, int r, int c) {
			this.f = f;
			this.r = r;
			this.c = c;
		}

	}

	static final int INF = (int) 1e9;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++)
				map[i][j] = input.charAt(j * 2) - '0';
		}

		int[][][] dist = new int[K + 1][N][M];
		for (int i = 0; i <= K; i++)
			for (int j = 0; j < N; j++)
				Arrays.fill(dist[i][j], INF);
		dist[K][0][0] = 0;

		Queue<Tuple> Q = new ArrayDeque<>();
		Q.add(new Tuple(K, 0, 0));

		while (!Q.isEmpty()) {
			Tuple cur = Q.poll();
			int cf = cur.f, cr = cur.r, cc = cur.c;

			int inc = cf == 0 ? 3 : 1;
			for (int i = 0; i < 12; i += inc) {
				int nr = cr + dir[i][0];
				int nc = cc + dir[i][1];
				int nf = i % 3 == 0 ? cf : cf - 1;
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 1)
					continue;
				if (dist[nf][nr][nc] > dist[cf][cr][cc] + 1) {
					dist[nf][nr][nc] = dist[cf][cr][cc] + 1;

					Q.add(new Tuple(nf, nr, nc));
				}
			}
		}

		int min = INF;
		for (int i = K; i >= 0; i--)
			min = Integer.min(min, dist[i][N - 1][M - 1]);
		System.out.println(min == INF ? -1 : min);
	}
}
