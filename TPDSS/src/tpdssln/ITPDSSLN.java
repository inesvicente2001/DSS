package tpdssln;

import tpdssln.ssempregados.Empregado;
import tpdssln.ssempregados.Funcionario;
import tpdssln.ssempregados.Tecnico;
import tpdssln.ssempregados.excecoes.CredenciaisErradasException;
import tpdssln.ssempregados.excecoes.EmpregadoNaoExisteException;
import tpdssln.ssreparacoes.Passo;
import tpdssln.ssreparacoes.Registo;
import tpdssln.ssreparacoes.Reparacao;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ITPDSSLN {
    public void adicionarPedidoOrcamentoNormal(String nomeEquipamento, int urgencia, String descricao,
                                               String local, LocalDateTime prazo, String nomeCliente, String nif,
                                               String telemovel, String email, Funcionario funcionario);

    public void adicionarPedidoOrcamentoExpresso(String nomeEquipamento, int urgencia, String descricao,
                                                 String local, LocalDateTime prazo, float precoFixo,
                                                 Duration duracaoPrevista, String nomeCliente, String nif,
                                                 String telemovel, String email, Funcionario funcionario);
    // Métodos do subsistema de empregados
    public Class<? extends Empregado> autenticar(String id, String password) throws CredenciaisErradasException;
    public Map<String, Tecnico> acederTecnicos();
    public Map<String, Funcionario> acederFuncionarios();
    public String adicionarTecnico(String nome, String password);
    public String adicionarFuncionario(String nome, String password);
    public String adicionarGestor(String nome, String password);
    public Empregado verEmpregado(String id) throws EmpregadoNaoExisteException;

    boolean existeEmpregado(String id);

    public void editarNome(String id, String nome) throws EmpregadoNaoExisteException;
    public void editarPassword(String id, String password);
    public void removerUtilizador(String id);

    // Métodos do subsistema de reparações
    Reparacao registarReparacao(String nome, String descricao, LocalDateTime prazoMaximo);
    public void registarEntrega(String id);
    public void repararProduto(String id);

    void registarEntrega(String id, Funcionario funcionario);

    public void registarPlanoTrabalho(String id, Map<Integer, Passo> planoTrabalho);
    public void registarConclusao(String idEquipamento);

    void registarConclusao(String idEquipamento, Tecnico tecnico);

    public int numRececoesEmpregado(String id);

    public int numEntregasEmpregado(String id);

    public Registo maisUrgente();

    public String toHTMLDescricao(String id) throws NullPointerException;
    public List<String> toLstInfosPlanosTrabalho(String idTecnico);

    public Map<String,List<String>> todosPlanosTrabalho();
}
