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
	
	//String n, inicio i
	private static double toDouble(String n, int i){
		double ret = 0;
		int pot = 1;
		for(; i >= 0 && n.charAt(i) != ' '; i--){
			if(n.charAt(i) != '.'){
				ret += (n.charAt(i) - '0') * pot;
				pot *= 10;
			}else{
				ret /= pot;
				pot = 1;
			}
		}
		return ret;
	}
	
	private double valor(String n){
		//TO DO
		//while(n.length > 1){
			int maior = 0, ii = 0, i; 
			Double n1, n2;
			char op;
			
			for(i = 0; i < n.length(); i++){
				if(Simbolos.pertence(n.charAt(i) + "") > maior){
					maior = Simbolos.pertence(n.charAt(i) + "");
					ii = i;
					op = n.charAt(i);
				}
			}
			
			if(maior != 5){
				int aux;   //Guarda posição do inicio do primeiro valor
				String temp;
				System.out.println(i);
				n1 = Atribuiçao.toDouble(n, ii - 2);
				
				for(i = ii + 2; i < n.length() && n.charAt(i) != ' '; i++); //Descobre posição final do numero 2
				for(aux = ii - 2; aux >= 0 && n.charAt(aux) != ' '; aux--); //Descobre posição inicial do numero 1
				
				System.out.println(i);
				n2 = Atribuiçao.toDouble(n, i - 1);
				
				System.out.println("->>" + n1 + " ~ ~ " + n2);
				
				if(aux > 0){
					temp = n.substring(0, aux + 1) + n1.toString();
				}else{
					temp = new String(n1.toString());
				}
				if(i < n.length())
					temp = temp + n.substring(i);
					
				System.out.println(temp);
				
			}
			return 0;
		//}
	}
	
	public void atribuirValor(String[] tokens){
		String nTokens = new String("");
		for(int i = 3; i < tokens.length - 2; i++){
			if(i > 3) nTokens = nTokens + " ";
			nTokens = nTokens + tokens[i];
		}
		System.out.println(nTokens);
		this.valor(nTokens);
		
		/*inteiro.value = calcula(Arrays.copyOfRange(tokens, 3, tokens.length - 2));
		Interpretador.novaVar(inteiro.getKey(), inteiro);*/
		
	}
	
	
}
