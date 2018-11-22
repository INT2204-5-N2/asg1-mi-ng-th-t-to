package bomberman.Entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Enemy extends MovableObject{
    public abstract Status generateMove();
    @Override
    public void kill() {
        this.status=Status.DEAD;
        this.render();
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
        checkCollideCharacter();
        render();
    }
}
