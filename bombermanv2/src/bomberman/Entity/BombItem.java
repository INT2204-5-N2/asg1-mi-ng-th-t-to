package bomberman.Entity;

import javafx.scene.image.Image;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BombItem extends HideawayObject {

    private static Image sprite;
    public BombItem(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
    }

    static {
        sprite = new Image(SpeedItem.class.getResource("/sprite/powerup_bombs.png").toExternalForm());
    }

    @Override
    public void show() {
        gc.drawImage(sprite, x, y, width, heigh);
    }

    @Override
    public boolean collide(Bomber bomber) {
        //TODO: DONE xử lý va chạm với bomber
        if (!isHiding) {
            bomber.setMaxBomb(3);
            return true;
        } else return false;
    }
}
