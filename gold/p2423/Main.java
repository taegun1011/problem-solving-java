package gold.p2423;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Tuple {
	int r;
	int c;
	int w;

	public Tuple(int r, int c, int w) {
		this.r = r;
		this.c = c;
		this.w = w;
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[][] pdir = { { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } }; // 점의 대각선 이동
		int[][] cdir = { { -1, -1 }, { -1, 0 }, { 0, 0 }, { 0, -1 } }; // 면의 대각선 이동

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] map = new char[N][M];
		int[][] dist = new int[N + 1][M + 1];
		for (int[] row : dist)
			Arrays.fill(row, (int) 1e9);

		for (int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();

		PriorityQueue<Tuple> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
		pq.add(new Tuple(0, 0, 0));
		dist[0][0] = 0;

		while (!pq.isEmpty()) {
			Tuple cur = pq.poll();
			if (dist[cur.r][cur.c] < cur.w)
				continue;

			// 칸이 아닌 점에서 이동해보자
			for (int i = 0; i < 4; i++) {
				int pnr = cur.r + pdir[i][0];
				int pnc = cur.c + pdir[i][1];
				int cnr = cur.r + cdir[i][0];
				int cnc = cur.c + cdir[i][1];

				if (pnr < 0 || pnr > N || pnc < 0 || pnc > M)
					continue;

				if (cnr < 0 || cnr >= N || cnc < 0 || cnc >= M)
					continue;

				int nw;
				if (i % 2 == 0) {
					if (map[cnr][cnc] == '\\') {
						nw = dist[cur.r][cur.c];
					} else
						nw = dist[cur.r][cur.c] + 1;
				} else {
					if (map[cnr][cnc] == '/') {
						nw = dist[cur.r][cur.c];
					} else
						nw = dist[cur.r][cur.c] + 1;
				}

				if (dist[pnr][pnc] > nw) {
					dist[pnr][pnc] = nw;
					pq.add(new Tuple(pnr, pnc, nw));
				}

			}
		}

		System.out.println(dist[N][M] == (int) 1e9 ? "NO SOLUTION" : dist[N][M]);
	}
}
