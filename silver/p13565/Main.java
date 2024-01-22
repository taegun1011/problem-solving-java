package silver.p13565;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static boolean DFS(int cr, int cc, boolean[][] visited, char[][] map) {
		visited[cr][cc] = true;
		if (cr == map.length - 1) {
			return true;
		}

		boolean ret = false;
		for (int i = 0; i < 4; i++) {
			int nr = cr + dir[i][0];
			int nc = cc + dir[i][1];
			if (nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length)
				continue;
			if (map[nr][nc] == '0' && !visited[nr][nc])
				ret |= DFS(nr, nc, visited, map);
		}

		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());

		char[][] map = new char[m][n];
		boolean[][] visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			map[i] = br.readLine().toCharArray();
		}

		boolean flag = false;
		for (int i = 0; i < n; i++) {
			if (flag)
				continue;
			if (!visited[0][i] && map[0][i] == '0') {
				flag |= DFS(0, i, visited, map);
			}
		}

		System.out.println(flag ? "YES" : "NO");

	}
}
