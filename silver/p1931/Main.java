package silver.p1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {
	int s;
	int e;

	public Pair(int s, int e) {
		super();
		this.s = s;
		this.e = e;
	}

	@Override
	public int compareTo(Pair o) {
		int comp = Integer.compare(this.e, o.e);
		if (comp == 0)
			return Integer.compare(this.s, o.s);
		return comp;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		// 먼저 끝나는

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Pair> pq = new PriorityQueue<>();

		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			pq.add(new Pair(s, e));
		}

		int cnt = 0;
		int curTime = 0;
		while (!pq.isEmpty()) {
			while (!pq.isEmpty() && pq.peek().s < curTime)
				pq.poll();
			if (!pq.isEmpty()) {
				cnt++;
				curTime = pq.poll().e;
			}
		}

		System.out.println(cnt);
	}
}
