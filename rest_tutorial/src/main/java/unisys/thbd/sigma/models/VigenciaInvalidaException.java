package unisys.thbd.sigma.models;

public class VigenciaInvalidaException extends AmsfwException {

//    private static final long serialVersionUID = 1L;

    /**
     * Construtor da excessao.
     */
    public VigenciaInvalidaException() {
        super("excecao.fim.antes.inicio");
    }

    /**
     * Construtor que possui a caracteristica de especificar a mensagem.
     */
    public VigenciaInvalidaException(String msg) {
        super(msg);
    }
}
