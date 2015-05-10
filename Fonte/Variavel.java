abstract class Variavel<Tipo> {
    public Tipo valor;

    public Variavel(Tipo valor) {
        setValor(valor);
    }

    public Tipo getValor() {
        return valor;
    }

    public String toString() {
        return valor + "";
    }

    public void setValor(Tipo valor){
        this.valor = valor;
    }

}
