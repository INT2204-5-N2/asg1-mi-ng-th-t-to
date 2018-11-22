package bomberman.Entity;

import javafx.scene.image.Image;

public class BombItem extends HideableObject {
    private static Image sprite;
    static {
        sprite = new Image(SpeedItem.class.getResource("/sprite/powerup_bombs.png").toExternalForm());
    }
    public BombItem(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
    }

    @Override
    public void show() {
        gc.drawImage(sprite, x, y, width, heigh);
    }
}
