package silver.p2164;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		Queue<Integer> Q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++)
			Q.add(i);

		int last = 0;
		while (!Q.isEmpty()) {
			last = Q.poll();
			if (!Q.isEmpty())
				Q.add(Q.poll());
		}

		System.out.println(last);
	}
}
