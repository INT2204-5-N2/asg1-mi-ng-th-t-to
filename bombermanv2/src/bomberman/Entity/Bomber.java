package bomberman.Entity;

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
    public boolean checkCollideCharacter(MovableObject other) {
        return false;
    }
    

    public void placeBomb(int posX, int posY){
    }
    public void handleKeyEvent(KeyEvent event){
        switch (event.getCode()){
            case RIGHT:
                status = Status.GO_RIGHT;
                move(1, 0);
                break;
            case LEFT:
                status = Status.GO_LEFT;
                move(-1, 0);
                break;
            case DOWN:
                status = Status.GO_DOWN;
                move(0, 1);
                break;
            case UP:
                status = Status.GO_UP;
                move(0, -1);
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
        if(alive && isMoving){
            indexOfFrame++;
        }
    }
}
