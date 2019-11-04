package unisys.thbd.sigma.models;

public class EnderecoEmpresa extends EntidadeCobrancaTerceirizada {

//    private static final long serialVersionUID = 1L;

//    private Long id;

    private EmpresaTerceirizada empresaTerceirizada;
    private String tipoEndereco;
    private String abreviacaoLogradouro;
    private Long numeroLogradouro;
    private String complementoLogradouro;
    private String logradouro;
    private Long cep;
    private String bairro;
    private String cidade;
    private String siglaUf;

//    public Long getId() {
//        return id;
//    }


//    public void setId(Long id) {
//        this.id = id;
//    }

    /**
     * Metodo que verifica se todos os dados obrigatorios estao preenchidos.
     *
     * @return
     */
    public boolean temTodosDadosObrigatorios() {
        return (this.logradouro != null && !"".equals(this.logradouro)) && (this.cep != null)
                && (this.bairro != null && !"".equals(this.bairro)) && (this.cidade != null && !"".equals(this.cidade))
                && (this.siglaUf != null && !"".equals(this.siglaUf));
    }

    /**
     * Metodo que verifica se tem pelo menos um dado obrigatorio preenchido.
     *
     * @return
     */
    public boolean temPeloMenosUmDadoObrigatorioPreenchido() {
        return (this.logradouro != null && !"".equals(this.logradouro)) || (this.cep != null)
                || (this.bairro != null && !"".equals(this.bairro)) || (this.cidade != null && !"".equals(this.cidade))
                || (this.siglaUf != null && !"".equals(this.siglaUf));
    }

    /**
     * Metodo que verifica se tem pelo menos um dado nao obrigatorio preenchido.
     *
     * @return
     */
    public boolean existePeloMenosUmDadoNaoObrigatorioPreenchido() {
        return (this.abreviacaoLogradouro != null && !"".equals(this.abreviacaoLogradouro)) || (this.numeroLogradouro != null)
                || (this.complementoLogradouro != null && !"".equals(this.complementoLogradouro));
    }

    /**
     * @return
     */
    public UnidadeFederativaEnum getUnidadeFederativaEnum() {
        return UnidadeFederativaEnum.getUnidadeFederativaEnum(siglaUf);
    }

    /**
     * @param unidadeFederativa
     * @return
     */
    public void setUnidadeFederativaEnum(UnidadeFederativaEnum unidadeFederativa) {
        if (unidadeFederativa != null) {
            siglaUf = unidadeFederativa.getSigla();
        } else {
            siglaUf = null;
        }
    }

    /**
     * @return the empresaTerceirizada
     */
    public EmpresaTerceirizada getEmpresaTerceirizada() {
        return empresaTerceirizada;
    }

    /**
     * @param empresaTerceirizada
     *            the empresa to set
     */
    public void setEmpresaTerceirizada(EmpresaTerceirizada empresaTerceirizada) {
        this.empresaTerceirizada = empresaTerceirizada;
    }

    /**
     * @return the tipoEndereco
     */
    public String getTipoEndereco() {
        return tipoEndereco;
    }

    /**
     * @param tipoEndereco
     *            the tipoEndereco to set
     */
    public void setTipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    /**
     * @return the abreviacaoLogradouro
     */
    public String getAbreviacaoLogradouro() {
        return abreviacaoLogradouro;
    }

    /**
     * @param abreviacaoLogradouro
     *            the abreviacaoLogradouro to set
     */
    public void setAbreviacaoLogradouro(String abreviacaoLogradouro) {
        this.abreviacaoLogradouro = abreviacaoLogradouro;
    }

    /**
     * @return the numeroLogradouro
     */
    public Long getNumeroLogradouro() {
        return numeroLogradouro;
    }

    /**
     * @param numeroLogradouro
     *            the numeroLogradouro to set
     */
    public void setNumeroLogradouro(Long numeroLogradouro) {
        this.numeroLogradouro = numeroLogradouro;
    }

    /**
     * @return the complementoLogradouro
     */
    public String getComplementoLogradouro() {
        return complementoLogradouro;
    }

    /**
     * @param complementoLogradouro
     *            the complementoLogradouro to set
     */
    public void setComplementoLogradouro(String complementoLogradouro) {
        this.complementoLogradouro = complementoLogradouro;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro
     *            the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return the cep
     */
    public Long getCep() {
        return cep;
    }

    /**
     * @param cep
     *            the cep to set
     */
    public void setCep(Long cep) {
        this.cep = cep;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro
     *            the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade
     *            the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the siglaUf
     */
    public String getSiglaUf() {
        return siglaUf;
    }

    /**
     * @param siglaUf
     *            the siglaUf to set
     */
    public void setSiglaUf(String siglaUf) {
        this.siglaUf = siglaUf;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((abreviacaoLogradouro == null) ? 0 : abreviacaoLogradouro.hashCode());
        result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
        result = prime * result + ((cep == null) ? 0 : cep.hashCode());
        result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
        result = prime * result + ((complementoLogradouro == null) ? 0 : complementoLogradouro.hashCode());
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
        result = prime * result + ((numeroLogradouro == null) ? 0 : numeroLogradouro.hashCode());
        result = prime * result + ((siglaUf == null) ? 0 : siglaUf.hashCode());
        result = prime * result + ((tipoEndereco == null) ? 0 : tipoEndereco.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        EnderecoEmpresa other = (EnderecoEmpresa) obj;
        if (abreviacaoLogradouro == null) {
            if (other.abreviacaoLogradouro != null) {
                return false;
            }
        } else if (!abreviacaoLogradouro.equals(other.abreviacaoLogradouro)) {
            return false;
        }
        if (bairro == null) {
            if (other.bairro != null) {
                return false;
            }
        } else if (!bairro.equals(other.bairro)) {
            return false;
        }
        if (cep == null) {
            if (other.cep != null) {
                return false;
            }
        } else if (!cep.equals(other.cep)) {
            return false;
        }
        if (cidade == null) {
            if (other.cidade != null) {
                return false;
            }
        } else if (!cidade.equals(other.cidade)) {
            return false;
        }
        if (complementoLogradouro == null) {
            if (other.complementoLogradouro != null) {
                return false;
            }
        } else if (!complementoLogradouro.equals(other.complementoLogradouro)) {
            return false;
        }
//        if (id == null) {
//            if (other.id != null) {
//                return false;
//            }
//        } else if (!id.equals(other.id)) {
//            return false;
//        }
        if (logradouro == null) {
            if (other.logradouro != null) {
                return false;
            }
        } else if (!logradouro.equals(other.logradouro)) {
            return false;
        }
        if (numeroLogradouro == null) {
            if (other.numeroLogradouro != null) {
                return false;
            }
        } else if (!numeroLogradouro.equals(other.numeroLogradouro)) {
            return false;
        }
        if (siglaUf == null) {
            if (other.siglaUf != null) {
                return false;
            }
        } else if (!siglaUf.equals(other.siglaUf)) {
            return false;
        }
        if (tipoEndereco == null) {
            if (other.tipoEndereco != null) {
                return false;
            }
        } else if (!tipoEndereco.equals(other.tipoEndereco)) {
            return false;
        }
        return true;
    }
}
