package gold.p1339;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		String[] arr = new String[N];
		for (int i = 0; i < arr.length; i++)
			arr[i] = br.readLine();

		// 각 문자의 우선순위를 계산해서 높은 우선순위에 높은 값을 부여한다
		int[] priority = new int[26];
		for (String str : arr) {
			int base = 1;
			for (int i = str.length() - 1; i >= 0; i--) {
				priority[str.charAt(i) - 'A'] += base;
				base *= 10;
			}
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int x : priority) {
			if (x != 0)
				pq.add(-x);
		}

		int num = 9;
		int sum = 0;
		while (!pq.isEmpty() && num >= 0) {
			sum += -pq.poll() * num--;
		}

		System.out.println(sum);
	}
}
