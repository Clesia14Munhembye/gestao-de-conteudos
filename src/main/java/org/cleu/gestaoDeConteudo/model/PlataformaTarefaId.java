package org.cleu.gestaoDeConteudo.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PlataformaTarefaId implements Serializable {

    private Integer plataformaId;
    private Integer tarefaId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((plataformaId == null) ? 0 : plataformaId.hashCode());
        result = prime * result + ((tarefaId == null) ? 0 : tarefaId.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlataformaTarefaId other = (PlataformaTarefaId) obj;
        if (plataformaId == null) {
            if (other.plataformaId != null)
                return false;
        } else if (!plataformaId.equals(other.plataformaId))
            return false;
        if (tarefaId == null) {
            if (other.tarefaId != null)
                return false;
        } else if (!tarefaId.equals(other.tarefaId))
            return false;
        return true;
    }
    
}
