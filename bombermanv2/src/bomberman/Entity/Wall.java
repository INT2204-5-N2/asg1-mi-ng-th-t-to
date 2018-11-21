package bomberman.Entity;

import javafx.scene.image.Image;

public class Wall extends FixedObject {
    private Image img;
    public Wall(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
        img = new Image(getClass().getResource("/sprite/wall.png").toExternalForm());
    }

    @Override
    public void render() {
        gc.drawImage(img, x, y, width, heigh);
    }
}
