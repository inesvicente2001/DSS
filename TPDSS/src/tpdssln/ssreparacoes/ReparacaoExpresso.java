package tpdssln.ssreparacoes;

import java.time.LocalDateTime;

public class ReparacaoExpresso extends Reparacao{
    public float precoFixo;

    public ReparacaoExpresso(String equipamento, String descricao, LocalDateTime prazoMaximo, float precoFixo) {
        super(equipamento, descricao, prazoMaximo);
        this.precoFixo = precoFixo;
    }

    public float getPrecoFixo() {
        return precoFixo;
    }

    public void setPrecoFixo(float precoFixo) {
        this.precoFixo = precoFixo;
    }


}
