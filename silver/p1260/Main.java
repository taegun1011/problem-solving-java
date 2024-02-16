package silver.p1260;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static List<Integer>[] graph;
	static StringBuilder sb = new StringBuilder();

	static void DFS(int cur) {
		sb.append(cur + " ");
		visited[cur] = true;
		for (int nxt : graph[cur])
			if (!visited[nxt])
				DFS(nxt);
	}

	static void BFS(int cur) {
		Queue<Integer> Q = new ArrayDeque<>();
		Q.add(cur);
		visited[cur] = true;
		while (!Q.isEmpty()) {
			cur = Q.poll();
			sb.append(cur + " ");
			for (int nxt : graph[cur]) {
				if (!visited[nxt]) {
					Q.add(nxt);
					visited[nxt] = true;
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];

		graph = new List[N + 1];
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
			graph[i].sort(null);

		DFS(V);
		sb.append('\n');

		visited = new boolean[N + 1];
		BFS(V);
		System.out.println(sb);
	}
}
