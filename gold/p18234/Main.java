package gold.p18234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {
	int p;
	int w;

	public Pair(int p, int w) {
		this.p = p;
		this.w = w;
	}

	public int compareTo(Pair o) {
		return this.p == o.p ? o.w - this.w : o.p - this.p;
	}

}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		Pair[] P = new Pair[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int w = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());

			P[i] = new Pair(p, w);
		}

		Arrays.sort(P);

		long result = 0;
		int M = T - 1;
		for (int i = 0; i < N; i++) {
			result += 1l * P[i].p * M-- + P[i].w;
		}

		System.out.println(result);
	}
}
