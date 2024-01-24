package silver.p9242;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void parse(String str, int start, int[] code) {
		int length = code.length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < 4; j++) {
				if (j == 3)
					continue;
				code[i] += str.charAt(4 * i + j) == '*' ? (1 << (start + j)) : 0;
			}
		}
	}

	public static int decode(int code) {
		switch (code) {
		case 31599:
			return 0;
		case 18724:
			return 1;
		case 29671:
			return 2;
		case 31207:
			return 3;
		case 18925:
			return 4;
		case 31183:
			return 5;
		case 31695:
			return 6;
		case 18727:
			return 7;
		case 31727:
			return 8;
		case 31215:
			return 9;
		default:
			return -1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String firstStr = br.readLine();
		int length = (firstStr.length() + 1) / 4;
		int[] code = new int[length];

		parse(firstStr, 0, code);
		for (int i = 1; i < 5; i++) {
			parse(br.readLine(), i * 3, code);
		}

		int base = 1, sum = 0;
		boolean flag = true;
		for (int i = code.length - 1; i >= 0; i--) {
			int num = decode(code[i]);
			if (num != -1) {
				sum += num * base;
				base *= 10;
			} else {
				flag = false;
				break;
			}
		}

		System.out.println((flag && sum % 6 == 0) ? "BEER!!" : "BOOM!!");
	}
}
