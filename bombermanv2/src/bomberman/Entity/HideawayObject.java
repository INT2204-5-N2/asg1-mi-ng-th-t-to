package bomberman.Entity;

public abstract class HideawayObject extends FixedObject{
    protected boolean isHiding = true;//cái này làm gì
    private Brick hidingBrick;//cái này làm gì

    public HideawayObject(int xInGrid, int yInGrid) {//đây á
        super(xInGrid, yInGrid);
        this.hidingBrick = new Brick(xInGrid, yInGrid);
    }
    public abstract void show();
    public abstract boolean collide(Bomber bomber);//truyền tham số vào đây á uk
    @Override
    public void update() {
        if (isHiding){
            hidingBrick.update();
        } else {
            show();
        }
    }

}
