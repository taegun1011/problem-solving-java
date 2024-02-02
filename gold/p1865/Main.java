package gold.p1865;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Tuple {
	int u;
	int v;
	int w;

	public Tuple(int u, int v, int w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}

}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static void solve() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[][] weight = new int[N][N];
		for (int[] row : weight) {
			Arrays.fill(row, (int) 1e9);
		}

		while (M + W > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());

			weight[u][v] = Integer.min(weight[u][v], w * (M > 0 ? 1 : -1));
			if (M > 0) {
				weight[v][u] = Integer.min(weight[v][u], w);
				M--;
			} else
				W--;
		}

		List<Tuple> Edges = new ArrayList<>();

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (weight[i][j] != (int) 1e9)
					Edges.add(new Tuple(i, j, weight[i][j]));

		int[] dist = new int[N];
		Arrays.fill(dist, (int) 1e9);
		dist[0] = 0;

		for (int i = 0; i < N - 1; i++)
			for (Tuple t : Edges)
				dist[t.v] = Integer.min(dist[t.v], dist[t.u] + t.w);

		boolean flag = false;
		for (int i = 0; i < N; i++)
			for (Tuple t : Edges) {
				if (dist[t.v] > dist[t.u] + t.w)
					flag = true;
				dist[t.v] = Integer.min(dist[t.v], dist[t.u] + t.w);
			}

		sb.append(flag ? "YES" : "NO").append('\n');
	}

	public static void main(String[] args) throws Exception {
		// E가 V^2보다 현저히 작기 때문에 벨만포드를 사용해야 한다
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0)
			solve();

		System.out.println(sb);
	}
}
