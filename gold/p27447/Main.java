package gold.p27447;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final String S = "success";
	static final String F = "fail";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		// 서빙해야할 때 준비된 커피가 없으면 fail
		// 가능하면 커피 담기는 arr[i] - M에 하고, 남은 시간에는 토기를 만들자
		// arr[i] - M -> 토기가 남았으면 커피를 담는다, 없으면 토기를 만든다

	}
}
