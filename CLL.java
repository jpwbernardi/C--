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

		VariavelDouble real = new VariavelDouble(13.2);
		VariavelDouble inteiro = new VariavelDouble(11.1);
		VariavelDouble inteiro1 = new VariavelDouble(0.0);
		//Variavel<String> letras = new Variavel<String>("Oie");

		b.interpreta(linhas);

		real.setValor(real.getValor() + 1);
		Variavel aux;

		Interpretador.novaVar("cacia", real);
		Interpretador.novaVar("lala", inteiro);
		Interpretador.novaVar("lalau", inteiro1);

		aux = Interpretador.getVar("i");

		System.out.println("i existe ? " + aux);




		//Interpretador.novaVar("s", letras);
		//aux = b.getVar("cacia");

		//String a = aux.getValor().toString();

		//System.out.println(a);
		/*System.out.println(b.getVar("lala"));
		System.out.println(b.getVar("s"));
		//System.out.println(b.getVar("Ola").value);*/

    }
}
