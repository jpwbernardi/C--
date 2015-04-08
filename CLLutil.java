import java.util.Stack;

public class CLLutil{

    public static String shuntingYard(String infix) {
        final String ops = "-+/*%^";
        StringBuilder sb = new StringBuilder();
        Stack<Integer> s = new Stack<>();

        for (String token : infix.split("\\s")) {
            if (token.isEmpty())
                continue;
            char c = token.charAt(0);
            int idx = ops.indexOf(c);

            // check for operator
            if (idx != -1) {
                if (s.isEmpty())
                    s.push(idx);

                else {
                    while (!s.isEmpty()) {
                        int prec2 = s.peek() / 2;
                        int prec1 = idx / 2;
                        if (prec2 > prec1 || (prec2 == prec1 && c != '^'))
                            sb.append(ops.charAt(s.pop())).append(' ');
                        else break;
                    }
                    s.push(idx);
                }
            }
            else if (c == '(') {
                s.push(-2); // -2 stands for '('
            }
            else if (c == ')') {
                // until '(' on stack, pop operators.
                while (s.peek() != -2)
                    sb.append(ops.charAt(s.pop())).append(' ');
                s.pop();
            }
            else {
                sb.append(token).append(' ');
            }
        }
        while (!s.isEmpty())
            sb.append(ops.charAt(s.pop())).append(' ');
        return sb.toString();
    }

    public static Double toDouble(String n){
        double ret = 0;
		int pot = 1, i;
        for(i = 0; i < n.length() && n.charAt(i) != ' '; i++);
		for(i--; i >= 0 && n.charAt(i) != ' '; i--){
			if((n.charAt(i) >= '0') && (n.charAt(i) <= '9')){
				ret += (n.charAt(i) - '0') * pot;
				pot *= 10;
			}else if(n.charAt(i) == '|' || n.charAt(i) == '-'){
                ret *= -1;
            }else if(n.charAt(i) == '.'){
				ret /= pot;
				pot = 1;
			}else{
                return null;
            }
            //System.out.println("toDouble i: " + i + "      ret: " + ret);
		}
		return ret;
    }

    public static boolean operadores(String n){
        for(int i = 0; i < n.length(); i++)
            if(Simbolos.pertence(n.charAt(i) + "") > 0) return true;
        return false;
    }

    public static String agrupa(String[] a, int ini, int fim){
        String ret = new String("");
        Interpretador b = new Interpretador();
        for(int i = ini; i < fim; i++){
			if(i > ini) ret += " ";
			
			
			/*Double aux = CLLutil.toDouble(a[i]);
			Object oi;
			oi = Interpretador.getVar(a[i].substring(1));
			
			System.out.println(oi.toString());*/
			
			if(a[i].charAt(0) == '-' && a[i].length() > 1) a[i] = a[i].replace('-', '|');
			/*if(aux == null && Simbolos.pertence(a[i]) < 0){
				if(a[i].charAt(0) == '|') ret += "-" + Interpretador.getVar(a[i].substring(1)).toString();
				ret +=  Interpretador.getVar(a[i].substring(1)).toString();
			}else{*/
				ret +=  a[i];
			//}
			
			
		}
        return ret;
    }

}
