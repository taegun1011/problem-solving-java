package gold.p17609;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int decision(String str, boolean type) {
		int i = 0, j = str.length() - 1;
		boolean flag = true;

		while (i < j) {
			if (str.charAt(i) != str.charAt(j)) {
				if (flag) {
					if (type)
						i++;
					else
						j--;
					flag = false;
				}

				else
					return 2;
			} else {
				i++;
				j--;
			}
		}

		if (flag)
			return 0;
		else
			return 1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			String str = br.readLine();
			sb.append(Integer.min(decision(str, true), decision(str, false))).append('\n');
		}

		System.out.println(sb);
	}
}
