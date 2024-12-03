import java.util.ArrayList;

public class PersCollection{
    // vetor de variaveis de classe (person pa [MAXPERSONS];)
    //private ArrayList<Person> personList; // Array of person objects

    
    /* A função chama action() para cada pessoa. Executa uma vez para cada
    tique do relógio da simulação. (pag38) */

    public void action() {
        for (int i = 0; i < MAXPERSONS; i++)
          personList[i].action();
    }

    /* Conta o número de pessoas esperando em um andar. retorna nup (número de pessoas subindo) e 
    ndn (número de pessoas descendo) no andar especificado.(pag39)
    */
    public void numWaiting(int floorNumber, int nup, int ndn ) {
        nup = ndn = 0;
        for (int i = 0; i<MAXPERSONS; i++) {
            nup += pa[i].upWaiting(floorNumber);
            ndn += pa[i].dnWaiting(floorNumber);
        }
    }
  
    /*
     Carregue qualquer pessoa esperando por um elevador neste número de andar. retorne o 
     destino da pessoa em pdest para que o elevador possa começar a viajar na direção necessária. 
     Retorne true se uma pessoa estiver carregada, caso contrário, retorne false. Se false, 
     pdest é indefinido.(pag40)
     */
    public int loadAny(int elevNumber, int floorNumber, int pdest) {
        for (int i = 0; i < MAXPERSONS; i++)
           if (pa[i].loadIfWaiting(elevNumber, floorNumber, pdest))
              return true;     //Person got on board
          return false;        // No person got into an elevator
    }
    
    /* Load one person waiting for an elevator at this floor number, and headed in the specified direction.
       Return true if a person is loaded, else return false. Similar to loadAny, but loads only persons
       traveling in a specified direction.
    */
    public int loadOne(int elevNumber, int floorNumber, int direction, int pdest) {
        for (int i = 0; i < MAXPERSONS; i++)
           if (pa[i].loadIfGoing(elevNumber,floorNumber, direction, pdest))
           return true;             // Person got into board
        return false;              // No person got into an elevator
    }
 
    /*
     Descarregar todas as pessoas neste elevador que estão viajando para o andar especificado. Retornar número
     de pessoas que saíram do elevador.
    */

    public int discharge(int elevNumber, int floorNumber) {
        int n=0;    // Number of people who get off elevator

        for (int i = 0; i<MAXPERSONS; i++)
           n += pa[i].discharge(elevNumber, floorNumber);
        return n;
        }


    }


