package silver.p20529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public static String intToMbti(int n) {
		String mbti = "";
		mbti += (n & 8) != 0 ? "I" : "E";
		mbti += (n & 4) != 0 ? "N" : "S";
		mbti += (n & 2) != 0 ? "F" : "T";
		mbti += (n & 1) != 0 ? "P" : "J";
		return mbti;
	}

	public static int mbtiToInt(String mbti) {
		int ret = 0;
		ret += (mbti.charAt(0) == 'I') ? 8 : 0;
		ret += (mbti.charAt(1) == 'N') ? 4 : 0;
		ret += (mbti.charAt(2) == 'F') ? 2 : 0;
		ret += (mbti.charAt(3) == 'P') ? 1 : 0;
		return ret;
	}

	public static void solve() throws IOException {
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = st.nextToken();
		}

		int[] visited = new int[16];

		for (int i = 0; i < N; i++) {
			int idx = mbtiToInt(arr[i]);
			visited[idx]++;
		}

		int min = 32;
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				for (int k = 0; k < 16; k++) {
					if (visited[i] == 0 || visited[j] == 0 || visited[k] == 0)
						continue;
					if ((i == j && visited[i] < 2) || (j == k && visited[j] < 2) || (k == i && visited[k] < 2))
						continue;
					if (i == j && j == k && visited[i] < 3)
						continue;

					int result = get3MbtiDiff(intToMbti(i), intToMbti(j), intToMbti(k));
					min = min < result ? min : result;
				}
			}
		}
		System.out.println(min);
	}

	public static void main(String[] args) throws IOException {
		int TC = Integer.parseInt(br.readLine());

		while (TC > 0) {
			TC--;
			solve();
		}
	}
}
