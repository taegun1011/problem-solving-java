package silver.p2839;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int cnt = 0;
		while (N % 5 != 0) {
			N -= 3;
			cnt++;
		}

		System.out.println(N < 0 ? -1 : cnt + N / 5);
	}
}
