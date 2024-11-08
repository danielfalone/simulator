public class Button {
    private int isUp;  // "está para cima"

    // Pressiona o botão e passa o valor 0 ou 1 para alterar o estado do botão
    public void push(int upDown){
       isUp = upDown;
    }

     // Mesma função porém com membros inLine {if (upDown == 0) isUp = 0; else isUp = 1; }
     void pushSecondVersion(int upDown){
      if (upDown == 0) {
        isUp = 0;
      }else{
        isUp = 1;  
      }
    }

    // Verificará se o botão está pressionado ou não
    public int state() {
        return isUp;
    }
   
}
