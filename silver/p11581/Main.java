package silver.p11581;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] graph;
	static int N;

	static boolean DFS(int cur, boolean[] visited) {

		visited[cur] = true;

		boolean flag = false;

		for (int nxt : graph[cur]) {
			if (visited[nxt])
				return true;
			flag |= DFS(nxt, visited);
		}

		visited[cur] = false;

		return flag;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		graph = new List[N + 1];
		for (int i = 1; i <= N; i++)
			graph[i] = new LinkedList<>();

		for (int i = 1; i < N; i++) {
			int M = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (M-- > 0) {
				graph[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		System.out.println(DFS(1, new boolean[N + 1]) ? "CYCLE" : "NO CYCLE");
	}
}
