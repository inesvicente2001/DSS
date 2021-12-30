package tpdssln.ssreparacoes;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Reparacao implements Serializable {
    protected LocalDateTime prazoMaximo;

    public Reparacao() {
        this.prazoMaximo = null;
    }

    public LocalDateTime getPrazoMaximo() {
        return prazoMaximo;
    }

    public void setPrazoMaximo(LocalDateTime prazoMaximo) {
        this.prazoMaximo = prazoMaximo;
    }

}
