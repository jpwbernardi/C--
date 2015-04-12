import java.util.*;

class Interpretador{

	Expressao expressao;
	If se;
	Loop laco;
	String linhas[];
	boolean funcao; //Se for um laço ou funcao, true


	public static HashMap<String, Variavel> vars = new HashMap<String, Variavel>();


	public Interpretador(boolean flag){
		expressao = new Expressao();
		funcao = flag;
	}

	public static void novaVar(String nome, Variavel valor){
		vars.put(nome, valor);
	}

	public static Variavel getVar(String n) {
		return vars.get(n);
	}

	private String[] divide(String n){
		ArrayList<String> sequencia = new ArrayList<String>();

		String[] quebrado = n.split("");		//Quebra string n e guarda em quebrado
		/*for(String x : quebrado){
			System.out.println(x);
		}*/

		String aux = null;

		int i;
		int flag = 1; //ultima quebra foi em um Simbolo ? 1 : 0

		//Forma mais simples que achei de consertar valores negativos!!! (tentar melhorar)
		for(i = 0; i < quebrado.length; i++){
			if(aux == null) aux = new String("");
			if(flag == 1 && quebrado[i].equals("+")) continue;	//Se o ultimo foi um sinal (operador) e o atual é +, ignora;
			if((quebrado[i].equals(" ") || quebrado[i].equals("	")) && (aux.equals("") == false)){
				if(flag != 1 && aux.equals("-") == false){
					sequencia.add(aux);
					if(Simbolos.pertence(aux) > 0) flag = 1;
					aux = null;
				}
				//System.out.println("1");
			}else if(Simbolos.pertence(quebrado[i]) > 0){
				if(quebrado[i].equals("(") && aux.equals("-")){
					sequencia.add("-1");
					sequencia.add("*");
					sequencia.add(quebrado[i]);
					aux = null;
					//System.out.println("2");
				}else if(quebrado[i].equals("-") == false || flag == 0){
					if(aux.equals("") == false) sequencia.add(aux);
					sequencia.add(quebrado[i]);
					aux = null;
					//System.out.println("3");
				}else{
					if(aux.equals("") == false) sequencia.add(aux);
					if(flag == 1) aux = new String(quebrado[i] + "");
					//System.out.println("4");
				}
				if(quebrado[i].equals(")") == false && quebrado[i].equals(")") == false){
					flag = 1;
					//System.out.println("5");
				}
			}else if(quebrado[i].equals(" ") == false && quebrado[i].equals("	") == false){
				aux += quebrado[i];
				flag = 0;
				//System.out.println("6");
			}
			//System.out.println("-> AUX  " + aux);
		}

		String[] t = new String[sequencia.size()];
		sequencia.toArray(t);

		return t;
	}

	public void atribuirValor(String[] tokens){
		String nTokens = expressao.agrupa(tokens, 2, tokens.length - 2); //remove nome da variavel, sinal de igual e ;

		//System.out.println("Atribuição :\n" + nTokens);
		Double resp = expressao.calcula(nTokens);
		//Variavel aux = Interpretador.getVar(tokens[0]);
		VariavelDouble nova = new VariavelDouble(resp);
		Interpretador.novaVar(tokens[0], nova);
				//TO DO variavel tokens[1] = expressao (ou qualquer coisa assim)
		//System.out.println("Oi");
		//System.out.println("->>>" + r);
	}

	public int fimEscopo(String l[], int i){
		int resp = 1, j;
		for(i++; i < l.length && resp != 0; i++){
			for(j = 0; j < l[i].length(); j++)
				if(Simbolos.pertence(l[i].charAt(j) + "") == 14) resp++;
				else if(Simbolos.pertence(l[i].charAt(j) + "") == 15) resp--;
		}
		return i;
	}

    public int interpreta(String l[]) {
        this.linhas = l;

        for(int i = 0; i < this.linhas.length; i++) {
			int operacao = 0;
            if(this.linhas[i] != null) {
				//System.out.println("1o ->" + linhas[i]);
                String[] tokens = this.divide(linhas[i]);
				//System.out.println("-------------");
				/*for(String x : tokens){
					System.out.println(x);
				}*/
				//System.out.println("-------------");

				//operacao = Simbolos.pertence(tokens[0]);	//Verifica qual é a primeira palavra

				for(int j = 0; j < tokens.length; j++){
					//System.out.println(tokens[j]);
					if(Simbolos.pertence(tokens[j]) > 1){
						operacao = Simbolos.pertence(tokens[j]);
						break;
					}
				}

				switch(operacao){
					case 1:
						System.out.println("Só operações matematicas... algo errado");
						break;
					case 2:								//Atribuição
						System.out.println("Tem atribuicao!!");
						atribuirValor(tokens);
						break;
					case 3:
						System.out.println("Tem um if!!");
						se = new If(Arrays.copyOfRange(tokens, 1, tokens.length - 1)); //Removendo a chave do final e o if do começo
						/*for(String x: se.condicao){
							System.out.println(x);
						}*/
						if(se.verificaCondicao()) continue;
						else i = fimEscopo(l, i) - 1;
						//return;
						//System.out.println();
						break;
					case 4:
						//System.out.println("Tem um loop!!");

						int j = fimEscopo(l, i);

						//System.out.println("------>>>>>" + l[i]);

						Loop p = new Loop(Arrays.copyOfRange(l, i + 1, j - 1), Arrays.copyOfRange(tokens, 1, tokens.length - 1));
						i = j;
						/*System.out.println("------");
						for(String x: p.atribuicao){
							System.out.println(x);
						}
						System.out.println("------");
						for(String x: p.se.condicao){
							System.out.println(x);
						}
						System.out.println("");
						for(String x: p.incremento){
							System.out.println(x);
						}
						System.out.println("");*/
						break;
					case 5:
						if(funcao){
							System.out.println("Tem um break aqui!");
							return 1;
						}
						break;
					case 17:
						if(funcao){
							System.out.println("Tem continue!");
							i = linhas.length;
						}
						break;
					case 16:
						System.out.println("Tem um ;");
						break;

					case 20:
						System.out.println("PRINT");
						Printa printa = new Printa(Arrays.copyOfRange(tokens, 2, tokens.length - 2));
					default:
						System.out.println("Algo errado??...");
						break;

				}


            }
        }
		return 0;
    }
}
