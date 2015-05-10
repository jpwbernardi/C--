import java.util.*;
class If{
	Expressao condicao;

	public If(String cond){
		condicao = new Expressao("(" + cond + ") ;");
	}

	public boolean verificaCondicao(){
		return condicao.percorre(0);
	}
}
