class Printa{
    String[] info;

    public Printa(String[] tokens){
        info = tokens;
        for(String x: tokens){
            System.out.println(x);

        }
        mostra();
    }

    private void mostra(){
        String ret = new String("");
        int flag = 0;
        for(int i = 0; i < info.length; i++){
            if(info[i].charAt(0) == '"'){
                flag = 1;
            }
            if(flag == 1 && info[i].charAt(info[i].length() - 1) != '"'){
                ret += info[i];
            }else if(info[i].charAt(0) == '"'){
                System.out.print(ret);
                ret = new String("");
                flag = 0;
                continue;
            }

        }
    }
}
