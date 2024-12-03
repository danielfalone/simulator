public class FloorCollection {
    /*Esta classe mantém todos os andares em um edifício e executa diversas funções importantes
      necessárias aos elevadores */
    // Um vetor declara uma estrutura de dados para armazenamento de coleções de objetos do tipo floor.
    private floor fa[MAXFLOORS];   //Array of floor objects

    /*O construtor de um objeto floorCollection. Esta função inicializa todos os objetos de andar em uma 
    coleção, ou seja, todos os andares do edifício. */
    public FloorCollection(){
      for (int i=0; i<MAXFLOORS; i++){
        fa[i].setFloorNumber(i); //Atribuir números de andar
      }
    }
    /*
    Chama a função showFloor() para cada andar. Esta função atualiza a exibição de todos os andares do edifício.
    (pag47)*/
    public void showFloors(persCollection thePersons){
      for (int i=0; i<MAXFLOORS; i++){
        fa[i].showFloor(thePersons);
      }
    }

    /*Reinicia o botão para cima ou para baixo para este andar */
    public void resetButton(int direction, int floorNumber){
        if (direction == UP)
          fa[floorNumber].resetUpButton();
        else if (direction == DOWN)
          fa[floorNumber].resetDownButton();
    }

    /*Retorna verdadeiro se houver algum andar acima do andar especificado sinalizando para um elevador
     em qualquer direção */
    public int signalUp(int floorNumber){
       for (int i = MAXFLOORS -- 1; i > floorNumber; i--)
          if (fa[i].upButton() || fa[i].downButton())
             return true;
       return false;
    }
    
    /*Retorna verdadeiro se houver algum andar abaixo da sinalização de andar especificada para um elevador em qualquer direção*/
    public int signalDown(int floorNumber){
       for (int i=0; i< floorNumber; i++)
          if (fa[i].upButton() || fa[i].downButton())
            return true;
       return false;
    }

    /* Retorna true se um botão na direção especificada (para cima ou para baixo) for pressionado neste andar. 
    Elevadores usam esta função para ajudar a decidir se devem parar em um andar. 
    Retorna false se nenhum botão for pressionado ou se direction não for definida.
    */
    public int signalSameDir(int direction, int floorNumber){
       if (direction == UP){
          return fa[floorNumber].upButton();
       }
       else if (direction == DOWN){
          return fa[floorNumber].downButton();
       }else{
         return FALSE;
       }
          
    }

    /*Retorna o número médio de pessoas esperando em todos os andares por um elevador em qualquer direção */
    public int avgWaiting(){
       int total = 0;

       for (int i = 0; i < MAXFLOORS; i++){
         total += fa[i].getNumWaiting();
         return (total/MAXFLOORS);
       }
       
     }                                    

}
