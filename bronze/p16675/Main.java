package bronze.p16675;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int TC = 1;
        //TC = Integer.parseInt(br.readLine());
        while(TC-- > 0){
            solve();
        }
    }

    private static void solve() throws Exception {
        String input = br.readLine();
        char ml = input.charAt(0), mr = input.charAt(2), tl = input.charAt(4), tr = input.charAt(6);
        if((win(ml, tl) && win(ml, tr)) || (win(mr, tl) && win(mr, tr)))
            sb.append("MS");
        else if((win(tl, ml) && win(tl, mr)) || (win(tr, ml) && win(tr, mr)))
            sb.append("TK");
        else
            sb.append("?");
        System.out.println(sb);
    }

    private static boolean win(char a, char b){
        return (a == 'R' && b == 'S')
                || (a == 'S' && b == 'P')
                || (a == 'P' && b == 'R');
    }
}
