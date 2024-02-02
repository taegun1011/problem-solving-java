package platinum.p5719;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
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

	static int dijkstra(int start, int end, boolean[][] used, List<Pair>[] Edges) {
		int V = used.length;
		int[] dist = new int[V];
		int[] path = new int[V];
		Arrays.fill(dist, -1);

		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
		pq.add(new Pair(start, (dist[start] = 0)));

		while (!pq.isEmpty()) {
			Pair cur = pq.poll();

			if (dist[cur.v] != -1 && dist[cur.v] < cur.w)
				continue;

			if (cur.v == end)
				break;

			for (Pair nxt : Edges[cur.v]) {
				if (used[cur.v][nxt.v])
					continue;

				if (dist[nxt.v] == -1 || dist[nxt.v] > dist[cur.v] + nxt.w) {
					path[nxt.v] = cur.v;
					dist[nxt.v] = dist[cur.v] + nxt.w;
					pq.add(new Pair(nxt.v, dist[nxt.v]));
				}
			}
		}

		int cur = end;
		while (cur != start) {
			int prv = path[cur];
			System.out.println(prv + " " + cur);
			used[prv][cur] = true;
			cur = prv;
		}

		return dist[end];
	}

	static int solve(int V, int E) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		List<Pair>[] Edges = new List[V];
		boolean[][] used = new boolean[V][V];

		for (int i = 0; i < Edges.length; i++)
			Edges[i] = new LinkedList<>();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			Edges[u].add(new Pair(v, w));
		}

		int res1 = dijkstra(S, D, used, Edges);
		int res;
		while ((res = dijkstra(S, D, used, Edges)) == res1)
			;

		return res;
	}

	public static void main(String[] args) throws Exception {
		// 처음에 최단거리를 구한다
		// 최단거리를 역추적하면서 사용한 간선들을 비활성화한다
		// 아직도 동일한 거리가 나오면 반복한다
		// 동일하지 않으면 그 거리를 출력

		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		while (N != 0 && M != 0) {
			sb.append(solve(N, M)).append('\n');

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		}

		System.out.println(sb);
	}
}
