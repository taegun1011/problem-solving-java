package silver.p30625;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int divisor = 1000000007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] dance = new int[N];
		int[] dir = new int[N];
		long total = 1;
		int numOf1 = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			dance[i] = Integer.parseInt(st.nextToken());
			dir[i] = Integer.parseInt(st.nextToken());
			total *= (dir[i] == 0) ? 1 : M - 1;

			total %= divisor;
		}

		long subtotal = 1;
		for (int i = 0; i < N; i++) {
			subtotal *= (dir[i] == 0 || (dir[i] == 1 && numOf1 == 0)) ? 1 : M - 1;
			numOf1 += (dir[i] == 1) ? 1 : 0;
			subtotal %= divisor;
		}

		// 춤 하나만 출 경우 처리
		if (M == 1 && numOf1 >= 2) {
			System.out.println(0);
			return;
		}

		// 나머지 연산은 +, -, *까진 괜찮은데 /에서 위험

		// i번째 라운드에서 틀렸을 때
		long ans = total;
		for (int i = 0; i < N; i++) {
			ans += (dir[i] == 0) ? total * (M - 1) : subtotal;
			ans %= divisor;
		}

		System.out.println(ans);
	}
}
