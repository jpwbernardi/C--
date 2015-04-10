import java.util.*;
class If{
	String[] condicao;
	int inic;
	
	public If(String[] a, int i){
		condicao = Arrays.copyOfRange(a, 0, a.length - 1);
		for(String x: condicao){
			System.out.println(x);
		}
		inic = i;
	}
	
	public boolean verificaCondicao(){
		Expressao ex = new Expressao();
		return ex.percorre(condicao, inic);
	}
}
