import java.util.*;

class Expressao{
	
	private String shuntingYard(String infix) {
        final String ops = "-+/*%^";
        StringBuilder sb = new StringBuilder();
        Stack<Integer> s = new Stack<>();

        for (String token : infix.split("\\s")) {
            if (token.isEmpty())
                continue;
            char c = token.charAt(0);
            int idx = ops.indexOf(c);

            // check for operator
            if (idx != -1) {
                if (s.isEmpty())
                    s.push(idx);

                else {
                    while (!s.isEmpty()) {
                        int prec2 = s.peek() / 2;
                        int prec1 = idx / 2;
                        if (prec2 > prec1 || (prec2 == prec1 && c != '^'))
                            sb.append(ops.charAt(s.pop())).append(' ');
                        else break;
                    }
                    s.push(idx);
                }
            }
            else if (c == '(') {
                s.push(-2); // -2 stands for '('
            }
            else if (c == ')') {
                // until '(' on stack, pop operators.
                while (s.peek() != -2)
                    sb.append(ops.charAt(s.pop())).append(' ');
                s.pop();
            }
            else {
                sb.append(token).append(' ');
            }
        }
        while (!s.isEmpty())
            sb.append(ops.charAt(s.pop())).append(' ');
        return sb.toString();
    }
    
    private Double toDouble(String n){
        double ret = 0;
		int pot = 1, i;
        for(i = 0; i < n.length() && n.charAt(i) != ' '; i++);
		for(i--; i >= 0 && n.charAt(i) != ' '; i--){
			if((n.charAt(i) >= '0') && (n.charAt(i) <= '9')){
				ret += (n.charAt(i) - '0') * pot;
				pot *= 10;
			}else if(n.charAt(i) == '|' || n.charAt(i) == '-'){
                ret *= -1;
            }else if(n.charAt(i) == '.'){
				ret /= pot;
				pot = 1;
			}else{
                return null;
            }
            //System.out.println("toDouble i: " + i + "      ret: " + ret);
		}
		return ret;
    }
    
    public String agrupa(String[] a, int ini, int fim){
        String ret = new String("");
        Interpretador b = new Interpretador();
        for(int i = ini; i <= fim; i++){
			if(a[i].equals(" ")){
				ini++;
				continue;
			}
			if(i > ini) ret += " ";
			if(a[i].charAt(0) == '-' && a[i].length() > 1) a[i] = a[i].replace('-', '|');
			ret +=  a[i];
		}
        return ret;
    }
    
	//----------- Expressões Aritméticas
	public Double calcula(String n){
		int i;
		n = shuntingYard(n);

		while(Simbolos.operadores(n) && (n.length() > 1 ? Simbolos.operadores(n.substring(1)) : true)){ //Enquanto há operadores
			int x0ant = 0;
			int x0 = 0, x1 = 0; //x0 -> posição inicial do primeiro numero   x1 -> posição inicial do segundo numero

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
		
			Double aux = toDouble(n.substring(x0, x1 - 1));
			Double aux2 = toDouble(n.substring(x1, i - 1));
		
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
			if(i + 1 < n.length()) i++;
			
			String tmp = aux.toString();
			
			if(aux < 0) tmp = tmp.replace('-', '|');
			n = n.replace(n.substring(x0, i), tmp);
		}

		return toDouble(n);
	}

	//-------------    Expressões booleanas
	
	public boolean percorre(String[] a, int inic){ //Recebe String[] com ( na primeira posição
		int i = inic;
		int j;

		for(j = inic + 1; j < a.length && !a[j].equals(")"); j++){
			if (a[j].equals("(")) percorre(a, j);
		}
		/*System.out.println("->>>>>>>>>>>>>iiii>>>>>>>>>>>>>>>>>>");
		for(int b = 0; b < a.length; b++){
			System.out.println(a[b]);
		}
		System.out.println("\n->>>>>>>>>>>>>>>>iiii>>>>>>>>>>>>>>>");*/

		if (resolve(a, i, j) > 0) return true;
		else return false;
	}

	public double resolve(String[] expressao, int inic, int fim){
		int k = 0, i;
		
		//Descobre se há operação de comparação
		for(i = inic + 1; i < fim; i++){
			k = Simbolos.pertence(expressao[i]);
			if (k >= 6 && k <= 13) break;
		}

		if(i == fim){ //Se não há comparação, resolve a expressao
			expressao[i] = this.calcula(agrupa(expressao, inic, fim)).toString();
			for(int h = inic; h < i; h++) expressao[h] = " ";
			return toDouble(expressao[i]);
		}else{  //Caso contrario, quebra a expressao na comparação e resolve independentemente
			expressao[i - 1] = this.calcula(agrupa(expressao, inic + 1, i - 1)).toString();
			expressao[i + 1] = this.calcula(agrupa(expressao, i + 1, fim - 1)).toString();
		}

		//Transforma valores resultantes para double
		double t1 = toDouble(expressao[i - 1]), t2 = toDouble(expressao[i + 1]);

		//Apaga as expressões resolvidas
		for(int h = inic; h <= fim; h++) expressao[h] = " ";
		expressao[i] = "0";

		switch(k){
			case 6:
				if (t1 > t2) expressao[i] = "1";
				break;
			case 7:
				if (t1 < t2) expressao[i] = "1";
				break;
			case 8:
				if (t1 >= t2) expressao[i] = "1";
				break;
			case 9:
				if (t1 <= t2) expressao[i] = "1";
				break;
			case 10: //IGUAL
				if (t1 == t2) expressao[i] = "1";
				break;
			case 11: //DIF
				if (t1 != t2) expressao[i] = "1";
				break;
			case 12: //EE
				if (t1 != 0 && t2 != 0) expressao[i] = "1";
				break;
			case 13: //OU
				if (t1 != 0 || t2 != 0) expressao[i] = "1";
				break;
		}

		return toDouble(expressao[i]);
	}

}
