package gold.p2493;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] ans = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		Stack<Integer> S = new Stack<>();
		for (int i = arr.length - 1; i >= 0; i--) {
			while (!S.empty() && arr[S.peek()] < arr[i]) {
				ans[S.peek()] = i + 1;
				S.pop();
			}
			S.add(i);
		}

		for (int x : ans)
			sb.append(x + " ");
		System.out.println(sb);
	}
}
