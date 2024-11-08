import java.io.IOException;
public class Action {
    
    private long timeAtStart; // hora de início (segundos para rodar a simulação)
    private long timeRemaining; // tempo restante (segundos restantes para terminar)
     
    /* Método construtor
     O construtor de Action é executado quando um objeto de ação
     (em outras palavras, uma variável do tipo Action) surge.
     As duas instruções dentro do construtor action atribuem 3600 para os
     campos privados da classe, timeAtStart e timeRemaining. Todos os objetos action
     serão inicializados para a simulação com 3600 segundos(1hora).
    */
    public Action(long timeAtStart, long timeRemaining) {
        this.timeAtStart = timeAtStart = 3600;
        this.timeRemaining = timeRemaining = 3600;
    }

    /*
    Retorna verdadeiro se o tempo restante for maior que 0, o 
    loop principal do programa continua a ação até que esta função.
    retorne falso
    */
    public boolean continues() {
       return timeRemaining > 0; 
    }
    
    /* 
    Define contador de tempo para este número de segundos 
    definir tempo(Passar como parâmetro a qtd de segundos)
    */
    public void setTime(int secs) { 
       this.timeAtStart = secs;
       this.timeRemaining = timeAtStart;
    }

    /*Retorna o tempo restante para a simulação, 
    obter o tempo (retorna o tempo)*/
    public long getTimeRemaining() {
        return timeRemaining;
    }

     
    /* reduz o tempo restante para simulação. A resolução mínima, 
    ou "granularidade", é de um segundo. Não é possível reduzir o tempo para < 0.
    //reduzir o tempo(reduz o tempo restante. "timeRamaining") */
     public void reduceTime(int secs) { 
       if (secs > timeRemaining){
         timeRemaining = 0;
       }
       else{
        timeRemaining -= secs;
       }  
    } 
 
    /* executar a ação. Neste caso, a função é apenas um shell. 
    mais tarde, adicionaremos programação para simular uma ação real. */
    public void perform() throws IOException { // ativa a simulação
       System.out.println("Press <Spacebar> to continue...");        

      // Scanner scanner = new Scanner(System.in);
       char c =  (char) System.in.read();  
       while (c != ' '){ // Pausa para pressionar a tecla barra de espaço <Spacebar> 
                                          
       } 
       reduceTime(900); // Diminuir o tempo restante 900 segundos
                                                                                             
    }


    /* exibir o status da ação atual. chamar display() 
    é a única maneira de uma instrução fora do objeto action acessar o valor do tempo restante.*/
    public void display() { // mostra o que está acontecendo
         System.out.println("Time remaining: "+ timeRemaining + "sec.");
    }

    /* exibir resultados finais. como em action.display(), chamar results() é a única maneira 
    de uma instrução fora do objeto de ação acessar os campos de dados privados na classe de ação.*/
    public void results() { // exibe um relatório final após o término da simulação
        System.out.println("Simulation results");
        System.out.println("==================");
        System.out.println("Time at start .. : " + timeAtStart + "sec.");
        System.out.println("Time at end .... : " + timeRemaining + "sec.");
    } 
}
