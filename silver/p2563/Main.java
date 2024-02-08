package silver.p2563;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		boolean[][] visited = new boolean[100][100];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st;
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					visited[a + i][b + j] = true;
				}
			}
		}

		int cnt = 0;
		for (int i = 0; i < 100; i++)
			for (int j = 0; j < 100; j++)
				cnt += visited[i][j] ? 1 : 0;

		System.out.println(cnt);
	}
}
