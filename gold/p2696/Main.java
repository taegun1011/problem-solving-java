package gold.p2696;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		while (TC-- > 0) {
			PriorityQueue<Integer> minQ = new PriorityQueue<>();
			PriorityQueue<Integer> maxQ = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1, o2));

			int M = Integer.parseInt(br.readLine());
			int m = 1, mid = 0;
			sb.append(M / 2 + 1).append('\n');

			StringTokenizer st = new StringTokenizer(br.readLine());
			;
			while (m <= M) {

				if (m > 1 && m % 10 == 1)
					st = new StringTokenizer(br.readLine());

				int n = Integer.parseInt(st.nextToken());

				if (m == 1)
					mid = n;
				else {
					// 오른쪽에 넣기
					if (mid <= n) {
						minQ.add(n);
					}

					// 왼쪽에 넣기
					else if (mid > n) {
						maxQ.add(n);
					}

				}

				if (m % 2 == 1) {

					// 오른쪽이 더 클 때
					while (maxQ.size() > minQ.size()) {
						minQ.add(mid);
						mid = maxQ.poll();
					}

					// 왼쪽이 더 클 때
					while (minQ.size() > maxQ.size()) {
						maxQ.add(mid);
						mid = minQ.poll();
					}

					sb.append(mid);
					sb.append(m % 20 == 19 ? '\n' : ' ');
				}
				m++;
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}
}
