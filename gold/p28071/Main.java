package gold.p28071;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		// dp[i] = i개 사탕을 사기 위해 필요한 상자의 최소 개수
		int[] dp = new int[90001];
		Arrays.fill(dp, (int) 1e9);
		dp[0] = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++)
			for (int j = arr[i]; j <= 90000; j++)
				dp[j] = Integer.min(dp[j], dp[j - arr[i]] + 1);

		int ans = 0;
		for (int i = 90000; i >= 0; i--)
			if (i % K == 0 && dp[i] <= M) {
				ans = i;
				break;
			}

		System.out.println(ans);
	}
}

//BFS로 풀이가 가능하다
//-> '90000'이라는 상태공간 안에서 모든 경우를 확인할 수 있다
//-> dp 배열도 90000으로 설정할 수 있겠구나!