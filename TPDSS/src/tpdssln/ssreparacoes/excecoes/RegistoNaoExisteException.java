package tpdssln.ssreparacoes.excecoes;

public class RegistoNaoExisteException extends Exception {
    public RegistoNaoExisteException() {
    }

    public RegistoNaoExisteException(String message) {
        super(message);
    }
}
