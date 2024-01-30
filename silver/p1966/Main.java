package silver.p1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int start;
	int w;

	Pair(int start, int w) {
		this.start = start;
		this.w = w;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			Queue<Pair> Q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());

			boolean[] visited = new boolean[N];
			int[] weight = new int[N];

			for (int i = 0; i < N; i++) {
				int w = Integer.parseInt(st.nextToken());
				weight[i] = w;
				Q.add(new Pair(i, w));
			}

			int cnt = 1;
			while (!Q.isEmpty()) {
				Pair cur = Q.poll();
				boolean flag = false;
				for (int i = 0; i < N; i++) {
					if (!visited[i] && weight[i] > cur.w) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					visited[cur.start] = true;
					if (cur.start == M) {
						sb.append(cnt).append('\n');
						break;
					}
					cnt++;
				}

				else {
					Q.add(cur);
				}
			}
		}
		System.out.println(sb);
	}
}
