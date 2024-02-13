package gold.p14503;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int cr = Integer.parseInt(st.nextToken());
		int cc = Integer.parseInt(st.nextToken());
		int cd = Integer.parseInt(st.nextToken());

		int[][] graph = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				graph[i][j] = Integer.parseInt(st.nextToken());
		}

		int cnt = 0;

		while (true) {
			if (graph[cr][cc] == 0) {
				graph[cr][cc] = 2;
				cnt++;
			}

			boolean flag = false;
			for (int i = 0; i < 4; i++) {
				int nr = cr + dir[i][0];
				int nc = cc + dir[i][1];

				if (graph[nr][nc] == 0) {
					flag = true;
					break;
				}
			}

			if (flag) {
				for (int i = 0; i < 4; i++) {
					cd = (cd + 3) % 4;
					int nr = cr + dir[cd][0];
					int nc = cc + dir[cd][1];

					if (graph[nr][nc] == 0) {
						cr = nr;
						cc = nc;
						break;
					}
				}
			}

			else {
				int nr = cr - dir[cd][0];
				int nc = cc - dir[cd][1];

				if (graph[nr][nc] == 1) {
					System.out.println(cnt);
					return;
				} else {
					cr = nr;
					cc = nc;
				}
			}
		}
	}
}
