package io.codementor.gtommee.rest_tutorial.models;

import java.util.ArrayList;
import java.util.List;

public class TipoDistribuicao extends EntidadeAuditavelCobrancaTerceirizada {

//    private static final long serialVersionUID = 1;

//    private Long id;

    private String descricao;
    private List<Atributo> atributosCriteriosDistribuicao;
    private List<TipoDistribuicaoOrdem> atributosCriteriosOrdenacao;
    private ClienteCobrancaTerceirizada clienteCobrancaTerceirizada;
    private boolean indicadorMultiplosConvenios;
    private List<TipoDistribuicaoOrdem> atributosOrdemNaoVinculados;
    private List<Atributo> atributosNaoVinculados;
    private List<Atributo> ordenacaoUtilizada;
//    @Override
//    public Long getId() {
//        return id;
//    }

//    public Long getCodigo() {
//        return id;
//    }

//    public void setCodigo(Long codigo) {
//        this.id = codigo;
//    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Atributo> getAtributosCriteriosDistribuicao() {
        iniciaAtributosCriteriosDistribuicao();
        return atributosCriteriosDistribuicao;
    }

    public void setAtributosCriteriosDistribuicao(List<Atributo> atributosCriteriosDistribuicao) {
        this.atributosCriteriosDistribuicao = atributosCriteriosDistribuicao;
    }

    private void iniciaAtributosCriteriosDistribuicao() {
        if (this.atributosCriteriosDistribuicao == null) {
            this.atributosCriteriosDistribuicao = new ArrayList<Atributo>();
        }
    }

    public void adicionaAtributoCriterioDistribuicao(Atributo atributoCriterioDistribuicao) {
        iniciaAtributosCriteriosDistribuicao();
        if (!this.atributosCriteriosDistribuicao.contains(atributoCriterioDistribuicao)) {
            this.atributosCriteriosDistribuicao.add(atributoCriterioDistribuicao);
            if (getAtributosNaoVinculados().contains(atributoCriterioDistribuicao)) {
                getAtributosNaoVinculados().remove(atributoCriterioDistribuicao);
            }
        }
    }

    public void removeAtributoCriterioDistribuicao(Atributo atributo) {
        iniciaAtributosCriteriosDistribuicao();
        if (this.atributosCriteriosDistribuicao.contains(atributo)) {
            this.atributosCriteriosDistribuicao.remove(atributo);
            adicionaAtributoNaoVinculados(atributo);
        }
    }

    public String getDescricaoAtributosCriteriosSelecionados() {
        StringBuffer sbDescricaoAtributosSelecionados = new StringBuffer();
        String descricaoAtributosSelecionados = null;

        iniciaAtributosCriteriosDistribuicao();

        for (Atributo atributo : atributosCriteriosDistribuicao) {
            sbDescricaoAtributosSelecionados.append(atributo.getRotulo());
            sbDescricaoAtributosSelecionados.append(", ");
        }
        if (sbDescricaoAtributosSelecionados.toString().length() > 0) {
            descricaoAtributosSelecionados =
                    sbDescricaoAtributosSelecionados.toString().substring(0, sbDescricaoAtributosSelecionados.toString().length() - 2);
        }
        return descricaoAtributosSelecionados;
    }

    public List<Atributo> getAtributosNaoVinculados() {
        iniciaAtributosNaoVinculados();
        return atributosNaoVinculados;
    }

    public void setAtributosNaoVinculados(List<Atributo> atributosNaoVinculados) {
        this.atributosNaoVinculados = atributosNaoVinculados;
    }

    private void iniciaAtributosNaoVinculados() {
        if (this.atributosNaoVinculados == null) {
            this.atributosNaoVinculados = new ArrayList<Atributo>();
        }
    }

    private void iniciaAtributosOrdemNaoVinculados() {
        if (this.atributosOrdemNaoVinculados == null) {
            this.atributosOrdemNaoVinculados = new ArrayList<TipoDistribuicaoOrdem>();
        }
    }

    public void adicionaAtributoNaoVinculados(Atributo atributo) {
        iniciaAtributosNaoVinculados();
        if (!this.atributosNaoVinculados.contains(atributo)) {
            this.atributosNaoVinculados.add(atributo);
        }
    }

    public void removeAtributoNaoVinculados(Atributo atributo) {
        iniciaAtributosNaoVinculados();
        this.atributosNaoVinculados.add(atributo);
    }

    public List<TipoDistribuicaoOrdem> getAtributosCriteriosOrdenacao() {
        iniciaAtributosCriteriosOrdenacao();
        return atributosCriteriosOrdenacao;
    }

    public void setAtributosCriteriosOrdenacao(List<TipoDistribuicaoOrdem> atributosCriteriosOrdenacao) {
        if (atributosCriteriosOrdenacao != null) {
//            relacionarParentesco(atributosCriteriosOrdenacao);
        }
        this.atributosCriteriosOrdenacao = atributosCriteriosOrdenacao;
    }

    private void iniciaAtributosCriteriosOrdenacao() {
        if (this.atributosCriteriosOrdenacao == null) {
            this.atributosCriteriosOrdenacao = new ArrayList<TipoDistribuicaoOrdem>();
        }
    }

    public void adicionaAtributoOrdemNaoVinculados(Atributo atributoCriterioOrdenacao) {
        iniciaAtributosOrdemNaoVinculados();
//        TipoDistribuicaoOrdem tipo = new TipoDistribuicaoOrdem(atributoCriterioOrdenacao, this);
        TipoDistribuicaoOrdem tipo = new TipoDistribuicaoOrdem(atributoCriterioOrdenacao);
        if (!this.atributosOrdemNaoVinculados.contains(tipo)) {
            this.atributosOrdemNaoVinculados.add(tipo);
        }
    }

    public Boolean getIndicadorMultiplosConvenios() {
        return indicadorMultiplosConvenios;
    }

    public void setIndicadorMultiplosConvenios(Boolean indicadorMultiplosConvenios) {
        this.indicadorMultiplosConvenios = indicadorMultiplosConvenios;
    }

    public ClienteCobrancaTerceirizada getClienteCobrancaTerceirizada() {
        return clienteCobrancaTerceirizada;
    }

    public void setClienteCobrancaTerceirizada(ClienteCobrancaTerceirizada clienteCobrancaTerceirizada) {
        this.clienteCobrancaTerceirizada = clienteCobrancaTerceirizada;
    }

    public List<TipoDistribuicaoOrdem> getAtributosOrdemNaoVinculados() {
        iniciaAtributosOrdemNaoVinculados();
        return atributosOrdemNaoVinculados;
    }

    public void setAtributosOrdemNaoVinculados(List<TipoDistribuicaoOrdem> tipoDistribuicaoOrdemNaoVinculados) {
        this.atributosOrdemNaoVinculados = tipoDistribuicaoOrdemNaoVinculados;
    }

//    private void relacionarParentesco(List<TipoDistribuicaoOrdem> tipoDistribuicaoOrdemNaoVinculados) {
//        for (TipoDistribuicaoOrdem ordem : tipoDistribuicaoOrdemNaoVinculados) {
//            ordem.getId().setTipoDistribuicao(this.getId());
//        }
//    }

    public List<Atributo> getOrdenacaoUtilizada() {
        ordenacaoUtilizada = new ArrayList<Atributo>();
        if (getAtributosCriteriosOrdenacao() != null) {
            for (TipoDistribuicaoOrdem tipoOrdem : getAtributosCriteriosOrdenacao()) {
                ordenacaoUtilizada.add(tipoOrdem.getAtributo());
            }
        }
        return ordenacaoUtilizada;
    }

    public void setOrdenacaoUtilizada(List<Atributo> ordenacaoUtilizada) {
        this.ordenacaoUtilizada = ordenacaoUtilizada;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        // int result = super.hashCode();
//        int result = prime * 1 + ((id == null) ? 0 : id.hashCode());
//        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        int result = prime + ((descricao == null) ? 0 : descricao.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        TipoDistribuicao other = (TipoDistribuicao) obj;
//        if (id == null) {
//            if (other.id != null) {
//                return false;
//            }
//        } else if (!id.equals(other.id)) {
//            return false;
//        }
        if (descricao == null) {
            if (other.descricao != null) {
                return false;
            }
        } else if (!descricao.equals(other.descricao)) {
            return false;
        }
        return true;
    }

}
