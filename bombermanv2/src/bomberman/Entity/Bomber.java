package bomberman.Entity;

import bomberman.Game;
import bomberman.GameObjectManager;
import bomberman.GameScene;
import bomberman.Sound.SoundPlay;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Bomber extends MovableObject {
    private final static int SPEED = 10;
    private int maxBomb;
    private int numOfActiveBomb;
    private int strength = 1;
    public Bomber(int posX, int posY){
        imageLists = new Image[5][];//số trạng thái và số hình
        x = posX;
        y = posY;
        width = GameScene.GAMETILE_SIZE-2;
        heigh = GameScene.GAMETILE_SIZE-2;
        speed = SPEED;
        status = Status.GO_DOWN;
        isMoving = false;
        maxBomb=1;
        numOfActiveBomb = 0;
        for (Status d: Status.values()){
            Image[] temp = new Image[3];
            temp[0] = new Image(getClass().getResource("/sprite/player_" + d +".png").toExternalForm());
            temp[1] = new Image(getClass().getResource("/sprite/player_" + d +"_1.png").toExternalForm());
            temp[2] = new Image(getClass().getResource("/sprite/player_" + d +"_2.png").toExternalForm());
            imageLists[d.getVal()] = temp;
        }
    }

    @Override
    public boolean processCollideWithOtherCharacter(MovableObject movableObject) {
        Rectangle2D recThis=new Rectangle(this.x,this.y,this.width,this.heigh);
        Rectangle2D recMovableObject=new Rectangle( movableObject.x,
                movableObject.y,
                movableObject.width,
                movableObject.heigh);
        if(((Rectangle) recThis).intersects(recMovableObject)){
            if(movableObject instanceof Enemy){
                kill();
            }
            return true;
        }
        else return false;
    }

    public void placeBomb(int posXPixel, int posYPixel) {
        if (numOfActiveBomb < maxBomb) {
            new Bomb((posXPixel + this.width / 2) / GameScene.GAMETILE_SIZE, (posYPixel + this.heigh / 2) / GameScene.GAMETILE_SIZE, strength);
            numOfActiveBomb++;
        }

    }
    public void handleKeyEvent(KeyEvent event){
        if(event == null){
            return;
        }
        SoundPlay.playSound(SoundPlay.BOMBER_RUN_SOUND);
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
        if(status == Status.DEAD){
            if(indexOfFrame >= 2){
                Game.getInstance().getGoManager().removeObject(this);
            } else {
                indexOfFrame++;
            }
        } else {
            KeyEvent event = Game.getInstance().getEventQueue().poll();
            if(event == null || event.getCode() == KeyCode.SPACE){
                checkCollideWithFixedObject(x, y);
            }
            handleKeyEvent(event);
        }
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

    public void decreaseActiveBomb(){
        numOfActiveBomb--;
    }
}
