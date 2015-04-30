class Simbolos{
	public static int pertence(String n){
		if(n.equals("+") || n.equals("-") || n.equals("*") || n.equals("/") || n.equals("%") || n.equals("^") || n.equals("(") || n.equals(")") || n.equals("\"")) return 1;
		else if(n.equals("=")) return 2; //Não alterar
		else if(n.equals("if")) return 3;
		else if(n.equals("else")) return 22;
		else if(n.equals("loop")) return 4;
		else if(n.equals("break")) return 5;
		else if(n.equals("continue")) return 17;
		else if(n.equals("MAIOR")) return 6;
		else if(n.equals("MENOR")) return 7;
		else if(n.equals("MAIORI")) return 8;
		else if(n.equals("MENORI")) return 9;
		else if(n.equals("IGUAL")) return 10;
		else if(n.equals("DIF")) return 11;
		else if(n.equals("EE")) return 12;
		else if(n.equals("OU")) return 13;
		else if(n.equals("{")) return 14;
		else if(n.equals("}")) return 15;
		else if(n.equals(";")) return 16;
		else if(n.equals("printa")) return 20;
		else if(n.equals("scan")) return 21;
		else return -1;
	}

	public static boolean operadores(String n){
        for(int i = 0; i < n.length(); i++)
            if(Simbolos.pertence(n.charAt(i) + "") > 0) return true;
        return false;
    }

}
