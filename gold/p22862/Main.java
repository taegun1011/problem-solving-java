package gold.p22862;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int odd = 0;
		int even = 0;
		int ans = 0;
		for (int right = 0; right < N; right++) {
			// 홀수면
			if (arr[right] % 2 != 0) {
				odd++;
				if (odd > K) {

					ans = Math.max(ans, even);
					while (true) {
						if (arr[left] % 2 != 0) {
							odd--;
						} else {
							even--;
						}

						left++;
						if (odd <= K)
							break;

					}
				}
			} else {
				even++;
			}
		}

		ans = Math.max(ans, even);
		System.out.println(ans);
	}

}
