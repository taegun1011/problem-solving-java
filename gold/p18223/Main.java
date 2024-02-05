package gold.p18223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {
	int v;
	int w;

	public Pair(int v, int w) {
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(Pair o) {
		return Integer.compare(this.w, o.w);
	}

}

public class Main {
	static int V, E;
	static List<Pair>[] graph;

	static int dijkstra(int start, int end) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		int[] distance = new int[V + 1];
		Arrays.fill(distance, -1);

		for (Pair p : graph[start])
			pq.add(new Pair(p.v, p.w));

		// 처음은 start에서
		pq.add(new Pair(start, 0));
		// while의 if문에 걸리지 않도록
		distance[start] = 0;

		while (!pq.isEmpty()) {
			Pair cur = pq.poll();

			// 이미 최단거리가 구해져있으면
			if (distance[cur.v] != cur.w)
				continue;

			for (Pair nxt : graph[cur.v]) {

				// 길이 없거나 경유하는게 더 짧으면
				if (distance[nxt.v] == -1 || distance[nxt.v] > nxt.w + distance[cur.v]) {
					distance[nxt.v] = nxt.w + distance[cur.v];
					pq.add(new Pair(nxt.v, distance[nxt.v]));
				}
			}
		}

		return distance[end];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		graph = new List[V + 1];
		for (int i = 1; i <= V; i++)
			graph[i] = new LinkedList<>();

		E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		while (E-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Pair(v, w));
			graph[v].add(new Pair(u, w));
		}

		if (dijkstra(1, P) + dijkstra(P, V) == dijkstra(1, V))
			System.out.println("SAVE HIM");
		else
			System.out.println("GOOD BYE");
	}
}
