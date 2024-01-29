package gold.p9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int solve() throws IOException {
		int N = Integer.parseInt(br.readLine());
		int[] graph = new int[N], cycle = new int[N];
		boolean[] visited = new boolean[N];

		// cycle[-1] : O, cycle[0] : ?, cycle[1] : X

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			graph[i] = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < N; i++) {
			if (cycle[i] != 0)
				continue;

			Stack<Integer> S = new Stack<>();
			Arrays.fill(visited, false);

			int cur = i;
			S.push(i);

			while (!S.empty()) {

				visited[cur] = true;
				cur = graph[cur];

				if (cycle[cur] != 0) {
					while (!S.empty())
						cycle[S.pop()] = -1;
				} else if (visited[cur]) {
					while (!S.empty() && S.peek() != cur)
						cycle[S.pop()] = 1;
					if (!S.empty())
						cycle[S.pop()] = 1;
					while (!S.empty())
						cycle[S.pop()] = -1;
				} else {
					S.push(cur);
				}

			}
		}

		int cnt = 0;

		for (int x : cycle)
			if (x == -1)
				cnt++;

		return cnt;
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			sb.append(solve()).append('\n');
		}

		System.out.println(sb);
	}
}
