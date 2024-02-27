package gold.p27447;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final String S = "success";
	static final String F = "fail";

	// 그리디 + 시뮬레이션 -> 특정 규칙을 정해서 시뮬레이션 해봐라
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Queue<Integer> Q1 = new ArrayDeque<>();
		Queue<Integer> Q2 = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Integer.parseInt(st.nextToken());
			Q1.add(max);
		}

		int tg = 0, cf = 0;
		// 한번에 한 동작만 할것
		for (int t = 0; t <= max; t++) {

			if (!Q2.isEmpty() && t == Q2.peek()) {
				if (cf == 0)
					break;
				cf--;
				Q2.poll();
				continue;
			}

			if (Q1.isEmpty())
				continue;

			if (t < Q1.peek() - M)
				tg++;
			else if (t >= Q1.peek())
				break;
			else {
				if (tg > 0) {
					tg--;
					cf++;
					Q2.add(Q1.poll());
				} else
					tg++;
			}
		}

		System.out.println(Q1.isEmpty() && Q2.isEmpty() ? S : F);
	}
}
//큐는 떠올리지 못했다. 시뮬레이션, 구현할 때는 자료구조를 활용해 문제를 그대로 코드로 옮길 생각을 해보자