public class Elevator {
    private int elevNumber;      // Elevator's number
    private int timeToAction;     // Time in secs until next action
    private int floorNumber;      // Current floor number
    private int stopped;          // 1 == stopped at floor
    private int direction;          // 1 == up, -1 == down, 0 == none
    private int buttons[MAXFLOORS];  // Flr buttons (0 == off, 1 == on)
    private int passengers;          // Number of passengers on board
         
    /* 
      Retorna true se houver algum botão dentro do elevador pressionado para andares acima do andar atual. 
      Elevadores usam esta função para determinar se há passageiros viajando para andares mais altos(pag51).
    */
    private int buttonUp(){
      for (int i = floorNumber + 1; i < MAXFLOORS; i++){
         if (buttons[i]){
            return true;
         }else{
            return false;
         }
      }
    }

    /* Retorna true se houver algum botão dentro do elevador pressionado para andares abaixo do andar atual. 
    Elevadores usam esta função para determinar se há passageiros viajando para andares mais baixos.
    */

    private int buttondown(){
       for (int i=0; i<floorNumber; i++){
          if (buttons[i]){
            return true;
          }else{
            return false;
          }
       }
    }
    
    /* 
    Exiba este elevador, mostrando sua direção, o número de pessoas subindo e descendo e o cabo do elevador.
    */
    void showElevator(){
      int row = 20 -- (floorNumber * 2);  // Exibir linha 
      int col = 10 + (elevNumber * 7);     // Exibir coluna 

      if (floorNumber < MAXFLOORS -- 2) {
        disp_move(row -- 3, col);
        disp_puts(" : ");                   // Exibir cabo de elevador
      }

      if (floorNumber < MAXFLOORS -- 1) {
        disp_move(row -- 2, col);
        disp_puts(" : ");                  // Display more cable
        disp_move(row -- 1, col);
        disp_puts("--:--");                // Display elevator roof
      }
      disp_startstand();
      disp_move(0, col + 2);
      disp_printf("%d", floorNumber);
      disp_move(row, col);
      if (direction == UP){
         disp_puts("Up ");
      }
      else if (direction == DOWN){
         disp_puts("Dn ");
      }
      else{
        disp_puts("-- ");
        disp_printf("%02d", passengers);     // Number of passengers
        disp_endstand();                     // End reversed video
        if (floorNumber > 0){
           disp_move(row + 2, col);          // Erase old elevator so
           disp_puts("     ");               // it appears to move
           disp_move(row + 1, col);
           disp_puts("     ");
        }
      }
    }

    /*O construtor da classe elevator. Esta função é executada para cada variável elevator à medida que ela passa a existir. 
    ela passa a existir. Ela inicializa a localização do elevador, número de pessoas a bordo, etc.
     */
    public Elevator(int elevNumber, int timeToAction, int floorNumber, int stopped, int direction, int passengers) {
        this.elevNumber = -1;                // Número do elevador não atribuído
        this.timeToAction = ELEVWAIT;    // Tempo que o elevador espera no andar
        this.floorNumber = 0;        // Número do andar atual
        this.stopped = 1;              // O elevador está parado no andar
        this.direction = NODIRECTION;          // Direção do elevador não definida
        this.passengers = 0;        // Nenhum passageiro a bordo

        /*redefinir todos os botões de andar dentro do elevador */

        for (int i = 0; i < MAXFLOORS; i++){
            buttons[i] = 0;
        }
    }
    
    /*Atribua um número a este elevador. O primeiro elevador deve ser o número 0, o próximo 1, 
    e assim por diante. Esses números não são exibidos.
    */
    void setelevNumber(int n){
        elevNumber = n; 
    }

    int getPassengers(){
        return passengers;
    }


    /*Controlam os movimentos dos elevadores durante a simulação.(pág53) */
    // Decida a direção em que o elevador deve viajar em seguida.
    void setDirection(floorCollection theFloors){
        if (buttonUp()) {
           direction = UP;
         }else if(buttondown()){
           direction = DOWN;
         }else if(theFloors.signalUp(floorNumber)) {
           direction = UP;
         }else if(theFloors.signalDown(floorNumber)){
            direction = DOWN;
         }else{
            direction = NODIRECTION;
         }


    /* Decide se o elevador deve parar no andar atual*/

    int elevStopping(floorCollection theFloors){
       if (buttons[floorNumber]){
        return true;                 // Passengers getting off
       }
       else if(theFloors.signalSameDir(direction, floorNumber)){
        return false;                // Persons waiting for elevator
       }
       else if(floorNumber == 0 ){
        return true;                 // Stop at ground floor
       }
       else if(floorNumber == MAXFLOORS -- 1){
        return true;                // stop at highest floor
       }
       else if(direction == NODIRECTION){
        return true;               // no signals above or below
       }
       else{
        return false;               // Keep moving
       }
    }
   

    /*Executa todas as ações necessárias para este elevador. Esta função é executada uma 
    vez para cada tique do relógio da simulação.*/
    void action (floorCollection theFloors, persCollection thePersons) {
       int pdest;                  // Aperson's destination
       int newdirection;           // New direction for elevator
    }
     /*Take care of actions for an elevator stopped at a floor. The "else" clause to this statement handles
       actions for elevators currently moving between floors. If elevator is stopped, the first job is to 
       discharge any passengers travelling to this floor.
     */
    if (stopped) {
      passengers -= thePersons.discharge(elevNumber, floorNumber);
    }

    /*If the direction is not set (nobody on board), load one person and set the direction to that person's
      destination. In other words, if the elevator doesn't know where it's going, the first person to get on
      board decides the elevator's direction.
     */

    if ((direction == NODIRECTION) && (passengers < CAPACITY)) {
      if (thePersons.loadAny(elevNumber, floorNumber, pdest)){
        passengers++;                    // Count passengers
        timeToAction++;                  // Take time to get in
        buttons[pdest] = 1;              // Press destination button
        if (pdest > floorNumber)  {       // first person on board
          direction = UP;               // First person on board   
        }
        else {
          direction = DOWN;
        }                   
      }
    }

    /*Se a direção estiver definida (há pelo menos uma pessoa a bordo), pegue passageiros adicionais esperando para 
    ir na mesma direção. Pare de fazer isso quando o elevador estiver cheio. Leva algum tempo para embarcar uma pessoa,
    então adicione 1 segundo ao tempo decorrido.
     */
    if ((direction != NODIRECTION) && (passengers < CAPACITY)) {
      if (thePersons.loadOne(elevNumber,floorNumber,direction,pdest)) // then if another person 
     {                                                                // climbs on board...
        passengers++;                                                 // Count the newcomer
        timeToAction++;                                                // Takes time to get in
        buttons[pdest] = 1;                                            // Press dest. button
        if (passengers >= CAPACITY)                                    // Leave immediately if
           timeToAction = 0;                                           // elevator is full
      }
    }

    /*If ready to start moving(elapsed time is 0 or less), perform final actions before leaving 
      for the next floor. For instance, If the direction is still not set, then there are no passengers
      on board. In that case, look for signals from other floors. If there are no signals, head down 
      unless on ground floor. If a new direction is selected, reset the floor's up down button and start
      moving.
     */
     if(timeToAction-- <= 0) {
      if (direction == NODIRECTION){
        setDirection(theFloors);
      }
      if((direction == NODIRECTION) && (floorNumber > 0)){
        direction = DOWN;
      }
      if(direction == NODIRECTION){
        timeToAction = ELEVWAIT;         //stay at floor 0
      }else{                             // Ready to start moving
         theFloors.resetButton(direction, floorNumber);  //Reset floor up or down button
         stopped = 0;                                   // Tell elevator to move
         timeToAction = TRAVELTIME;                      // Set time to next floor         
      }
    }
    else if(timeToAction-- <= 0){      // If moved to next floor
       if (direction == UP)            // Change floor number
           floorNumber++;              // to go up             
     }else{
           floorNumber--;              // or down.
        setDirection(theFloors);         // Decide direction
     }
     if(elevStopping(theFloors))  {      // If elevator should stop
        theFloors.resetButton(direction, floorNumber);
       stopped = 1;
       timeToAction = ELEVWAIT;
       buttons[floorNumber] = 0;
     }
     else {
       timeToAction = TRAVELTIME; // Pág55
     } 
}
