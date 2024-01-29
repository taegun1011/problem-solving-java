package silver.p1389;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] dist;
	static List<Integer>[] graph;

	static void BFS(int start) {
		Queue<Integer> Q = new LinkedList<>();

		Q.add(start);

		while (!Q.isEmpty()) {
			int cur = Q.poll();

			for (int nxt : graph[cur]) {
				if (nxt == start || dist[start][nxt] != 0)
					continue;
				dist[start][nxt] = dist[start][cur] + 1;
				Q.add(nxt);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		dist = new int[N + 1][N + 1];
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
			BFS(i);

		int min = Integer.MAX_VALUE;
		int ans = 1;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				if (i != j)
					sum += dist[i][j];
			}
			if (sum < min) {
				min = sum;
				ans = i;
			}
		}

		System.out.println(ans);
	}
}
