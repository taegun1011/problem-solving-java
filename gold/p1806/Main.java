package gold.p1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int j = 0, sum = 0, ans = 100001;
		for (int i = 0; i < N; i++) {
			while (j < N && sum < S)
				sum += arr[j++];
			if (sum >= S)
				ans = Integer.min(ans, j - i); // 이미 +를 했으니까
			sum -= arr[i];
		}

		System.out.println(ans > N ? 0 : ans);
	}
}
