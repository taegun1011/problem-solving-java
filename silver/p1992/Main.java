package silver.p1992;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static String divideConquer(char[][] arr, int sr, int sc, int size) {
		char first = arr[sr][sc];

		if (size == 1)
			return String.valueOf(first);

		boolean flag = true;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (arr[sr + i][sc + j] != first) {
					flag = false;
					break;
				}
			}
		}

		if (flag)
			return String.valueOf(first);
		else {
			String ret = "(";
			ret += divideConquer(arr, sr, sc, size / 2);
			ret += divideConquer(arr, sr, sc + size / 2, size / 2);
			ret += divideConquer(arr, sr + size / 2, sc, size / 2);
			ret += divideConquer(arr, sr + size / 2, sc + size / 2, size / 2);
			ret += ")";
			return ret;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] arr = new char[N][N];

		for (int i = 0; i < N; i++)
			arr[i] = br.readLine().toCharArray();

		System.out.println(divideConquer(arr, 0, 0, N));
	}
}
