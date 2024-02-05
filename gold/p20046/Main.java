package gold.p20046;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Tuple {
	int r;
	int c;
	int w;

	public Tuple(int r, int c, int w) {
		this.r = r;
		this.c = c;
		this.w = w;
	}

}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] graph = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				graph[i][j] = Integer.parseInt(st.nextToken());
		}

		int[][] dist = new int[N][M];
		for (int[] row : dist)
			Arrays.fill(row, (int) 1e9);
		PriorityQueue<Tuple> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
		pq.add(new Tuple(0, 0, (dist[0][0] = graph[0][0])));
		if (dist[0][0] == -1) {
			System.out.println(-1);
			return;
		}

		while (!pq.isEmpty()) {
			Tuple cur = pq.poll();

			if (dist[cur.r][cur.c] < cur.w)
				continue;

			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dir[i][0];
				int nc = cur.c + dir[i][1];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || graph[nr][nc] == -1)
					continue;

				int nw = dist[cur.r][cur.c] + graph[nr][nc];
				if (dist[nr][nc] > nw) {
					dist[nr][nc] = nw;
					pq.add(new Tuple(nr, nc, nw));
				}
			}
		}

		System.out.println(dist[N - 1][M - 1] == (int) 1e9 ? -1 : dist[N - 1][M - 1]);
	}
}
