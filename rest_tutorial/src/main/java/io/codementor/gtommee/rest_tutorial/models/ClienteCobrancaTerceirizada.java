package io.codementor.gtommee.rest_tutorial.models;

public class ClienteCobrancaTerceirizada extends EntidadeAuditavelCobrancaTerceirizada {

    private String nome;
    private String descricao;
    private String codigo;
    private Integer administrador;
    private Integer credor;

//    public void setId(Long id) {
//        this.id = id;
//    }

    public ClienteCobrancaTerceirizada (){
        nome = null;
        descricao = null;
        codigo = null;
        administrador = null;
        credor = null;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Integer administrador) {
        this.administrador = administrador;
    }

    public Integer getCredor() {
        return credor;
    }

    public void setCredor(Integer credor) {
        this.credor = credor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((administrador == null) ? 0 : administrador.hashCode());
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((credor == null) ? 0 : credor.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
        ClienteCobrancaTerceirizada other = (ClienteCobrancaTerceirizada) obj;
        if (administrador == null) {
            if (other.administrador != null) {
                return false;
            }
        } else if (!administrador.equals(other.administrador)) {
            return false;
        }
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        if (credor == null) {
            if (other.credor != null) {
                return false;
            }
        } else if (!credor.equals(other.credor)) {
            return false;
        }
        if (descricao == null) {
            if (other.descricao != null) {
                return false;
            }
        } else if (!descricao.equals(other.descricao)) {
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
        return true;
    }
}


