package bomberman.Entity;

import bomberman.Game;
import bomberman.GameScene;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Enemy extends MovableObject{
    private final  int SPEED=1;
    public abstract Status generateMove();
    public Enemy(int posX, int posY){
        imageLists = new Image[5][];
        x = posX;
        y = posY;
        width = GameScene.GAMETILE_SIZE;
        heigh = GameScene.GAMETILE_SIZE;
        speed = SPEED;
        status = Status.GO_DOWN;
        isMoving = false;
    }
    @Override
    public void kill() {
        this.status=Status.DEAD;
        this.update();
        //gc.drawImage(imageLists[status.getVal()][indexOfFrame % imageLists[status.getVal()].length], x, y, width, heigh);
    }
    @Override
    public boolean checkCollideCharacter(MovableObject movableObject) {
        Rectangle2D recThis=new Rectangle(this.x,this.y,this.width,this.heigh);
        Rectangle2D recMovableObject=new Rectangle( movableObject.x,
                                                    movableObject.y,
                                                    movableObject.width,
                                                    movableObject.heigh);
        if(((Rectangle) recThis).intersects(recMovableObject)) return true;
        else return false;
    }

    @Override
    public void update() {
        Status newStatus = generateMove();
        if(status != Status.DEAD && newStatus != status){
            indexOfFrame = 0;
            status = newStatus;
        }
       // checkCollideCharacter();
        else if(status == Status.DEAD){
            gc.drawImage(imageLists[4][0], x, y, width, heigh);
            Game.getInstance().getGoManager().removeObject(this);
        }
        else {
            gc.drawImage(imageLists[status.getVal()][indexOfFrame], x, y, width, heigh);
        }
    }
}
