public class Variavel<KeyType, ValueType> {
    private final KeyType key;
    public ValueType value;

    public Variavel(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
    }

    public KeyType getKey() {
        return key;
    }

    /*public ValueType getValue() {
        return value;
    }

    public String toString() {
        return "(" + key + ", " + value + ")";
    }*/
}
