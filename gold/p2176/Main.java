package gold.p2176;

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
	static List<Pair>[] Edges;

	static int[] dp;

	static int re_dp(int cur, int[] dist) {
		if (dp[cur] != -1)
			return dp[cur];
		int ret = 0;
		for (Pair nxt : Edges[cur])
			if (dist[nxt.v] > dist[cur])
				ret += re_dp(nxt.v, dist);

		return dp[cur] = ret;
	}

	static int[] dijkstra(int start) {
		int N = Edges.length;

		int[] dist = new int[N];
		Arrays.fill(dist, (int) 1e9);

		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
		pq.add(new Pair(start, dist[start] = 0));

		while (!pq.isEmpty()) {
			Pair cur = pq.poll();

			if (dist[cur.v] != cur.w)
				continue;

			for (Pair nxt : Edges[cur.v]) {
				if (dist[cur.v] + nxt.w < dist[nxt.v]) {
					dist[nxt.v] = dist[cur.v] + nxt.w;
					pq.add(new Pair(nxt.v, dist[nxt.v]));
				}
			}
		}

		return dist;
	}

	public static void main(String[] args) throws Exception {
		// dp[i] : 1에서 i까지 가는 합리적 이동경로의 수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Edges = new List[N + 1];
		for (int i = 1; i <= N; i++)
			Edges[i] = new LinkedList<>();

		int M = Integer.parseInt(st.nextToken());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			Edges[u].add(new Pair(v, w));
			Edges[v].add(new Pair(u, w));
		}

		int[] dist = dijkstra(2);
		dp = new int[N + 1];
		Arrays.fill(dp, -1);
		dp[1] = 1;

		int ans = 0;
		for (Pair nxt : Edges[2])
			ans += re_dp(nxt.v, dist);

		System.out.println(ans);
		// '최단거리'라는 조건에 의해 DAG 생성
	}
}
