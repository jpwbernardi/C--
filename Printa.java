class Printa{
    Expressao a;

    public Printa(String imprime){
        //System.out.println("Imprime: " + imprime);
        int flag = 0, escape = 0, i;
        String t;
        for(i = 0; i < imprime.length(); i++){
            char x = imprime.charAt(i);
            if(x == '\"'){
                flag = (flag + 1) % 2;
                escape = 0;
                continue;
            }
            if(flag == 1){
                if(x == '\\' && escape == 0) escape = 1;
                else if(escape == 1){
                    if(x == '\\' || x == '\"') System.out.print(x);
                    else if(x == 'n') System.out.print("\n");
                    else System.out.print("\\" + x);
                    escape = 0;
                }else{
                    escape = 0;
                    System.out.print(x);
                }
            }else if(flag == 0){
                int primeiro = 1, j;
                t = new String("");
                for(j = i; j < imprime.length(); j++){
                    if(imprime.charAt(j) == ' ' && primeiro != 1){
                        System.out.print(Interpretador.getVar(t));
                        primeiro = 1;
                    }else{
                        t += imprime.charAt(j) + " ";
                        primeiro = 0;
                    }
                }
            }
        }
    }

    private void mostra(){


        /*String ret = new String("");
        int flag = 0;
        for(int i = 0; i < info.length; i++){
            if(info[i].equals("\"")){
                flag = flag == 1 ? 0 : 1;
            }else if(flag == 1){
                ret += info[i];
            }
            if(flag == 0){
                if(ret.equals("") == false) System.out.print(ret);
                ret = new String("");
                flag = 0;
                continue;
            }

        }

        System.out.print("\n");*/
    }
}
