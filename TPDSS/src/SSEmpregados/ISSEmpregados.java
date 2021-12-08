package SSEmpregados;

import java.util.Map;

public interface ISSEmpregados {
    public Boolean autenticar(String id, String password);
    public Map<String, Tecnico> acederTecnicos();
    public Map<String, Funcionario> acederFuncionario();
    public String adicionarUtilizador(String id, String nome);
    public void editarUtilizador(String id);
    public void removerUtilizador(String id);

}
