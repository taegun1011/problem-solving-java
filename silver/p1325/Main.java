package silver.p1325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] map;
	static int[] height;

	public static int DFS(int cur, int h, boolean[] visited) {
		visited[cur] = true;
		int ret = h;

		for (int nxt : map[cur]) {
			if (visited[nxt])
				continue;
			ret = (height[nxt] > 1) ? Integer.max(height[nxt] + 1, ret) : DFS(nxt, h + 1, visited);
		}

		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
		}

		while (M > 0) {
			M--;
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			// u가 v를 해킹한다 -> v를 해킹하면 u도 해킹할 수 있다
			map[v].add(u);
		}

		height = new int[N + 1];
		Arrays.fill(height, 1);

		for (int i = 1; i <= N; i++) {
			height[i] = DFS(i, 1, new boolean[N + 1]);
		}
	}
}
