package silver.p28103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long binary_search(int cost, int min, long N, long X) {
		long s = 0, e = N, mid;

		// mid : 현재 상품을 구매할 개수
		while (s < e) {
			mid = (s + e + 1) / 2;
			if (mid * cost + min * (N - mid) <= X)
				s = mid;
			else
				e = mid - 1;
		}

		return s;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		long N = Long.parseLong(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long X = Long.parseLong(st.nextToken());

		int[] item = new int[M];
		long[] ans = new long[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < item.length; i++)
			item[i] = Integer.parseInt(st.nextToken());

		int min = item[item.length - 1];
		for (int i = 0; i < item.length; i++) {
			ans[i] = binary_search(item[i], min, N, X);
			N -= ans[i];
			X -= item[i] * ans[i];
		}

		for (long x : ans)
			sb.append(x + " ");
		System.out.println(sb);
	}
}
