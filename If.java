import java.util.*;
class If{
	Expressao condicao;

	public If(String cond){
		condicao = new Expressao("(" + cond + ") ;");
		//System.out.println(condicao.comando);
		/*System.out.println("Condição do if:");
		for(String x: condicao){
			System.out.println(x);
		}
		System.out.println("Fim da condição do if");*/
		//inic = i;
	}

	public boolean verificaCondicao(){
		//Expressao ex = new Expressao();
		//return true;
		//boolean aux = ;
		//System.out.println(aux);
		return condicao.percorre(0);
	}
}
