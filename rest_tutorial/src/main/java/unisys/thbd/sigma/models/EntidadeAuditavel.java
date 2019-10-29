package unisys.thbd.sigma.models;

import java.util.Date;
import org.springframework.data.annotation.Version;

public abstract class EntidadeAuditavel extends Entidade implements Cloneable {

    private Date dataInclusao;
    private Date dataAlteracao;
    private Date dataExclusao;
    private String usuarioResponsavel;
    private EntidadeAuditavel versaoParaAuditoria;
    @Version
    private Long versao;

    public Boolean isAuditoriaComHistorico() {
        return Boolean.FALSE;
    }

    public final void prepararAuditoriaAposCarregarDoBanco() {
        if (isAuditoriaComHistorico()) {
            prepararParaCriarVersaoParaAuditoria();
            criarVersaoParaAuditoria();
            executarAposCriarVersaoParaAuditoria();
        }
    }

    public void prepararParaRegistroAuditoria(AcaoRegistroAuditoriaEnum acao) {
    }

    public EntidadeAuditavel getVersaoParaAuditoria() {
        return versaoParaAuditoria;
    }

    private void criarVersaoParaAuditoria() {
        try {
            this.versaoParaAuditoria = (EntidadeAuditavel) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    protected void prepararParaCriarVersaoParaAuditoria() {
    }


    protected void executarAposCriarVersaoParaAuditoria() {
    }

    public void preencheUsuarioAlteracao(String usuario) {
        setUsuarioResponsavel(usuario);
    }

    public void preencheDataHoraAlteracao(Date data) {
//        if (getId() == null) {
//            this.setDataInclusao(new Date(data.getTime()));
//        }
        this.setDataAlteracao(new Date(data.getTime()));
    }

    public void preencheDataHoraExclusao(Date data) {
        this.setDataExclusao(new Date(data.getTime()));
    }

    public String getDataAlteracaoFormatadaHoraMinSeg() {
        if (this.dataAlteracao != null) {
            return DateUtil.formataDataHoraMinuto(new Date(this.dataAlteracao.getTime()));
        } else {
            return null;
        }
    }

    public Date getDataInclusao() {
        if (dataInclusao == null) {
            return null;
        }
        return new Date(dataInclusao.getTime());
    }

    public void setDataInclusao(Date dataInclusao) {
        if (dataInclusao == null) {
            this.dataInclusao = null;
        } else {
            this.dataInclusao = new Date(dataInclusao.getTime());
        }
    }

    public Date getDataAlteracao() {
        if (dataAlteracao == null) {
            return null;
        }
        return new Date(dataAlteracao.getTime());
    }

    public void setDataAlteracao(Date dataAlteracao) {
        if (dataAlteracao == null) {
            this.dataAlteracao = null;
        } else {
            this.dataAlteracao = new Date(dataAlteracao.getTime());
        }
    }

    public Date getDataExclusao() {
        if (this.dataExclusao == null) {
            return null;
        }
        return new Date(this.dataExclusao.getTime());
    }

    public void setDataExclusao(Date dataExclusao) {
        if (dataExclusao == null) {
            this.dataExclusao = null;
        } else {
            this.dataExclusao = new Date(dataExclusao.getTime());
        }
    }

    public String getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(String usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public Long getVersao() {
        return versao;
    }

    public void setVersao(Long versao) {
        this.versao = versao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
        result = prime * result + ((dataExclusao == null) ? 0 : dataExclusao.hashCode());
        result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
        result = prime * result + ((usuarioResponsavel == null) ? 0 : usuarioResponsavel.hashCode());
        result = prime * result + ((versao == null) ? 0 : versao.hashCode());
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
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        EntidadeAuditavel other = (EntidadeAuditavel) obj;
        if (dataAlteracao == null) {
            if (other.dataAlteracao != null) {
                return false;
            }
        } else if (!dataAlteracao.equals(other.dataAlteracao)) {
            return false;
        }
        if (dataExclusao == null) {
            if (other.dataExclusao != null) {
                return false;
            }
        } else if (!dataExclusao.equals(other.dataExclusao)) {
            return false;
        }
        if (dataInclusao == null) {
            if (other.dataInclusao != null) {
                return false;
            }
        } else if (!dataInclusao.equals(other.dataInclusao)) {
            return false;
        }
        if (usuarioResponsavel == null) {
            if (other.usuarioResponsavel != null) {
                return false;
            }
        } else if (!usuarioResponsavel.equals(other.usuarioResponsavel)) {
            return false;
        }
        if (versao == null) {
            if (other.versao != null) {
                return false;
            }
        } else if (!versao.equals(other.versao)) {
            return false;
        }
        return true;
    }

}
