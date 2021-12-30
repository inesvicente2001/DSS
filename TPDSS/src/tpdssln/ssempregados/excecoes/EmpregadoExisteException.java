package tpdssln.ssempregados.excecoes;

public class EmpregadoExisteException extends Exception {
    public EmpregadoExisteException() {
    }

    public EmpregadoExisteException(String message) {
        super(message);
    }
}
