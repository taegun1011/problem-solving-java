package silver.p4779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void recursion(int start, int size, boolean b) {
		if (size == 1) {
			char c = b ? '-' : ' ';
			sb.append(c);
			return;
		}
		if (!b) {
			for (int i = 0; i < size; i++) {
				sb.append(' ');
			}
			return;
		}
		size /= 3;
		recursion(start, size, true);
		recursion(start + size, size, false);
		recursion(start + size * 2, size, true);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str;
		while ((str = br.readLine()) != null && !str.isEmpty()) {
			sb.setLength(0);
			int N = Integer.parseInt(str);
			recursion(0, (int) Math.pow(3, N), true);
			System.out.println(sb);
		}
	}
}
