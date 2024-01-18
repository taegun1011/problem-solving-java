package gold.p2900;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static StringBuilder sb;

	public static long prefix(int L, int R, int[] sumCount) {
		long sum = (L == 0) ? K : 0;
		System.out.println(L + " " + R);
		for (int i = 1; i < N; i++) {

			int head = L % i == 0 ? 1 : (L / i + 1) * i - L + 1;
			int tail = (R - L + 1) - head;

			sum += 1l * sumCount[i] * (tail / i + 1);
		}

		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[] sumCount = new int[N];
		st = new StringTokenizer(br.readLine());
		int i = 0;
		while (i < K) {
			i++;
			sumCount[Integer.parseInt(st.nextToken())]++;
		}

		int Q = Integer.parseInt(br.readLine());
		while (Q > 0) {
			Q--;
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());

			sb.append(prefix(L, R, sumCount)).append('\n');
		}
		System.out.println(sb);
	}
}
