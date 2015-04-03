import java.util.*;

class Interpretador{

	Atribuicao atribuir;
	String linhas[];


	public static HashMap<String, Variavel> vars;


	public Interpretador(){
		vars = new HashMap<String, Variavel>();
		atribuir = new Atribuicao();
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

		int i, ret = 0;
		int flag = 1; //ultima quebra foi em um Simbolo ? 1 : 0

		for(i = 0; i < quebrado.length; i++){
			if(aux == null) aux = new String("");
			if(quebrado[i].equals(" ") && (aux.equals("") == false)){
				if(flag != 1 && aux.equals("-") == false){
					sequencia.add(aux);
					aux = null;
				}
			}else if(Simbolos.pertence(quebrado[i]) > 0){
				if(ret == 0) ret = Simbolos.pertence(quebrado[i]);		//Pega o primeiro valor de simbolo que encontrou
				if(quebrado[i].equals("-") == false || flag == 0){
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
			}else if(quebrado[i].equals(" ") == false){
				aux += quebrado[i];
				flag = 0;
			}
		}
		sequencia.add("" + ret);
		String[] t = new String[sequencia.size()];
		sequencia.toArray(t);
		return t;
	}

    public void interpreta(String l[]) {
        this.linhas = l;

        for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {
				//System.out.println("1o ->" + linhas[i]);
                String[] tokens = this.divide(linhas[i]);
				for(String x : tokens){
					System.out.println(x);
				}

                int operacao = Integer.parseInt(tokens[(tokens.length) - 1]); //Ultima posição da string guarda a primeira operação encontrada;

                if(operacao != 2){ //Se não for uma atribuição

				}

				switch(operacao){
					case 2:								//Atribuição
						atribuir.atribuirValor(tokens);
						break;
				}


            }
        }
    }
}
