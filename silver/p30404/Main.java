package silver.p30404;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int last = Integer.MIN_VALUE, cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > last + K) {
				cnt++;
				last = arr[i];
			}
		}

		System.out.println(cnt);
	}
}
