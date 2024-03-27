package gold.p1194;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Tuple {
		int r;
		int c;
		int keys;
		int d;

		public Tuple(int r, int c, int keys, int d) {
			this.r = r;
			this.c = c;
			this.keys = keys;
			this.d = d;
		}

	}

	private static final int INF = (int) 1e9;

	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		// BFS할 때 열쇠 정보도 같이 관리한다

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] board = new char[N][M];
		int[][][] dist = new int[N][M][1 << 6];

		Queue<Tuple> Q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (board[i][j] == '0')
					Q.add(new Tuple(i, j, 0, (dist[i][j][0] = 0)));
			}
		}

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				Arrays.fill(dist[i][j], INF);

		int ans = INF;
		while (!Q.isEmpty()) {
			Tuple cur = Q.poll();
			int cr = cur.r, cc = cur.c;
			int ck = cur.keys, cd = cur.d;

			if (board[cr][cc] == '1') {
				ans = Integer.min(ans, cd);
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nr = cr + dir[i][0];
				int nc = cc + dir[i][1];
				int nd = cd + 1, nk = ck;

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == '#')
					continue;

				if (board[nr][nc] >= 'A' && board[nr][nc] <= 'F') {
					if ((ck & (1 << (board[nr][nc] - 'A'))) == 0)
						continue;
				} else if (board[nr][nc] >= 'a' && board[nr][nc] <= 'f')
					nk |= (1 << (board[nr][nc] - 'a'));
				if (nd < dist[nr][nc][nk]) {
					dist[nr][nc][nk] = nd;
					Q.add(new Tuple(nr, nc, nk, nd));
				}
			}
		}

		System.out.println(ans == INF ? -1 : ans);
	}
}
