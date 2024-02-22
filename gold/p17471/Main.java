package gold.p17471;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	static final int INF = 987654321;
	static int N;
	private static boolean[] visited;
	static ArrayList<Integer> graph[];

	static void input() throws Exception {
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N];
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int j = 0; j < N; j++) {

			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int i = 0; i < n; i++) {
				graph[j].add(Integer.parseInt(st.nextToken()) - 1);
			}
		}
	}

	static int solve(boolean[] choice, int size, boolean isSelected) {
		int res = 0;
		if (size == 0 || size == N)
			return INF;
		visited = new boolean[N];
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < choice.length; i++) {
			if (choice[i] == isSelected) {
				q.add(i);
				visited[i] = true;
				break;
			}
		}
		--size;

		while (!q.isEmpty()) {
			int cur = q.poll();
			res += arr[cur];
			for (int nxt : graph[cur]) {
				if (visited[nxt] || choice[nxt] != isSelected)
					continue;
				visited[nxt] = true;
				q.add(nxt);
				--size;
			}
		}
		if (size != 0)
			return INF;
		return res;
	}

	static int recursion(boolean[] choice, int idx, int count) {
		if (idx == arr.length) {

			int sum1 = solve(choice, count, true);
			int sum2 = solve(choice, choice.length - count, false);
			if (sum1 == INF || sum2 == INF)
				return INF;
			return Math.abs(sum2 - sum1);
		}

		int ret = INF;
		choice[idx] = true;
		ret = Integer.min(ret, recursion(choice, idx + 1, count + 1));
		choice[idx] = false;
		ret = Integer.min(ret, recursion(choice, idx + 1, count));

		return ret;
	}

	public static void main(String[] args) throws Exception {

		input();
		int res = recursion(new boolean[N], 0, 0);
		System.out.print(res == INF ? -1 : res);
	}
}