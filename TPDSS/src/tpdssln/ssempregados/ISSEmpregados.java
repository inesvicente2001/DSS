package tpdssln.ssempregados;

import java.util.Map;

public interface ISSEmpregados {
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
    public int numEntregasEmpregado(String id);
    public int numRececoesEmpregado(String id);


}
