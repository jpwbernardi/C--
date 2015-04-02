import java.util.*;

class Atribuicao{
	private Variavel<String, Integer> inteiro;

	private double calcula(String n){
		int i;

		n = CLLutil.shuntingYard(n);

		while(CLLutil.operadores(n)){ //Enquanto há operadores
			int x0ant = 0;
			int x0 = 0, x1 = 0; //x0 -> posição inicial do primeiro numero   x1 -> posição inicial do segundo numero
			for(i = 0; i < n.length(); i++){
				if(n.charAt(i) == ' '){
					if(x1 != 0){
						x0ant = x0;
						x0 = x1;
					}
					x1 = i + 1;
				} else if (Simbolos.pertence(n.charAt(i) + "") > 0) break;
			}
			x1 = x0; x0 = x0ant;

			Double aux = CLLutil.toDouble(n.substring(x0, x1 - 1));
			Double aux2 = CLLutil.toDouble(n.substring(x1, i - 1));

			switch(n.charAt(i)){
				case '*':
					aux *= aux2;
					break;
				case '/':
					aux /= aux2;
					break;
				case '+':
					aux += aux2;
					break;
				case '-':
					aux -= aux2;
					break;
				case '^':
					aux = Math.pow(aux, aux2);
			}

			if(i + 1 < n.length()) i++;
			n = n.replace(n.substring(x0, i), aux.toString());
		}

		return CLLutil.toDouble(n);
	}

	public void atribuirValor(String[] tokens){
		String nTokens = new String("");
		for(int i = 3; i < tokens.length - 2; i++){
			if(i > 3) nTokens = nTokens + " ";
			nTokens = nTokens + tokens[i];
		}
		double r = this.calcula(nTokens);

		/*inteiro.value = calcula(Arrays.copyOfRange(tokens, 3, tokens.length - 2));
		Interpretador.novaVar(inteiro.getKey(), inteiro);*/

	}


}
