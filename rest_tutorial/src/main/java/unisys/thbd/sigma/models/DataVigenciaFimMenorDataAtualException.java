package unisys.thbd.sigma.models;

public class DataVigenciaFimMenorDataAtualException extends AmsfwException {

//    private static final long serialVersionUID = 1L;

    /**
     * Construtor para a excecao.
     */
    public DataVigenciaFimMenorDataAtualException() {
        super("excecao.fim.vigencia.menor.data.atual");
    }

    /**
     * Construtor para a classe com espeficacao de mensagem.
     *
     * @param msg
     */
    public DataVigenciaFimMenorDataAtualException(String msg) {
        super(msg);
    }
}
