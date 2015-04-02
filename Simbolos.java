class Simbolos{
	public static int pertence(String n){
		if(n.equals("=")) return 1;
		else if(n.equals("+") || n.equals("-")) return 2;
		else if(n.equals("*") || n.equals("/") || n.equals("%")) return 3;
		else if(n.equals(";")) return 4;
		else if(n.equals("(") || n.equals(")") || n.equals("^")) return 5;
		else if(n.equals("var")) return 6; //Double, int ou string (decidir melhor)
		
		else return -1;
		
		
	}
}
