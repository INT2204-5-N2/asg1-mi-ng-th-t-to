package bomberman.Entity;

import bomberman.Game;
import bomberman.GameObjectManager;
import bomberman.GameScene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Bomber extends MovableObject {
    private final static int SPEED = 5;
    private final static int STRENGTH=1;
    private int maxBomb;
    private int strength;
    public Bomber(int posX, int posY){
        imageLists = new Image[5][];//số trạng thái và số hình
        x = posX;
        y = posY;
        width = GameScene.GAMETILE_SIZE;
        heigh = GameScene.GAMETILE_SIZE;
        speed = SPEED;
        status = Status.GO_DOWN;
        isMoving = false;
        strength=STRENGTH;
        maxBomb=1;
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
    

    public void placeBomb(int posXPixel, int posYPixel){
        Bomb bomb=new Bomb((posXPixel+this.width / 2)/GameScene.GAMETILE_SIZE,(posYPixel+this.heigh / 2)/GameScene.GAMETILE_SIZE,Bomber.STRENGTH);

    }
    public void handleKeyEvent(KeyEvent event){
        if(event == null){
            return;
        }
        switch (event.getCode()){
            case RIGHT:
                move(Status.GO_RIGHT);
                break;
            case LEFT:
                move(Status.GO_LEFT);
                break;
            case DOWN:
                move(Status.GO_DOWN);
                break;
            case UP:
                move(Status.GO_UP);
                break;
            case SPACE:
                placeBomb(x, y);
        }
    }

    @Override
    public void kill() {
        status = Status.DEAD;
        indexOfFrame = 0;
    }
    @Override
    public void update() {
        if(status == Status.DEAD && indexOfFrame >= 2){
            Game.getInstance().getGoManager().removeObject(this);
        }
        handleKeyEvent(Game.getInstance().getEventQueue().poll());
        //TODO: sửa lại cách load hình
        gc.drawImage(imageLists[status.getVal()][indexOfFrame % imageLists[status.getVal()].length], x, y, width, heigh);
    }

    @Override
    public boolean checkCollideWithFixedObject(int posX, int posY) {
        GameObjectManager manager = Game.getInstance().getGoManager();
        ArrayList<FixedObject> collideObjs = manager.getFixedObjectInRect(posX, posY, width, heigh);
        for (int i = 0; i < collideObjs.size(); i++){
            if(collideObjs.get(i) instanceof HideawayObject){
                return ((HideawayObject) collideObjs.get(i)).collide(this);
            }
        }
        return super.checkCollideWithFixedObject(posX, posY);
    }
    public int getMaxBomb()
    {
        return this.maxBomb;
    }
    public void setMaxBomb(int maxBomb)
    {
        this.maxBomb=maxBomb;
    }
    public int getStrength()
    {
        return this.strength;
    }
    public void setStrength(int strength)
    {
        this.strength=strength;
    }
}
