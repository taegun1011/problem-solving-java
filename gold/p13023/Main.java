package gold.p13023;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static boolean[] visited;
	static List<Integer>[] graph;

	static int DFS(int cur) {
		// 방문 체크를 어떻게?

		visited[cur] = true;
		int ret = 1;

		for (int nxt : graph[cur]) {
			if (!visited[nxt]) {
				ret = Integer.max(ret, DFS(nxt) + 1);
				// 재귀에서 탈출하면 방문 해제
				visited[nxt] = false;
			}

			// 시간 초과 방지
			if (ret >= 5)
				break;
		}

		return ret;
	}

	public static void main(String[] args) throws Exception {
		// 풀이 1.DFS
		// 풀이 2.unionfind : 불가능
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new List[N];
		for (int i = 0; i < N; i++)
			graph[i] = new ArrayList<>();

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}

		boolean flag = false;

		for (int i = N - 1; i >= 0; i--) {
			visited = new boolean[N];
			if (DFS(i) >= 5) {
				flag = true;
				break;
			}
		}

		System.out.println(flag ? 1 : 0);
	}
}
