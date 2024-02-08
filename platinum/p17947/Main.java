package platinum.p17947;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 나머지가 K인, 사용 가능한 카드의 개수
		int[] mod = new int[K];
		int total = 4 * N;

		for (int i = 0; i < K; i++)
			mod[i] = (total - i) / K + 1;
		mod[0]--;

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			mod[Integer.parseInt(st.nextToken()) % K]--;
			mod[Integer.parseInt(st.nextToken()) % K]--;
		}

		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		mod[a % K]--;
		mod[b % K]--;
		int diff = Math.abs(a % K - b % K);

		// 남은 카드 수는 4*N - 2*(M+1)
		int[] low = new int[2 * N - (M + 1)];
		int[] high = new int[2 * N - (M + 1)];

		int j = 0;
		for (int i = 0; i < K; i++) {
			while (mod[i] > 0 && j < low.length) {
				low[j++] = i;
				mod[i]--;
			}
			if (j == low.length)
				break;
		}

		j = 0;
		for (int i = 0; i < K; i++) {
			while (mod[i] > 0 && j < high.length) {
				high[j++] = i;
				mod[i]--;
			}
			if (j == high.length)
				break;
		}

		int sum = 0;
		int i = 0;
		j = 0;
		while (i < low.length && j < high.length) {
			if (high[j] - low[i] > diff) {
				sum++;
				j++;
				i++;
			} else
				j++;
		}

		System.out.println(Integer.min(sum, M - 1));
	}
}
