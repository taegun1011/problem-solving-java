package silver.p20529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static int get2MbtiDiff(String a, String b) {
		int ret = 0;
		for (int i = 0; i < 4; i++) {
			if (a.charAt(i) != b.charAt(i))
				ret++;
		}
		return ret;
	}

	public static int get3MbtiDiff(String a, String b, String c) {
		return get2MbtiDiff(a, b) + get2MbtiDiff(b, c) + get2MbtiDiff(c, a);
	}

	public static void solve() throws IOException {
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = st.nextToken();
		}

		Map<Character, Integer> map = Map.of('I', 8, 'N', 4, 'F', 2, 'P', 1);
		int[] visited = new int[16];

		for (int i = 0; i < N; i++) {
			int idx = 0;
			for (int j = 0; j < 4; j++) {
				idx += map.containsKey(arr[i].charAt(j)) ? map.get(arr[i].charAt(j)) : 0;
			}
			visited[idx]++;
		}

		int max = 0;
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				for (int k = 0; k < 16; k++) {
					if (visited[i] == 0 || visited[j] == 0 || visited[k] == 0)
						continue;
					if ((i == j && visited[i] < 2) || (j == k && visited[j] < 2) || (k == i && visited[k] < 2))
						continue;
					if (i == j && j == k && visited[i] < 3)
						continue;
					System.out.println(arr[i] + arr[j] + arr[k]);
					int result = get3MbtiDiff(arr[i], arr[j], arr[k]);
					max = max > result ? max : result;
				}
			}
		}
		System.out.println(max);
	}

	public static void main(String[] args) throws IOException {
		int TC = Integer.parseInt(br.readLine());

		while (TC > 0) {
			TC--;
			solve();
		}
	}
}
