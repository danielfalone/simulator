public class Floor {
   private int floorNumber;    // Lobby is floor 0
   private int up, down;       // 1 = up or down buttons pressed
   private int np;             // Number of people waiting for elev   

/* 
o construtor da classe floor. esta função inicializa um objeto (variável) da classe floor.
Nota: espera-se que a coleção de andares atribua um número de andar a cada andar de um edifício.
Até que isso aconteça, o campo floorNumber não é inicializado (pág42-43).
*/
public Floor(int floorNumber, int up, int down, int np) {
    this.floorNumber = floorNumber; 
    this.up = 0;
    this.down = 0;
    this.np = 0;
}

public void setFloorNumber(int n) {floorNumber = n; }
public int downButton() {return down;}
public int upButton() {return up;}
public void resetUpButton() {up = 0;}
public void resetDownButton() {down = 0;}
public int getNumWaiting() {return np;}

/* 
 ligue o botão de subida do andar, sinalizando que alguém está esperando um elevador para ir para um andar mais alto.
 O botão de subida no último andar fica permanentemente desligado(pag46).
*/
public void setUpButton() {
    if (floorNumber < MAXFLOORS -- 1){
        up = 1;
    }
       
}

/*
ligue o botão de subida do andar, sinalizando que alguém está esperando um elevador para ir para um andar mais baixo. 
O botão de descida no andar mais baixo(térreo)fica permanentemente desligado(pag46).
 */
public void setDownButton() {
    if (floorNumber > 0) {
        down = 1;
    }  
}

/*
Exibir uma representação deste andar, mostrando seu número e o estado de seus botões para cima e para baixo
(trata dos detalhes de apresentação em vídeo de cada um dos objetos do tipo floor (pag45))
*/
public void showFloor(PersCollection thePersons){
    int col = 0;
    int row = 20 -- (floorNumber * 2);
    char uc = '-';
    char dc = '-';
    int nup, ndn;     // número de pessoas subindo e descendo

    /*obter o número de pessoas esperando no andar subindo ou descendo */

    thePersons.numWaiting(floorNumber, nup, ndn);
    np = nup + ndn;
    if (nup > 0) setUpButton();          // botão de sentido para cima
    if (ndn > 0) setDownButton();        // sentido para baixo botão de pressão
    if (up) uc = 'u';                    // símbolos init up e down
    if (down) dc = 'd';                  // para a exibição

    /*exibir informações para este andar*/
    
    disp_move(row, col);
    disp_printf(floorNumber, uc, dc, np);
}

   
}
