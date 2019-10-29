package unisys.thbd.sigma.models;

public class ContatoEmpresa extends EntidadeCobrancaTerceirizada {

//    private static final long serialVersionUID = 1L;
//    private Long id;

    private EmpresaTerceirizada empresaTerceirizada;

    private String nome;

    private String descricao;

    private Long comercial;

    private Long celular;

    private String email;

    public String getCelularFormatado() {
        return formatadorTelefones(this.celular);
    }

    /**
     * Metodo que formata o comercial.
     *
     * @return
     */
    public String getComercialFormatado() {
        return formatadorTelefones(this.comercial);
    }

    private String formatadorTelefones(Long telefone) {
        if (telefone != null && telefone.toString() != null) {
            return Util.formataTelefone(Util.removerZerosEsquerda(telefone.toString()));
        }
        return null;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

//    @Override
//    public Long getId() {
//        return id;
//    }

    public EmpresaTerceirizada getEmpresaTerceirizada() {
        return empresaTerceirizada;
    }

    public void setEmpresaTerceirizada(EmpresaTerceirizada empresaTerceirizada) {
        this.empresaTerceirizada = empresaTerceirizada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getComercial() {
        return comercial;
    }

    public void setComercial(Long telefoneComercial) {
        this.comercial = telefoneComercial;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long telefoneCelular) {
        this.celular = telefoneCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((celular == null) ? 0 : celular.hashCode());
        result = prime * result + ((comercial == null) ? 0 : comercial.hashCode());
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        ContatoEmpresa other = (ContatoEmpresa) obj;
        if (descricao == null) {
            if (other.descricao != null) {
                return false;
            }
        } else if (!descricao.equals(other.descricao)) {
            return false;
        }
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
//        if (id == null) {
//            if (other.id != null) {
//                return false;
//            }
//        } else if (!id.equals(other.id)) {
//            return false;
//        }
        if (nome == null) {
            if (other.nome != null) {
                return false;
            }
        } else if (!nome.equals(other.nome)) {
            return false;
        }
        if (celular == null) {
            if (other.celular != null) {
                return false;
            }
        } else if (!celular.equals(other.celular)) {
            return false;
        }
        if (comercial == null) {
            if (other.comercial != null) {
                return false;
            }
        } else if (!comercial.equals(other.comercial)) {
            return false;
        }
        return true;
    }
}
