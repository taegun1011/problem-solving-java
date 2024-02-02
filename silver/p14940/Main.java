package silver.p14940;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int r;
	int c;

	public Pair(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		int[][] dist = new int[N][M];
		int sr = 0, sc = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					sr = i;
					sc = j;
				}
			}
		}

		Queue<Pair> Q = new LinkedList<>();
		Q.add(new Pair(sr, sc));

		while (!Q.isEmpty()) {
			Pair p = Q.poll();
			int cr = p.r;
			int cc = p.c;

			for (int i = 0; i < 4; i++) {
				int nr = cr + dir[i][0];
				int nc = cc + dir[i][1];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if (map[nr][nc] != 1 || dist[nr][nc] != 0)
					continue;

				dist[nr][nc] = dist[cr][cc] + 1;
				Q.add(new Pair(nr, nc));
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist[i].length; j++) {
				sb.append(map[i][j] != 1 ? 0 : (dist[i][j] == 0 ? -1 : dist[i][j])).append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}
}
