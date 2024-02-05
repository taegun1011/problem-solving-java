package bronze.p20053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int min = Integer.parseInt(st.nextToken());
			int max = min;

			for (int i = 0; i < N - 1; i++) {
				int cur = Integer.parseInt(st.nextToken());
				if (cur > max)
					max = cur;
				if (cur < min)
					min = cur;
			}

			sb.append(min + " " + max).append('\n');
		}

		System.out.println(sb);
	}
}
