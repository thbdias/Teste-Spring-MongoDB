package unisys.thbd.sigma.models;

import java.io.Serializable;
import java.util.Comparator;

public class ContatoEmpresaComparator implements Comparator<ContatoEmpresa>, Serializable {

//    private static final long serialVersionUID = 1L;

    @Override
    public int compare(ContatoEmpresa r1, ContatoEmpresa r2) {

        if (r1 != null && r2 != null && r1.getNome() != null && r2.getNome() != null) {
            return r1.getNome().compareTo(r2.getNome());
        }
        return 0;
    }
}
