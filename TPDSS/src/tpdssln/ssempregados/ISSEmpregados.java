package tpdssln.ssempregados;

import tpdssln.ssempregados.excecoes.CredenciaisErradasException;
import tpdssln.ssempregados.excecoes.EmpregadoNaoExisteException;
import tpdssln.ssreparacoes.Registo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ISSEmpregados {
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
    public int numEntregasEmpregado(String id);
    public int numRececoesEmpregado(String id);
    public int nReparacoesNormaisTecnico(String id);
    public int nReparacoesExpressoTecnico(String id);
    public List<String> toLstInfosPlanosTrabalho(String idTecnico);
    public Map<String,List<String>> todosPlanosTrabalho();

    public void aumentarRececoesEmpregado(String id);

    public void aumentarEntregasEmpregado(String id);

}
