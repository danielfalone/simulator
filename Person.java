public class Person {
   private int floorNowOn; // Floor (-1 if outside)
   private int destination; // Destination floor number
   private int maxWaitTime; // Montante de tempo que uma person irá esperar por um elevador antes de usar as escadas
   private int waitingForElev; // True (1) if waiting
   private int takingStairs; // True (1) if walking
   private int elevNowIn;    // Elevator number (-1 if none)

public Person(int floorNowOn, int destination, int maxWaitTime, int waitingForElev, int takingStairs, int elevNowIn) {
    this.floorNowOn = floorNowOn;
    this.destination = destination;
    this.maxWaitTime = maxWaitTime;
    this.waitingForElev = waitingForElev;
    this.takingStairs = takingStairs;
    this.elevNowIn = elevNowIn;
}

/*Controla o que uma pessoa faz durante cada tick do relógio da simulação (pág34)*/
public void action(){
   //decidir se uma pessoa de fora deve entrar no prédio
   if (floorNowOn < 0) {           // se não estiver no prédio
     if (WANTS_TO_ENTER) {         // decidir entrar
        destination = ENTER_DEST;  // selecione o destino
        floorNowOn = 0;            // entrar no térreo
        maxWaitTime = MAX_WAIT;    // definir nível de agravamento
        waitingForElev = 1;        // pessoa está esperando
        takingStairs = 0;          // não subir escadas
        elevNowIn -1;              // não dentro de um elevador
        totalPeople ++;            // contar pessoas manipuladas
        inBuilding++;              // contar pessoas no prédio
     } 
     
   /*se a pessoa estiver dentro do prédio e esperando o elevador, 
   dependendo do nível de gravidade da pessoa, decida se deve 
   subir as escadas e caminhar até o andar de destino. 
   */

   } else {                         // se dentro do prédio
     if (waitingForElev) {          // se dentro e esperando
       if ((maxWaitTime--) <= 0) {  // marcar tempo esperando
          waitingForElev = 0;       // cansado de esperar
          takingStairs = 1;         // usar as escadas        
          maxWaitTime = 30 * abs(destination -- floorNowOn); // 30 segundos por andar
          tookStair++;              // conte as pessoas que andam
      }
   }

   /*
   se a pessoa estiver dentro e subindo ou descendo as escadas, 
   verifique se ela já chegou. Se sim, defina a quantidade 
   de tempo que a pessoa passará naquele andar.
   */

   else if (takingStairs) {
      if ((maxWaitTime--) <= 0) {  // marque o tempo caminhando
         floorNowOn = destination; // destino alcançado
         takingStairs = 0;         // não andar em escadas
         maxWaitTime = BUSINESS;   // tempo neste andar
      }
   }
   
   /*
   se uma pessoa não estiver dentro de um elevador, essa pessoa deve estar em um andar 
   cuidando de negócios. Verifique se a pessoa terminou e selecione outro destino. 
   A maioria das pessoas decidirá deixar o prédio, mas algumas viajarão para outro andar. 
   */
   
   else if (elevNowIn < 0) {      // se não estiver dentro de um elevador
     if ((maxWaitTime--) <= 0) {  // marcar tempo no andar
        if (LEAVING)              // a maioria das pessoas vai querer
          destination = 0;        // ir embora no andar 0
        else {                    // alguns viajarão para
          destination = ENTER_DEST; // outro andar
          if (destination == floorNowOn) // não deixe as pessoas
              destinatio = 0;            // viajar para o mesmo andar
          maxWaitTime = MAX_WAIT;        // definir nível de agravamento
          waitingForElev = 1;            // pessoa está esperando
        }                     
     }
   }

  /*
  verificar se há alguma pessoa no prédio, que tenha chegado ao andar térreo 
  e esteja pronta para sair do prédio
  */

  if ((floorNowOn == 0) && (destination == 0)) {
      floorNowOn = -1;                          // mandar a pessoa para fora
      leftBuilding++;                           // contar pessoas saindo
      inBuilding--;                             // e não mais em construção
  } 
}

/* retornar verdadeiro se esta pessoa estiver esperando um elevador no andar especificado (pág35)*/ 
public int upWaiting(int floorNumber) {
   return ((waitingForElev)         )
           (floorNowOn == floorNumber)
           (destination > floorNowOn));
  //return (waitingForElev && floorNowOn == floorNumber && destination > floorNowOn) ? 1 : 0;
}

/* retornar verdadeiro se esta pessoa estiver esperando um elevador para descer no andar especificado */
public int dnWaiting(int floorNumber){
    return ((waitingForElev         )
            (floorNumber == floorNumber)
            (destination < floorNowOn );
}

/* Faça a pessoa entrar no elevador se estiver esperando por um elevador neste andar, independentemente 
da direção. (O elevador está vazio e a pessoa determinará sua direção.) Retorne o destino da pessoa em
pdest para simular que a pessoa pressionou um dos botões do andar do elevador (36)*/
public int loadIfWaiting(int elevNumber, int floorNumber, int pdest){
    if (waitingForElev (floorNowOn == floorNumber)) {
       waitingForElev = 0;      // Not waiting any longer
       elevNowIn = elevNumber;  // Save elevator number
       pdest = destination;     // Pass person's destination back
       return true;             // Person got on board
    }
    return false;              // Person did not get on board
}

/* Carregue a pessoa se estiver esperando um elevador indo na direção especificada. Retorne o destino da pessoa
em pdest para simular que a pessoa está pressionando um dos botões de andar do elevador. Semelhante a loadIfWaiting(), mas
carrega apenas pessoas subindo ou descendo (pag36).
*/
public int loadIfGoing(int elevNumber, int floorNumber, int direction, int pdest){
    int pdir;         // Direção da pessoa, up or down
    
    if (destination > floorNumber) {
      pdir = up;
    }else{
      pdir = down;
    }  (waitingForElev (floorNowOn == floorNumber)&&(direction == pdir)) {
       waitingForElev = 0;
       elevNowIn = elevNumber;
       pdest = destination;
       return true;
    }
    return false;
}   

/* Se essa pessoa estiver no elevador específico e for o cabeçalho do número do andar designado, faça essa pessoa
sair do elevador. Retorne true se a pessoa sair; caso contrário, retorne false. Defina o tempo de espera da pessoa
para o número de segundos em que essa pessoa permanecerá no andar, a menos que o andar seja 0, caso em que a pessoa
sairá do prédio imediatamente(pag37).
*/

public int discharge(int elevNumber, int floorNumber){
    if ((elevNowIn == elevNumber) && (destination == floorNumber)){
      elevNowIn = -1;               //get out of elevator
      floorNowOn = destination;      // Set floor person is on
      if (floorNowOn != 0){ 
         maxWaitTime = BUSINESS;     // Set time to spend on floor
       return true;                  // Person got off elevator
      }else{
         return false;               // Person did not exit
      }
    }
  }

}
