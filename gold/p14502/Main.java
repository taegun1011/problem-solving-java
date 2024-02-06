package gold.p14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int BFS(int[][] g, Queue<Pair> virus) {
		int N = g.length;
		int M = g[0].length;

		int[][] graph = new int[N][M];
		for (int i = 0; i < N; i++)
			graph[i] = Arrays.copyOf(g[i], M);
		Queue<Pair> Q = new ArrayDeque<>();
		Q.addAll(virus);

		while (!Q.isEmpty()) {
			Pair cur = Q.poll();
			int cr = cur.r;
			int cc = cur.c;

			for (int i = 0; i < 4; i++) {
				int nr = cr + dir[i][0];
				int nc = cc + dir[i][1];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (graph[nr][nc] == 0) {
					graph[nr][nc] = 2;
					Q.add(new Pair(nr, nc));
				}
			}
		}

		int cnt = 0;

		for (int[] row : graph)
			for (int x : row)
				cnt += (x == 0) ? 1 : 0;

		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] graph = new int[N][M];
		Queue<Pair> virus = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] == 2)
					virus.add(new Pair(i, j));
			}
		}

		int size = N * M;
		int max = 0;

		for (int i = 0; i < size; i++) {
			if (graph[i / M][i % M] != 0)
				continue;
			graph[i / M][i % M] = 1;
			for (int j = i + 1; j < size; j++) {
				if (graph[j / M][j % M] != 0)
					continue;
				graph[j / M][j % M] = 1;
				for (int k = j + 1; k < size; k++) {
					if (graph[k / M][k % M] != 0)
						continue;
					graph[k / M][k % M] = 1;
					max = Integer.max(BFS(graph, virus), max);
					graph[k / M][k % M] = 0;
				}
				graph[j / M][j % M] = 0;
			}
			graph[i / M][i % M] = 0;
		}

		System.out.println(max);
	}
}
