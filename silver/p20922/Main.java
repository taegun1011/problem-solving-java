package silver.p20922;

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
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int[] cnt = new int[100001];

		int j = 0, ans = 0;
		for (int i = 0; i < N; i++) {
			while (j < N && cnt[arr[j]] < K) {
				cnt[arr[j]]++;
				j++;
			}
			cnt[arr[i]]--;
			ans = Integer.max(ans, j - i);
		}

		System.out.println(ans);
	}
}
