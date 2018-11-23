package bomberman.Entity;

import javafx.scene.image.Image;

public class Oneal extends Enemy {
    public Oneal(int posX, int posY){
        super(posX, posY);
        for (Status d: Status.values()){
            Image[] temp;
            if(d != Status.DEAD){
                temp = new Image[3];
                temp[0] = new Image(getClass().getResource("/sprite/oneal_" + d +"1.png").toExternalForm());
                temp[1] = new Image(getClass().getResource("/sprite/oneal_" + d +"2.png").toExternalForm());
                temp[2] = new Image(getClass().getResource("/sprite/oneal_" + d +"3.png").toExternalForm());
            }
            else {
                temp = new Image[1];
                temp[0] = new Image(getClass().getResource("/sprite/oneal_dead.png").toExternalForm());
            }
            imageLists[d.getVal()] = temp;
        }
    }
    @Override
    public Status generateMove() {
        //TODO: viết hàm sinh bước tiếp theo cho quái
        return Status.GO_DOWN;
    }
}
