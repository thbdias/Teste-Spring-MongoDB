package unisys.thbd.sigma.models;

public class ConversorAtributo {

    private ConversorAtributo() {
    }

    @SuppressWarnings("rawtypes")
    public static Comparable converterAtributo(Atributo atributo, String valor) {
        try {
            Decorator dec = atributo.getTipoDados().getDecorator().newInstance();
            return (Comparable) dec.fromString(valor);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked" })
    public static String atributoToString(Atributo atributo, String valor) {
        try {
            Decorator dec = atributo.getTipoDados().getDecorator().newInstance();
            return dec.toString(dec.fromString(valor));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

}
