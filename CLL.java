import java.util.Scanner;
import java.io.*;

class CLL{
	public static void main(String args[]) throws Exception {
        Scanner s;
        Interpretador b;
        String linhas[] = new String[2000];
        f = new File(args[0]);
        s = new Scanner(f);
        b = new Interpretador();

        int i = 0;
        while(s.hasNext()) {
            linhas[i] = s.nextLine();
            i++;
        }

        b.interpreta(linhas);
    }
}
