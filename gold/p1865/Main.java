package gold.p1865;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Pair {
	int v;
	int w;

	public Pair(int v, int w) {
		this.v = v;
		this.w = w;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static void solve() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		List<Pair>[] AL = new List[N];
		for (int i = 0; i < N; i++)
			AL[i] = new ArrayList<>();

		// 중복 간선 처리

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			AL[u].add(new Pair(v, w));
		}

		while (W-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = -Integer.parseInt(st.nextToken());
			AL[u].add(new Pair(v, w));
		}
	}

	public static void main(String[] args) throws Exception {
		// E가 V^2보다 현저히 작기 때문에 벨만포드를 사용해야 한다
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0)
			solve();
	}
}
