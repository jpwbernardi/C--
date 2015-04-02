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

		Variavel<String, Double> real = new Variavel<String, Double>("caca", 13.2);
		Variavel<String, Integer> inteiro = new Variavel<String, Integer>("lala", 11);
		Variavel<String, String> letras = new Variavel<String, String>("s", "Oie");

		b.novaVar(real.getKey(), real);
		b.novaVar(inteiro.getKey(), inteiro);
		b.novaVar(letras.getKey(), letras);

		System.out.println(b.getVar("caca").value);
		System.out.println(b.getVar("lala").value);
		System.out.println(b.getVar("s").value);

    }
}
