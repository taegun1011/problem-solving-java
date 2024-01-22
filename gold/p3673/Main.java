package gold.p3673;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void Solve() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());

		int[] arr = new int[n];
		long[] sum = new long[n + 1];
		int[] sum_mod = new int[d];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i + 1] = sum[i] + arr[i];
			sum_mod[(int) (sum[i + 1] % d)]++;
		}

		int cnt = sum_mod[0];
		for (int i = 0; i < d; i++) {
			cnt += 1L * sum_mod[i] * (sum_mod[i] - 1) / 2;
		}

		sb.append(cnt + "\n");
	}

	public static void main(String[] args) throws IOException {
		int TC = Integer.parseInt(br.readLine());

		while (TC > 0) {
			TC--;
			Solve();
		}

		System.out.println(sb);
	}
}
