package bomberman.Entity;

import javafx.scene.image.Image;

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
        return false;
    }
    public boolean canMove(int newX, int newY){
        return !checkCollideWithFixedObject(newX, newY);
    }
}
