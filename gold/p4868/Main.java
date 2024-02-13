package gold.p4868;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int[][] dp = new int[21][10001];

	// B는 A의 mixed extension
	static boolean ME(String A, String B) {
		int[] cnt = new int[26];
		for (char ch : A.toCharArray())
			cnt[ch - 'a']++;
		int left = 1;
		for (char ch : B.toCharArray()) {
			cnt[ch - 'a']--;
			if (cnt[ch - 'a'] < 0) {
				if (left > 0)
					left--;
				else
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		// 길이를 기준으로 그래프 구성?
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp;
		List<String>[] graph = new List[21];
		for (int i = 1; i <= 20; i++)
			graph[i] = new ArrayList<>();

		while ((temp = br.readLine()) != null)
			graph[temp.length()].add(temp);

		for (int i = 20; i > 1; i++) {
			for (int j = 0; j < graph[i].size(); j++) {
				for (int k = 0; k < graph[i - 1].size(); k++) {
					String A = graph[i].get(j);
					String B = graph[i - 1].get(k);
					if (ME(A, B)) {
						dp[i][j] = dp[i - 1][k] + 1;
					}
				}
			}
		}
	}
}
