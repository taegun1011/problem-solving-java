package platinum.p15586;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Tuple {
		int u;
		int v;
		int w;

		public Tuple(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}

	static int[] p;
	static int[] s;

	static int find(int x) {
		if (p[x] == x)
			return x;
		return p[x] = find(p[x]);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		p[y] = x;
		s[x] += s[y];
	}

	public static void main(String[] args) throws Exception {
		// 쿼리와 union 연산을 크기 순으로 내림차순 정렬해서, k >= r일 때까지 union을 수행한 뒤 쿼리를 처리한다

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		p = new int[N];
		s = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
			s[i] = 1;
		}

		int Q = Integer.parseInt(st.nextToken());

		PriorityQueue<Tuple> pq = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1.w, o2.w));

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			pq.add(new Tuple(u, v, w));
		}

		int[] ans = new int[Q];
		PriorityQueue<Tuple> q = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1.u, o2.u));
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken()) - 1;
			q.add(new Tuple(k, v, i));
		}

		while (!q.isEmpty()) {
			Tuple cur = q.poll();
			int k = cur.u;
			int v = cur.v;
			int idx = cur.w;
			while (!pq.isEmpty() && k <= pq.peek().w) {
				union(pq.peek().u, pq.peek().v);
				pq.poll();
			}
			ans[idx] = s[find(v)] - 1;
		}

		StringBuilder sb = new StringBuilder();
		for (int a : ans)
			sb.append(a).append('\n');
		System.out.println(sb);
	}
}
