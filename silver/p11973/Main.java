package silver.p11973;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static int N;
	static int K;

	static int ub(int x) {
		// x보다 큰 원소 중 최소를 반환
		int s = 0, e = arr.length - 1, m;
		while (s <= e) {
			m = (s + e) / 2;
			if (x < arr[m])
				e = m - 1;
			else
				s = m + 1;
		}

		return s;
	}

	static boolean check(int x) {
		int cur = arr[0];
		for (int i = 0; i < K; i++) {
			int idx = ub((int) Long.min(cur + x * 2, 1000000000));
			if (idx == N)
				return true;
			cur = arr[idx];
		}

		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		Arrays.sort(arr);

		int s = 0, e = 1000000000, m;
		// XXXXOOOO
		// 매개변수 탐색 + 이분 탐색
		while (s < e) {
			m = (s + e) / 2;
			if (check(m))
				e = m;
			else
				s = m + 1;
		}

		System.out.println(s);
	}
}
