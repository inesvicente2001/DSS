package SSReparacoes;

import java.time.LocalDateTime;

public interface ISSReparacoes {

    public void registarReparacao(String nif, String nome, String descricao);
    public void registarEntrega(String id);
    public void repararProduto(String id);
    public void registarPlanoTrabalho(String id);
    public void registarConclusao(String id);
    public LocalDateTime obterPrazoMaximo(String id);

}
