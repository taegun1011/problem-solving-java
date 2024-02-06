package gold.p2473;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(arr);

		int a = 0, b = 0, c = 0;

		if (arr[0] >= 0) {
			a = 0;
			b = 1;
			c = 2;
		}

		else if (arr[N - 1] <= 0) {
			a = N - 3;
			b = N - 2;
			c = N - 1;
		}

		else {
			long min = Long.MAX_VALUE;
			loop: for (int i = 0; i < N - 2; i++) {
				// 합이 arr[i]에 가까운 j, k를 찾는다

				int j = i + 1, k = N - 1;
				while (j < k) {
					long sum = 0l + arr[i] + arr[j] + arr[k];
					if (sum == 0) {
						a = i;
						b = j;
						c = k;
						break loop;
					}

					else {
						long abs = Long.max(sum, -sum);
						if (abs < min) {
							a = i;
							b = j;
							c = k;
							min = abs;
						}

						if (sum < 0)
							j++;
						else
							k--;
					}
				}
			}
		}

		System.out.println(arr[a] + " " + arr[b] + " " + arr[c]);
	}
}
