package bomberman.Entity;

import javafx.scene.image.Image;

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
    public boolean collide() {
        //TODO: xử lý va chạm với bomber
        return false;
    }
}
