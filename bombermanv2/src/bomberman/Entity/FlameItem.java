package bomberman.Entity;

import javafx.scene.image.Image;

public class FlameItem extends HideawayObject {
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

    @Override
    public boolean collide(Bomber bomber) {
        //TODO: xử lý va chạm với bomber
        if(!isHiding)
        {
            bomber.setStrength(bomber.getStrength()*2);
            return true;
        }
        return false;
    }

}
