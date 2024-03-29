package platinum.p1786;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int[] fail(String N) {
		int n = N.length();
		int[] pi = new int[n];

		int i = 1, j = 0;
		while (i + j < n) {
			if (N.charAt(j) == N.charAt(i + j)) {
				pi[i + j] = j + 1;
				j++;
			} else {
				if (j == 0)
					i++;
				else {
					i += j - pi[j - 1];
					j = pi[j - 1];
				}
			}
		}

		return pi;
	}

	static List<Integer> kmp(String H, String N, int[] pi) {
		int h = H.length(), n = N.length(), i = 0, j = 0;
		List<Integer> ans = new ArrayList<>();

		while (i + n <= h) {
			if (j < n && H.charAt(i + j) == N.charAt(j)) {
				if (++j == n)
					ans.add(i + 1);
			} else {
				if (j == 0)
					i++;
				else {
					i += j - pi[j - 1];
					j = pi[j - 1];
				}
			}
		}

		return ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		String P = br.readLine();

		List<Integer> ans = kmp(T, P, fail(P));
		System.out.println(ans.size());
		StringBuilder sb = new StringBuilder();
		for (int x : ans)
			sb.append(x).append(' ');
		System.out.println(sb);
	}
}
