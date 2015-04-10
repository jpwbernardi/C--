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
        b = new Interpretador();

        int i = 0;
        while(s.hasNext()) {
            linhas[i] = s.nextLine();
            i++;
        }

        b.interpreta(linhas);

		Variavel<Double> real = new Variavel<Double>(13.2);
		Variavel<Integer> inteiro = new Variavel<Integer>(11);
		Variavel<String> letras = new Variavel<String>("Oie");

		Interpretador.novaVar("cacia", real);
		Interpretador.novaVar("lala", inteiro);
		Interpretador.novaVar("s", letras);

		/*System.out.println(b.getVar("cacia"));
		System.out.println(b.getVar("lala"));
		System.out.println(b.getVar("s"));
		//System.out.println(b.getVar("Ola").value);*/

    }
}
