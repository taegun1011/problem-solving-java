package gold.p24888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

class Pair {
	int v;
	long w;

	public Pair(int v, long w) {
		this.v = v;
		this.w = w;
	}
}

public class Main {
//	static List<Integer>[] path;
	static int[] note;

	static int N;
	static int[] dp = new int[200001];
	static int[] prev = new int[200001];

	// dp도 여기서 하자
	static void dijkstra(List<Pair>[] Edges) {
		long[] dist = new long[N + 1];

		Arrays.fill(dist, (long) 1e18);
		Arrays.fill(dp, -1);

		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.w, o2.w));
		pq.add(new Pair(1, (dist[1] = 0)));
		dp[1] = note[1];

		while (!pq.isEmpty()) {
			Pair cur = pq.poll();

			if (dist[cur.v] != cur.w)
				continue;

			for (Pair nxt : Edges[cur.v]) {
				if (dist[nxt.v] >= dist[cur.v] + nxt.w) {
					dist[nxt.v] = dist[cur.v] + nxt.w;
					pq.add(new Pair(nxt.v, dist[nxt.v]));

					if (dp[nxt.v] <= dp[cur.v] + note[nxt.v]) {
						prev[nxt.v] = cur.v;
						dp[nxt.v] = dp[cur.v] + note[nxt.v];
					}
				}

			}
		}
	}

	static void construct() {
		Stack<Integer> S = new Stack<>();
		int cur = N;
		while (cur != 1) {
			S.add(cur);
			cur = prev[cur];
		}
		S.add(1);
		StringBuilder sb = new StringBuilder();
		sb.append(S.size()).append('\n');
		while (!S.isEmpty())
			sb.append(S.pop() + " ");
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Pair>[] Edges = new List[N + 1];
		for (int i = 1; i <= N; i++)
			Edges[i] = new LinkedList<>();

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			Edges[u].add(new Pair(v, 0l + w));
			Edges[v].add(new Pair(u, 0l + w));
		}

		note = new int[N + 1];
		String input = br.readLine();

		int total = 0;
		for (int i = 1; i <= N; i++)
			total += (note[i] = input.charAt(2 * (i - 1)) - '0');

		dijkstra(Edges);

		if (dp[N] == total)
			construct();
		else
			System.out.println(-1);
	}
}
