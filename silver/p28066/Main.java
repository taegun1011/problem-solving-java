package silver.p28066;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(), K = sc.nextInt();

		Queue<Integer> Q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			Q.add(i);
		}

		while (Q.size() > 1) {
			int start = Q.poll();

			for (int i = 1; i < K; i++) {
				if (!Q.isEmpty()) {
					Q.poll();
				}
			}
			Q.add(start);
		}
		System.out.println(Q.peek());
	}
}
