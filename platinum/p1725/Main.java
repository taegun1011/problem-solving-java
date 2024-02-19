package platinum.p1725;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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

		while (s >= left || e <= right) {
			int length = e - s + 1;
			b = Long.max(b, 1l * length * height);

			if (e == right) {
				if (s == left)
					break;
				else
					height = Integer.min(height, arr[--s]);
			} else if (s == left) {
				height = Integer.min(height, arr[++e]);
			} else if (arr[s - 1] < arr[e + 1]) {
				height = Integer.min(height, arr[++e]);
			} else
				height = Integer.min(height, arr[--s]);
		}
		return Long.max(a, b);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());

		System.out.println(recursion(arr, 0, N));
	}
}
