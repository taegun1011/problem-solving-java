package silver.p10845;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		Queue<Integer> Q = new LinkedList<>();

		int N = Integer.parseInt(st.nextToken());
		int last = -1;
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			switch (cmd) {
			case "push":
				Q.add(last = Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				if (Q.isEmpty())
					sb.append(-1).append('\n');
				else
					sb.append(Q.poll()).append('\n');
				break;
			case "size":
				sb.append(Q.size()).append('\n');
				break;
			case "empty":
				sb.append(Q.isEmpty() ? 1 : 0).append('\n');
				break;
			case "front":
				if (Q.isEmpty())
					sb.append(-1).append('\n');
				else
					sb.append(Q.peek()).append('\n');
				break;
			case "back":
				if (Q.isEmpty())
					sb.append(-1).append('\n');
				else
					sb.append(last).append('\n');
				break;
			}
		}

		System.out.println(sb);
	}
}
