import java.util.*;
class Loop{
    If se;
    String[] atribuicao;
    String[] incremento;

    public Loop(String[] bloco, String[] comando){
        int aux, i;

        for(i = 0; i < comando.length; i++)
            if(comando[i].equals(";")) break;
        atribuicao = Arrays.copyOfRange(comando, 1, i);

        for(aux = ++i; i < comando.length; i++)
            if(comando[i].equals(";")) break;

        ArrayList<String> tmp = new ArrayList<String>();
        tmp.add("(");
        while(aux < i){ tmp.add(comando[aux]); aux++; }
        tmp.add(")");

        String[] condicao = new String[tmp.size()];
        tmp.toArray(condicao);
        se = new If(condicao);

        incremento = Arrays.copyOfRange(comando, i + 1, comando.length - 2);
        executa(bloco);
    }

    private void executa(String[] bloco){
        String[] aux = Arrays.copyOfRange(se.condicao, 0, se.condicao.length);
        Interpretador x = new Interpretador();
        int i = 5; //Para não ficar em loop infinito (Condição sempre é estatica já que não trabalhamos com variaveis ainda)
        while(se.verificaCondicao() && i > 0){
            //for(String a: aux) System.out.print(a);
            //System.out.print("\n");
            x.interpreta(bloco);
            i--;
            //System.out.println("~~" + i + "~~\n\n");
            //System.out.print("\n\n");
            se = new If(aux);
        }
    }
}
