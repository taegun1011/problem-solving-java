package gold.p12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] Morigin;
	static int N;
	static int initialMax;
	static int[][] DIR = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean[][] combined;

	static int move(int[][] map, int d, int sr, int sc) {
		int cr = sr, cc = sc;
		int cur = map[cr][cc];

		int result = 0;

		int nr = cr + DIR[d][0], nc = cc + DIR[d][1];
		while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
			if (map[nr][nc] != 0) {
				if (map[nr][nc] == cur && !combined[nr][nc]) {
					map[nr][nc] += cur;
					combined[nr][nc] = true;
					result = map[nr][nc];
				}
				break;
			}

			cr = nr;
			cc = nc;
			nr += DIR[d][0];
			nc += DIR[d][1];
		}

		// 움직이긴 함
		if (cr != sr || cc != sc || result != 0) {
			map[sr][sc] = 0;
			map[cr][cc] = result == 0 ? cur : 0;
			result = result == 0 ? cur : result;
		}

		return result;
	}

	static int moveAll(int[][] map, int dir) {
		int max = 0;

		// 기본 방향
		int iFrom = 0, iTo = N, iDiff = 1;
		int jFrom = 0, jTo = N, jDiff = 1;

		switch (dir) {
		case 1:
			jFrom = N - 1;
			// 요기 조심
			jTo = -1;
			jDiff = -1;
			break;
		case 2:
			iFrom = N - 1;
			iTo = -1;
			iDiff = -1;
			break;
		}

		// 한번 합쳐진 건 바로 합쳐지면 안됨 ex)<- 4 4 8 => 8 8
		combined = new boolean[N][N];

		for (int i = iFrom; i != iTo; i += iDiff)
			for (int j = jFrom; j != jTo; j += jDiff)
				if (map[i][j] != 0) {
					int result = move(map, dir, i, j);
					max = Integer.max(max, result);
				}

		return max;
	}

	static int calc(int[][] map, int[] list, int idx, int lastMax) {
		if (idx == 5)
			return lastMax;

		int dir = list[idx];

		int result = moveAll(map, dir);
		if (result == 0)
			return lastMax;

		return calc(map, list, idx + 1, Integer.max(result, lastMax));
	}

	static int perm(int[] list, int idx) {
		if (idx == 5) {
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++)
				map[i] = Arrays.copyOf(Morigin[i], N);
			return calc(map, list, 0, initialMax);
		}

		int ret = 0;
		for (int i = 0; i < 4; i++) {
			list[idx] = i;
			ret = Integer.max(ret, perm(list, idx + 1));
		}

		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Morigin = new int[N][N];

		initialMax = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				Morigin[i][j] = Integer.parseInt(st.nextToken());
				initialMax = Integer.max(initialMax, Morigin[i][j]);
			}
		}

		System.out.println(Integer.max(initialMax, perm(new int[5], 0)));
	}
}
