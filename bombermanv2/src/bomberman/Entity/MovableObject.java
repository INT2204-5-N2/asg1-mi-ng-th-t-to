package bomberman.Entity;

import bomberman.Game;
import bomberman.GameObjectManager;
import bomberman.GameScene;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class MovableObject extends GameObject {
    //TODO: DONE thay ảnh của nhân vật (các ảnh hiện tại in lên có nền hồng)
    protected Status status;
    protected int speed;
    protected boolean alive;
    protected boolean isMoving;
    protected Image[][] imageLists;
    protected int indexOfFrame = 0;

    public abstract void kill();

    public abstract boolean processCollideWithOtherCharacter(MovableObject other);

    public void move(Status status){
        if(status == this.status){
            indexOfFrame++;
        } else {
            this.status = status;
            indexOfFrame = 0;
        }
        int newX = 0, newY = 0;
        switch (status){
            case GO_UP:
                newY = -speed;
                break;
            case GO_DOWN:
                newY = speed;
                break;
            case GO_LEFT:
                newX = -speed;
                break;
            case GO_RIGHT:
                newX = speed;
                break;
            default:
                break;
        }
        if(newX == 0){
            newX = centerlizeCoordinate(x);
            newY += y;
        } else {
            newY = centerlizeCoordinate(y);
            newX += x;
        }
        if(canMove(newX, newY)){
            x = newX;
            y = newY;
        }
    }

    public boolean checkCollideWithFixedObject(int posX, int posY){
        GameObjectManager manager = Game.getInstance().getGoManager();
        ArrayList<FixedObject> collideObjs = manager.getFixedObjectInRect(posX, posY, width, heigh);
        for (int i = 0; i < collideObjs.size(); i++){
            FixedObject curObj = collideObjs.get(i);
            if(curObj instanceof  Wall || curObj instanceof  Brick){
                return false;
            }
            if(curObj instanceof Bomb){
                if(((Bomb) curObj).isExploded()){
                    kill();
                    return true;
                }
            }
            if(curObj instanceof Flame){
                kill();
                return true;
            }
        }
        return true;
    }
    public boolean canMove(int newX, int newY){
        return checkCollideWithFixedObject(newX, newY);
    }

    private int centerlizeCoordinate(int point){
        return (point + GameScene.GAMETILE_SIZE / 2) / GameScene.GAMETILE_SIZE * GameScene.GAMETILE_SIZE;
    }
}
