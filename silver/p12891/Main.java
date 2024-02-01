package silver.p12891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Map<Character, Integer> m = new HashMap<>();
		m.put('A', 0);
		m.put('C', 1);
		m.put('G', 2);
		m.put('T', 3);

		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		String DNA = br.readLine();

		st = new StringTokenizer(br.readLine());
		int[] need = new int[4];
		int[] count = new int[4];

		for (int i = 0; i < need.length; i++)
			need[i] = Integer.parseInt(st.nextToken());

		int cnt = 0;
		boolean flag = true;
		for (char c : DNA.substring(0, P).toCharArray())
			count[m.get(c)]++;

		for (int i = 0; i < 4; i++)
			if (count[i] < need[i])
				flag = false;

		if (flag)
			cnt++;

		for (int i = 1; i <= DNA.length() - P; i++) {
			flag = true;

			count[m.get(DNA.charAt(i - 1))]--;
			count[m.get(DNA.charAt(i + P - 1))]++;

			for (int j = 0; j < 4; j++)
				if (count[j] < need[j])
					flag = false;

			if (flag)
				cnt++;
		}

		System.out.println(cnt);
	}
}
