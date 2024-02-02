package gold.p11404;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[][] dist = new int[N][N];
		for (int i = 0; i < dist.length; i++)
			for (int j = 0; j < dist.length; j++)
				dist[i][j] = (i == j) ? 0 : (int) 1e9;

		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			// 중복 간선이 있다
			dist[u][v] = Integer.min(w, dist[u][v]);
		}

		for (int k = 0; k < N; k++)
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					dist[i][j] = Integer.min(dist[i][j], dist[i][k] + dist[k][j]);

		for (int[] row : dist) {
			for (int x : row)
				sb.append((x == (int) 1e9 ? 0 : x) + " ");
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
