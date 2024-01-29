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

		// 대충 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[] count = new int[N];
		st = new StringTokenizer(br.readLine());
		int k = 0;

		// x를 k번 입력받기
		while (k < K) {
			k++;

			// 각 값의 등장 횟수 기록
			count[Integer.parseInt(st.nextToken())]++;
		}

		// 1 <= i < N인 i에 대해
		// j = 0 또는 i % j == 0인
		int[] res = new int[N];
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N; j += i) {
				res[j] += count[i];
			}
		}

		// 부분합 계산, 오버플로우 방지 위해 long으로 선언
		long[] sum = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			sum[i] = sum[i - 1] + res[i - 1];
		}

		int Q = Integer.parseInt(br.readLine());
		while (Q > 0) {
			Q--;
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());

			// 부분합 계산 및 출력
			sb.append(sum[R + 1] - sum[L]).append('\n');
		}
		System.out.println(sb);
	}
}
