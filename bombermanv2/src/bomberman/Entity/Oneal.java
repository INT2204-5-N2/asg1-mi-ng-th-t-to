package bomberman.Entity;

public class Oneal extends Enemy {
    public Oneal(int posX, int posY){
        super(posX, posY);
        //TODO: load ảnh
    }
    @Override
    public Status generateMove() {
        //TODO: viết hàm sinh bước tiếp theo cho quái
        return Status.GO_DOWN;
    }
}
