package gold.p17822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int r;
	int c;

	public Pair(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	private static Deque<Integer>[] D;
	private static int[][] arr;
	private static int N;
	private static int M;
	private static int T;
	static final int NONE = (int) 1e9;
	static int[][] MoveDir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static void rotate(int i, int d, int k) {
		for (int idx = 0; idx < k; idx++) {
			if (d == 0)
				D[i].addFirst(D[i].pollLast());
			else
				D[i].addLast(D[i].pollFirst());
		}
	}

	static void rotateAll(int x, int d, int k) {
		for (int i = 0; i < N; i++)
			if ((i + 1) % x == 0)
				rotate(i, d, k);

		for (int i = 0; i < N; i++) {
			int j = 0;
			for (int num : D[i])
				arr[i][j++] = num;
		}
	}

	static void set() {

		boolean flag = false;

		int sum = 0, cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != NONE) {
					sum += arr[i][j];
					cnt++;

					flag |= clear(i, j);
				}
			}
		}

		if (!flag) {
			double avg = 1.0 * sum / cnt;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == NONE)
						continue;
					arr[i][j] += (double) arr[i][j] > avg ? -1 : ((double) arr[i][j] < avg ? 1 : 0);
				}
			}
			// 평균 구해서 갱신하기
		}

		for (int i = 0; i < N; i++) {
			D[i].clear();
			for (int j = 0; j < M; j++)
				D[i].addLast(arr[i][j]);
		}
	}

	// BFS
	static boolean clear(int sr, int sc) {
		int num = arr[sr][sc];
		int cnt = 1;

		Queue<Pair> Q = new ArrayDeque<>();
		Q.add(new Pair(sr, sc));

		boolean[][] visited = new boolean[N][M];
		visited[sr][sc] = true;

		while (!Q.isEmpty()) {
			Pair cur = Q.poll();
			int cr = cur.r;
			int cc = cur.c;

			for (int i = 0; i < 4; i++) {
				int nr = cr + MoveDir[i][0];
				int nc = (cc + MoveDir[i][1] + M) % M;
				if (nr < 0 || nr >= N)
					continue;
				if (arr[nr][nc] == num && !visited[nr][nc]) {
					cnt++;
					arr[nr][nc] = NONE;

					visited[nr][nc] = true;
					Q.add(new Pair(nr, nc));
				}
			}
		}

		if (cnt > 1) {
			arr[sr][sc] = NONE;
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		D = new Deque[N];
		for (int i = 0; i < N; i++)
			D[i] = new LinkedList<>();
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				D[i].addLast(Integer.parseInt(st.nextToken()));
		}

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			rotateAll(x, d, k);
			set();
		}

		int sum = 0;
		for (int[] row : arr) {
			for (int x : row)
				if (x != NONE)
					sum += x;
		}

		System.out.println(sum);
	}
}
