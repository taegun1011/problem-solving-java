package gold.p24888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	static List<Integer>[] path;
	static int[] key;
	static Stack<Integer> Ans = new Stack<>();

	static boolean DFS(int cur, int end, int left, Stack<Integer> S) {
		S.add(cur);

		if (cur == end && left == 0) {
			Ans.clear();
			Ans.addAll(S);
			return true;
		}

		boolean flag = false;
		for (int nxt : path[cur])
			flag |= DFS(nxt, end, left - key[nxt], S);
		S.pop();

		return flag;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
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
//			Edges[v].add(new Pair(u, w)); 양방향일 필요는 없다
		}

		key = new int[N + 1];
		st = new StringTokenizer(br.readLine());

		int total = 0;
		for (int i = 1; i <= N; i++) {
			key[i] = Integer.parseInt(st.nextToken());
			if (key[i] == 1)
				total++;
		}

		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.w, o2.w));
		long[] dist = new long[N + 1];
		path = new List[N + 1];
		for (int i = 1; i <= N; i++)
			path[i] = new ArrayList<>();

		Arrays.fill(dist, -1);
		pq.add(new Pair(1, (dist[1] = 0)));

		while (!pq.isEmpty()) {
			Pair cur = pq.poll();

			if (dist[cur.v] != cur.w)
				continue;

			for (Pair nxt : Edges[cur.v]) {
				if (dist[nxt.v] < 0 || dist[nxt.v] > dist[cur.v] + nxt.w) {
					dist[nxt.v] = dist[cur.v] + nxt.w;
					pq.add(new Pair(nxt.v, dist[nxt.v]));

					path[nxt.v].clear();
					path[nxt.v].add(cur.v);
				}

				else if (dist[nxt.v] == dist[cur.v] + nxt.w)
					path[nxt.v].add(cur.v);
			}
		}

		boolean flag = DFS(N, 1, total - key[N], new Stack<Integer>());

		StringBuilder sb = new StringBuilder();
		if (flag) {
			sb.append(total).append('\n');
			while (!Ans.empty()) {
				sb.append(Ans.pop()).append(' ');
			}
		} else
			sb.append(-1);

		System.out.println(sb);
	}
}
