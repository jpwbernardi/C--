class If{

	public boolean percorre(String[] a, int inic){ //Recebe String[] com ( na primeira posição
		int i = inic;
		int j;

		for(j = inic + 1; j < a.length && !a[j].equals(")"); j++){
			if (a[j].equals("(")) percorre(a, j);
		}
		/*System.out.println("->>>>>>>>>>>>>iiii>>>>>>>>>>>>>>>>>>");
		for(int b = 0; b < a.length; b++){
			System.out.println(a[b]);
		}
		System.out.println("\n->>>>>>>>>>>>>>>>iiii>>>>>>>>>>>>>>>");*/

		if (resolve(a, i, j) > 0) return true;
		else return false;
	}

	public double resolve(String[] expressao, int inic, int fim){
		int k = 0, i;
		Atribuicao a = new Atribuicao();
		/*
		System.out.println("->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for(int j = inic; j <= fim; j++){
			System.out.print(expressao[j]);
		}
		System.out.println("\n->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		*/
		//Descobre se há operação de comparação
		for(i = inic + 1; i < fim; i++){
			k = Simbolos.pertence(expressao[i]);
			if (k >= 6 && k <= 13) break;
		}

		if(i == fim){ //Se não há comparação, resolve a expressao
			expressao[i] = a.calcula(CLLutil.agrupa(expressao, inic, fim)).toString();
			for(int h = inic; h < i; h++) expressao[h] = " ";
			return CLLutil.toDouble(expressao[i]);
		}else{  //Caso contrario, quebra a expressao na comparação e resolve independentemente
			expressao[i - 1] = a.calcula(CLLutil.agrupa(expressao, inic + 1, i - 1)).toString();
			expressao[i + 1] = a.calcula(CLLutil.agrupa(expressao, i + 1, fim - 1)).toString();
		}

		//Transforma valores resultantes para double
		double t1 = CLLutil.toDouble(expressao[i - 1]), t2 = CLLutil.toDouble(expressao[i + 1]);

		//Apaga as expressões resolvidas
		for(int h = inic; h <= fim; h++) expressao[h] = " ";
		expressao[i] = "0";

		switch(k){
			case 6:
				if (t1 > t2) expressao[i] = "1";
				break;
			case 7:
				if (t1 < t2) expressao[i] = "1";
				break;
			case 8:
				if (t1 >= t2) expressao[i] = "1";
				break;
			case 9:
				if (t1 <= t2) expressao[i] = "1";
				break;
			case 10: //IGUAL
				if (t1 == t2) expressao[i] = "1";
				break;
			case 11: //DIF
				if (t1 != t2) expressao[i] = "1";
				break;
			case 12: //EE
				if (t1 != 0 && t2 != 0) expressao[i] = "1";
				break;
			case 13: //OU
				if (t1 != 0 || t2 != 0) expressao[i] = "1";
				break;
		}

		return CLLutil.toDouble(expressao[i]);
	}

}
