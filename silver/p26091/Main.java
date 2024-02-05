package silver.p26091;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(arr);

		int j = 0;
		int cnt = 0;
		for (int i = N - 1; i >= 0; i--) {
			while (i > j && arr[i] + arr[j] < M) {
				j++;
			}
			if (i > j && arr[i] + arr[j] >= M) {
				cnt++;
				j++;
			}
		}

		System.out.println(cnt);
	}
}
