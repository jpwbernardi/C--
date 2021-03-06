import java.util.*;

class Expressao{

	public String comando;
	public String[] tokens;


	public Expressao(String s){
		comando = s.substring(0); //Passa copia de s para comando (não apontando para o mesmo lugar na memória!)
		tokens = divide();
		if(tokens.length >= 1)
		 	if(Simbolos.pertence(tokens[0]) != 20) organiza(); //Se for diferente de printa, organiza
			else printa();
	}

	public void set(String s){
		comando = s.substring(0); //Passa copia de s para comando (não apontando para o mesmo lugar na memória!)
		tokens = divide();
		if(tokens.length >= 1)
		 	if(Simbolos.pertence(tokens[0]) != 20) organiza(); //Se for diferente de printa, organiza
			else printa();
	}

	private String[] divide(){
		ArrayList<String> sequencia = new ArrayList<String>();

		String[] quebrado = comando.split("");		//Quebra string n e guarda em quebrado
		String aux = null;

		int i;
		int flag = 1; //ultima quebra foi em um Simbolo ? 1 : 0

		//Forma mais simples que achei de consertar valores negativos!!!
		for(i = 0; i < quebrado.length; i++){
			if(aux == null) aux = new String("");
			if(quebrado [i].equals("#")){
				if(aux.equals("") == false) sequencia.add(aux);
				break;
			} //Comentario
			if(flag == 1 && quebrado[i].equals("+")) continue;	//Se o ultimo foi um sinal (operador) e o atual é +, ignora;
			if((quebrado[i].equals(" ") || quebrado[i].equals("	")) && (aux.equals("") == false)){
				if(flag != 1 && aux.equals("-") == false){
					sequencia.add(aux);
					if(Simbolos.pertence(aux) > 0) flag = 1;
					aux = null;
				}
			}else if(Simbolos.pertence(quebrado[i]) > 0){
				if(quebrado[i].equals("(") && aux.equals("-")){
					sequencia.add("-1");
					sequencia.add("*");
					sequencia.add(quebrado[i]);
					aux = null;
				}else if(quebrado[i].equals("-") == false || flag == 0){
					if(aux.equals("") == false) sequencia.add(aux);
					sequencia.add(quebrado[i]);
					aux = null;
				}else{
					if(aux.equals("") == false) sequencia.add(aux);
					if(flag == 1) aux = new String(quebrado[i] + "");
				}
				if(quebrado[i].equals(")") == false && quebrado[i].equals(")") == false){
					flag = 1;
				}
			}else if(quebrado[i].equals(" ") == false && quebrado[i].equals("	") == false){
				aux += quebrado[i];
				flag = 0;
			}
		}

		String[] t = new String[sequencia.size()];
		sequencia.toArray(t);

		return t;
	}

	public void organiza(){
		comando = new String("");
		for(int i = 0; i < tokens.length; i++){
			if(tokens[i].length() > 1 && tokens[i].charAt(0) == '-') tokens[i] = tokens[i].replace('-', '|');
			if(toDouble(tokens[i]) == null && Simbolos.pertence(tokens[i]) < 0){ //Se não é numero nem simbolo (váriavel)
				Variavel aux;
				if(tokens[i].charAt(0) == '|'){
					aux = Interpretador.getVar(tokens[i].substring(1));
					if(aux != null){
						comando += "|1 * ";
						String tmp = aux.getValor().toString();
						if(tmp != null){
							tmp = tmp.replace('-', '|');
							comando += tmp;
						}
					}
				}else{
					aux = Interpretador.getVar(tokens[i]);
					if(aux != null){
						String tmp = aux.getValor().toString();
						if(tmp != null){
							tmp = tmp.replace('-', '|');
							comando += tmp;
						}
					}
				}
			}else comando += tokens[i];
			if(i + 1 != tokens.length) comando += " ";
		}
	}

	public int qual(){
		for(int j = 0; j < tokens.length; j++){
			int ret = Simbolos.pertence(tokens[j]);
			if(ret > 1) return ret;
		}
		return -1;
	}

	public String condicao(){
		String ret = new String("");
		int a = qual();
		if(a == 3 || a == 4){ //É um if ou loop
			for(int i = 1; i < tokens.length - 1; i++) ret += tokens[i] + " ";
			//return comando.substring(2, comando.length() - 2);
			return ret;
		}else if(a == 20){ //É um print
			return comando.substring(comando.indexOf('(') + 1, comando.length() - 2);
		}
		return null;
	}

	public boolean percorre(int inic){ //Recebe String[] com ( na primeira posição
		int i = inic;
		int j;

		for(j = inic + 1; j < tokens.length && !tokens[j].equals(")"); j++)
			if (tokens[j].equals("(")) percorre(j);

		if (resolve(i, j) > 0) return true;
		else return false;
	}

	public double resolve(int inic, int fim){
		int k = 0, i, j;
		Expressao aux = new Expressao("");
		double t1, t2;
		String t;
		//Descobre se há operação de comparação
		for(i = inic + 1; i < fim; i++){
			k = Simbolos.pertence(tokens[i]);
			if (k >= 6 && k <= 13) break;
		}

		if(i == fim){ //Se não há comparação, resolve a expressao
			t = new String("");
			for(i = inic + 1; i < fim; i++) t += tokens[i] + " ";
			t += " ;";	aux.set(t);
			for(i = inic; i < fim; i++) tokens[i] = " ";
			tokens[fim] = aux.calcula().toString();
			return aux.calcula();

		}else{  //Caso contrario, quebra a expressao na comparação e resolve independentemente
			t = new String("");
			for(j = inic + 1; j < i; j++) t += tokens[j] + " ";
			t += " ;";	aux.set(t);
			t1 = aux.calcula();

			t = new String("");
			for(j++; j < fim; j++) t += tokens[j] + " ";
			t += " ;";	aux.set(t);
			t2 = aux.calcula();
		}

		//Apaga as expressões resolvidas
		for(i = inic; i < fim; i++) tokens[i] = " ";
		tokens[fim] = "0";
		switch(k){
			case 6:
				if (t1 > t2) tokens[fim] = "1";
				break;
			case 7:
				if (t1 < t2) tokens[fim] = "1";
				break;
			case 8:
				if (t1 >= t2) tokens[fim] = "1";
				break;
			case 9:
				if (t1 <= t2) tokens[fim] = "1";
				break;
			case 10: //IGUAL
				if (t1 == t2) tokens[fim] = "1";
				break;
			case 11: //DIF
				if (t1 != t2) tokens[fim] = "1";
				break;
			case 12: //EE
				if (t1 != 0 && t2 != 0) tokens[fim] = "1";
				break;
			case 13: //OU
				if (t1 != 0 || t2 != 0) tokens[fim] = "1";
				break;
		}

		//Transforma valor resultant para double
		return toDouble(tokens[fim]);
	}

	private void printa(){
        int flag = 0, escape = 0, i;
        String t = null, imprime = this.condicao();
        for(i = 0; i < imprime.length(); i++){
            char x = imprime.charAt(i);
            if(x == '\"' && (i - 1 < 0 || imprime.charAt(i - 1) != '\\')){
				if(t != null) System.out.println(Interpretador.getVar(t));
				t = null;
                flag = (flag + 1) % 2;
                escape = 0;
                continue;
            }
            if(flag == 1){
                if(x == '\\' && escape == 0) escape = 1;
                else if(escape == 1){
                    if(x == '\\' || x == '\"' || x == '#' || x == ';') System.out.print(x);
                    else if(x == 'n') System.out.print("\n");
                    else System.out.print("\\" + x);
                    escape = 0;
                }else{
                    escape = 0;
                    System.out.print(x);
                }
            }else if(flag == 0){

				if(x == ' ' || x == '	'){
					if(t != null) System.out.print(Interpretador.getVar(t));
					t = null;
					continue;
				}

                if(t == null) t = new String("");
				t += x;
            }
        }
		if(t != null) System.out.println(Interpretador.getVar(t));
		t = null;
    }

//============================================= Velho

	private String shuntingYard(int ini){
		String infix = comando.substring(ini, comando.length() - 2);
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
		}
		return ret;
    }

	//----------- Expressões Aritméticas
	public Double calcula(){
		String n = new String("");
		int i;
		if(qual() == 2)	n = shuntingYard(comando.indexOf('=') + 2); //Atribuição
		else n = shuntingYard(0);

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
}
