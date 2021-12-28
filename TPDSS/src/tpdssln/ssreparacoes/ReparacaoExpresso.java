package tpdssln.ssreparacoes;

import java.time.Duration;
import java.time.LocalDateTime;

public class ReparacaoExpresso extends Reparacao{
    public float precoFixo;
    public Duration duracaoPrevista;

    public ReparacaoExpresso(LocalDateTime prazoMaximo, float precoFixo, Duration duracaoPrevista) {
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
