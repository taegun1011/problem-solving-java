package bronze.p10988;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = true;
		String str = br.readLine();
		for (int s = 0, e = str.length() - 1; s < e; s++, e--)
			if (str.charAt(s) != str.charAt(e)) {
				flag = false;
				break;
			}
		System.out.println(flag ? 1 : 0);
	}
}
