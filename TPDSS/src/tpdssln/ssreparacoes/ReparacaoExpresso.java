package tpdssln.ssreparacoes;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReparacaoExpresso extends Reparacao implements Serializable {
    public float precoFixo;

    public ReparacaoExpresso(LocalDateTime prazoMaximo, float precoFixo) {
        super(prazoMaximo);
        this.precoFixo = precoFixo;
    }

    public float getPrecoFixo() {
        return precoFixo;
    }

    public void setPrecoFixo(float precoFixo) {
        this.precoFixo = precoFixo;
    }


}
