package gold.p2252;

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
		int M = Integer.parseInt(st.nextToken());

		List<Integer>[] to = new List[N];
		for (int i = 0; i < N; i++)
			to[i] = new ArrayList<>();
		int[] in = new int[N];

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			to[a].add(b);
			in[b]++;
		}

		Queue<Integer> Q = new ArrayDeque<>();
		for (int i = 0; i < N; i++)
			if (in[i] == 0)
				Q.add(i);

		StringBuilder sb = new StringBuilder();
		while (!Q.isEmpty()) {
			int cur = Q.poll();
			sb.append(cur + 1 + " ");

			for (int nxt : to[cur]) {
				in[nxt]--;
				if (in[nxt] == 0)
					Q.add(nxt);
			}
		}

		System.out.println(sb);
	}
}
