package bronze.p28062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		List<Integer> L = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ans = 0;
		while (N-- > 0) {
			int a = Integer.parseInt(st.nextToken());
			if (a % 2 == 1)
				L.add(a);
			else
				ans += a;
		}
		Collections.sort(L, (o1, o2) -> -Integer.compare(o1, o2));

		for (int i = 0; i < L.size() / 2 * 2; i++)
			ans += L.get(i);

		System.out.println(ans);
	}
}
