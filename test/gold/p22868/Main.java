package gold.p22868;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] dist = new int[N + 1];
		int[] prv = new int[N + 1];
		Arrays.fill(dist, -1);

		List<Integer>[] graph = new List[N + 1];
		for (int i = 1; i <= N; i++)
			graph[i] = new LinkedList<>();

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}

		for (int i = 1; i <= N; i++)
			Collections.sort(graph[i]);

		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		Queue<Integer> Q = new LinkedList<>();

		dist[S] = 0;
		Q.add(S);

		while (!Q.isEmpty()) {
			int cur = Q.poll();

			for (int nxt : graph[cur]) {
				if (dist[nxt] == -1) {
					dist[nxt] = dist[cur] + 1;
					prv[nxt] = cur;
					Q.add(nxt);
				}
			}
		}

		// 최단 경로 찾아서 접근 불허
		boolean[] visited = new boolean[N + 1];
		int cur = E;
		while (cur != S) {
			visited[cur] = true;
			cur = prv[cur];
		}

		Q.clear();
		Q.add(E);

		while (!Q.isEmpty()) {
			cur = Q.poll();

			for (int nxt : graph[cur]) {
				if (!visited[nxt]) {
					dist[nxt] = dist[cur] + 1;
					visited[nxt] = true;
					Q.add(nxt);
				}
			}
		}

		System.out.println(dist[S]);
	}
}
