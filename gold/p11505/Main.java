package gold.p11505;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[] arr;
	static int[] segTree;
	static final int mod = (int) 1e9 + 7;

	static int init(int cur, int cl, int cr) {
		if (cl == cr)
			return segTree[cur] = arr[cl];
		int cm = (cl + cr) / 2;
		long result = 1l * init(cur * 2, cl, cm) * init(cur * 2 + 1, cm + 1, cr);
		return segTree[cur] = (int) (result % mod);
	}

	static int query(int l, int r, int cur, int cl, int cr) {
		if (l > cr || r < cl)
			return 1;
		if (l <= cl && cr <= r)
			return segTree[cur];
		int cm = (cl + cr) / 2;
		long result = 1l * query(l, r, cur * 2, cl, cm) * query(l, r, cur * 2 + 1, cm + 1, cr);
		return (int) (result % mod);
	}

	static int update(int idx, int num, int cur, int cl, int cr) {
		if (idx < cl || idx > cr)
			return segTree[cur];
		if (cl == cr)
			return segTree[cur] = arr[idx] = num;
		int cm = (cl + cr) / 2;
		long result = 1l * update(idx, num, cur * 2, cl, cm) * update(idx, num, cur * 2 + 1, cm + 1, cr);
		return segTree[cur] = (int) (result % mod);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		segTree = new int[4 * N];

		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		init(1, 0, N - 1);

		StringBuilder sb = new StringBuilder();

		while (M + K > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) {
				M--;
				update(b - 1, c, 1, 0, N - 1);
			} else {
				K--;
				sb.append(query(b - 1, c - 1, 1, 0, N - 1)).append('\n');
			}
		}

		System.out.println(sb);
	}
}
