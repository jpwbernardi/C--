import java.util.*;

class Atribuiçao{
	private Variavel<String, Integer> inteiro;
	
	//var nome = valor
	public int calcula(String[] tokens){
		for(String x: tokens)
					System.out.println("->" + x);
		if(tokens.length == 1) return Integer.parseInt(tokens[0]);
		else return 0;
	}
	
	public void atribuirValor(String[] tokens){
		inteiro = new Variavel<String, Integer>(tokens[1], 0);
		
		inteiro.value = calcula(Arrays.copyOfRange(tokens, 3, tokens.length - 2));
		Interpretador.novaVar(inteiro.getKey(), inteiro);
		
	}
	
	
}
