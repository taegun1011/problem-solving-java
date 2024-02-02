package gold.p2900;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[] count = new int[N];
		st = new StringTokenizer(br.readLine());
		int i = 0;
		while (i < K) {
			i++;
			count[Integer.parseInt(st.nextToken())]++;
		}

		int[] res = new int[N];
		for (i = 1; i < N; i++) {
			for (int j = 0; j < N; j += i) {
				res[j] += count[i];
			}
		}

		long[] sum = new long[N + 1];
		for (i = 1; i <= N; i++) {
			sum[i] = sum[i - 1] + res[i - 1];
		}

		int Q = Integer.parseInt(br.readLine());
		while (Q > 0) {
			Q--;
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());

			sb.append(sum[R + 1] - sum[L]).append('\n');
		}
		System.out.println(sb);
	}
}