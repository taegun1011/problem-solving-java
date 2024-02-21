package gold.p1759;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static char[] letters;
	static int moum = 0b100000100000100010001;
	static int L;
	static int C;

	static String perm(int idx, String pw, int z, int m) {
		if (pw.length() == L)
			return z >= 1 && m >= 2 ? pw + '\n' : "";
		if (idx == C)
			return "";
		char l = letters[idx];
		int flag = (moum & (1 << (l - 'a'))) > 0 ? 1 : 0;
		return perm(idx + 1, pw + l, z + flag, m + 1 - flag) + perm(idx + 1, pw, z, m);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		letters = new char[C];
		String input = br.readLine();
		for (int i = 0; i < C; i++)
			letters[i] = input.charAt(i * 2);
		Arrays.sort(letters);
		System.out.println(perm(0, "", 0, 0));
	}
}
