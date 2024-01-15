import java.io.Serializable;
import java.util.LinkedHashSet;

public class Proposta implements Serializable{

    private LinkedHashSet<Integer> conjuntoProper = new LinkedHashSet<>();
    private int indiceProposta;

    public Proposta(LinkedHashSet<Integer> conjunto){
        this.conjuntoProper = conjunto;
    }


    public LinkedHashSet<Integer> getConjuntoProper() {
        return conjuntoProper;
    }

    public int getIndiceProposta() {
        return indiceProposta;
    }

    public void setConjuntoProper(LinkedHashSet<Integer> conjuntoProper) {
        this.conjuntoProper = conjuntoProper;
    }

    public void setIndiceProposta(int indiceProposta) {
        this.indiceProposta = indiceProposta;
    }
}
