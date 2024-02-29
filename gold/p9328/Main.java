package gold.p9328;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int T, n, m, cnt;
	static int[] dirR = { -1, 0, 1, 0 };
	static int[] dirC = { 0, 1, 0, -1 };
	static boolean[] doors;
	static char[][] map;
	static boolean[][] visited;
	static ArrayDeque<int[]> locked;
	static ArrayDeque<int[]> q;

	static void init() {
		locked = new ArrayDeque<>();
		q = new ArrayDeque<>();
		doors = new boolean[27];
		visited = new boolean[n][m];
		cnt = 0;
	}

	static void getStart() {

		for (int c = 0; c < m; c++) {
			if (map[0][c] != '*') {
				char cur = map[0][c];
				if (Character.isAlphabetic(cur) && Character.isUpperCase(cur)) {
					locked.add(new int[] { 0, c });
					continue;
				}

				if (Character.isAlphabetic(cur) && Character.isLowerCase(cur)) {
					doors[cur - 'a'] = true;
				}

				if (map[0][c] == '$') {
					cnt++;
				}

				visited[0][c] = true;
				q.add(new int[] { 0, c });
			}
		}

		for (int c = 0; c < m; c++) {
			if (map[n - 1][c] != '*') {
				char cur = map[n - 1][c];
				if (Character.isAlphabetic(cur) && Character.isUpperCase(cur)) {
					locked.add(new int[] { n - 1, c });
					continue;
				}
				if (Character.isAlphabetic(cur) && Character.isLowerCase(cur)) {
					doors[cur - 'a'] = true;
				}

				if (map[n - 1][c] == '$') {
					cnt++;
				}

				visited[n - 1][c] = true;
				q.add(new int[] { n - 1, c });
			}
		}

		for (int r = 1; r < n - 1; r++) {
			if (map[r][0] != '*') {
				char cur = map[r][0];
				if (Character.isAlphabetic(cur) && Character.isUpperCase(cur)) {
					locked.add(new int[] { r, 0 });
					continue;
				}
				if (Character.isAlphabetic(cur) && Character.isLowerCase(cur)) {
					doors[cur - 'a'] = true;
				}

				if (map[r][0] == '$') {
					cnt++;
				}

				visited[r][0] = true;
				q.add(new int[] { r, 0 });
			}
		}

		for (int r = 1; r < n - 1; r++) {
			if (map[r][m - 1] != '*') {
				char cur = map[r][m - 1];
				if (Character.isAlphabetic(cur) && Character.isUpperCase(cur)) {
					locked.add(new int[] { r, m - 1 });
					continue;
				}
				if (Character.isAlphabetic(cur) && Character.isLowerCase(cur)) {
					doors[cur - 'a'] = true;
				}

				if (map[r][m - 1] == '$') {
					cnt++;
				}

				visited[r][m - 1] = true;
				q.add(new int[] { r, m - 1 });
			}
		}
	}

	static boolean isNotRange(int r, int c) {
		return !(0 <= r && r < n && 0 <= c && c < m);
	}

	static void bfs() {
		while (!q.isEmpty()) {
			while (!q.isEmpty()) {
				int[] cur = q.poll();

				int r = cur[0];
				int c = cur[1];

				if (Character.isAlphabetic(map[r][c]) && Character.isUpperCase(map[r][c]) && !doors[map[r][c] - 'A']) {
					continue;
				}

				for (int d = 0; d < 4; d++) {
					int nr = r + dirR[d];
					int nc = c + dirC[d];

					if (isNotRange(nr, nc) || visited[nr][nc] || map[nr][nc] == '*') {
						continue;
					}

					if (Character.isAlphabetic(map[nr][nc])) {
						if (Character.isUpperCase(map[nr][nc]) && !doors[map[nr][nc] - 'A']) {
							visited[nr][nc] = true;
							locked.add(new int[] { nr, nc });
							continue;
						}

						if (Character.isLowerCase(map[nr][nc]))
							doors[map[nr][nc] - 'a'] = true;
					}

					visited[nr][nc] = true;

					if (map[nr][nc] == '$') {
						cnt += 1;
					}

					q.add(new int[] { nr, nc });
				}
			}

			int size = locked.size();
			for (int i = 0; i < size; i++) {
				int[] cur = locked.poll();

				int r = cur[0];
				int c = cur[1];

				if (doors[map[r][c] - 'A']) {
					q.add(new int[] { r, c });
					continue;
				}

				locked.add(new int[] { r, c });
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			map = new char[n][m];

			for (int i = 0; i < n; i++) {
				map[i] = br.readLine().toCharArray();
			}

			init();
			getStart();

			char[] keys = br.readLine().toCharArray();

			if (keys[0] != '0') {
				for (char key : keys) {
					doors[key - 'a'] = true;
				}
			}

			bfs();

			sb.append(cnt + "\n");
		}

		System.out.print(sb);
	}
}