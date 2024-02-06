package gold.p15823;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static boolean decision(int size, int total, int[] arr) {
		Set<Integer> S = new HashSet<>();
		int N = arr.length;

		// 여기를 투포인터로 구현하자
		int i = 0, j = 0;
		while (i + size <= N) {
			if (j == N)
				break;

			while (j < N && j < i + size) {
				if (!S.contains(arr[j])) {
					S.add(arr[j]);
					j++;
				} else {
					while (i < j) {
						S.remove(arr[i]);
						i++;
						if (arr[i - 1] == arr[j])
							break;
					}
				}
			}

			if (S.size() == size) {
				S.clear();
				i = j;
				total--;
				if (total == 0)
					return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] id = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < id.length; i++)
			id[i] = Integer.parseInt(st.nextToken());

		int s = 1, e = 100000, mid;
		// OOOOXXXXX
		while (s < e) {
			mid = (s + e + 1) / 2;
			if (decision(mid, M, id))
				s = mid;
			else
				e = mid - 1;
		}

		System.out.println(s);
	}
}
