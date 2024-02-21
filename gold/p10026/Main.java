package gold.p10026;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static boolean[][] visited;

	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int N;

	static void DFS(int cr, int cc, String[] map) {
		visited[cr][cc] = true;

		for (int i = 0; i < 4; i++) {
			int nr = cr + dir[i][0];
			int nc = cc + dir[i][1];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;
			if (!visited[nr][nc] && map[nr].charAt(nc) == map[cr].charAt(cc))
				DFS(nr, nc, map);
		}
	}

	static int solve(String[] map) {
		visited = new boolean[N][N];

		int ret = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				if (!visited[i][j]) {
					ret++;
					DFS(i, j, map);
				}
		}

		return ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		String[] map_normal = new String[N];
		String[] map_disabled = new String[N];

		for (int i = 0; i < N; i++) {
			map_normal[i] = br.readLine();
			map_disabled[i] = map_normal[i].replaceAll("G", "R");
		}

		System.out.println(solve(map_normal) + " " + solve(map_disabled));
	}
}
