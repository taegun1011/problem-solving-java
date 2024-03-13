package gold.p1644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, S;

	static int ans = 0;

	public static void main(String[] args) throws IOException {
		// BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		boolean[] isPrime = new boolean[18];
		ArrayList<Integer> prime = new ArrayList<>();

		for (int i = 2; i <= 17; i++) {
			if (isPrime[i])
				continue;
			prime.add(i);
			for (int j = i * 2; j <= 17; j += i) {
				isPrime[j] = true;
			}
		}

		int start = 0;
		int end = 0;
		int total = 0;
		int size = prime.size();
		int ans = 0;
		while (start < size || end < size) {
			System.out.println(total + " " + prime.get(start) + " " + prime.get(end));
			if (total < N) {
				total += prime.get(end);
				end++;
			} else if (total > N) {
				total -= prime.get(start);
				start++;
			}
			if (total == N) {
				ans += 1;
				total += prime.get(end);
				end++;
			}
		}

		System.out.println(ans);

	}
}
