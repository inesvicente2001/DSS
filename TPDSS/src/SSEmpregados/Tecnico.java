package SSEmpregados;

import java.util.Map;
import java.util.Set;

public class Tecnico extends Empregado{

    public float mediaDesvio;
    public float duracaoMedia;
    public Map<String, Reparacao> reparacoes;
    public Set<Passo> passosRealizados;
}
