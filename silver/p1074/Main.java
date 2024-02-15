package silver.p1074;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] pos = { { 0, 1 }, { 2, 3 } };

	static int recursion(int sr, int sc, int size, int r, int c) {
		if (size == 2)
			return pos[r - sr][c - sc];

		int mr = sr + size / 2, mc = sc + size / 2, subArea = size * size / 4;
		if (r >= mr)
			sr = mr;
		if (c >= mc)
			sc = mc;

		int area = 0;
		if (sr == mr)
			area += subArea * 2;
		if (sc == mc)
			area += subArea;
		return recursion(sr, sc, size / 2, r, c) + area;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int size = (int) Math.pow(2, N);

		System.out.println(recursion(0, 0, size, r, c));
	}
}
