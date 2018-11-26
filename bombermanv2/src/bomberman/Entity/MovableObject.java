package bomberman.Entity;

import bomberman.Game;
import bomberman.GameObjectManager;
import bomberman.GameScene;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class MovableObject extends GameObject {
    //TODO: thay ảnh của nhân vật (các ảnh hiện tại in lên có nền hồng)
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
            indexOfFrame = 0;
            this.status = status;
        }
        int addX = 0, addY = 0;
        switch (status){
            case GO_UP:
                addY = -1;
                break;
            case GO_DOWN:
                addY = 1;
                break;
            case GO_LEFT:
                addX = -1;
                break;
            case GO_RIGHT:
                addX = 1;
                break;
            default:
                break;
        }
        addX *= speed;
        addY *= speed;
        if(canMove(x + addX, y + addY)){
            x += addX;
            y += addY;
        }
    }

    public boolean checkCollideWithFixedObject(int posX, int posY){
        //TODO: DONE Lấy đối tượng trong mảng FIXEDOBJECT và xử lý va cham
        //TODO: DONE xử lý các trường hợp còn lại (va chạm với bomb, wall, grass, brick)
        //TODO: DONE nếu là HideawayObject thì xử lý riêng ở từng đối tượng
        GameObjectManager manager = Game.getInstance().getGoManager();
        ArrayList<FixedObject> collideObjs = manager.getFixedObjectInRect(x, y, width, heigh);
        for (int i = 0; i < collideObjs.size(); i++){
            if(collideObjs.get(i) instanceof  Wall || collideObjs.get(i) instanceof  Brick){
                return false;
            }
            if(collideObjs.get(i) instanceof Flame){
                kill();
                return true;
            }
        }
        return true;
    }
    public boolean canMove(int newX, int newY){
        return checkCollideWithFixedObject(newX, newY);
    }
    
}
