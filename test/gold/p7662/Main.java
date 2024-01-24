package gold.p7662;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder sb = new StringBuilder();

	public void solution() throws IOException {

		PriorityQueue<Integer> minH = new PriorityQueue<>();
		PriorityQueue<Integer> maxH = new PriorityQueue<>((o1, o2) -> {
			return -Integer.compare(o1, o2);
		});
		Map<Integer, Integer> M = new HashMap<>();

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			int n = Integer.parseInt(st.nextToken());

			if (cmd == 'I') {

				minH.add(n);
				maxH.add(n);
				M.put(n, M.containsKey(n) ? M.get(n) + 1 : 1);

			} else if (cmd == 'D') {

				if (n == -1) {

					while (!minH.isEmpty() && M.get(minH.peek()) == 0)
						minH.poll();

					if (minH.isEmpty())
						continue;

					int min = minH.poll();
					M.put(min, M.get(min) - 1);

				} else if (n == 1) {

					while (!maxH.isEmpty() && M.get(maxH.peek()) == 0)
						maxH.poll();

					if (maxH.isEmpty())
						continue;
					int max = maxH.poll();
					M.put(max, M.get(max) - 1);

				}
			}

		}

		while (!minH.isEmpty() && M.get(minH.peek()) == 0)
			minH.poll();
		while (!maxH.isEmpty() && M.get(maxH.peek()) == 0)
			maxH.poll();

		if (minH.isEmpty() || maxH.isEmpty()) {
			sb.append("EMPTY").append('\n');
		} else {
			sb.append(maxH.poll() + " " + minH.poll()).append('\n');
		}
	}

	public static void main(String[] args) throws IOException {
		int TC = Integer.parseInt(br.readLine());

		Main M = new Main();
		while (TC-- > 0) {
			M.solution();
		}

		System.out.println(M.sb);
	}
}
