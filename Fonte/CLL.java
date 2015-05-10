import java.util.Scanner;
import java.io.*;

class CLL{
	public static void main(String args[]) throws Exception {
		File f;
        Scanner s;
        Interpretador b;
        String linhas[] = new String[2000];
        f = new File(args[0]);
        s = new Scanner(f);
        b = new Interpretador(false);

        int i = 0;
        while(s.hasNext()) {
            linhas[i] = s.nextLine();
            i++;
        }

		if(f.getName().matches(".+\\.cll")) b.interpreta(linhas);
		else System.out.println("Não é um arquivo .cll!\n");
    }
}
