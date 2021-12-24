package SSEmpregados;

import java.util.Map;
import java.util.Set;
import SSReparacoes.Passo;
import SSReparacoes.Reparacao;

public class Tecnico extends Empregado{

    public float mediaDesvio;
    public float duracaoMedia;
    public Map<String, Reparacao> reparacoes;
    public Set<Passo> passosRealizados;
}
