import java.util.*;

class Interpretador{
	private String linhas[];
	HashMap<String, Variavel> vars;

	public Interpretador(){
		vars = new HashMap<String, Variavel>();
	}

	public void novaVar(String nome, Variavel valor){
		this.vars.put(nome, valor);
	}

	public Variavel getVar(String n) {
		return this.vars.get(n);
	}

    public void interpreta(String l[]) {
        this.linhas = l;

        for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {
                // TODO: interpretar a linha
                System.out.println("Linha " + (i + 1) + ": " + this.linhas[i]);
            }
        }
    }
}
