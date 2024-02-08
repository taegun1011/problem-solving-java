package gold.p23888;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	static long sum(int l, int r) {
		l--;
		r--;
		return 1l * r * (r + 1) / 2 - 1l * l * (l - 1) / 2;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int a = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(br.readLine());

		while (q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			long sum = 1l * a * (r - l + 1) + sum(l, r) * d;
			long Al = 1l * (l - 1) * d + a;

			if (cmd == 1)
				sb.append(sum).append('\n');
			else {
				sb.append(r == l ? Al : gcd(d, Al)).append('\n');
			}
		}

		System.out.println(sb);
	}
}
