package gold.p2617;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// graph[u][v] : u가 v보다 무겁다는게 확실하다, v가 u보다 가볍다는게 확실하다
		boolean[][] graph = new boolean[N][N];

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;

			graph[u][v] = true;
		}

		// 플로이드로 연결관계 확장
		for (int k = 0; k < N; k++)
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					graph[i][j] = graph[i][j] | (graph[i][k] & graph[k][j]);

		// 둘 중 하나가 N/2보다 큰 구슬의 개수를 센다
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			int heavy = 0;
			int light = 0;
			for (int j = 0; j < N; j++) {
				if (graph[i][j])
					heavy++;
				if (graph[j][i])
					light++;
			}

			if (heavy > N / 2 || light > N / 2)
				cnt++;
		}

		System.out.println(cnt);
	}
}
