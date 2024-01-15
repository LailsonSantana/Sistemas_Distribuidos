import java.io.IOException;
import java.util.LinkedHashSet;

public class Principal {

    public static void main(String[] args) throws IOException{

        Controle controle = new Controle();

        System.out.println("INICIADO");
        LinkedHashSet<Integer> conjuntoProper1 = new LinkedHashSet<>();
        conjuntoProper1.add(1);
        conjuntoProper1.add(3);
        conjuntoProper1.add(5);
        Proposta proposta1 = new Proposta(conjuntoProper1);

        LinkedHashSet<Integer> conjuntoProper2 = new LinkedHashSet<>();
        conjuntoProper2.add(1);
        conjuntoProper2.add(2);
        Proposta proposta2 = new Proposta(conjuntoProper2);

        LinkedHashSet<Integer> conjuntoProper3 = new LinkedHashSet<Integer>();
        conjuntoProper3.add(2);
        conjuntoProper3.add(4);
        Proposta proposta3 = new Proposta(conjuntoProper3);

        LinkedHashSet<Integer> conjuntoProper4 = new LinkedHashSet<Integer>();
        conjuntoProper4.add(2);
        conjuntoProper4.add(3);
        conjuntoProper4.add(5);
        Proposta proposta4 = new Proposta(conjuntoProper4);

        LinkedHashSet<Integer> conjuntoProper5 = new LinkedHashSet<Integer>();
        conjuntoProper5.add(3);
        conjuntoProper5.add(5);
        Proposta proposta5 = new Proposta(conjuntoProper5);   
        
        LinkedHashSet<Integer> conjuntoProper6 = new LinkedHashSet<Integer>();
        conjuntoProper6.add(1);
        Proposta proposta6 = new Proposta(conjuntoProper6); 

        ThreadNo tn1 = new ThreadNo(1,proposta1,controle);
        ThreadNo tn2 = new ThreadNo(2,proposta2,controle);
        ThreadNo tn3 = new ThreadNo(3,proposta3,controle);
        ThreadNo tn4 = new ThreadNo(4,proposta4,controle);
        ThreadNo tn5 = new ThreadNo(5,proposta5,controle);
        ThreadNo tn6 = new ThreadNo(6,proposta6,controle);
        
        tn1.start();
        tn2.start();
        tn3.start();
        tn4.start();
        tn5.start();
        //tn6.start();

    }
}
