package gold.p2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(arr);
		if (arr[0] >= 0) {
			System.out.println(arr[0] + " " + arr[1]);
			return;
		}

		if (arr[N - 1] <= 0) {
			System.out.println(arr[N - 2] + " " + arr[N - 1]);
			return;
		}

		// 음수와 양수가 섞여있는 경우
		int i = 0, j = N - 1, min = Integer.MAX_VALUE;
		int a = 0, b = 0;
		while (i < j) {
			int abs = Integer.max(arr[i] + arr[j], -arr[i] - arr[j]);
			if (abs == 0) {
				System.out.println(arr[i] + " " + arr[j]);
				return;
			} else {
				if (abs < min) {
					a = i;
					b = j;
					min = abs;
				}

				if (arr[i] + arr[j] < 0)
					i++;
				else
					j--;
			}
		}
		System.out.println(arr[a] + " " + arr[b]);
	}
}
