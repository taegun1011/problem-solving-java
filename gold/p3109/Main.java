package gold.p3109;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] dir = { { -1, -1 }, { 0, -1 }, { 1, -1 } };
	static char[][] graph;
	static boolean[][] visited;
	static int R;
	static int C;

	static boolean DFS(int cr, int cc) {
		visited[cr][cc] = true;
		if (cc == 0) {
			return true;
		}

		for (int i = 0; i < 3; i++) {
			int nr = cr + dir[i][0];
			int nc = cc + dir[i][1];
			if (nr < 0 || nr >= R || nc < 0)
				continue;
			if (graph[nr][nc] == '.' && !visited[nr][nc]) {
				if (DFS(nr, nc))
					return true;
			}
		}

		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		graph = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++)
			graph[i] = br.readLine().toCharArray();

		int ans = 0;
		for (int i = 0; i < R; i++)
			ans += DFS(i, C - 1) ? 1 : 0;

		System.out.println(ans);
	}
}
