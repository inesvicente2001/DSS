package tpdssln.ssreparacoes;

import java.time.Duration;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ReparacaoExpresso extends Reparacao implements Serializable {
    private float precoFixo;
    private Duration duracaoPrevista;

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

    public Duration getDuracaoPrevista() {
        return duracaoPrevista;
    }

    public void setDuracaoPrevista(Duration duracaoPrevista) {
        this.duracaoPrevista = duracaoPrevista;
    }
}
