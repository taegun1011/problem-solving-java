package gold.p1647;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] p;

	static int find(int x) {
		if (p[x] == x)
			return x;
		return p[x] = find(p[x]);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		p[y] = x;
	}

	static int kruskal(List<Edge> Edges, int V) {
		int cnt = 0;
		int sum = 0;
		int max = 0;
		for (Edge e : Edges) {
			int u = e.u, v = e.v, w = e.w;
			if (find(u) != find(v)) {
				union(u, v);
				cnt++;
				sum += w;
				max = Integer.max(max, w);
			}
			if (cnt == V - 1)
				break;
		}

		return sum - max;
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		p = new int[N];
		for (int i = 0; i < N; i++)
			p[i] = i;

		int M = Integer.parseInt(st.nextToken());
		List<Edge> Edges = new ArrayList<>();

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			Edges.add(new Edge(u, v, w));
		}

		Collections.sort(Edges, (o1, o2) -> Integer.compare(o1.w, o2.w));
		System.out.println(kruskal(Edges, N));
	}
}
