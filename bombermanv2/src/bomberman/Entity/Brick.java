package bomberman.Entity;

import javafx.scene.image.Image;

public class Brick extends FixedObject{
    private static Image img = new Image(Wall.class.getResource("/sprite/brick.png").toExternalForm());
    public Brick(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
        //TODO: load ảnh
    }

    @Override
    public void update() {
        //TODO -h
        gc.drawImage(img, x, y, width, heigh);
    }

}
