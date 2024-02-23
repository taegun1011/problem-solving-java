package gold.p1053;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static boolean check(String str) {
		int N = str.length();
		for (int i = 0; i < N / 2; i++)
			if (str.charAt(i) != str.charAt(N - 1 - i))
				return false;
		return true;
	}

	static int[][][] dp = new int[51][4][2];

	static int re_dp(String str, int pos, int type, int used) {
		if (check(str))
			return 0;
		if (dp[pos][type][used] != -1)
			return dp[pos][type][used];
		// 그냥 넘어가기
		int N = str.length();
		int ret = (int) 1e9;
		// 삭제
		// 앞 바꾸기
		// 가능하면 두개 옮기기

		return 0;
	}

	public static void main(String[] args) throws Exception {
		// 팰린드롬 검사 후 변환
		// 무조건 반대쪽 글자와 동일하게 만든다
		// 삽입할 바에 삭제를...
		// 4번 연산은 한번에 두개를 옮길 수 있음 -> 가장 효과적으로 사용하자
		// 바꾸면 좋을 상황 -> 바꾸면 둘 다 딱 맞음
		// a의 반대 = b, b의 반대 = a
		// dp는 i번 위치에서 j번 연산을 했고 4번 연산 사용 여부가 k일 때의 최솟값
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
	}
}
