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
	static int[] size;

	public static void DFS(int cur, boolean[] visited) {
		visited[cur] = true;

		for (int nxt : map[cur]) {
			System.out.println(cur + " " + size[cur] + " " + nxt + " " + size[nxt]);
			if (size[nxt] > size[cur])
				continue;
			else
				size[nxt] = size[cur] + 1;

			if (!visited[nxt]) {
				DFS(nxt, visited);
			}
		}
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

			map[u].add(v);
		}

		size = new int[N + 1];
		Arrays.fill(size, 1);

		for (int i = 1; i <= N; i++) {
			DFS(i, new boolean[N + 1]);
		}

		int max = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int res = size[i];
			System.out.println(res);
			if (max < res) {
				max = res;
				sb = new StringBuilder().append(i);
			} else if (res == max) {
				sb.append(" " + i);
			}
		}
		System.out.println(sb);
	}
}