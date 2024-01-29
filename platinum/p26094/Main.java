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
		boolean[] visited = new boolean[N + 1];

		Deque<Integer> D = new LinkedList<>();
		PriorityQueue<Integer> min_pq = new PriorityQueue<>();
		PriorityQueue<Integer> max_pq = new PriorityQueue<>((o1, o2) -> {
			return -Integer.compare(o1, o2);
		});

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
				p = type ? D.getFirst() : D.getLast();
				if (p == 0) {
					PriorityQueue<Integer> pq = type ? min_pq : max_pq;
					while (!pq.isEmpty() && visited[pq.peek()])
						pq.poll();

					if (pq.isEmpty()) {
						if (type) {
							D.pollFirst();
							sb.append(D.pollFirst()).append('\n');
						} else {
							D.pollLast();
							sb.append(D.pollLast()).append('\n');
						}
					} else {
						visited[pq.peek()] = true;
						sb.append(pq.poll()).append('\n');

					}

				} else {
					sb.append(p).append('\n');
					if (type)
						D.pollFirst();
					else
						D.pollLast();
				}

			}
		}

		System.out.println(sb);
	}
}
