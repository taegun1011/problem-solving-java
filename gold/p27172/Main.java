package gold.p27172;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int size = 1000000;

		int[] arr = new int[N];
		int[] num = new int[size + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		boolean[] visited = new boolean[size + 1];
		for (int x : arr)
			visited[x] = true;

		for (int x : arr) {
			for (int i = x; i <= size; i += x) {
				if (visited[i]) {
					num[i]--;
					num[x]++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int x : arr)
			sb.append(num[x]).append(' ');

		System.out.println(sb);
	}
}
