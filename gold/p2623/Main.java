package gold.p2623;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		int[] in = new int[N + 1];
		List<Integer>[] graph = new List[N + 1];
		for (int i = 1; i <= N; i++)
			graph[i] = new ArrayList<>();

		int M = Integer.parseInt(st.nextToken());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int prev = 0;
			while (K-- > 0) {
				int cur = Integer.parseInt(st.nextToken());
				if (prev != 0) {
					graph[prev].add(cur);
					in[cur]++;
				}
				prev = cur;
			}
		}

		Queue<Integer> Q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++)
			if (in[i] == 0)
				Q.add(i);

		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		while (!Q.isEmpty()) {
			int cur = Q.poll();
			cnt++;
			sb.append(cur).append('\n');

			for (int nxt : graph[cur])
				if (--in[nxt] <= 0)
					Q.add(nxt);
		}

		System.out.println(cnt == N ? sb : 0);
	}
}
