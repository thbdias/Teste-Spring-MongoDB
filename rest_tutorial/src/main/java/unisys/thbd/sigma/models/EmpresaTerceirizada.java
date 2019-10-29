package unisys.thbd.sigma.models;

import java.util.Collections;
import java.util.List;


public class EmpresaTerceirizada extends EntidadeAuditavelCobrancaTerceirizada {

//    private static final long serialVersionUID = 1L;

//    private Long id;

    private EnderecoEmpresaMatriz enderecoEmpresaMatriz;

    private EnderecoEmpresaFilial enderecoEmpresaFilial;

    private ClienteCobrancaTerceirizada clienteCobrancaTerceirizada;

    private List<ContatoEmpresa> contatos;

    private Long codigo;

    private String razaoSocial;

    private Long numeroCnpj;

    private Long numero0800;

    private Integer ramalSegundoTelefone;

    private Long numeroSegundoTelefone;

    private String nomeResponsavel;

    private Long cpfResponsavel;

    private String emailInstitucional;

    private String emailNegocial;

    private String site;

    private String agencia;

    private Long contaCorrente;

    private String codigoBanco;

    private String codigoOperacao;

    private TipoContaEnum tipoConta;

    private EnderecoEmpresaFilial enderecoEmpresaFilialNoBanco;

    public boolean isContaCaixa() {

        return getCodigoBanco() != null && getCodigoBanco().equals("104") && TipoContaEnum.isContaCaixa(getTipoConta());
    }

    /**
     * Metodo que recupera o codigo da empresa e o cnpj e a transforma em uma
     * string.
     *
     * @return
     */
    public String getCodigoEmpresaCnpj() {
        if (getCodigo() != null && getRazaoSocial() != null) {
            return getCodigo() + " - " + getRazaoSocial();
        }
        return null;
    }

    public EnderecoEmpresaFilial getEnderecoEmpresaFilialNoBanco() {
        return enderecoEmpresaFilialNoBanco;
    }

    public void setEnderecoEmpresaFilialNoBanco(EnderecoEmpresaFilial enderecoEmpresaFilialNoBanco) {
        this.enderecoEmpresaFilialNoBanco = enderecoEmpresaFilialNoBanco;
    }

    /**
     * @return the numero0800
     */
    public Long getNumero0800() {
        return numero0800;
    }

    /**
     * @param numero0800
     *            the numero0800 to set
     */
    public void setNumero0800(Long numero0800) {
        this.numero0800 = numero0800;
    }

    /**
     * @return the emailInstitucional
     */
    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    /**
     * @param emailInstitucional
     *            the emailInstitucional to set
     */
    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    /**
     * @return the emailNegocial
     */
    public String getEmailNegocial() {
        return emailNegocial;
    }

    /**
     * @param emailNegocial
     *            the emailNegocial to set
     */
    public void setEmailNegocial(String emailNegocial) {
        this.emailNegocial = emailNegocial;
    }

    /**
     * @return the site
     */
    public String getSite() {
        return site;
    }

    /**
     * @param site
     *            the site to set
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * @param numeroCnpj
     *            the numeroCnpj to set
     */
    public void setNumeroCnpjStr(String numeroCnpj) {

        if (numeroCnpj != null) {
            try {
                this.numeroCnpj = Long.parseLong(numeroCnpj);
            } catch (NumberFormatException e) {
                this.numeroCnpj = null;
            }
        } else {
            this.numeroCnpj = null;
        }
    }

    /**
     * @return the numeroCnpj
     */
    public String getNumeroCnpjStr() {
        if (numeroCnpj != null) {
            return numeroCnpj.toString();
        }
        return null;
    }

    /**
     * @return the numeroSegundoTelefone
     */
    public String getNumeroSegundoTelefoneStr() {
        if (numeroSegundoTelefone != null) {
            return numeroSegundoTelefone.toString();
        } else {
            return "";
        }
    }

    /**
     * @param numeroSegundoTelefone
     *            the numeroSegundoTelefone to set
     */
    public void setNumeroSegundoTelefoneStr(String numeroSegundoTelefone) {
        if (numeroSegundoTelefone != null && !numeroSegundoTelefone.equals("")) {
            try {
                this.numeroSegundoTelefone = Long.parseLong(numeroSegundoTelefone.replaceAll("[()]", ""));
            } catch (NumberFormatException e) {
                this.numeroSegundoTelefone = null;
            }
        } else {
            this.numeroSegundoTelefone = null;
        }
    }

    /**
     * @return the enderecoEmpresaMatriz
     */
    public EnderecoEmpresaMatriz getEnderecoEmpresaMatriz() {
        return enderecoEmpresaMatriz;
    }

    /**
     * @param enderecoEmpresaMatriz
     *            the enderecoEmpresaMatriz to set
     */
    public void setEnderecoEmpresaMatriz(EnderecoEmpresaMatriz enderecoEmpresaMatriz) {
        this.enderecoEmpresaMatriz = enderecoEmpresaMatriz;
    }

    /**
     * @return the enderecoEmpresaFilial
     */
    public EnderecoEmpresaFilial getEnderecoEmpresaFilial() {
//        if (enderecoEmpresaFilialNoBanco == null && enderecoEmpresaFilial != null && enderecoEmpresaFilial.getId() != null) {
        if (enderecoEmpresaFilialNoBanco == null && enderecoEmpresaFilial != null) {
            this.enderecoEmpresaFilialNoBanco = this.enderecoEmpresaFilial;
        }
        return enderecoEmpresaFilial;
    }

    /**
     * @param enderecoEmpresaFilial
     *            the enderecoEmpresaFilial to set
     */
    public void setEnderecoEmpresaFilial(EnderecoEmpresaFilial enderecoEmpresaFilial) {

//        if (enderecoEmpresaFilialNoBanco == null && enderecoEmpresaFilial != null && enderecoEmpresaFilial.getId() != null) {
        if (enderecoEmpresaFilialNoBanco == null && enderecoEmpresaFilial != null ) {
            this.enderecoEmpresaFilialNoBanco = enderecoEmpresaFilial;
        }
        this.enderecoEmpresaFilial = enderecoEmpresaFilial;
    }

//    @Override
//    public Long getId() {
//        return this.id;
//    }

    /**
     * @return the codigo
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the numeroCnpj
     */
    public Long getNumeroCnpj() {
        return numeroCnpj;
    }

    /**
     * @param numeroCnpj
     *            the numeroCnpj to set
     */
    public void setNumeroCnpj(Long numeroCnpj) {
        this.numeroCnpj = numeroCnpj;
    }

    /**
     * @return the razaoSocial
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * @param razaoSocial
     *            the razaoSocial to set
     */
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    /**
     * @param numeroSegundoTelefone
     *            the numeroSegundoTelefone to set
     */
    public void setNumeroSegundoTelefone(Long numeroSegundoTelefone) {
        this.numeroSegundoTelefone = numeroSegundoTelefone;
    }

    /**
     * @return the ramalPrimeiroTelefone
     */
    public Integer getRamalSegundoTelefone() {
        return ramalSegundoTelefone;
    }

    /**
     * @param ramalSegundoTelefone
     *            the ramalPrimeiroTelefone to set
     */
    public void setRamalSegundoTelefone(Integer ramalSegundoTelefone) {
        this.ramalSegundoTelefone = ramalSegundoTelefone;
    }

    /**
     * @return the numeroSegundoTelefone
     */
    public Long getNumeroSegundoTelefone() {
        return numeroSegundoTelefone;
    }

    /**
     * @return the nomeResponsavel
     */
    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    /**
     * @param nomeResponsavel
     *            the nomeResponsavel to set
     */
    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    /**
     * @return the cpfResponsavel
     */
    public Long getCpfResponsavel() {
        return cpfResponsavel;
    }

    /**
     * @param cpfResponsavel
     *            the cpfResponsavel to set
     */
    public void setCpfResponsavel(Long cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    /**
     * @param id
     *            the id to set
     */
//    public void setId(Long id) {
//        this.id = id;
//    }

    /**
     * Obter Cliente de Cobrança Terceirizada.
     *
     * @return the clienteCobrancaTerceirizada
     */
    public ClienteCobrancaTerceirizada getClienteCobrancaTerceirizada() {
        return clienteCobrancaTerceirizada;
    }

    /**
     * Atribuir Cliente de Cobrança Terceirizada.
     *
     * @param clienteCobrancaTerceirizada
     *            the clienteCobrancaTerceirizada to set
     */
    public void setClienteCobrancaTerceirizada(ClienteCobrancaTerceirizada clienteCobrancaTerceirizada) {
        this.clienteCobrancaTerceirizada = clienteCobrancaTerceirizada;
    }

    /**
     * {@inheritDoc}
     */
    public Long getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(Long contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getCodigoOperacao() {
        return codigoOperacao;
    }

    public void setCodigoOperacao(String codigoOperacao) {
        this.codigoOperacao = codigoOperacao;
    }

    /**
     * Metodo get que ordena todos os contatos pelo nome.
     *
     * @return
     */
    public List<ContatoEmpresa> getContatos() {
        if (contatos != null) {
            Collections.sort(contatos, new ContatoEmpresaComparator());
        }
        return contatos;
    }

    public void setContatos(List<ContatoEmpresa> contatosEmpresa) {
        this.contatos = contatosEmpresa;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public TipoContaEnum getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoContaEnum tipoConta) {
        this.tipoConta = tipoConta;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
        result = prime * result + ((clienteCobrancaTerceirizada == null) ? 0 : clienteCobrancaTerceirizada.hashCode());
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((codigoBanco == null) ? 0 : codigoBanco.hashCode());
        result = prime * result + ((codigoOperacao == null) ? 0 : codigoOperacao.hashCode());
        result = prime * result + ((contaCorrente == null) ? 0 : contaCorrente.hashCode());
        result = prime * result + ((cpfResponsavel == null) ? 0 : cpfResponsavel.hashCode());
        result = prime * result + ((emailInstitucional == null) ? 0 : emailInstitucional.hashCode());
        result = prime * result + ((emailNegocial == null) ? 0 : emailNegocial.hashCode());
        result = prime * result + ((enderecoEmpresaFilial == null) ? 0 : enderecoEmpresaFilial.hashCode());
        result = prime * result + ((enderecoEmpresaFilialNoBanco == null) ? 0 : enderecoEmpresaFilialNoBanco.hashCode());
        result = prime * result + ((enderecoEmpresaMatriz == null) ? 0 : enderecoEmpresaMatriz.hashCode());
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nomeResponsavel == null) ? 0 : nomeResponsavel.hashCode());
        result = prime * result + ((numero0800 == null) ? 0 : numero0800.hashCode());
        result = prime * result + ((numeroCnpj == null) ? 0 : numeroCnpj.hashCode());
        result = prime * result + ((numeroSegundoTelefone == null) ? 0 : numeroSegundoTelefone.hashCode());
        result = prime * result + ((ramalSegundoTelefone == null) ? 0 : ramalSegundoTelefone.hashCode());
        result = prime * result + ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
        result = prime * result + ((site == null) ? 0 : site.hashCode());
        result = prime * result + ((tipoConta == null) ? 0 : tipoConta.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof EmpresaTerceirizada)) {
            return false;
        }
        EmpresaTerceirizada other = (EmpresaTerceirizada) obj;
        if (agencia == null) {
            if (other.agencia != null) {
                return false;
            }
        } else if (!agencia.equals(other.agencia)) {
            return false;
        }
        if (clienteCobrancaTerceirizada == null) {
            if (other.clienteCobrancaTerceirizada != null) {
                return false;
            }
        } else if (!clienteCobrancaTerceirizada.equals(other.clienteCobrancaTerceirizada)) {
            return false;
        }
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        if (codigoBanco == null) {
            if (other.codigoBanco != null) {
                return false;
            }
        } else if (!codigoBanco.equals(other.codigoBanco)) {
            return false;
        }
        if (codigoOperacao == null) {
            if (other.codigoOperacao != null) {
                return false;
            }
        } else if (!codigoOperacao.equals(other.codigoOperacao)) {
            return false;
        }
        if (contaCorrente == null) {
            if (other.contaCorrente != null) {
                return false;
            }
        } else if (!contaCorrente.equals(other.contaCorrente)) {
            return false;
        }
        if (cpfResponsavel == null) {
            if (other.cpfResponsavel != null) {
                return false;
            }
        } else if (!cpfResponsavel.equals(other.cpfResponsavel)) {
            return false;
        }
        if (emailInstitucional == null) {
            if (other.emailInstitucional != null) {
                return false;
            }
        } else if (!emailInstitucional.equals(other.emailInstitucional)) {
            return false;
        }
        if (emailNegocial == null) {
            if (other.emailNegocial != null) {
                return false;
            }
        } else if (!emailNegocial.equals(other.emailNegocial)) {
            return false;
        }
        if (enderecoEmpresaFilial == null) {
            if (other.enderecoEmpresaFilial != null) {
                return false;
            }
        } else if (!enderecoEmpresaFilial.equals(other.enderecoEmpresaFilial)) {
            return false;
        }
        if (enderecoEmpresaFilialNoBanco == null) {
            if (other.enderecoEmpresaFilialNoBanco != null) {
                return false;
            }
        } else if (!enderecoEmpresaFilialNoBanco.equals(other.enderecoEmpresaFilialNoBanco)) {
            return false;
        }
        if (enderecoEmpresaMatriz == null) {
            if (other.enderecoEmpresaMatriz != null) {
                return false;
            }
        } else if (!enderecoEmpresaMatriz.equals(other.enderecoEmpresaMatriz)) {
            return false;
        }
//        if (id == null) {
//            if (other.id != null) {
//                return false;
//            }
//        } else if (!id.equals(other.id)) {
//            return false;
//        }
        if (nomeResponsavel == null) {
            if (other.nomeResponsavel != null) {
                return false;
            }
        } else if (!nomeResponsavel.equals(other.nomeResponsavel)) {
            return false;
        }
        if (numero0800 == null) {
            if (other.numero0800 != null) {
                return false;
            }
        } else if (!numero0800.equals(other.numero0800)) {
            return false;
        }
        if (numeroCnpj == null) {
            if (other.numeroCnpj != null) {
                return false;
            }
        } else if (!numeroCnpj.equals(other.numeroCnpj)) {
            return false;
        }
        if (numeroSegundoTelefone == null) {
            if (other.numeroSegundoTelefone != null) {
                return false;
            }
        } else if (!numeroSegundoTelefone.equals(other.numeroSegundoTelefone)) {
            return false;
        }
        if (ramalSegundoTelefone == null) {
            if (other.ramalSegundoTelefone != null) {
                return false;
            }
        } else if (!ramalSegundoTelefone.equals(other.ramalSegundoTelefone)) {
            return false;
        }
        if (razaoSocial == null) {
            if (other.razaoSocial != null) {
                return false;
            }
        } else if (!razaoSocial.equals(other.razaoSocial)) {
            return false;
        }
        if (site == null) {
            if (other.site != null) {
                return false;
            }
        } else if (!site.equals(other.site)) {
            return false;
        }
        if (tipoConta != other.tipoConta) {
            return false;
        }
        return true;
    }
}
