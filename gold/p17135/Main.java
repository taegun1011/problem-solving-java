package gold.p17135;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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

class Tuple extends Pair {
	int left;

	public Tuple(int r, int c, int left) {
		super(r, c);
		this.left = left;
	}
}

public class Main {
	static int[][] graph;
	static Queue<Pair> enemy;
	static int N;
	static int M;
	static int D;

	static int[][] dir = { { 0, -1 }, { -1, 0 }, { 0, 1 } };

	static Pair attack(int start) {
		Queue<Tuple> Q = new ArrayDeque<>();
		Q.add(new Tuple(N - 1, start, D - 1));

		boolean[][] visited = new boolean[N][M];
		visited[N - 1][start] = true;

		while (!Q.isEmpty()) {
			Tuple cur = Q.poll();
			int cr = cur.r;
			int cc = cur.c;
			int left = cur.left;

			if (graph[cr][cc] == 1) {
				return new Pair(cr, cc);
			}

			if (left == 0)
				continue;

			for (int i = 0; i < 3; i++) {
				int nr = cr + dir[i][0];
				int nc = cc + dir[i][1];
				if (nr < 0 || nc < 0 || nc >= M)
					continue;
				if (!visited[nr][nc]) {
					visited[nr][nc] = true;
					Q.add(new Tuple(nr, nc, left - 1));
				}
			}
		}

		return null;
	}

	static int move() {
		int left = 0;
		for (int i = N - 1; i > 0; i--)
			for (int j = 0; j < M; j++)
				left += (graph[i][j] = graph[i - 1][j]);
		Arrays.fill(graph[0], 0);
		return left;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		graph = new int[N][M];
		int[][] input = new int[N][M];
		enemy = new ArrayDeque<>();
		Queue<Pair> initialEnemy = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				input[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++)
				if (input[i][j] == 1)
					initialEnemy.add(new Pair(i, j));
		}

		int ans = 0;

		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				for (int k = j + 1; k < M; k++) {
					int kill = 0;

					for (int l = 0; l < N; l++)
						graph[l] = Arrays.copyOf(input[l], M);

					do {
						Pair iTarget = attack(i);
						Pair jTarget = attack(j);
						Pair kTarget = attack(k);

						if (iTarget != null) {
							kill += graph[iTarget.r][iTarget.c];
							graph[iTarget.r][iTarget.c] = 0;
						}
						if (jTarget != null) {
							kill += graph[jTarget.r][jTarget.c];
							graph[jTarget.r][jTarget.c] = 0;
						}
						if (kTarget != null) {
							kill += graph[kTarget.r][kTarget.c];
							graph[kTarget.r][kTarget.c] = 0;
						}

					} while (move() > 0);
					ans = Integer.max(ans, kill);
				}
			}
		}
		System.out.println(ans);
	}
}
