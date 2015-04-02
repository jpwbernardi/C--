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
		String aux = null;

		int i, ret = 0;

		for(i = 0; i < quebrado.length; i++){
			if(aux == null) aux = new String("");
			if(quebrado[i].equals(" ") && (aux.equals("") == false)){
				sequencia.add(aux);
				aux = null;
			}else if(Simbolos.pertence(quebrado[i]) > 0){
				if(ret == 0) ret = Simbolos.pertence(quebrado[i]);
				if(aux.equals("") == false) sequencia.add(aux);
				sequencia.add(quebrado[i]);
				aux = null;
			}else if(quebrado[i].equals(" ") == false){
				aux += quebrado[i];
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
                String[] tokens = this.divide(linhas[i]);

                int operacao = Integer.parseInt(tokens[(tokens.length) - 1]);
                if(Simbolos.pertence(tokens[0]) > 0) operacao = Simbolos.pertence(tokens[0]);

				switch(operacao){
					case 1:								//Atribuição
						atribuir.atribuirValor(tokens);
						break;
				}


            }
        }
    }
}
