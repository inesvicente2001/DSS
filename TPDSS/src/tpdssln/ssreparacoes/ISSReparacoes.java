package tpdssln.ssreparacoes;

import tpdssln.ssempregados.Funcionario;
import tpdssln.ssempregados.Tecnico;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public interface ISSReparacoes {

    public void adicionarPedidoOrcamentoNormal(String nomeEquipamento, int urgencia, String descricao,
                                               String local, LocalDateTime prazo, String nomeCliente, String nif,
                                               String telemovel, String email, Funcionario funcionario);

    public void adicionarPedidoOrcamentoExpresso(String nomeEquipamento, int urgencia, String descricao,
                                                 String local, LocalDateTime prazo, float precoFixo,
                                                 Duration duracaoPrevista, String nomeCliente, String nif,
                                                 String telemovel, String email, Funcionario funcionario);

    public void registarPlanoTrabalho(Map<Integer, Passo> planoTrabalho);

    public void confirmarReparacao(String idEquipamento);

    public void registarConclusao(String idEquipamento, Tecnico tecnico);

    public void registarEntrega(String id, Funcionario funcionario);

    public void registarEntregaDeEquipamentoRecusado(String id, Funcionario funcionario);

    public void equipamentoAbandonado(String id);

    public void iniciarPasso(String id);

    public void concluirPasso(String id, Tecnico tecnico);

    Reparacao registarReparacao(String nome, String descricao, LocalDateTime prazoMaximo);

    public void addPecaEstimada(Registo registo, Integer passo, String nomePeca, float custo, int quantidade);

    public void addPecaUsada(Registo registo, Integer passo, String nomePeca, float custo, int quantidade);

    public void addSubPasso(Registo registo, Integer passo, String nomePasso, Duration tempoPrevisto);

    LocalDateTime obterPrazoMaximo(Registo registo);
}
