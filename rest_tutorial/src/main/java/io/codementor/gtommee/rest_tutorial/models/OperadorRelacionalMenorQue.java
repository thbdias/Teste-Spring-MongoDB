package io.codementor.gtommee.rest_tutorial.models;

public class OperadorRelacionalMenorQue implements OperadorRelacional {

    @Override
    @SuppressWarnings({"unchecked", "rawtypes" })
    public Boolean avaliarCondicao(Atributo atributo, String valorRegra, String valorContrato) {

        Comparable valorConvertidoRegra = ConversorAtributo.converterAtributo(atributo, valorRegra);
        Comparable valorConvertidoContrato = ConversorAtributo.converterAtributo(atributo, valorContrato);

        return valorConvertidoContrato.compareTo(valorConvertidoRegra) < 0;
    }

}
