package bomberman.Entity;

import bomberman.GameScene;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Enemy extends MovableObject{
    private final  int SPEED=1;
    public abstract Status generateMove();
    public Enemy(int posX, int posY){
        //TODO: set các giá trị (tham khảo bomber)
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
        if(newStatus != status){
            indexOfFrame = 0;
            status = newStatus;
        }
       // checkCollideCharacter();
        super.update();
    }
}
