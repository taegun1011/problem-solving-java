package gold.p1976;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] p;

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
		int N = Integer.parseInt(br.readLine());
		p = new int[N];
		for (int i = 0; i < N; i++)
			p[i] = i;
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++)
				if (input.charAt(j * 2) - '0' == 1)
					union(i, j);
		}

		// disjoint set : 첫 번째 값을 기준으로 find 비교를 M-1번
		// graph : input[i] -> input[i+1] DFS 탐색을 M-1번
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()) - 1;
		boolean flag = true;

		for (int i = 1; i < M; i++)
			if (find(start) != find(Integer.parseInt(st.nextToken()) - 1))
				flag = false;

		System.out.println(flag ? "YES" : "NO");
	}
}
