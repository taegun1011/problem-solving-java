package silver.p24954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int permutation(int cur, int end, int[] arr, boolean[] visited, int[] price, int[][] discount) {
		if (cur == end) {
			int[] temp_price = Arrays.copyOf(price, end);

			int sum = 0;
			for (int x : arr) {
				sum += temp_price[x];
				for (int i = 0; i < end; i++) {
					temp_price[i] -= discount[x][i];
					temp_price[i] = temp_price[i] > 0 ? temp_price[i] : 1;
				}
			}
			return sum;
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < end; i++) {
			if (!visited[i]) {

				boolean[] vv = Arrays.copyOf(visited, end);
				vv[i] = true;
				arr[cur] = i;
				int p = permutation(cur + 1, end, arr, vv, price, discount);
				min = min < p ? min : p;
			}
		}

		return min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] price = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}

		int[][] discount = new int[N][N];
		for (int i = 0; i < N; i++) {
			int rev = Integer.parseInt(br.readLine());
			while (rev > 0) {
				rev--;
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
				discount[i][a - 1] = d;
			}
		}

		System.out.println(permutation(0, N, new int[N], new boolean[N], price, discount));
	}
}
