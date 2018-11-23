package bomberman.Entity;

import bomberman.Game;
import bomberman.GameScene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Bomber extends MovableObject {
    private final static int SPEED = 1;

    public Bomber(int posX, int posY){
        imageLists = new Image[5][];//số trạng thái và số hình
        x = posX;
        y = posY;
        width = GameScene.GAMETILE_SIZE;
        heigh = GameScene.GAMETILE_SIZE;
        speed = SPEED;
        status = Status.GO_DOWN;
        isMoving = false;
        for (Status d: Status.values()){
            Image[] temp = new Image[3];
            temp[0] = new Image(getClass().getResource("/sprite/player_" + d +".png").toExternalForm());
            temp[1] = new Image(getClass().getResource("/sprite/player_" + d +"_1.png").toExternalForm());
            temp[2] = new Image(getClass().getResource("/sprite/player_" + d +"_2.png").toExternalForm());
            imageLists[d.getVal()] = temp;
        }
    }

    @Override
    public boolean processCollideWithOtherCharacter(MovableObject other) {
        return false;
    }
    

    public void placeBomb(int posX, int posY){
        //TODO: tạo bomb và đặt vào vị trí
        //TODO: lưu ý convert từ tọa độ pixel sang tọa độ lưới
    }
    public void handleKeyEvent(KeyEvent event){
        switch (event.getCode()){
            case RIGHT:
                status = Status.GO_RIGHT;
                move(status);
                break;
            case LEFT:
                status = Status.GO_LEFT;
                move(status);
                break;
            case DOWN:
                status = Status.GO_DOWN;
                move(status);
                break;
            case UP:
                status = Status.GO_UP;
                move(status);
                break;
            case SPACE:
                placeBomb(x, y);
            default:
                isMoving = false;
                indexOfFrame = 0;
                break;
        }
    }

    @Override
    public void kill() {
        status = Status.DEAD;
        indexOfFrame = 0;
    }
    @Override
    public void update() {
        if(!alive && indexOfFrame >= 2){
            Game.getInstance().getGoManager().removeObject(this);
        }
        gc.drawImage(imageLists[status.getVal()][indexOfFrame % imageLists[status.getVal()].length], x, y, width, heigh);
        if(isMoving){
            indexOfFrame++;
        }
    }

    @Override
    public boolean checkCollideWithFixedObject(int posX, int posY) {
        //TODO: xử lý va chạm với HideawayObject bằng cách gọi hàm collide
        // TODO: (hàm này trả về true nếu brick bị phá hủy, bomber có thể ăn các item hoặc đi vào portal)
        //TODO: nhớ gọi super cho các trường hợp còn lại
        return super.checkCollideWithFixedObject(posX, posY);
    }
}
