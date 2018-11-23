package bomberman.Entity;

public abstract class HideawayObject extends FixedObject{
    private boolean isHiding = true;
    private Brick hidingBrick;

    public HideawayObject(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
        this.hidingBrick = new Brick(xInGrid, yInGrid);
    }
    public abstract void show();
    public abstract boolean collide();
    @Override
    public void update() {
        if (isHiding){
            hidingBrick.update();
        } else {
            show();
        }
    }

}
