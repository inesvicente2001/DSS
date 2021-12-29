package tpdssln;

import tpdssln.ssempregados.Empregado;
import tpdssln.ssempregados.Funcionario;
import tpdssln.ssempregados.Tecnico;
import tpdssln.ssreparacoes.Passo;
import tpdssln.ssreparacoes.Reparacao;

import java.time.LocalDateTime;
import java.util.Map;

public interface ITPDSSLN {
    // Métodos do subsistema de empregados
    public Boolean autenticar(String id, String password);
    public Map<String, Tecnico> acederTecnicos();
    public Map<String, Funcionario> acederFuncionarios();
    public String adicionarTecnico(String nome, String password);
    public String adicionarFuncionario(String nome, String password);
    public String adicionarGestor(String nome, String password);
    public Empregado verEmpregado(String id);
    public void editarNome(String id, String nome);
    public void editarPassword(String id, String password);
    public void removerUtilizador(String id);

    // Métodos do subsistema de reparações
    Reparacao registarReparacao(String nome, String descricao, LocalDateTime prazoMaximo);
    public void registarEntrega(String id);
    public void repararProduto(String id);

    void registarEntrega(String id, Funcionario funcionario);

    public void registarPlanoTrabalho(Map<Integer, Passo> planoTrabalho);
    public void registarConclusao(String idEquipamento);

    void registarConclusao(String idEquipamento, Tecnico tecnico);

    LocalDateTime obterPrazoMaximo();


    public int numRececoesEmpregado(String id);

    public int numEntregasEmpregado(String id);
}
