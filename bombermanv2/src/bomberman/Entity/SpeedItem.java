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
        //TODO: DONE xử lý va chạm với bomber
        //TODO: DONE đầu tiên kiểm tra cái isHiding, nếu true nghĩa là vẫn còn cái che ở trên => return false
        //TODO: DONE ngược lại, bomber được thưởng (thưởng gì tuy loại item), muốn lấy bomber thì có thể truyền nó vào tham số
        //TODO: DONE sau đó return true
        if(this.isHiding) return false;
        else
        {
            bomber.speed*=2;
            return true;
        }
    }
}
