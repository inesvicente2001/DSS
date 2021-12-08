package SSReparacoes;

import java.time.LocalDateTime;
import java.util.Set;

public class Passo {

    public Boolean concluido;
    public LocalDateTime dataInicio;
    public LocalDateTime dataFim;
    public LocalDateTime tempoPrevisto;
    public Set<Peca> pecasEstimadas;
    public Set<Peca> pecasUsadas;
}
