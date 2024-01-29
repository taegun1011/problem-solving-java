package silver.p11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int V, E;
	static boolean[] visited;
	static List<Integer>[] graph;

	static void DFS(int cur) {
		visited[cur] = true;
		for (int nxt : graph[cur])
			if (!visited[nxt])
				DFS(nxt);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		visited = new boolean[V + 1];
		graph = new List[V + 1];

		for (int i = 1; i <= V; i++)
			graph[i] = new LinkedList<>();

		while (E-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			graph[u].add(v);
			graph[v].add(u);
		}

		int cnt = 0;
		for (int i = 1; i <= V; i++) {
			if (!visited[i]) {
				cnt++;
				DFS(i);
			}
		}
		System.out.println(cnt);
	}
}
