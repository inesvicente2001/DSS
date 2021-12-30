package tpdssln.ssempregados;

import java.time.Duration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tpdssln.ssreparacoes.Reparacao;
import tpdssln.ssreparacoes.ReparacaoExpresso;
import tpdssln.ssreparacoes.ReparacaoNormal;

public class Tecnico extends Empregado implements Serializable {

    private Duration mediaDesvio;
    private Duration duracaoMedia;
    private Map<String, Reparacao> reparacoes;

    public Tecnico(String id, String nome, String password) {
        super(id,nome,password);
        mediaDesvio = Duration.ofHours(0);
        duracaoMedia = Duration.ofHours(0);
        reparacoes = new HashMap<>();
    }

    public Duration getMediaDesvio() {
        return mediaDesvio;
    }

    public void setMediaDesvio(Duration mediaDesvio) {
        this.mediaDesvio = mediaDesvio;
    }

    public Duration getDuracaoMedia() {
        return duracaoMedia;
    }

    public void setDuracaoMedia(Duration duracaoMedia) {
        this.duracaoMedia = duracaoMedia;
    }

    public Map<String, Reparacao> getReparacoes() {
        return reparacoes;
    }

    public void setReparacoes(Map<String,Reparacao> reparacoes) {
        this.reparacoes = reparacoes;
    }

    public void atualizarMediaDesvio(Duration desvio){
        int size = reparacoes.size();
        mediaDesvio = Duration.ofSeconds((mediaDesvio.getSeconds() * size + desvio.getSeconds()) / (size + 1));
    }

    public void atualizarDuracaoMedia(Duration duracao){
        int size = reparacoes.size();
        duracaoMedia = Duration.ofSeconds((duracaoMedia.getSeconds() * size + duracao.getSeconds()) / (size + 1));
    }

    public void addReparacao(String id, Reparacao reparacao) {
        reparacoes.put(id, reparacao);
    }

    public int nReparacoesNormais(){

        int n = 0;

        for(Reparacao reparacao : reparacoes.values())
            if (reparacao instanceof ReparacaoNormal)
                n++;

        return n;
    }

    public int nReparacoesExpresso(){
        int n = 0;

        for(Reparacao reparacao : reparacoes.values())
            if (reparacao instanceof ReparacaoExpresso)
                n++;

        return n;
    }


    public List<String> toLstInfosPlanosTrabalho(String idTecnico){

        List<String> planosTrabalhoInfos = new ArrayList<>();

        for (Map.Entry<String,Reparacao> entry : this.getReparacoes().entrySet()){
            StringBuilder sb = new StringBuilder();

            if(entry.getValue() instanceof ReparacaoNormal){

                sb.append(entry.getKey()); //IdReparacao
                sb.append("%");
                sb.append(((ReparacaoNormal) entry.getValue()).getCustoFinal());
                sb.append("%");
                sb.append(((ReparacaoNormal) entry.getValue()).toHTMLPlanoTrabalho());

            }

            if(entry.getValue() instanceof ReparacaoExpresso){

                sb.append(entry.getKey()); //IdReparacao
                sb.append("%");
                sb.append(((ReparacaoExpresso) entry.getValue()).getPrecoFixo());
                sb.append("%");
                sb.append(" "); //Não tem plano de trabalhos: vai vazio



            }

            planosTrabalhoInfos.add(sb.toString());



        }









        return planosTrabalhoInfos;
    }


}
