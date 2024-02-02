package gold.p17298;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		int[] NGE = new int[N], arr = new int[N];
		Arrays.fill(NGE, -1);

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Stack<Integer> S = new Stack<>();
		for (int i = 0; i < N; i++) {
			while (!S.empty() && arr[S.peek()] < arr[i]) {
				NGE[S.pop()] = arr[i];
			}
			S.push(i);
		}

		for (int x : NGE)
			sb.append(x).append(' ');

		System.out.println(sb);
	}
}
