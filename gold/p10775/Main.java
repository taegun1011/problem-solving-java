package gold.p10775;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] p;
	static int G;

	static void make_set() {
		for (int i = 0; i <= G; i++)
			p[i] = i;
	}

	static int find(int x) {
		if (p[x] == x)
			return x;
		return p[x] = find(p[x]);
	}

	static void union(int x, int y) {
		int px = find(x), py = find(y);
		if (px != py)
			p[Integer.max(px, py)] = Integer.min(px, py);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		p = new int[G + 1];

		make_set();

		int P = Integer.parseInt(br.readLine());
		int cnt = 0;

		while (P-- > 0) {
			int g = Integer.parseInt(br.readLine());
			int pg = find(g);
			if (pg == 0)
				break;
			union(pg, pg - 1);
			cnt++;
		}

		System.out.println(cnt);
	}
}
