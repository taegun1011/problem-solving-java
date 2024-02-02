package gold.p1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] graph;
	static int[] dist;

	static boolean BFS(int start) {
		Queue<Integer> Q = new LinkedList<>();
		Q.add(start);
		while (!Q.isEmpty()) {
			int cur = Q.poll();
			for (int nxt : graph[cur]) {
				if (dist[nxt] == 0 && nxt != start) {
					dist[nxt] = dist[cur] + 1;
					Q.add(nxt);
				} else if (dist[cur] + 1 - dist[nxt] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			dist = new int[V];
			graph = new List[V];
			for (int i = 0; i < V; i++)
				graph[i] = new LinkedList<>();

			int E = Integer.parseInt(st.nextToken());
			while (E-- > 0) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				graph[u].add(v);
				graph[v].add(u);
			}

			boolean flag = true;
			for (int i = 0; i < V; i++)
				if (dist[i] == 0)
					flag &= BFS(i);

			sb.append(flag ? "YES" : "NO").append('\n');
		}
		System.out.println(sb);
	}
}
