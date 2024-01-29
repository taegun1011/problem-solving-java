package gold.p1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int r, c;

	public Pair(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	// graph[i][j] : i번 파티에 j번 사람이 참석
	static boolean[][] graph;

	// pVisited[i] : start에서 출발해 i번 파티에 도달할 수 있다
	static boolean[] pVisited;

	static int N, M;

	static int[] dir = { -1, 1 };

	public static void BFS(int sr, int sc, boolean[][] visited) {

		Queue<Pair> Q = new LinkedList<>();
		visited[sr][sc] = true;
		pVisited[sr] = false;
		Q.add(new Pair(sr, sc));

		while (!Q.isEmpty()) {
			int cr = Q.peek().r;
			int cc = Q.peek().c;
			Q.poll();

			// 위아래
			for (int i = 0; i < M; i++) {
				int nr = i;
				if (nr < 0 || nr >= M || !graph[nr][cc] || visited[nr][cc])
					continue;
				visited[nr][cc] = true;
				pVisited[nr] = false;
				Q.add(new Pair(nr, cc));
			}

			// 좌우
			for (int i = 0; i < N; i++) {
				int nc = i;
				if (!graph[cr][nc] || visited[cr][nc])
					continue;
				visited[cr][nc] = true;
				pVisited[cr] = false;
				Q.add(new Pair(cr, nc));
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new boolean[M][N];
		pVisited = new boolean[M];
		Arrays.fill(pVisited, true);

		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());

		int[] key = new int[K];
		for (int i = 0; i < K; i++) {
			key[i] = Integer.parseInt(st.nextToken()) - 1;
		}

		for (int party = 0; party < M; party++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			for (int j = 0; j < K; j++) {
				int person = Integer.parseInt(st.nextToken()) - 1;
				graph[party][person] = true;
			}
		}

		for (int k : key) {
			for (int i = 0; i < M; i++) {
				if (graph[i][k])
					BFS(i, k, new boolean[M][N]);
			}
		}

		int cnt = 0;
		for (boolean b : pVisited) {
			if (b)
				cnt++;
		}

		System.out.println(cnt);
	}
}
