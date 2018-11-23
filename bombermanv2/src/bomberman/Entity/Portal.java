package bomberman.Entity;

import javafx.scene.image.Image;

public class Portal extends HideawayObject {
    private static Image img = new Image(Wall.class.getResource("/sprite/portal.png").toExternalForm());
    public Portal(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
    }

    @Override
    public void show() {
        gc.drawImage(img, x, y, width, heigh);
    }

    @Override
    public boolean collide() {
        //TODO: xử lý va chạm với bomber
        return false;
    }
}
