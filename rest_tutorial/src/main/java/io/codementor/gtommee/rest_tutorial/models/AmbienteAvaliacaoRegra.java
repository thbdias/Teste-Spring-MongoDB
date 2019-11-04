package io.codementor.gtommee.rest_tutorial.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmbienteAvaliacaoRegra {

    private Map<String, List<DadoContratoCobrancaTerceirizada>> mapaDadosContrato;
    private List<AcaoRegra> acoesRegra;

    /**
     * @param dadosContrato
     * @param listaRegraComposicao
     */
    public AmbienteAvaliacaoRegra(List<DadoContratoCobrancaTerceirizada> dadosContrato, List<Regra> listaRegraComposicao) {
        this.mapaDadosContrato = new HashMap<String, List<DadoContratoCobrancaTerceirizada>>();
        for (DadoContratoCobrancaTerceirizada dado : dadosContrato) {
            List<DadoContratoCobrancaTerceirizada> listaDado = this.mapaDadosContrato.get(dado.getAtributo().getAtributo());
            if (listaDado == null) {
                listaDado = new ArrayList<DadoContratoCobrancaTerceirizada>();
            }
            listaDado.add(dado);
            this.mapaDadosContrato.put(dado.getAtributo().getAtributo(), listaDado);
        }

        this.acoesRegra = new ArrayList<>();
        if (listaRegraComposicao != null) {
            for (Regra regra : listaRegraComposicao) {
                adicionaRegraMetadadoComposto(regra.getAcoesRegra());
            }
        }
    }

    private void adicionaRegraMetadadoComposto(List<AcaoRegra> acoesRegra) {
        for (AcaoRegra acaoRegra : acoesRegra) {
            if (acaoRegra.getAtributo().isComposicao()) {
                this.acoesRegra.add(acaoRegra);
            }
        }

    }

    /**
     * @param atributo
     * @return
     */

    public List<DadoContratoCobrancaTerceirizada> getDadoContratoCobTerc(String atributo) {
        return this.mapaDadosContrato.get(atributo);
    }

    public List<AcaoRegra> getAcoesRegra() {
        return acoesRegra;
    }

    /**
     * Retorna a regraComposicao do valor e atributoComposto.
     *
     * @param atributo
     * @param valor
     * @return
     */
    public RegraComposicao recuperarRegraComposicao(Atributo atributo, String valor) {
        if (!atributo.isComposicao()) {
            throw new AmsfwException("Deve se utilizar para atibutos compostos.");
        }
        for (AcaoRegra acaoRegra : acoesRegra) {
            if (acaoRegra.atributoValorCorrespondente(atributo, valor)) {
                return (RegraComposicao) acaoRegra.getRegra();
            }
        }
        return null;

    }

    public void setAcoesRegra(List<AcaoRegra> acoesRegra) {
        this.acoesRegra = acoesRegra;
    }

}
