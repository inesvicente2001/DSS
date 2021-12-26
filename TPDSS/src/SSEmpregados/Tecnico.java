package SSEmpregados;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import SSReparacoes.Passo;
import SSReparacoes.Reparacao;

public class Tecnico extends Empregado{

    public float mediaDesvio;
    public float duracaoMedia;
    public Map<String, Reparacao> reparacoes;
    public Set<Passo> passosRealizados;

    public Tecnico(String id, String nome, String password) {
        super(id,nome,password);
        mediaDesvio = 0;
        duracaoMedia = 0;
        reparacoes = new HashMap<>();
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
        reparacoes.put(reparacao.equipamento,reparacao);
    }

    public void addPasso(Passo passo) {
        passosRealizados.add(passo);
    }
}
