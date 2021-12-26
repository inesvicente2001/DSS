package SSReparacoes;

import java.time.LocalDateTime;
import java.util.Map;

public interface ISSReparacoes {

    Reparacao registarReparacao(String nome, String descricao, LocalDateTime prazoMaximo);

    public void registarEntrega(String id);

    public void repararProduto(String id);

    public void registarPlanoTrabalho(Map<Integer,Passo> planoTrabalho);

    public void registarConclusao();

    LocalDateTime obterPrazoMaximo();
}
