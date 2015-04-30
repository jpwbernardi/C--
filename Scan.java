import java.util.*;

class Scan{
    Scanner entrada;
    ArrayList<String> variaveis;
    boolean erro;

    public Scan(Expressao vars){
        erro = false;
        entrada = new Scanner(System.in);
        variaveis = new ArrayList<String>();
        for(int i = 2; i < vars.tokens.length; i++){
            if(vars.tokens[i].equals(",") || vars.tokens[i].equals(")")){
                if(vars.tokens[i - 1].matches("(\\w*\\d*)*")){
                    variaveis.add(vars.tokens[i - 1]);
                }else{
                    erro = true;
                    break;
                }
            }
        }
    }

    public void le(){
        for(int i = 0; i < variaveis.size(); i++){
            VariavelDouble aux = new VariavelDouble(entrada.nextDouble());
            Interpretador.novaVar(variaveis.get(i), aux);
        }
    }
}
