import java.util.*;
class Loop{
    If se;


    /*public Loop(String[] bloco, String[] comando){
        int aux, i;

		ArrayList<String> tmp = new ArrayList<String>();
        tmp.add("(");
        for(int i = 0; i < comando.length; i++) tmp.add(comando[i]);
        tmp.add(")");

        String[] condicao = new String[tmp.size()];
        tmp.toArray(condicao);
        se = new If(condicao);

        /*for(String p: bloco){
            System.out.println(p);
        }

        for(i = 0; i < comando.length; i++)
            if(comando[i].equals(";")) break;
        atribuicao = Arrays.copyOfRange(comando, 1, i);

        for(aux = ++i; i < comando.length; i++)
            if(comando[i].equals(";")) break;

        incremento = Arrays.copyOfRange(comando, i + 1, comando.length - 2);*/
    //    executa(bloco);
//    }

    /*private void executa(String[] bloco){
        String[] aux = Arrays.copyOfRange(se.condicao, 0, se.condicao.length);
        Interpretador x = new Interpretador(true);

        //int i = 5; //Para não ficar em loop infinito (Condição sempre é estatica já que não trabalhamos com variaveis ainda)
        /*System.out.println("~~~~~~");
        for(String a: aux){
            System.out.println(a);
        }
        System.out.println("~~~~~~");
        //System.out.println(aux1);
        while(se.verificaCondicao() && x.interpreta(bloco) == 0)
            se = new If(aux);
    }*/
}
