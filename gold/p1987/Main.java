package gold.p1987;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int C;

	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static char[][] graph;

	public static void main(String[] args) throws Exception {
		// visited 대신 문자열을 가지고

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		graph = new char[R][C];
		for (int i = 0; i < R; i++)
			graph[i] = br.readLine().toCharArray();

		System.out.println(DFS(0, 0, ""));
	}

	private static int DFS(int cr, int cc, String key) {
		key += graph[cr][cc];

		int ret = 1;
		for (int i = 0; i < 4; i++) {
			int nr = cr + dir[i][0];
			int nc = cc + dir[i][1];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C)
				continue;
			if (key.indexOf(graph[nr][nc]) == -1)
				ret = Integer.max(ret, DFS(nr, nc, key) + 1);
		}

		return ret;
	}
}