package silver.p1302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Integer> M = new HashMap<>();

		int N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			String key = br.readLine();
			int value = M.containsKey(key) ? M.get(key) + 1 : 1;
			M.put(key, value);
		}

		int max = 0;
		String maxKey = "";
		for (Entry<String, Integer> E : M.entrySet()) {
			if (E.getValue() > max) {
				maxKey = E.getKey();
				max = E.getValue();
			} else if (E.getValue() == max) {
				maxKey = maxKey.compareTo(E.getKey()) >= 0 ? E.getKey() : maxKey;
			}
		}

		System.out.println(maxKey);
	}
}
