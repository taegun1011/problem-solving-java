package gold.p1967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Pair {
	int node;
	int w;

	public Pair(int node, int w) {
		this.node = node;
		this.w = w;
	}
}

public class Main {

	static int max;
	static int maxNode;
	static boolean[] visited;

	static void DFS(int cur, int w, List<Pair>[] graph) {
		visited[cur] = true;

		for (Pair nxt : graph[cur]) {
			if (visited[nxt.node])
				continue;

			if (w + nxt.w > max) {
				max = w + nxt.w;
				maxNode = nxt.node;
			}

			DFS(nxt.node, w + nxt.w, graph);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		if (N == 1) {
			System.out.println(0);
			return;
		}

		List<Pair>[] to = new List[N + 1];
		List<Pair>[] toto = new List[N + 1];
		visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			to[i] = new LinkedList<>();
			toto[i] = new LinkedList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			to[u].add(new Pair(v, w));
			toto[u].add(new Pair(v, w));
			toto[v].add(new Pair(u, w));
		}

		DFS(1, 0, to);
		Arrays.fill(visited, false);

		DFS(maxNode, 0, toto);
		System.out.println(max);
	}
}
