package platinum.p17132;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 얘도 가중치 순으로 해볼까?

	static int[] p;
	static int[] s;

	static int find(int x) {
		if (x == p[x])
			return x;
		return p[x] = find(p[x]);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		p[y] = x;
		s[x] += s[y];
	}

	static class Edge {
		int u;
		int v;
		int w;

		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		p = new int[N];
		s = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
			s[i] = 1;
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1.w, o2.w));
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			pq.add(new Edge(u, v, w));
		}

		long sum = 0; // 10만 * 10만 / 2 * 200
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			sum += 1l * cur.w * s[find(cur.u)] * s[find(cur.v)];
			union(cur.u, cur.v);
		}

		System.out.println(sum);
	}
}
