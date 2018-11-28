package bomberman.Entity;

import bomberman.Game;
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
        //TODO: viết hàm sinh bước tiếp theo cho quá
        if(isMoving)
        {
            this.status=status;
        }
        else
        {
            if(this.x<Game.getInstance().getGoManager().getBomber().x&&isMoving) this.status=Status.GO_LEFT;
            else if(this.x<Game.getInstance().getGoManager().getBomber().x&&isMoving) this.status= Status.GO_RIGHT;
            else if(this.y<Game.getInstance().getGoManager().getBomber().x&&isMoving) this.status= Status.GO_UP;
            else if(this.y<Game.getInstance().getGoManager().getBomber().x&&isMoving) this.status= Status.GO_DOWN;
//        else this.status=Status.GO_DOWN;
        }

        return  this.status;
    }

    public Status goRow()
    {
        return Status.GO_RIGHT;
    }
    public Status goColum()
    {
        return Status.GO_DOWN;
    }
}
