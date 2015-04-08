import java.util.*;

class Atribuicao{
	private Variavel<String, Integer> inteiro;

	public Double calcula(String n){
		int i;

		n = CLLutil.shuntingYard(n);

		System.out.println(n);
		System.out.println("1");
		while(CLLutil.operadores(n) && (n.length() > 1 ? CLLutil.operadores(n.substring(1)) : true)){ //Enquanto há operadores
			int x0ant = 0;
			int x0 = 0, x1 = 0; //x0 -> posição inicial do primeiro numero   x1 -> posição inicial do segundo numero

			//System.out.println(n);

			//posição 0 sempre vai ter um numero ou sinal, não preciso me "preocupar"
			for(i = 0; i < n.length(); i++){
				if(n.charAt(i) == ' '){
					if(x1 != 0){
						x0ant = x0;
						x0 = x1;
					}
					x1 = i + 1;
				} else if (Simbolos.pertence(n.charAt(i) + "") > 0){
					if(i + 1 < n.length() && n.charAt(i + 1) == ' ') break;
					if(i + 1 == n.length()) break;
					//Se não entrou em nenhum if, encontrou um numero negativo, então é o inicio de um novo numero
					if(x1 != 0){
						x0ant = x0;
						x0 = x1;
					}
					x1 = i;
				}
			}
			x1 = x0; x0 = x0ant;
			//System.out.println("2  x0: " + x0 + "     x1: " + x1 + "     tam: " + n.length());
			Double aux = CLLutil.toDouble(n.substring(x0, x1 - 1));
			Double aux2 = CLLutil.toDouble(n.substring(x1, i - 1));
			//System.out.println("3       aux1: " + aux + "          aux2: " + aux2);
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
					break;
				case '%':
					aux %= aux2;
			}
			//System.out.println("4");
			if(i + 1 < n.length()) i++;
			String tmp = aux.toString();
			if(aux < 0) tmp = tmp.replace('-', '|');
			n = n.replace(n.substring(x0, i), tmp);
			//System.out.println("5");
		}

		//System.out.println("6      return: " + CLLutil.toDouble(n));
		return CLLutil.toDouble(n);
	}

	public void atribuirValor(String[] tokens){
		String nTokens = CLLutil.agrupa(tokens, 3, tokens.length - 2);
		/*String nTokens = new String("");


		for(int i = 3; i < tokens.length - 2; i++){
			if(i > 3) nTokens = nTokens + " ";
			if(tokens[i].charAt(0) == '-' && tokens[i].length() > 1) tokens[i] = tokens[i].replace('-', 'u');
			nTokens = nTokens + tokens[i];
		}*/
		System.out.println(nTokens);

		double r = this.calcula(nTokens);
		//System.out.println("7");
		System.out.println("->>>" + r);
		/*inteiro.value = calcula(Arrays.copyOfRange(tokens, 3, tokens.length - 2));
		Interpretador.novaVar(inteiro.getKey(), inteiro);*/

	}


}
