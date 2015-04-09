class If{
	
	public boolean percorre(String[] a, int inic){ //Recebe String[] com ( na primeira posição
		int i = inic;
		int j;
		for(j = inic + 1; j < a.length && !a[j].equals(")"); j++){
			if (a[j].equals("(")) percorre(a, j);
		}
		double aux = resolve(a, i, j);
		if (aux > 0) return true;
		else return false;
	}
	
	public double resolve(String[] expressao, int inic, int fim){
		int k = 0, i;
		Atribuicao a = new Atribuicao();
		for(i = inic + 1; i < fim; i++){
			k = Simbolos.pertence(expressao[i]);
			if (k >= 6 && k <= 13) break;
		}
		if (i == fim){
			 expressao[i - 1] = a.calcula(CLLutil.agrupa(expressao, inic, fim)).toString();
			 return CLLutil.toDouble(expressao[i - 1]);
		 }
		else {
			expressao[i - 1] = a.calcula(CLLutil.agrupa(expressao, inic + 1, i - 1)).toString();
			expressao[i + 1] = a.calcula(CLLutil.agrupa(expressao, i + 1, fim - 1)).toString();
		}
		switch(k){
			case 6:
				if (CLLutil.toDouble(expressao[i - 1]) > CLLutil.toDouble(expressao[i + 1])) return 1;
				else return 0;

			case 7:
				if (CLLutil.toDouble(expressao[i - 1]) < CLLutil.toDouble(expressao[i + 1])) return 1;
				else return 0;

			case 8:
				if (CLLutil.toDouble(expressao[i - 1]) <= CLLutil.toDouble(expressao[i + 1])) return 1;
				else return 0;

			case 9:
				if (CLLutil.toDouble(expressao[i - 1]) >= CLLutil.toDouble(expressao[i + 1])) return 1;
				else return 0;

			case 10:
				if (CLLutil.toDouble(expressao[i - 1]) == CLLutil.toDouble(expressao[i + 1])) return 1;
				else return 0;

			case 11:
				if (CLLutil.toDouble(expressao[i - 1]) != CLLutil.toDouble(expressao[i + 1])) return 1;
				else return 0;

			case 12:
				if (CLLutil.toDouble(expressao[i - 1]) != 0 && CLLutil.toDouble(expressao[i + 1]) != 0) return 1;
				else return 0;

			case 13:
				if (CLLutil.toDouble(expressao[i - 1]) == 0 && CLLutil.toDouble(expressao[i + 1]) == 0) return 0;
				else return 1;

			default:
				return 0;
		}
	}
	
}
