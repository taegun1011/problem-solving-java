package silver.p2961;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] sweet = new int[N];
		int[] bitter = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sweet[i] = Integer.parseInt(st.nextToken());
			bitter[i] = Integer.parseInt(st.nextToken());
		}

		int answer = Integer.MAX_VALUE;
		for (int i = 1; i < (1 << N); i++) {
			int mul = 1, sum = 0;
			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) != 0) {
					mul *= sweet[j];
					sum += bitter[j];
				}
			}

			answer = Integer.min(Math.abs(mul - sum), answer);
		}

		System.out.println(answer);
	}
}
