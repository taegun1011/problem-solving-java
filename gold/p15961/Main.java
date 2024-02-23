package gold.p15961;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		// 쿠폰은 0일 때 1을 더해준다

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];

		int d = Integer.parseInt(st.nextToken());
		int[] sum = new int[d];

		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine()) - 1;

		int s = 0, e = s + k - 1;
		int total = 0;
		// 먼저 슥 본다
		for (int i = s; i <= e; i++) {
			int cur = arr[i];
			if (sum[cur] == 0)
				total++;
			sum[cur]++;
		}
		int ans = total + (sum[c] == 0 ? 1 : 0);

		// 시작 값을 가지고 슬라이딩 윈도우로 한바퀴 빙 돈다 O(N)
		do {
			// 맨 앞에 있던 초밥을 뺀다
			int h = arr[s];
			sum[h]--;
			// 해당 초밥의 개수가 0이 되면 종류를 줄인다
			if (sum[h] == 0)
				total--;
			// 맨 뒤에서 한칸 더 뒤의 초밥을 더한다
			e = (e + 1) % N;
			int t = arr[e];
			sum[t]++;
			// 새로 생겼으면 종류를 늘린다
			if (sum[t] == 1)
				total++;
			// 쿠폰에 포함된 초밥이 현재 윈도우 안에 없으면 한 개 더 추가
			// 현재 종류와 기존 최댓값을 비교
			ans = Integer.max(ans, total + (sum[c] == 0 ? 1 : 0));
		} while (++s < N);

		System.out.println(ans);
	}
}
