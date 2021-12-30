package tpdssln.ssreparacoes;

import java.time.LocalDateTime;
import java.util.Map;

public abstract class Reparacao {
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
