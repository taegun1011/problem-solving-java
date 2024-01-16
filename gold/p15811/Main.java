package gold.p15811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String A = st.nextToken(), B = st.nextToken(), C = st.nextToken();

		int len = Integer.max(A.length(), B.length());

		if (len < C.length() || len > C.length() + 1) {
			System.out.println("NO");
			return;
		}

		// 앞 자리부터 접근

	}
}
