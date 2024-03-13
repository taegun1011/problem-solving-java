package gold.p2357;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] minSegTree;
	private static int[] maxSegTree;
	private static int[] arr;
	private static int N;
	static final int MIN = 1;
	static final int MAX = 2;

	static int init(int cur, int cl, int cr, int type) {
		if (cl == cr) {
			if (type == MIN)
				return minSegTree[cur] = arr[cl];
			else
				return maxSegTree[cur] = arr[cl];
		}
		int cm = (cl + cr) / 2;
		if (type == MIN)
			return minSegTree[cur] = Integer.min(init(cur * 2, cl, cm, type), init(cur * 2 + 1, cm + 1, cr, type));
		else
			return maxSegTree[cur] = Integer.max(init(cur * 2, cl, cm, type), init(cur * 2 + 1, cm + 1, cr, type));
	}

	static int query(int l, int r, int cur, int cl, int cr, int type) {
		if (l > cr || r < cl) {
			if (type == MIN)
				return Integer.MAX_VALUE;
			else
				return 0;
		}
		if (l <= cl && cr <= r) {
			if (type == MIN)
				return minSegTree[cur];
			else
				return maxSegTree[cur];
		}
		int cm = (cl + cr) / 2;
		if (type == MIN)
			return Math.min(query(l, r, cur * 2, cl, cm, type), query(l, r, cur * 2 + 1, cm + 1, cr, type));
		else
			return Math.max(query(l, r, cur * 2, cl, cm, type), query(l, r, cur * 2 + 1, cm + 1, cr, type));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		minSegTree = new int[4 * N];
		maxSegTree = new int[4 * N];
		arr = new int[N];

		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		init(1, 0, N - 1, MIN);
		init(1, 0, N - 1, MAX);

		StringBuilder sb = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			sb.append(query(a, b, 1, 0, N - 1, MIN) + " " + query(a, b, 1, 0, N - 1, MAX)).append('\n');
		}
		System.out.println(sb);
	}
}
