package gold.p17472;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	private static int N, M;

	static class Pair {
		int no;
		int pos;

		public Pair(int no, int pos) {
			super();
			this.no = no;
			this.pos = pos;
		}
	}

	static class Edge {
		int u;
		int v;
		int w;

		public Edge(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}

	static final int INF = 1000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] graph = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++)
				graph[i][j] = str.charAt(2 * j) - '0';
		}

		// DFS로 섬 찾기
		int[][] island = new int[N][M];

		int nextNo = 1;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (graph[i][j] == 1 && island[i][j] == 0)
					dfs(graph, island, i, j, nextNo++);

		// 브루트포스로 다리 길이 찾기
		int[][] dist = new int[nextNo][nextNo];
		for (int i = 0; i < nextNo; i++)
			Arrays.fill(dist[i], INF);

		// 가로방향 다리
		for (int i = 0; i < N; i++) {
			Stack<Pair> S = new Stack<>();
			for (int j = 0; j < M; j++) {
				if (island[i][j] != 0) {
					if (!S.isEmpty()) {
						Pair cur = S.pop();
						// top과 같으면
						if (island[i][j] != cur.no) {
							int diff = j - cur.pos - 1;
							if (diff != 1) {
								dist[cur.no][island[i][j]] = Integer.min(dist[cur.no][island[i][j]], diff);
								dist[island[i][j]][cur.no] = Integer.min(dist[island[i][j]][cur.no], diff);
							}
						}
					}
					S.push(new Pair(island[i][j], j));
				}
			}
		}

		// 세로방향 다리
		for (int i = 0; i < M; i++) {
			Stack<Pair> S = new Stack<>();
			for (int j = 0; j < N; j++) {
				if (island[j][i] != 0) {
					if (!S.isEmpty()) {
						Pair cur = S.pop();
						if (island[j][i] != cur.no) {
							int diff = j - cur.pos - 1;
							if (diff != 1) {
								dist[cur.no][island[j][i]] = Integer.min(dist[cur.no][island[j][i]], diff);
								dist[island[j][i]][cur.no] = Integer.min(dist[island[j][i]][cur.no], diff);
							}
						}
					}
					S.push(new Pair(island[j][i], j));
				}
			}
		}

		// 크루스칼로 최단거리 찾기
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		for (int i = 1; i < dist.length; i++)
			for (int j = i + 1; j < dist.length; j++)
				if (dist[i][j] != INF && dist[i][j] > 1)
					pq.add(new Edge(i, j, dist[i][j]));

		int[] p = new int[dist.length];

		make_set(p);

		int sum = 0;
		int cnt = 0;
		int V = dist.length - 1;
		while (!pq.isEmpty() && cnt < V - 1) {
			Edge cur = pq.poll();
			if (find(cur.u, p) == find(cur.v, p))
				continue;
			cnt++;
			sum += cur.w;
			union(cur.u, cur.v, p);
		}

		System.out.println(cnt == V - 1 ? sum : -1);
	}

	private static void union(int u, int v, int[] p) {
		u = find(u, p);
		v = find(v, p);
		p[v] = u;
	}

	private static int find(int x, int[] p) {
		if (x == p[x])
			return x;
		return p[x] = find(p[x], p);
	}

	private static void make_set(int[] p) {
		for (int i = 0; i < p.length; i++)
			p[i] = i;
	}

	private static void dfs(int[][] graph, int[][] island, int cr, int cc, int no) {
		island[cr][cc] = no;
		for (int i = 0; i < 4; i++) {
			int nr = cr + dir[i][0];
			int nc = cc + dir[i][1];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				continue;
			if (graph[nr][nc] == 1 && island[nr][nc] == 0) {
				dfs(graph, island, nr, nc, no);
			}
		}
	}
}
