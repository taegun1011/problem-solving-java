package gold.p17425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		int size = 1000000;
		int[] sum = new int[size + 1];
		for (int i = 1; i <= size; i++)
			for (int j = i; j <= size; j += i)
				sum[j] += i;

		long[] total = new long[size + 1];
		for (int i = 1; i <= size; i++)
			total[i] = total[i - 1] + sum[i];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (N-- > 0) {
			int num = Integer.parseInt(br.readLine());
			sb.append(total[num]).append('\n');
		}

		System.out.println(sb);
	}
}
