class Printa{
    String[] info;

    public Printa(String[] tokens){
        info = tokens;
        /*for(String x: tokens){
            System.out.println(x);

        }*/
        mostra();
    }

    private void mostra(){
        String ret = new String("");
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

        System.out.print("\n");
    }
}
