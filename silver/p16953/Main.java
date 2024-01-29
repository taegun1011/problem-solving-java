package silver.p16953;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long A = sc.nextLong();
		long B = sc.nextLong();

		Map<Long, Integer> M = new HashMap<>();
		Queue<Long> Q = new LinkedList<>();
		M.put(A, 0);
		Q.add(A);

		while (!Q.isEmpty()) {
			Long cur = Q.poll();
			if (cur == B)
				break;

			Long nxt = cur * 2;

			if (nxt <= B && !M.containsKey(nxt)) {
				Q.add(nxt);
				M.put(nxt, M.get(cur) + 1);
			}

			nxt = cur * 10 + 1;
			if (nxt <= B && !M.containsKey(nxt)) {
				Q.add(nxt);
				M.put(nxt, M.get(cur) + 1);
			}
		}

		System.out.println(M.containsKey(B) ? M.get(B) + 1 : -1);
	}
}
