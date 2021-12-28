package tpdssln.ssreparacoes;

import java.time.LocalDateTime;
import java.util.Map;

public class Reparacao {
    public LocalDateTime prazoMaximo;

    public Reparacao(LocalDateTime prazoMaximo) {
        this.prazoMaximo = prazoMaximo;
    }


    public LocalDateTime getPrazoMaximo() {
        return prazoMaximo;
    }

    public void setPrazoMaximo(LocalDateTime prazoMaximo) {
        this.prazoMaximo = prazoMaximo;
    }


}
