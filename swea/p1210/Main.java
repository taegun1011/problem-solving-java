package swea.p1210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int size = 100;
	static boolean[][] visited;
	static int[][][] dir = { { { -1, 0 } }, { { 0, 1 }, { 0, -1 } } };
	static int[][] graph;

	static int DFS(int cr, int cc, int cd) {

		if (cr == 0) {
			return cc;
		}

		visited[cr][cc] = true;
		for (int i = 1; i <= 2; i++) {
			// 현재 방향 우선 탐색

			int nd = (cd + i) % 2;
			for (int j = 0; j < dir[nd].length; j++) {
				int nr = cr + dir[nd][j][0];
				int nc = cc + dir[nd][j][1];
				if (nr < 0 || nr >= size || nc < 0 || nc >= size || graph[nr][nc] != 1 || visited[nr][nc])
					continue;
				return DFS(nr, nc, nd);
			}
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = 10;

		// 거꾸로 DFS
		// 가능한 방향은 위, 좌우
		while (TC-- > 0) {

			graph = new int[size][size];
			visited = new boolean[size][size];
			int tc = Integer.parseInt(br.readLine());
			int er = 0, ec = 0;

			for (int i = 0; i < size; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < size; j++) {
					if ((graph[i][j] = Integer.parseInt(st.nextToken())) == 2) {
						er = i;
						ec = j;
					}
				}
			}

			sb.append("#" + tc + " " + DFS(er, ec, 0)).append('\n');
		}

		System.out.println(sb);
	}
}
