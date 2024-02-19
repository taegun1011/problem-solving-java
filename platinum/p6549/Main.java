package platinum.p6549;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long recursion(int[] arr, int left, int size) {
		if (size == 1)
			return arr[left];
		int right = left + size - 1;
		long a = Long.max(recursion(arr, left, size / 2), recursion(arr, left + size / 2, size - size / 2));
		long b = 0;
		int mid = left + size / 2;
		int s = mid - 1, e = mid;

		int height = Integer.min(arr[s], arr[e]);

		while (s != left && e != right) {
			b = Long.max(b, 1l * (e - s + 1) * height);

			if (arr[s - 1] < arr[e + 1])
				height = Integer.min(height, arr[++e]);
			else
				height = Integer.min(height, arr[--s]);
		}

		while (s != left) {
			b = Long.max(b, 1l * (e - s + 1) * height);
			height = Integer.min(height, arr[--s]);
		}

		while (e != right) {
			b = Long.max(b, 1l * (e - s + 1) * height);
			height = Integer.min(height, arr[++e]);
		}

		return Long.max(a, b);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N;
		while ((N = Integer.parseInt(st.nextToken())) != 0) {
			int[] arr = new int[N];
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());

			sb.append(recursion(arr, 0, N)).append('\n');
			st = new StringTokenizer(br.readLine());
		}

		System.out.println(sb);
	}
}
