package silver.p14235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
			return -Integer.compare(o1, o2);
		});
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if (a == 0)
				sb.append(pq.isEmpty() ? -1 : pq.poll()).append('\n');
			else {
				for (int j = 0; j < a; j++)
					pq.add(Integer.parseInt(st.nextToken()));
			}
		}

		System.out.println(sb);
	}
}
