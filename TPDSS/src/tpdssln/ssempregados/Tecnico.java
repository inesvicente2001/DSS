package tpdssln.ssempregados;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import tpdssln.ssreparacoes.Passo;
import tpdssln.ssreparacoes.Reparacao;
import tpdssln.ssreparacoes.ReparacaoExpresso;
import tpdssln.ssreparacoes.ReparacaoNormal;

public class Tecnico extends Empregado{

    public Duration mediaDesvio;
    public Duration duracaoMedia;
    public Set<Reparacao> reparacoes;
    public Set<Passo> passosRealizados;

    public Tecnico(String id, String nome, String password) {
        super(id,nome,password);
        mediaDesvio = Duration.ofHours(0);
        duracaoMedia = Duration.ofHours(0);
        reparacoes = new HashSet<>();
        passosRealizados = new HashSet<>();
    }

    public void atualizarMediaDesvio(Duration desvio){
        int size = reparacoes.size();
        mediaDesvio = Duration.ofSeconds((mediaDesvio.getSeconds() * size + desvio.getSeconds()) / (size + 1));
    }

    public void atualizarDuracaoMedia(Duration duracao){
        int size = reparacoes.size();
        duracaoMedia = Duration.ofSeconds((duracaoMedia.getSeconds() * size + duracao.getSeconds()) / (size + 1));
    }

    public void addReparacao(Reparacao reparacao) {
        reparacoes.add(reparacao);
    }

    public void addPasso(Passo passo) {
        passosRealizados.add(passo);
    }

    public int nReparacoesNormais(){

        int n = 0;

        for(Reparacao reparacao : reparacoes)
            if (reparacao instanceof ReparacaoNormal)
                n++;

        return n;
    }

    public int nReparacoesExpresso(){
        int n = 0;

        for(Reparacao reparacao : reparacoes)
            if (reparacao instanceof ReparacaoExpresso)
                n++;

        return n;
    }
}
