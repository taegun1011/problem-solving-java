package gold.p2023;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static boolean check(long n) {
		for (long i = 2; i * i <= n; i++)
			if (n % i == 0)
				return false;

		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt() - 1;
		int start = (int) Math.pow(10, N);

		StringBuilder sb = new StringBuilder();

		Queue<Integer> Q = new LinkedList<>();
		Q.add(2);
		Q.add(3);
		Q.add(5);
		Q.add(7);

		while (!Q.isEmpty()) {
			int cur = Q.poll();
			if (cur > start) {
				sb.append(cur).append('\n');
				continue;
			}
			for (int i = 0; i < 10; i++) {
				int nxt = cur * 10 + i;
				if (check(nxt))
					Q.add(nxt);
			}
		}

		System.out.println(sb);
	}
}
