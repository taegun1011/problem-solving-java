package silver.p1920;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int find(int[] arr, int num) {
		int s = 0, e = arr.length - 1, mid;
		while (s <= e) {
			mid = (s + e) / 2;
			if (num < arr[mid])
				e = mid - 1;
			else if (num == arr[mid])
				return 1;
			else
				s = mid + 1;
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(arr);
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		while (M-- > 0) {
			int num = Integer.parseInt(st.nextToken());
			sb.append(find(arr, num)).append('\n');
		}

		System.out.println(sb);
	}
}
