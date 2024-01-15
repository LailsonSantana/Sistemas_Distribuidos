import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class ThreadNo extends Thread{

    private ServerSocket serverSocket;
    private int id;
    private Socket socket;
    private int quantidadeNos;
    private Proposta proposta;
    private Controle controle;
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public ThreadNo(int id,Proposta proposta,Controle c) throws IOException{
        this.id = id;
        this.quantidadeNos = 5;
        this.proposta = proposta;
        this.controle = c;
        serverSocket = new ServerSocket(54322 + id);
        // Somar o id faz com que criemos diferentes servidores
        // Além de possibilitar o acesso dinâmico
    }

    
    public void run(){

        int vez = 1; 
        while(true){
            if(controle.isConsenso() == true){
                System.out.println("EU NÓ "+id+" CONCORDO COM O VALOR: " + controle.getElementoMaisFrequente());
                break;
            }
            try{
                if(id == vez){
                    broadcast();
                }
                else{
                    // Um pequeno atraso para que todas possam receber antes 
                    // de começarem a enviar
                    tempoExecucao(500); //*id 
                    receive();
                }
                vez++; // Próximo nó
                if(vez > quantidadeNos){ // Fim da rodada
                    if(id == 1){
                        controle.imprimePropostas(quantidadeNos);
                    }
                    vez = 1; // Nova rodada 
                }
                // Ponto de encontro entre várias threads
                barrier.await();
            }catch(Exception e){
                e.getMessage();
            }      
        } 
    }

    public void broadcast() throws UnknownHostException, IOException{

        geraProposta();

        for(int i=1;i<quantidadeNos+1;i++){
            if(i != id){ // Envia para todos menos para si próprio
                send(i);
            }
        }
    }

    public void receive(){
        try{
            Socket socket = serverSocket.accept();

            // Definir stream de entrada de dados no servidor
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            Proposta p = (Proposta) entrada.readObject(); // Recebe um conjunto proper enviado

            proposta.getConjuntoProper().addAll(p.getConjuntoProper());
            for(Integer num:p.getConjuntoProper()){
                 //Imprime o conjunto proposto
                //System.out.print(num + " ");
            }
 
            //System.out.println();
            // Fechar stream de entrada  de dados
            entrada.close();
            
            //Fechar socket de comunicação
            socket.close();
            
        }catch(Exception e){
            e.getMessage();
        }
    }

    public void send(int no) throws UnknownHostException, IOException{

        socket = new Socket("127.0.0.1", 54322 + no);
        ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
        saida.writeObject(this.proposta); // Envia seu conjunto proper
        
        saida.close();
        socket.close();
    }

    public void geraProposta(){
        Random random = new Random();
        int indice = random.nextInt(proposta.getConjuntoProper().size());
        proposta.setIndiceProposta(indice);
        List<Integer> lista = new LinkedList<>(proposta.getConjuntoProper());
        controle.insereProposta(lista.get(indice));
        System.out.println("EU NÓ " + id + " PROPONHO QUE ESCOLHAMOS O VALOR: " 
        + lista.get(indice));
    }

    public void tempoExecucao(int tempo){

        try {sleep(tempo);} catch (InterruptedException e) {e.printStackTrace(); }
                           
    }  
}
