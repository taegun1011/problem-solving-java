package gold.p11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pair {
	int s;
	int e;

	public Pair(int s, int e) {
		this.s = s;
		this.e = e;
	}

}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Pair> L = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			L.add(new Pair(s, e));
		}

		L.sort((o1, o2) -> Integer.compare(o1.s, o2.s));

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		int ans = 0;
		for (Pair p : L) {

			// 이번 강의 시작 전 끝난 강의를 모두 없앤다
			while (!pq.isEmpty() && pq.peek() <= p.s)
				pq.poll();

			// 이번 강의의 종료 시간을 pq에 넣는다
			pq.add(p.e);

			// 남은 강의의 수를 체크한다
			ans = Integer.max(ans, pq.size());
		}

		System.out.println(ans);
	}
}
