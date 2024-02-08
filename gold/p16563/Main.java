package gold.p16563;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int size = 5000000;

		// 에라토스테네스 체를 변형해 각 값에 '1이 아닌 가장 작은 약수'를 저장하면 소인수분해도 금방 할 수 있다
		int[] factor = new int[size + 1];
		factor[1] = 1;
		for (int i = 2; i <= size; i++) {
			if (factor[i] != 0)
				continue;
			for (int j = i; j <= size; j += i) {
				if (factor[j] != 0)
					continue;
				factor[j] = i;
			}
		}

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (N-- > 0) {
			int n = Integer.parseInt(st.nextToken());
			while (n > 1) {
				sb.append(factor[n] + " ");
				n /= factor[n];
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}
}
