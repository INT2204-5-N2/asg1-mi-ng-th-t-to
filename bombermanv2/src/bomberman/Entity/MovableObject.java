package bomberman.Entity;

import bomberman.Game;
import bomberman.GameScene;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.geom.Rectangle2D;

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
                addY = 1;
                break;
            default:
                break;
        }
        if(canMove(x + addX, y + addY)){
            x += addX;
            y += addY;
        }
    }

    public boolean checkCollideWithFixedObject(int posX, int posY){
        //TODO: lấy đối tượng trong mảng FIXEDOBJECT và xử lý va cham
        //TODO: xử lý các trường hợp còn lại (va chạm với bomb, wall, grass, brick)
        //TODO: nếu là HideawayObject thì xử lý riêng ở từng đối tượng
        Rectangle2D recThis=new Rectangle(this.x,this.y,this.width,this.heigh);
        Rectangle2D recHideawayObject=new Rectangle(posX,posY,this.width,this.heigh);
        FixedObject fixedObject=new FixedObject(posX,posY) {
            @Override
            public void update() {

            }
        };
        if(((Rectangle) recThis).intersects(recHideawayObject))
        {
            if((fixedObject instanceof  Grass)) return false;
            return true;
        }
        return false;
    }
    public boolean canMove(int newX, int newY){
        return !checkCollideWithFixedObject(newX, newY);
    }
}
