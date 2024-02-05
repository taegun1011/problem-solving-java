package platinum.p5719;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int v;
	int w;

	public Pair(int v, int w) {
		this.v = v;
		this.w = w;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// 최단 경로가 여러개 존재할 경우
	static List<Integer>[] path;
	static boolean[][] used;

	static int[] dijkstra(int start, int end, List<Pair>[] Edges, boolean first) {
		int V = used.length;
		int[] dist = new int[V];
		Arrays.fill(dist, -1);

		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
		pq.add(new Pair(start, (dist[start] = 0)));

		while (!pq.isEmpty()) {
			Pair cur = pq.poll();

			if (dist[cur.v] != -1 && dist[cur.v] < cur.w)
				continue;

			for (Pair nxt : Edges[cur.v]) {
				if (used[cur.v][nxt.v])
					continue;

				// 같은 값은 모두 출력하는 실버 문제처럼
				// 새로운 값이면 갱신, 같은 값이면 추가
				if (dist[nxt.v] == -1 || dist[nxt.v] > dist[cur.v] + nxt.w) {
					if (first) {

						path[nxt.v].clear();
						path[nxt.v].add(cur.v);
					}

					dist[nxt.v] = dist[cur.v] + nxt.w;
					pq.add(new Pair(nxt.v, dist[nxt.v]));
				}

				else if (first && dist[nxt.v] == dist[cur.v] + nxt.w)
					path[nxt.v].add(cur.v);
			}
		}

		return dist;
	}

	// 최단경로 그래프만 순회하는 건 오래 걸리지 않는다
	static void check(int start, int end) {
		Queue<Integer> Q = new ArrayDeque<>();
		Q.add(start);

		while (!Q.isEmpty()) {
			int cur = Q.poll();

			for (int nxt : path[cur]) {

				// 같은 정점이 아닌 같은 간선을 방문했는 지 체크하자
				if (used[nxt][cur])
					continue;
				used[nxt][cur] = true;
				Q.add(nxt);
			}
		}
	}

	static int solve(int V, int E) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		List<Pair>[] Edges = new List[V];
		used = new boolean[V][V];

		for (int i = 0; i < Edges.length; i++)
			Edges[i] = new LinkedList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			Edges[u].add(new Pair(v, w));
		}

		int[] dist = dijkstra(S, D, Edges, true);
		check(D, S);
		dist = dijkstra(S, D, Edges, false);

		return dist[D];
	}

	public static void main(String[] args) throws Exception {

		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		while (N != 0 && M != 0)

		{
			path = new List[N];
			for (int i = 0; i < N; i++)
				path[i] = new LinkedList<>();
			used = new boolean[N][N];
			sb.append(solve(N, M)).append('\n');

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		}

		System.out.println(sb);
	}
}
