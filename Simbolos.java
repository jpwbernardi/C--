class Simbolos{
	public static int pertence(String n){
		if(n.equals("+") || n.equals("-") || n.equals("*") || n.equals("/") || n.equals("%") || n.equals("^") || n.equals("(") || n.equals(")")) return 1;
		else if(n.equals("=")) return 2; //Não alterar
		else if(n.equals("if")) return 3;
		else if(n.equals("loop")) return 4;
		else if(n.equals("var")) return 5;
		else if(n.equals(";")) return 10;
		else return -1;
	}
	
}
