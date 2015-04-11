import java.util.*;
class If{
	String[] condicao;

	public If(String[] a){
		condicao = Arrays.copyOfRange(a, 0, a.length);
		/*System.out.println("Condição do if:");
		for(String x: condicao){
			System.out.println(x);
		}
		System.out.println("Fim da condição do if");*/
		//inic = i;
	}

	public boolean verificaCondicao(){
		Expressao ex = new Expressao();
		return ex.percorre(condicao, 0);
	}
}
