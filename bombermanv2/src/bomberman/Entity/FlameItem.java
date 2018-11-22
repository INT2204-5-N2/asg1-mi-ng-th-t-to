package bomberman.Entity;

import javafx.scene.image.Image;

public class FlameItem extends HideableObject {
    private static Image sprite;
    static {
        sprite = new Image(FlameItem.class.getResource("/sprite/powerup_flames.png").toExternalForm());
    }
    public FlameItem(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
    }

    @Override
    public void show() {
        gc.drawImage(sprite, x, y, width, heigh);
    }

}
