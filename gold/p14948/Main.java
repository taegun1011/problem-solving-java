package gold.p14948;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//배열 크기가 작으니까 mid를 사용한 BFS 결과로 이진탐색을 해보자
class Tuple {
	int r;
	int c;
	int jump;

	public Tuple(int r, int c, int jump) {
		this.r = r;
		this.c = c;
		this.jump = jump;
	}

}

public class Main {
	static int n, m;
	static int[][] graph;

	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static boolean BFS(int key) {
		if (key < graph[0][0])
			return false;

		boolean[][][] visited = new boolean[n][m][2];

		Queue<Tuple> Q = new ArrayDeque<>();
		Q.add(new Tuple(0, 0, 1));
		visited[0][0][1] = true;

		while (!Q.isEmpty()) {
			Tuple cur = Q.poll();
			int cr = cur.r;
			int cc = cur.c;
			int cj = cur.jump;

			for (int i = 0; i < 4; i++) {
				int nr = cr + dir[i][0];
				int nc = cc + dir[i][1];
				int nj = cj;

				if (nr < 0 || nr >= n || nc < 0 || nc >= m)
					continue;

				// 한칸 더간다 -> 구현이 너무 엉성해
				if (key < graph[nr][nc]) {
					if (nj == 1) {
						nr += dir[i][0];
						nc += dir[i][1];
						if (nr < 0 || nr >= n || nc < 0 || nc >= m || key < graph[nr][nc])
							continue;
						nj = 0;
					} else
						continue;
				}
				if (!visited[nr][nc][nj]) {
					visited[nr][nc][nj] = true;
					Q.add(new Tuple(nr, nc, nj));
				}
			}
		}

		return visited[n - 1][m - 1][0] || visited[n - 1][m - 1][1];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				graph[i][j] = Integer.parseInt(st.nextToken());
		}

		int s = 0, e = (int) 1e9, mid;
		// XXXXOOOO
		while (s < e) {
			mid = (s + e) / 2;
			if (BFS(mid))
				e = mid;
			else
				s = mid + 1;
		}

		System.out.println(s);
	}
}
