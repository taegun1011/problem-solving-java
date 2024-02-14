package platinum.p14939;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[][] dir = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static final int N = 10;

	static void reverse(char[][] graph, int cr, int cc) {
		for (int i = 0; i < 5; i++) {
			int nr = cr + dir[i][0];
			int nc = cc + dir[i][1];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;
			graph[nr][nc] = graph[nr][nc] == '#' ? 'O' : '#';
		}
	}

	public static void main(String[] args) throws Exception {
		// 맨 윗줄만 비트마스킹으로 선택 -> 나머지는 윗줄에 따라 테스트

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] graph = new char[N][N];
		for (int i = 0; i < graph.length; i++)
			graph[i] = br.readLine().toCharArray();

		int ans = -1;
		for (int i = 0; i < (1 << N); i++) {
			char[][] temp = new char[N][N];
			for (int j = 0; j < 10; j++)
				temp[j] = Arrays.copyOf(graph[j], graph[j].length);
			int result = solve(temp, i);
			if (result >= 0) {

				if (ans == -1)
					ans = result;
				else
					ans = Integer.min(ans, result);
			}
		}
		System.out.println(ans);
	}

	static int solve(char[][] graph, int field) {
		int cnt = 0;
		// 첫 줄 뒤집기
		for (int i = 0; i < N; i++)
			if ((field & (1 << i)) > 0) {
				cnt++;
				reverse(graph, 0, i);
			}

		// 위만 확인한다
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (graph[i - 1][j] == 'O') {
					cnt++;
					reverse(graph, i, j);
				}
			}
		}

		for (int i = 0; i < N; i++)
			if (graph[N - 1][i] == 'O') {
				return -1;
			}

		return cnt;
	}
}
