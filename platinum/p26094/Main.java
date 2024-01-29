package platinum.p26094;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());

		Deque<Integer> D = new LinkedList<>();
		PriorityQueue<Integer> min_pq = new PriorityQueue<>();
		PriorityQueue<Integer> max_pq = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1, o2));

		int Q = Integer.parseInt(st.nextToken());

		boolean type = true; // T : 앞, 최소힙 F : 뒤, 최대힙

		int p;
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {

			case 0:
				p = Integer.parseInt(st.nextToken());
				if (type)
					D.addFirst(p);
				else
					D.addLast(p);
				break;

			case 1:
				// 현재까지 덱의 원소를 우선순위 큐에 저장
				while (!D.isEmpty()) {
					if (D.peek() != 0) {
						min_pq.add(D.peek());
						max_pq.add(D.peek());
					}
					D.poll();
				}
				// 여기부턴 우선순위 큐로 해라
				D.add(0);
				type = true;
				break;

			// 2 최소힙/최대힙 전환, 방향 전환
			case 2:
				type = !type;
				break;

			// 3 맨 앞이 0 -> 힙에서 선택
			case 3:
				p = type ? D.pollFirst() : D.pollLast();
				if (p == 0) {
					PriorityQueue<Integer> pq = type ? min_pq : max_pq;

					sb.append(pq.poll()).append('\n');

					if (!pq.isEmpty()) {
						if (type)
							D.addFirst(0);
						else
							D.addLast(0);
					}
				} else {
					sb.append(p).append('\n');
				}

			}
		}

		System.out.println(sb);
	}
}
