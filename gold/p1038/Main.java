package gold.p1038;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int cnt = 0;

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int i = 0; i <= 987654321; i++) {
			if (cnt == N) {
				System.out.println(i);
				break;
			}

		}
	}
}
