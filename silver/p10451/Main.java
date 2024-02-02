package silver.p10451;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static void DFS(int cur, boolean[] visited, int[] graph) {
		visited[cur] = true;
		int nxt = graph[cur];
		if (!visited[nxt])
			DFS(nxt, visited, graph);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] graph = new int[N];
			boolean[] visited = new boolean[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				graph[i] = Integer.parseInt(st.nextToken()) - 1;

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i])
					continue;
				cnt++;
				DFS(i, visited, graph);
			}
			sb.append(cnt).append('\n');
		}

		System.out.println(sb);
	}
}
