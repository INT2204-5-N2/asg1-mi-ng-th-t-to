package bomberman.Entity;

import javafx.scene.image.Image;

public class SpeedItem extends HideawayObject {
    private static Image sprite;
    static {
        sprite = new Image(SpeedItem.class.getResource("/sprite/powerup_speed.png").toExternalForm());
    }

    public SpeedItem(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
    }

    @Override
    public void show() {
        gc.drawImage(sprite, x, y, width, heigh);
    }

    @Override
    public boolean collide(Bomber bomber) {
        if(this.isHiding) return false;
        else
        {
            bomber.speed*=2;
            return true;
        }
    }
}
