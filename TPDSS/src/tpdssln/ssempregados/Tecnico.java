package tpdssln.ssempregados;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import tpdssln.ssreparacoes.Passo;
import tpdssln.ssreparacoes.Reparacao;

public class Tecnico extends Empregado{

    public float mediaDesvio;
    public float duracaoMedia;
    public Set<Reparacao> reparacoes;
    public Set<Passo> passosRealizados;

    public Tecnico(String id, String nome, String password) {
        super(id,nome,password);
        mediaDesvio = 0;
        duracaoMedia = 0;
        reparacoes = new HashSet<>();
        passosRealizados = new HashSet<>();
    }

    public void atualizarMediaDesvio(float desvio){
        int size = reparacoes.size();
        mediaDesvio = (mediaDesvio * size + desvio) / (size + 1);
    }

    public void atualizarDuracaoMedia(float duracao){
        int size = reparacoes.size();
        duracaoMedia = (duracaoMedia * size + duracao) / (size + 1);
    }

    public void addReparacao(Reparacao reparacao) {
        reparacoes.add(reparacao);
    }

    public void addPasso(Passo passo) {
        passosRealizados.add(passo);
    }
}
