package gold.p1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
	static int V, E;
	static List<Pair>[] AL;

	static void dijkstra(int start) {
		int[] dist = new int[V + 1];
		Arrays.fill(dist, (int) 1e9);
		dist[start] = 0;

		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
		for (Pair p : AL[start]) {
			// 간선 중복 제거
			dist[p.v] = Integer.min(p.w, dist[p.v]);
			pq.add(p);
		}

		while (!pq.isEmpty()) {
			Pair cur = pq.poll();

			if (cur.w > dist[cur.v])
				continue;

			for (Pair nxt : AL[cur.v]) {
				if (dist[nxt.v] > dist[cur.v] + nxt.w) {
					dist[nxt.v] = dist[cur.v] + nxt.w;
					pq.add(new Pair(nxt.v, dist[nxt.v]));
				}
			}
		}

		for (int i = 1; i <= V; i++)
			System.out.println(dist[i] == (int) 1e9 ? "INF" : dist[i]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		int start = Integer.parseInt(br.readLine());

		AL = new List[V + 1];
		for (int i = 1; i <= V; i++)
			AL[i] = new ArrayList<>();

		while (E-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			AL[u].add(new Pair(v, w));
		}

		dijkstra(start);
	}
}
