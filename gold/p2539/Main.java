package gold.p2539;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean decision(int size, int left, int[] height) {
		int N = height.length;
		int i = 0;
		while (i < N) {
			if (height[i] == 0)
				i++;
			else {
				if (left == 0)
					return false;
				for (int j = 0; j < size && i + j < N; j++)
					if (height[i + j] > size)
						return false;

				left--;
				i += size;
			}
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		// c열에서 가장 높이 있는 칸
		int[] height = new int[c];

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int rr = Integer.parseInt(st.nextToken());
			int cc = Integer.parseInt(st.nextToken()) - 1;
			height[cc] = Integer.max(height[cc], rr);
		}

		// XXXXOOOO
		int s = 0, e = 1000000, mid;
		while (s < e) {
			mid = (s + e) / 2;
			if (decision(mid, N, height))
				e = mid;
			else
				s = mid + 1;
		}

		System.out.println(s);
	}
}
