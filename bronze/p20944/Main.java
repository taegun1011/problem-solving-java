package bronze.p20944;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		StringBuilder sb = new StringBuilder();
		while (N-- > 0) {
			sb.append('a');
		}

		System.out.println(sb);
	}
}
