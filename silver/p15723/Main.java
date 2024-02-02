package silver.p15723;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		boolean[][] graph = new boolean[26][26];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			char u = str.charAt(0);
			char v = str.charAt(5);

			graph[u - 'a'][v - 'a'] = true;
		}

		for (int k = 0; k < 26; k++)
			for (int i = 0; i < 26; i++)
				for (int j = 0; j < 26; j++)
					graph[i][j] = graph[i][j] | (graph[i][k] & graph[k][j]);

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			char u = str.charAt(0);
			char v = str.charAt(5);

			System.out.println(graph[u - 'a'][v - 'a'] ? 'T' : 'F');
		}
	}
}
