package gold.p4485;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int[][] dist;
	static int N;

	static class Pair {
		int r;
		int c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static final int INF = (int) 1e9;

	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int BFS() {
		Queue<Pair> Q = new ArrayDeque<>();
		Q.add(new Pair(0, 0));

		dist[0][0] = arr[0][0];

		while (!Q.isEmpty()) {
			Pair cur = Q.poll();

			int cr = cur.r, cc = cur.c;

			for (int i = 0; i < 4; i++) {
				int nr = cr + dir[i][0];
				int nc = cc + dir[i][1];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;

				if (dist[nr][nc] > dist[cr][cc] + arr[nr][nc]) {
					dist[nr][nc] = dist[cr][cc] + arr[nr][nc];
					Q.add(new Pair(nr, nc));
				}
			}
		}

		return dist[N - 1][N - 1];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while ((N = Integer.parseInt(br.readLine())) > 0) {
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					arr[i][j] = Integer.parseInt(st.nextToken());
			}

			dist = new int[N][N];
			for (int i = 0; i < N; i++)
				Arrays.fill(dist[i], INF);

			sb.append(String.format("Problem %d: %d\n", tc++, BFS()));
		}
		System.out.println(sb);
	}
}
