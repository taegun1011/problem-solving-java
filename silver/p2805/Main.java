package silver.p2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean decision(int[] arr, int height, int target) {
		long sum = 0;
		for (int i = 0; i < arr.length; i++)
			sum += 0l + Integer.max(arr[i] - height, 0);

		return sum >= target;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		// OOOOOOXXXXXX
		int s = 0, e = 1000000000, mid;
		while (s < e) { // s < e : 왼쪽 인덱스를 반환
			mid = (s + e + 1) / 2;

			if (decision(arr, mid, M))
				s = mid;
			else
				e = mid - 1;
		}

		System.out.println(s);
	}
}
