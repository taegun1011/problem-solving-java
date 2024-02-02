package silver.p1244;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		int[] sw = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			sw[i] = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			if (g == 1)
				for (int i = s; i <= N; i += s)
					sw[i] = 1 - sw[i];
			else
				for (int i = s, j = s; i > 0 && j <= N && sw[i] == sw[j]; i--, j++) {
					sw[i] = 1 - sw[i];
					if (i != j)
						sw[j] = 1 - sw[j];
				}
		}

		for (int i = 1; i <= N; i++) {
			sb.append(sw[i]);
			sb.append(i % 20 == 0 ? '\n' : ' ');
		}

		System.out.println(sb);
	}
}
