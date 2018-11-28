package bomberman.Entity;

import bomberman.Game;
import bomberman.GameScene;
import javafx.scene.image.Image;

import java.util.Random;

public class Oneal extends Enemy {
    public Oneal(int posX, int posY){
        super(posX, posY);
        this.speed=5;
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
//        if(isMoving)
//        {
//            this.status=status;
//        }
//        else
//        {
//            if((this.x<Game.getInstance().getGoManager().getBomber().x&&canMove(this.x+5,this.y))
//                        this.status=Status.GO_RIGHT;
//            else if((this.y>Game.getInstance().getGoManager().getBomber().x&&canMove(this.x,this.y+5))||
//                    (this.y<Game.getInstance().getGoManager().getBomber().x&&canMove(this.x,this.y+5)))
//                this.status= Status.GO_DOWN;
//            else if((this.x>Game.getInstance().getGoManager().getBomber().x&&canMove(this.x-5,this.y))||
//                    (this.x<Game.getInstance().getGoManager().getBomber().x&&canMove(this.x-5,this.y)))
//                this.status= Status.GO_LEFT;
//            else if((this.y<Game.getInstance().getGoManager().getBomber().x&&canMove(this.x,this.y-5))||
//                    (this.y>Game.getInstance().getGoManager().getBomber().x&&canMove(this.x,this.y-5)))
//                this.status= Status.GO_UP;
//        }
//
//        return  this.status;
//        if(isMoving)
//        {
//            this.status=status;
//        }
////        if(!isMoving)
//        else
//        {
//            Random random=new Random();
//            int randomMove= random.nextInt(2);
//            if(randomMove==0)
//            {
//                status=goRow();
//                if(status==Status.GO_DOWN&&canMove(this.x,this.y+1)||this.canMove(this.x,this.y-1))
//                    status=goColum();
//            }
//            else
//            {
//                status=goColum();
//                if(status==Status.GO_RIGHT&&(canMove(this.x+1,this.y)||canMove(this.x-1,this.y)))
//                {
//                    status=goRow();
//                }
//            }
//        }
////        else this.status=status;
//        return status;
        if(Game.getInstance().getGoManager().getBomber().isMoving==false)
        {
            if(isMoving){
                return status;
            }
            Random random=new Random();
            int move=random.nextInt(4);
            switch (move)
            {
                case 0:
                    return Status.GO_LEFT;
                case 1:
                    return Status.GO_RIGHT;
                case 2:
                    return Status.GO_DOWN;
                case 3:
                    return Status.GO_UP;
            }
        }
        else
        {
            if(Game.getInstance().getGoManager().getBomber().isMoving)
            {
                if(this.isMoving)
                {
                    if(Game.getInstance().getGoManager().getBomber().status==Status.GO_DOWN) this.status=Status.GO_UP;
                    else if(Game.getInstance().getGoManager().getBomber().status==Status.GO_UP) this.status=Status.GO_DOWN;
                    else if(Game.getInstance().getGoManager().getBomber().status==Status.GO_RIGHT) this.status=Status.GO_LEFT;
                    else if(Game.getInstance().getGoManager().getBomber().status==Status.GO_LEFT) this.status=Status.GO_RIGHT;
                }
                else
                {
                    Random random=new Random();
                    int move=random.nextInt(4);
                    switch (move) {
                        case 0:
                            return Status.GO_LEFT;
                        case 1:
                            return Status.GO_RIGHT;
                        case 2:
                            return Status.GO_DOWN;
                        case 3:
                            return Status.GO_UP;
                    }
                }
            }

        }

        return this.status;

    }

    public Status goColum()
    {
        if(Game.getInstance().getGoManager().getBomber().y>this.y)
            return Status.GO_DOWN;
        else if(Game.getInstance().getGoManager().getBomber().y<this.y)
            return Status.GO_UP;
        return Status.GO_RIGHT;
    }
    public Status goRow()
    {
        if(Game.getInstance().getGoManager().getBomber().x<this.x)
            return Status.GO_LEFT;
        else if(Game.getInstance().getGoManager().getBomber().y<this.y)
            return Status.GO_RIGHT;
        return Status.GO_DOWN;
    }
}
