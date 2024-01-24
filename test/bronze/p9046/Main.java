package bronze.p9046;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		while (TC > 0) {
			TC--;

			int[] freq = new int[26];
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				String str = st.nextToken();
				for (char c : str.toCharArray()) {
					freq[c - 'a']++;
				}
			}

			char res = '\0';
			int max = 0;

			for (int i = 0; i < 26; i++) {
				if (freq[i] > max) {
					res = (char) ('a' + i);
					max = freq[i];
				} else if (freq[i] == max) {
					res = '?';
				}
			}

			sb.append(res).append('\n');
		}

		System.out.println(sb);
	}
}
