package silver.p1012;

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
	static boolean[][] graph;
	static boolean[][] visited;
	static int N, M;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static void BFS(int sr, int sc) {
		Queue<Pair> Q = new LinkedList<>();
		visited[sr][sc] = true;
		Q.add(new Pair(sr, sc));

		while (!Q.isEmpty()) {
			Pair p = Q.poll();
			int cr = p.r, cc = p.c;

			for (int i = 0; i < 4; i++) {
				int nr = cr + dir[i][0], nc = cc + dir[i][1];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if (!visited[nr][nc] && graph[nr][nc]) {
					Q.add(new Pair(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			graph = new boolean[N][M];
			visited = new boolean[N][M];

			while (K-- > 0) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				graph[Y][X] = true;
			}

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (graph[i][j] && !visited[i][j]) {
						cnt++;
						BFS(i, j);
					}
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}
}
