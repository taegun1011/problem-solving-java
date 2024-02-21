package gold.p15683;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static class Pair {
		int r;
		int c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Tuple extends Pair {
		int range;

		public Tuple(Pair p, int range) {
			super(p.r, p.c);
			this.range = range;
		}
	}

	static int[][] range = { { 1, 2, 4, 8 }, { 5, 10 }, { 3, 6, 9, 12 }, { 7, 11, 13, 14 }, { 15 } };
	static ArrayList<Pair> cam;
	private static int[][] map;
	private static int N;
	private static int M;

	static int perm(int idx, Tuple[] pos) {
		if (idx == cam.size())
			return calc(pos);

		Pair p = cam.get(idx);
		int type = map[p.r][p.c] - 1;
		int ret = (int) 1e9;

		for (int i = 0; i < range[type].length; i++) {
			pos[idx] = new Tuple(p, range[type][i]);
			ret = Integer.min(ret, perm(idx + 1, pos));
		}

		return ret;
	}

	static int calc(Tuple[] pos) {
		int[][] tmpMap = new int[N][M];
		for (int i = 0; i < N; i++)
			tmpMap[i] = Arrays.copyOf(map[i], map[i].length);

		for (Tuple t : pos) {
			int range = t.range;
			int sr = t.r;
			int sc = t.c;
			// 상
			if ((range & 1) != 0) {
				int dy = 0;
				while (sr - dy >= 0 && tmpMap[sr - dy][sc] != 6)
					tmpMap[sr - dy++][sc] = -1;
			}
			// 우
			if ((range & 2) != 0) {
				int dx = 0;
				while (sc + dx < M && tmpMap[sr][sc + dx] != 6)
					tmpMap[sr][sc + dx++] = -1;
			}
			// 하
			if ((range & 4) != 0) {
				int dy = 0;
				while (sr + dy < N && tmpMap[sr + dy][sc] != 6)
					tmpMap[sr + dy++][sc] = -1;
			}
			// 좌
			if ((range & 8) != 0) {
				int dx = 0;
				while (sc - dx >= 0 && tmpMap[sr][sc - dx] != 6)
					tmpMap[sr][sc - dx++] = -1;
			}
		}

		int ret = 0;
		for (int[] row : tmpMap)
			for (int x : row)
				if (x == 0)
					ret++;

		return ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inp = br.readLine();
		N = inp.charAt(0) - '0';
		M = inp.charAt(2) - '0';

		map = new int[N][M];
		cam = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			inp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = inp.charAt(j * 2) - '0';
				if (1 <= map[i][j] && map[i][j] <= 5)
					cam.add(new Pair(i, j));
			}
		}

		System.out.println(perm(0, new Tuple[cam.size()]));
	}
}
