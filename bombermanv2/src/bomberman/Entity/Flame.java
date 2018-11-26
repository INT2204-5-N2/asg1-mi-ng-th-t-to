package bomberman.Entity;

import bomberman.Game;
import javafx.scene.image.Image;

public class Flame extends FixedObject{
    public enum FlameStatus{
        horizontal, horizontal_left_last, horizontal_right_last, vertical, vertical_down_last,
        vertical_top_last
    }
    private int indexOfImage = 0;
    private Image[] imageList;
    public Flame(int xInGrid, int yInGrid, FlameStatus status) {
        super(xInGrid, yInGrid);
        imageList = new Image[3];
        for (int i = 0; i < 3; i++){
            imageList[i] = new Image(getClass().getResource("/sprite/explosion_" + status + i + ".png").toExternalForm());
        }
        Game.getInstance().getGoManager().addObject(this);
    }

    @Override
    public void update() {
        if(indexOfImage >= imageList.length){
            Game.getInstance().getGoManager().removeObject(this);
        } else {
            gc.drawImage(imageList[indexOfImage], x, y, width, heigh);
        }
    }
}
