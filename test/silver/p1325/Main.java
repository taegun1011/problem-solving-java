package silver.p1325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] graph;
	static int[] count;
	static boolean[] visited;

	public static void DFS(int cur) {
		visited[cur] = true;
		count[cur]++;

		for (int nxt : graph[cur]) {
			if (!visited[nxt])
				DFS(nxt);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		while (M > 0) {
			M--;
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			graph[u].add(v);
		}

		count = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			DFS(i);
		}

		int max = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int res = count[i];
			if (max < res) {
				max = res;
				sb = new StringBuilder().append(i);
			} else if (res == max) {
				sb.append(" " + i);
			}
		}
		System.out.println(sb);
	}
}