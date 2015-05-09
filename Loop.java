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

        //System.out.println("Bloco: ");
        //for(String x: bloco) System.out.println(x);
        //System.out.println("");
        //System.out.println("LOOP -> " + condicao);
        executa();
    }

    private void executa(){
        //String aux = se.condicao.comando.substring(0);
        Interpretador x = new Interpretador(true);

        //int i = 5; //Para não ficar em loop infinito (Condição sempre é estatica já que não trabalhamos com variaveis ainda)
        /*System.out.println("~~~~~~");
        for(String a: aux){
            System.out.println(a);
        }
        System.out.println("~~~~~~");
        //System.out.println(aux1);*/
        while(se.verificaCondicao()){
            int ret = x.interpreta(bloco);
            if(ret == 2) erro = true;
            if(ret != 0) break;
            se = new If(condicao);
        }

    }
}
