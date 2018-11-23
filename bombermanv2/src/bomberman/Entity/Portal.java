package bomberman.Entity;

import javafx.scene.image.Image;

public class Portal extends FixedObject {
    //TODO: khởi tạo image, load ảnh (tham khảo Wall)
    private static Image img = new Image(Wall.class.getResource("/sprite/portal.png").toExternalForm());
    public Portal(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
    }

    @Override
    public void update() {
        //TODO: -h
        gc.drawImage(img, x, y, width, heigh);
    }

}
