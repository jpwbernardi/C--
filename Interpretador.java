import java.util.*;

class Interpretador{
	private String linhas[];
	public static HashMap<String, Variavel> vars;

	public Interpretador(){
		vars = new HashMap<String, Variavel>();
	}

	public void novaVar(String nome, Variavel valor){
		this.vars.put(nome, valor);
	}

	public Variavel getVar(String n) {
		return this.vars.get(n);
	}
	
	private String[] divide(String n){
		ArrayList<String> sequencia = new ArrayList<String>();
		
		String[] quebrado = n.split("");		//Quebra string n e guarda em quebrado
		String aux = null;
		
		int i;
		
		for(i = 0; i < quebrado.length; i++){
			if(aux == null) aux = new String("");
			if(quebrado[i].equals("\n")){
				sequencia.add(aux);
				aux = null;
			}else if(Simbolos.pertence(quebrado[i]) > 0){
				sequencia.add(aux);
				sequencia.add(quebrado[i]);	
				aux = null;
			}else if(quebrado[i].equals(" ") == false){
				aux += quebrado[i];
			}
		}
		String[] t = new String[sequencia.size()];
		sequencia.toArray(t);
		return t;
	}

    public void interpreta(String l[]) {
        this.linhas = l;

        for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {
                String[] teste = this.divide(linhas[i]);
                
                for(String x: teste)
					System.out.println(x);
            }
        }
    }
}
