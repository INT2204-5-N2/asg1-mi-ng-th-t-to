package bomberman.Entity;

import bomberman.Game;
import bomberman.GameObjectManager;
import bomberman.GameScene;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class Enemy extends MovableObject{
    private final  int SPEED=2;
    public static Status oldStatus;
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
        this.status =Status.DEAD;
        Game.getInstance().getInfoBar().increaseScore();
    }
    @Override
    public boolean processCollideWithOtherCharacter(MovableObject movableObject) {
        Rectangle2D recThis=new Rectangle(this.x,this.y,this.width,this.heigh);
        Rectangle2D recMovableObject=new Rectangle( movableObject.x,
                                                    movableObject.y,
                                                    movableObject.width,
                                                    movableObject.heigh);
        if(((Rectangle) recThis).intersects(recMovableObject)){
            if(movableObject instanceof Bomber){
                movableObject.kill();
            } else {
                isMoving = false;
            }
            return true;
        }
        else return false;
    }

    @Override
    public void update() {
        Status newStatus = generateMove();
        /*if(!isMoving)
        {
            this.status=generateMove();
        }*/
        if(this.status != Status.DEAD && newStatus != this.status){
            indexOfFrame = 0;
            this.status = newStatus;
        }
       // processCollideWithOtherCharacter();
        if(this.status == Status.DEAD){
            gc.drawImage(imageLists[4][0], x, y, width, heigh);
            Game.getInstance().getGoManager().removeObject(this);
        }
        else {
            move(status);
            //TODO: sửa lại cách load hình
            gc.drawImage(imageLists[this.status.getVal()][indexOfFrame % imageLists[this.status.getVal()].length], x, y, width, heigh);
        }
    }

    @Override
    public boolean checkCollideWithFixedObject(int posX, int posY) {
        GameObjectManager manager = Game.getInstance().getGoManager();
        ArrayList<FixedObject> collideObjs = manager.getFixedObjectInRect(posX, posY, width, heigh);
        for (int i = 0; i < collideObjs.size(); i++){
            if(collideObjs.get(i) instanceof HideawayObject){
                return false;
            }
        }
        return super.checkCollideWithFixedObject(posX, posY);
    }
}
