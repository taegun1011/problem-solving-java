package gold.p1717;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] p;

	static int find(int x) {
		if (p[x] == x)
			return x;
		return p[x] = find(p[x]);
	}

	static void union(int x, int y) {
		int px = find(x), py = find(y);
		p[py] = px;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		p = new int[N + 1];
		for (int i = 0; i <= N; i++)
			p[i] = i;

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (cmd == 0)
				union(a, b);
			else
				sb.append(find(a) == find(b) ? "YES" : "NO").append('\n');

		}

		System.out.println(sb);
	}
}
