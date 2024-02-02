package silver.p18352;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dist;
	static List<Integer>[] graph;

	static void BFS(int start) {
		Queue<Integer> Q = new LinkedList<>();

		Q.add(start);

		while (!Q.isEmpty()) {
			int cur = Q.poll();

			for (int nxt : graph[cur]) {
				if (dist[nxt] == 0 && nxt != start) {
					dist[nxt] = dist[cur] + 1;
					Q.add(nxt);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		dist = new int[N];
		graph = new List[N];
		for (int i = 0; i < N; i++)
			graph[i] = new LinkedList<>();

		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken()) - 1;

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			graph[u].add(v);
		}

		BFS(X);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			if (dist[i] == K)
				sb.append(i + 1).append('\n');
		}
		System.out.println(sb.length() == 0 ? -1 : sb);
	}
}
