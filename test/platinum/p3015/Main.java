package platinum.p3015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Pair {
	int h;
	int no;

	public Pair(int h, int no) {
		this.h = h;
		this.no = no;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());

		long cnt = 0;

		// 오큰수
		Stack<Pair> S = new Stack<>();
		for (int i = 0; i < N; i++) {
			Pair p = new Pair(arr[i], 1);
			while (!S.empty() && S.peek().h <= arr[i]) {
				cnt += S.peek().no;
				if (S.peek().h == arr[i])
					p.no += S.peek().no;
				S.pop();
			}
			S.push(p);
		}

		S.clear();
		for (int i = N - 1; i >= 0; i--) {
			while (!S.empty() && S.peek().h < arr[i]) {
				S.pop();
				cnt++;
			}
			S.push(new Pair(arr[i], 1));
		}

		System.out.println(cnt);
	}
}
