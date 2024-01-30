package swea.p1208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	public static int chooseMax(int[] box) {
		int max = 0;
		int ret = 0;
		for (int i = 0; i < box.length; i++) {
			if (box[i] > max) {
				max = box[i];
				ret = i;
			}
		}

		return ret;
	}

	public static int chooseMin(int[] box) {
		int min = 100;
		int ret = 0;
		for (int i = 0; i < box.length; i++) {
			if (box[i] < min) {
				min = box[i];
				ret = i;
			}
		}

		return ret;
	}

	public static void main(String[] args) throws Exception {
		int TC = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= TC; tc++) {
//			int N = Integer.parseInt(br.readLine());
			int N = sc.nextInt();
//			StringTokenizer st = new StringTokenizer(br.readLine());

			int[] box = new int[100];
			for (int i = 0; i < box.length; i++)
//				box[i] = Integer.parseInt(st.nextToken());
				box[i] = sc.nextInt();

			while (N-- > 0) {
				int h = chooseMax(box);
				int l = chooseMin(box);
				if (box[h] - box[l] <= 1)
					break;
				box[h]--;
				box[l]++;
			}

			System.out.println("#" + tc + " " + (box[chooseMax(box)] - box[chooseMin(box)]));
		}
	}
}
