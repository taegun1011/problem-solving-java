package platinum.p28068;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pair {
	int a;
	int b;

	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}

}

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Pair> good = new ArrayList<>();
		ArrayList<Pair> bad = new ArrayList<>();

		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a <= b)
				good.add(new Pair(a, b));
			else
				bad.add(new Pair(a, b));
		}

		good.sort((o1, o2) -> Integer.compare(o1.a, o2.a));

		bad.sort((o1, o2) -> -Integer.compare(o1.b, o2.b));

		long sum = 0;
		boolean flag = true;
		for (Pair p : good) {
			if ((long) p.a > sum) {
				flag = false;
				break;
			}
			sum += p.b - p.a;
		}

		for (Pair p : bad) {
			if ((long) p.a > sum) {
				flag = false;
				break;
			}
			sum += p.b - p.a;
		}

		System.out.println(flag ? 1 : 0);
	}
}
