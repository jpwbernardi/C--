class If{
	
	public boolean percorre(String[] a, int inic){ //Recebe String[] com ( na primeira posição
		int i = inic;
		int j;
		for(j = inic + 1; j < a.length && !a[j].equals(")"); j++){
			if (a[j].equals("(")) percorre(a, j);
		}
		double aux = resolve(a, i, j);
		if (aux) return true;
		else return false;
	}
	
	public double resolve(String[] expressao, int inic, int fim){
		int k;
		Atribuicao a = new Atribuicao();
		for(int i = inic + 1; i < fim; i++){
			k = Simbolos.pertence(expressao[i]);
			if (k >= 6 && k <= 13) break;
		}
		if (i == fim) return expressao[i - 1] = a.calcula(CLLutil.agrupa(expressao), inic, fim);
		else {
			expressao[i - 1] = a.calcula(CLLutil.agrupa(expressao), inic, i - 1) + "";
			expressao[i + 1] = a.calcula(CLLutil.agrupa(expressao), i + 1, fim) + "";
		}
		switch(k){
			case 6:
				if (CLLutil.toDouble(expressao[i - 1]) > CLLutil.toDouble(expressao[i + 1])) return 1;
				else return 0;
				break;
			case 7:
				if (CLLutil.toDouble(expressao[i - 1]) < CLLutil.toDouble(expressao[i + 1])) return 1;
				else return 0;
				break;
			case 8:
				if (CLLutil.toDouble(expressao[i - 1]) <= CLLutil.toDouble(expressao[i + 1])) return 1;
				else return 0;
				break;
			case 9:
				if (CLLutil.toDouble(expressao[i - 1]) >= CLLutil.toDouble(expressao[i + 1])) return 1;
				else return 0;
				break;
			case 10:
				if (CLLutil.toDouble(expressao[i - 1]) == CLLutil.toDouble(expressao[i + 1])) return 1;
				else return 0;
				break;
			case 11:
				if (CLLutil.toDouble(expressao[i - 1]) != CLLutil.toDouble(expressao[i + 1])) return 1;
				else return 0;
				break;
			case 12:
				if (CLLutil.toDouble(expressao[i - 1]) != 0 && CLLutil.toDouble(expressao[i + 1]) != 0) return 1;
				else return 0;
				break;
			case 13:
				if (CLLutil.toDouble(expressao[i - 1]) == 0 && CLLutil.toDouble(expressao[i + 1]) == 0) return 0;
				else return 1;
				break;		
			default:
				break;			
		}
	}
	
}
