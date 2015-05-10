import java.util.*;
class Loop{
    If se;
    String condicao;
    String[] bloco;
    boolean erro;

    public Loop(String[] b, String cond){
        bloco = b;
        condicao = cond;
        se = new If(condicao);
        erro = false;
        executa();
    }

    private void executa(){
        Interpretador x = new Interpretador(true);
        
        while(se.verificaCondicao()){
            int ret = x.interpreta(bloco);
            if(ret == 2) erro = true;
            if(ret != 0) break;
            se = new If(condicao);
        }

    }
}
