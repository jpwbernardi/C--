import java.util.*;

class Interpretador{

	Expressao expressao;
	If se;
	Loop laco;
	String linhas[];
	boolean funcao; //Se for um laço ou funcao, true
	boolean erro;


	public static HashMap<String, Variavel> vars = new HashMap<String, Variavel>();


	public Interpretador(boolean flag){
		expressao = new Expressao("");
		funcao = flag;
		erro = false;
	}

	public static void novaVar(String nome, Variavel valor){
		vars.put(nome, valor);
	}

	public static Variavel getVar(String n) {
		return vars.get(n);
	}



	public void atribuirValor(){
		//System.out.println("Atribuição :\n" + nTokens);
		Double resp = expressao.calcula();
		//Variavel aux = Interpretador.getVar(tokens[0]);
		if(resp != null){
			VariavelDouble nova = new VariavelDouble(resp);
			Interpretador.novaVar(expressao.tokens[0], nova);
		}else{
			erro = true;
		}
				//TO DO variavel tokens[1] = expressao (ou qualquer coisa assim)
		//System.out.println("Oi");
		//System.out.println("->>>" + r);
	}

	public int fimEscopo(int i){
		int resp = 1, j;
		for(i++; i < linhas.length; i++){
			for(j = 0; j < linhas[i].length(); j++)
				if(Simbolos.pertence(linhas[i].charAt(j) + "") == 14) resp++;
				else if(Simbolos.pertence(linhas[i].charAt(j) + "") == 15) resp--;
				if(resp == 0) break;
		}
		return i;
	}

	private String[] montaCodigo(String[] s){
		ArrayList<String> codigo = new ArrayList<String>();
		for(int i = 0; s[i] != null; i++){
			s[i].replace('\n', '\0');
			int inicio = 0, j;
			for(j = 0; j < s[i].length(); j++){
				if(s[i].charAt(j) == ';' || s[i].charAt(j) == '{' || s[i].charAt(j) == '}'){
					codigo.add(s[i].substring(inicio, j + 1));
					inicio = j + 1;
				}else if(s[i].charAt(j) == '#') break;
			}
			if(inicio < s[i].length()){
				if(s[i + 1] != null) s[i + 1] = s[i].substring(inicio, j) + " " + s[i + 1];
				else erro = true;
			}
		}

		String[] t = new String[codigo.size()];
		codigo.toArray(t);

		return t;
	}

    public int interpreta(String l[]) {
        linhas = montaCodigo(l);
		if(erro) return -1;
		/*for(String x : linhas){
			System.out.println(x + " ");
		}*/
        for(int i = 0; i < this.linhas.length; i++) {
			int operacao = 0;
            if(this.linhas[i] != null) {
				//System.out.println("1o ->" + linhas[i]);
                expressao.set(linhas[i]);
				//System.out.println("-------------");
				//System.out.println(expressao.comando);
				for(String x : expressao.tokens){
					System.out.println(x + " ");
				}
//				System.out.println("-------------");

				operacao = expressao.qual();	//Verifica qual é a operação (comando) a ser executado
				System.out.println(operacao);
				switch(operacao){
					case 1:
					//	System.out.println("Só operações matematicas... algo errado");
						break;
					case 2:								//Atribuição
						//System.out.println("Tem atribuicao!!");
						atribuirValor();
						break;
					case 3:
					//	System.out.println("Tem um if!!");
						se = new If(expressao.condicao()); //Removendo a chave do final e o if do começo
						if(se.verificaCondicao()) continue;
						else i = fimEscopo(i);
						//System.out.println("Fim ->" + i);
						//}
						//return;
						//System.out.println("-----> "+ linhas[i]);
						break;
					/*//case 4:
						//System.out.println("Tem um loop!!");

						int j = fimEscopo(l, i);

					//	System.out.println("j = " + j + "    i = " + i);

						Loop p = new Loop(Arrays.copyOfRange(l, i + 1, j), Arrays.copyOfRange(tokens, 1, tokens.length - 1));
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
						System.out.println("");
						break;
					case 5:
						if(funcao){
						//	System.out.println("Tem um break aqui!");
							return 1;
						}
						break;
					case 17:
						if(funcao){
						//	System.out.println("Tem continue!");
							i = linhas.length;
						}
						break;
					case 16:
						//System.out.println("Tem um ;");
						break;

					case 20:
						//System.out.println("PRINT");
						Printa printa = new Printa(Arrays.copyOfRange(tokens, 2, tokens.length - 2));
					default:
					//	System.out.println("Algo errado??...");
						break;
						*/
				}
			}
        }
		return 0;
	}
}
