package gold.p13460;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Pair {
		int r;
		int c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static char[][] map;
	static Pair Rorigin, Borigin;

	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int INF = (int) 1e9;
	private static int N;
	private static int M;

	static boolean move(int d, Pair me, Pair you) {
		boolean moved = false;
		int dr = dir[d][0], dc = dir[d][1];
		int cr = me.r, cc = me.c;

		// 흠...
		while (map[cr + dr][cc + dc] != '#'
				&& !(cr + dr == you.r && cc + dc == you.c && map[cr + dr][cc + dc] != 'O')) {
			moved = true;

			cr += dr;
			cc += dc;
			if (map[cr][cc] == 'O')
				break;
		}

		// Pair의 현재 위치 갱신
		me.r = cr;
		me.c = cc;

		return moved;
	}

	static int calc(int[] list, int idx, Pair R, Pair B) {

		// 10번 움직여도 성공 못 했으면
		if (idx == 10)
			return INF;

		int d = list[idx];

		// R 이동
		boolean moved = move(d, R, B);

		// B 이동
		moved |= move(d, B, R);

		// B가 도착했으면 무조건 fail
		if (map[B.r][B.c] == 'O')
			return INF;

		if (map[R.r][R.c] == 'O')
			return 1;

		// B 때문에 R이 못 갔을 수도 있으니 한 번 더 이동
		moved |= move(d, R, B);

		if (map[R.r][R.c] == 'O')
			return 1;

		// 백트래킹 : 이번에 안 움직였으면 최소 횟수가 아님
		if (!moved)
			return INF;

		return 1 + calc(list, idx + 1, R, B);
	}

	static int perm(int[] list, int idx) {
		if (idx == 10) {
			// 이런 류 문제 풀 때는 항상 주의하자
			Pair R = new Pair(Rorigin.r, Rorigin.c);
			Pair B = new Pair(Borigin.r, Borigin.c);
			return calc(list, 0, R, B);
		}
		int ret = INF;
		for (int i = 0; i < 4; i++) {
			list[idx] = i;
			ret = Integer.min(ret, perm(list, idx + 1));
		}

		return ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		// ** R, B도 .으로 표시해줄 것
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'R') {
					Rorigin = new Pair(i, j);
					map[i][j] = '.';
				} else if (map[i][j] == 'B') {
					Borigin = new Pair(i, j);
					map[i][j] = '.';
				}
			}
		}

		int result = perm(new int[10], 0);
		System.out.println(result == INF ? -1 : result);
	}
}
