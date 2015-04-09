class Simbolos{
	public static int pertence(String n){
		if(n.equals("+") || n.equals("-") || n.equals("*") || n.equals("/") || n.equals("%") || n.equals("^") || n.equals("(") || n.equals(")")) return 1;
		else if(n.equals("=")) return 2; //Não alterar
		else if(n.equals("if")) return 3;
		else if(n.equals("loop")) return 4;
		else if(n.equals("var")) return 5;
		else if(n.equals("MAIOR")) return 6;
		else if(n.equals("MENOR")) return 7;
		else if(n.equals("MAIORI")) return 8;
		else if(n.equals("MENORI")) return 9;
		else if(n.equals("IGUAL")) return 10;
		else if(n.equals("DIF")) return 11;
		else if(n.equals("EE")) return 12;
		else if(n.equals("OU")) return 13;
		else if(n.equals(";")) return 14;
		else return -1;
	}
}
