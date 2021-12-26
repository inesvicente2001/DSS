package SSEmpregados;

public class Funcionario extends Empregado{

    public int nEntregas;
    public int nRececoes;

    public Funcionario(String id, String nome, String password) {
        super(id,nome,password);
        nEntregas = 0;
        nRececoes = 0;
    }

    public void addEntrega() {
        nEntregas++;
    }

    public void addRececao() {
        nRececoes++;
    }
}
