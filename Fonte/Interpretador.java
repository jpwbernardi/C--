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
		VariavelDouble erro = new VariavelDouble(0.0);
		return vars.get(n) != null ? vars.get(n) : erro;
	}



	public void atribuirValor(){
		Double resp = expressao.calcula();
		if(resp != null && expressao.tokens[0].matches("([a-z]+\\d*)+")){
			VariavelDouble nova = new VariavelDouble(resp);
			Interpretador.novaVar(expressao.tokens[0], nova);
		}else{
			erro = true;
		}
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
		for(int i = 0; i < s.length && s[i] != null; i++){
			s[i].replace('\n', '\0');
			int inicio = 0, j;
			for(j = 0; j < s[i].length(); j++){
				if(((j - 1 < 0 || s[i].charAt(j - 1) != '\\') && s[i].charAt(j) == ';') || s[i].charAt(j) == '{' || s[i].charAt(j) == '}'){
					codigo.add(s[i].substring(inicio, j + 1));
					inicio = j + 1;
				}else if(s[i].charAt(j) == '#' && (j - 1 < 0 || s[i].charAt(j - 1) != '\\')) break;
			}
			if(inicio < s[i].length()){
				if(s[i + 1] != null) s[i + 1] = s[i].substring(inicio, j) + " " + s[i + 1];
				else if(s[i].charAt(j) != '#'){
					erro = true;
					System.out.println("Erro ao montar o código na linha: " + (i + 1));
					break;
				}
			}
		}

		String[] t = new String[codigo.size()];
		codigo.toArray(t);

		return t;
	}

    public int interpreta(String l[]) {
        linhas = montaCodigo(l);
		if(erro) return -1;

        for(int i = 0; i < this.linhas.length; i++) {
			int operacao = 0;
            if(this.linhas[i] != null) {
                expressao.set(linhas[i]);
				operacao = expressao.qual();	//Verifica qual é a operação (comando) a ser executado
				switch(operacao){
					case 2:								//Atribuição
						atribuirValor();
						break;
					case 3:
						se = new If(expressao.condicao()); //Removendo a chave do final e o if do começo
						if(se.verificaCondicao()) continue;
						else i = fimEscopo(i);
						break;
					case 4:
						int j = fimEscopo(i);
						Loop p = new Loop(Arrays.copyOfRange(linhas, i + 1, j), expressao.condicao());
						erro = p.erro;
						i = j;
					case 5:
						if(funcao){
							return 1;
						}
						break;
					case 17:
						if(funcao){
							i = linhas.length;
						}
						break;
					case 14:
					case 15:
					case 20:
						//Se for printf, resolve na propria expressao!
						//Se for chaves, ignora!
						break;
					case 21:
						//System.out.println("Oi");
						Scan scan = new Scan(expressao);
						erro = scan.erro;
						scan.le();
						break;
					default:
						erro = true;
						break;
				}
				if(erro){
					System.out.println("\nErro no comando: " + linhas[i]);
					return 2;
				}
			}
        }
		return 0;
	}
}
