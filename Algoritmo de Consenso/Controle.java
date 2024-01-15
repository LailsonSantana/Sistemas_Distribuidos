import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Controle {

    private List<Integer> propostas;
    private int elementoMaisFrequente;
    private int frequencia;
    private boolean consenso = false;

    public Controle(){
        this.propostas = new ArrayList<>();
    }

    public void insereProposta(Integer p){
        propostas.add(p);
    }

    public void verificaMaioria(int quantidadeNos){

        Integer elementoMaisFrequente = Collections.max(propostas, 
                Comparator.comparingInt(e -> Collections.frequency(propostas, e)));

        int frequencia = Collections.frequency(propostas, elementoMaisFrequente);

        this.elementoMaisFrequente = elementoMaisFrequente;
        this.frequencia = frequencia;

        if(frequencia >= (quantidadeNos / 2) + 1){ // Verifica se existe o Quorum
            this.consenso = true;
            System.out.println("TEMOS UMA MAIORIA DE " + frequencia + " VALORES");
        }
        else{
            System.out.println("NOVA RODADA");
            novaRodada();
        }
    }

    public void imprimePropostas(int quantNos){
        verificaMaioria(quantNos);
        for(Integer num:propostas){
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public void novaRodada(){
        propostas = new ArrayList<>();
        elementoMaisFrequente = -1;
        frequencia = -1;
        consenso = false;
    }

    public int getElementoMaisFrequente() {
        return elementoMaisFrequente;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public boolean isConsenso() {
        if(consenso == true){

        }
        return consenso;
    }
}
