package silver.p16926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static void rotate(int sr, int sc, int er, int ec, int[][] board) {

		int last = board[sr][ec];
		for (int i = sr + 1; i <= er; i++) {
			board[i - 1][ec] = board[i][ec];
		}

		for (int j = ec - 1; j >= sc; j--) {
			board[er][j + 1] = board[er][j];
		}

		for (int i = er - 1; i >= sr; i--) {
			board[i + 1][sc] = board[i][sc];
		}

		for (int j = sc + 1; j <= ec; j++) {
			board[sr][j - 1] = board[sr][j];
		}

		board[sr][ec - 1] = last;

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		int[][] board = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}

		}

		int i = 0;
		int j = 0;

		// n = 2 m = 2

		while (i < (int) (n / 2) && j < (int) (m / 2)) {
			int length = 2 * ((n - 2 * i) + (m - 2 * j)) - 4;
			if (length == 0) {
				break;
			}
			for (int t = 0; t < r % length; t++) {
				rotate(i, j, n - i - 1, m - j - 1, board);
			}
			i++;
			j++;
		}

		for (i = 0; i < board.length; i++) {
			for (j = 0; j < board[0].length; j++) {
				sb.append(board[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
