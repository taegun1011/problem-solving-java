package silver.p3187;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int r, c;

	Pair(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static int sheep = 0, wolf = 0;

	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void BFS(int sr, int sc, char[][] map, boolean[][] visited) {
		Queue<Pair> Q = new LinkedList<>();

		int v = 0, k = 0;
		Q.add(new Pair(sr, sc));
		visited[sr][sc] = true;

		while (!Q.isEmpty()) {
			Pair cur = Q.poll();
			if (map[cur.r][cur.c] == 'v')
				v++;
			else if (map[cur.r][cur.c] == 'k')
				k++;

			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dir[i][0];
				int nc = cur.c + dir[i][1];

				if (nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length || visited[nr][nc])
					continue;
				if (map[nr][nc] == '#')
					continue;

				visited[nr][nc] = true;
				Q.add(new Pair(nr, nc));
			}
		}
		if (v >= k)
			wolf += v;
		else
			sheep += k;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		boolean[][] visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String str = (new StringTokenizer(br.readLine())).nextToken();
			map[i] = str.toCharArray();
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != '#' && !visited[i][j]) {
					BFS(i, j, map, visited);
				}
			}
		}

		System.out.println(sheep + " " + wolf);
	}
}
