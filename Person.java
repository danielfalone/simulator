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

/*Controla o que uma pessoa faz durante cada tick do relógio da simulação */
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

  
public int upWaiting(int floorNumber){
    return floorNumber;
}

public int dnWaiting(int floorNumber){
    return floorNumber;

}

public int loadIfWaiting(int elevNumber, int floorNumber, int pdest){
    return pdest;
}

public int loadIfGoing(int elevNumber, int floorNumber, int direction, int pdest){
    return pdest;

}

public int discharge(int elevNumber, int floorNumber){
    return floorNumber;
}

}
