package bronze.p11035;

//package bronze.11035로 해야하나?

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];

		int cr = 0, cc = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					cr = i;
					cc = j;
				}
			}
		}

		int cnt = Integer.parseInt(br.readLine());
		while (cnt > 0) {
			cnt--;
			char cmd = br.readLine().charAt(0);
			switch (cmd) {
			case 'U':
			case 'D':
			case 'L':
			case 'R':
			case 'X':

			}
		}
	}

}
