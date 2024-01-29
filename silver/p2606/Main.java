package silver.p2606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int V, E;
	static List<Integer>[] graph;
	static boolean[] visited;

	static int DFS(int cur) {
		int ret = 1;

		visited[cur] = true;
		for (int nxt : graph[cur])
			if (!visited[nxt])
				ret += DFS(nxt);

		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());

		graph = new LinkedList[V + 1];
		visited = new boolean[V + 1];

		for (int i = 1; i <= V; i++) {
			graph[i] = new LinkedList<>();
		}

		while (E-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}

		System.out.println(DFS(1) - 1);
	}
}
