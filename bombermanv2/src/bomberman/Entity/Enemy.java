package bomberman.Entity;

public abstract class Enemy extends MovableObject{
    public abstract Status generateMove();

    @Override
    public void kill() {

    }

    @Override
    public void render() {

    }

    @Override
    public boolean checkCollideCharacter() {
        return false;
    }

    @Override
    public void update() {
        Status newStatus = generateMove();
        if(newStatus != status){
            indexOfFrame = 0;
            status = newStatus;
        }
        checkCollideCharacter();
        render();
    }
}
