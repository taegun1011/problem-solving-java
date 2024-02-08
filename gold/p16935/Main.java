package gold.p16935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static void filpLR(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M / 2; j++)
				swap(arr, i, j, i, M - 1 - j);
	}

	static void filpUD(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;

		for (int i = 0; i < M; i++)
			for (int j = 0; j < N / 2; j++)
				swap(arr, j, i, N - 1 - j, i);
	}

	static int[][] transpose(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;

		int[][] newArr = new int[M][N];

		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				newArr[i][j] = arr[j][i];

		return newArr;
	}

	static int[][] move(int[][] arr, int dir) {
		int N = arr.length;
		int M = arr[0].length;

		int[] pos = (dir == 1) ? new int[] { 1, 3, 0, 2 } : new int[] { 2, 0, 3, 1 };
		int[][] newArr = new int[N][M];

		for (int i = 0; i < 4; i++) {
			int fromR = i < 2 ? 0 : N / 2;
			int fromC = i % 2 == 0 ? 0 : M / 2;
			int toR = pos[i] < 2 ? 0 : N / 2;
			int toC = pos[i] % 2 == 0 ? 0 : M / 2;

			for (int j = 0; j < N / 2; j++)
				for (int k = 0; k < M / 2; k++)
					newArr[toR + j][toC + k] = arr[fromR + j][fromC + k];

		}

		return newArr;
	}

	static void swap(int[][] arr, int r1, int c1, int r2, int c2) {
		int temp = arr[r1][c1];
		arr[r1][c1] = arr[r2][c2];
		arr[r2][c2] = temp;
	}

	public static void main(String[] args) throws IOException {

		// 소행렬의 위치와 회전상태를 기억한다
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		while (R-- > 0) {
			int cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case 1:
				filpUD(arr);
				break;
			case 2:
				filpLR(arr);
				break;
			case 3:
				arr = transpose(arr);
				filpLR(arr);
				break;
			case 4:
				filpLR(arr);
				arr = transpose(arr);
				break;
			case 5:
				arr = move(arr, 1);
				break;
			case 6:
				arr = move(arr, -1);
			}
		}

		for (int[] row : arr) {
			for (int x : row)
				sb.append(x + " ");
			sb.append('\n');
		}

		System.out.println(sb);
	}
}
