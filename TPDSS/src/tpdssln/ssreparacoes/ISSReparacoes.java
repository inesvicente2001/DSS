package tpdssln.ssreparacoes;

import java.time.LocalDateTime;
import java.util.Map;

public interface ISSReparacoes {

    public void adicionarPedidoOrcamentoNormal(String nomeEquipamento, int urgencia, String descricao,
                                               String local, LocalDateTime prazo, String nomeCliente, String nif,
                                               String telemovel, String email);

    public void adicionarPedidoOrcamentoExpresso(String nomeEquipamento, int urgencia, String descricao,
                                                 String local, LocalDateTime prazo, float precoFixo, String nomeCliente,
                                                 String nif, String telemovel, String email);

    public void registarPlanoTrabalho();

    public void confirmarReparacao(String idEquipamento);

    public void registarConclusao(String idEquipamento);

    public void registarEntrega(String id);

    public void equipamentoAbandonado(String id);

    public void iniciarPasso(String id);

    public void concluirPasso(String id);
}
