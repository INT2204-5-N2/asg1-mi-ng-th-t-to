package bomberman.Entity;

public abstract class HideawayObject extends FixedObject{
    private boolean isHiding = true;
    private Brick hidingBrick;

    public HideawayObject(int xInGrid, int yInGrid) {
        super(xInGrid, yInGrid);
        this.hidingBrick = new Brick(xInGrid, yInGrid);
    }
    public abstract void show();
    @Override
    public void render() {
        if (isHiding){
            hidingBrick.render();
        } else {
            show();
        }
    }

    @Override
    public void update() {

    }
}
