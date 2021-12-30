package tpdssln.ssempregados.excecoes;

public class EmpregadoNaoExisteException extends Exception {
    public EmpregadoNaoExisteException() {
    }

    public EmpregadoNaoExisteException(String message) {
        super(message);
    }
}
