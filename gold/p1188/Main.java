package gold.p1188;

import java.util.Scanner;

public class Main {
	static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	public static void main(String[] args) {
		// 전체를 b등분하되, gcd(a, b)번은 자를 필요 없다
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		System.out.println(b - gcd(a, b));
	}
}
