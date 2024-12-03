import java.util.Random;

public class Elevsim {
    public static void main(String[] args) {
        
        Random random = new Random();
        

        /* -- Várias constantes. Não as altere. */

        final int FALSE = 0;              // Valor que significa falso
        final int TRUE = 1;               // Valor que significa verdadeiro
        final int UP = 1;                 // Valor para direção == up
        final int DOWN = -1;              // Valor para direção == down
        final int NODIRECTION = 0;        // Valor para direção == none
        final int ESCKEY = 27;            // ASCII valor para <Esc> key


        /*Outras constantes. Ok para mudar com cuidado. MAXPERSONS é limitado pela memória disponível--você pode 
         não ser capaz de aumentar esse valor muito além de 1000 ou mais. Além disso, certifique-se de manter MAXELEVS 
         e MAXFLOORS dentro dos intervalos declarados nos comentários abaixo.Valores fora desses intervalos podem funcionar,
         mas muitos andares ou elevadores transformarão a exibição das simulações em mingau. */

        final int MAXELEVS = 10;    // Número de elevadores (1 to 10 only)
        final int MAXFLOORS = 10;   // Número de andares (2 to 10 only)
        final int MAXPERSONS = 500;    // Máximo de pessoas no prédio
        final int ELEVWAIT = 15;   // Min. segundos para esperar nos andares
        final int CAPACITY = 24;    // Máximo de pessoas em um elevador
        final int TRAVELTIME = 5;   //Segundos para viajar entre andares
        

        /*Fórmulas usando a função rand() para tomar várias decisões. Sinta-se à vontade para ajustar esses valores para 
        alterar a simulação. WANTS_TO_ENTER controla a frequência com que as pessoas entram no prédio. ENTER_DEST seleciona
        o andar de destino, que deve estar entre 1 e MAXFLOORS -- 1. MAX_WAIT determina quantos segundos uma pessoa esperará 
        por um elevador antes de ficar farta e subir as escadas. BUSINESS controla quantos segundos as pessoas passam em seus 
        andares de destino antes de ir para outro lugar ou sair do prédio. LEAVING controla se uma pessoa sairá ou irá para 
        outro andar - atualmente está definido para forçar as pessoas a decidirem sair cerca de 2/3 do tempo. */


        final boolean WANTS_TO_ENTER = random.nextInt() < 200;                 // frequência que as pessoas entram no prédio
        final int ENTER_DEST = 1 + (random.nextInt() % (MAXFLOORS -- 1));      // seleciona andar de destino
        final int MAX_WAIT = 180 + (random.nextInt() % 180);                   // quantos segundos uma pessoa esperará por um elevador
        final int BUSINESS = 400 + (random.nextInt() % 6200);                  // segundos que as pessoas ficam em seus andares de destino
        final boolean LEAVING = random.nextInt() < 22000;                      // controla se a pessoa sairá ou irá para outro andar
                  
         
        /*--Declarações de variáveis ​​globais. Essas variáveis ​​são definidas em elevsim.cpp e são as únicas variáveis ​​globais no
        programa, exceto para o objeto de construção (veja elevsim.cpp). O programa usa esses valores para exibir as estatísticas de simulação
        na linha inferior. Todas as outras variáveis ​​do programa são variáveis ​​de classe ou são locais para funções de classe.*/
          
        extern unsigned totalPeople;       // Number of people
        extern unsigned totalPeople;       // People in building now
        extern unsigned totalPeople;       // People who left building
        extern unsigned totalPeople;       // Average no. people waiting
        extern unsigned totalPeople;       // Average no. people in elevators
        extern unsigned totalPeople;       // Number people who walked
        extern unsigned totalPeople;       // Seconds simulation has run
                    
          
         }
         
        }
