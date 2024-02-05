package silver.p10866;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		Deque<Integer> D = new LinkedList<>();

		int N = Integer.parseInt(st.nextToken());
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			switch (cmd) {
			case "push_front":
				D.addFirst(Integer.parseInt(st.nextToken()));
				break;
			case "push_back":
				D.addLast(Integer.parseInt(st.nextToken()));
				break;
			case "pop_front":
				if (D.isEmpty())
					sb.append(-1).append('\n');
				else
					sb.append(D.pollFirst()).append('\n');
				break;
			case "pop_back":
				if (D.isEmpty())
					sb.append(-1).append('\n');
				else
					sb.append(D.pollLast()).append('\n');
				break;
			case "size":
				sb.append(D.size()).append('\n');
				break;
			case "empty":
				sb.append(D.isEmpty() ? 1 : 0).append('\n');
				break;
			case "front":
				if (D.isEmpty())
					sb.append(-1).append('\n');
				else
					sb.append(D.peekFirst()).append('\n');
				break;
			case "back":
				if (D.isEmpty())
					sb.append(-1).append('\n');
				else
					sb.append(D.peekLast()).append('\n');
				break;
			}
		}

		System.out.println(sb);
	}
}