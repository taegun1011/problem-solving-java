package silver.p10828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		Stack<Integer> S = new Stack<>();

		int N = Integer.parseInt(st.nextToken());
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			switch (cmd) {
			case "push":
				S.push(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				if (S.isEmpty())
					sb.append(-1).append('\n');
				else
					sb.append(S.pop()).append('\n');
				break;
			case "size":
				sb.append(S.size()).append('\n');
				break;
			case "empty":
				sb.append(S.isEmpty() ? 1 : 0).append('\n');
				break;
			case "top":
				if (S.isEmpty())
					sb.append(-1).append('\n');
				else
					sb.append(S.peek()).append('\n');
				break;
			}
		}

		System.out.println(sb);
	}
}