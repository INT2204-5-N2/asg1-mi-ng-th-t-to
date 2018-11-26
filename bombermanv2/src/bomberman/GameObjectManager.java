package bomberman;

import bomberman.Entity.*;

import java.util.ArrayList;

public class GameObjectManager {

    private int numOfEnemy;
    private ArrayList<MovableObject> characters = new ArrayList<MovableObject>();
    private FixedObject[][] fixedObjectList;
    private Bomber bomber;
    public GameObjectManager(int width, int height){
        fixedObjectList = new FixedObject[height][width];
    }

    public ArrayList<MovableObject> getCharacters() {
        return characters;
    }

    public FixedObject[][] getFixedObjectList() {
        return fixedObjectList;
    }

    public void addObject(GameObject obj){
        if(obj instanceof MovableObject){
            characters.add((MovableObject) obj);
            if(obj instanceof Bomber){
                bomber = (Bomber) obj;
            }
        }
        else {
            FixedObject fixedRef = (FixedObject) obj;
            fixedObjectList[fixedRef.getyInGrid()][fixedRef.getxInGrid()] = fixedRef;
        }
    }
    public ArrayList<FixedObject> getFixedObjectInRect(int x, int y, int width, int heigh){
        int xInGridLeft, xInGridRight, yInGridUp, yInGridDown;
        xInGridLeft = (x + 1) / GameScene.GAMETILE_SIZE;
        xInGridRight = (x + width - 1) / GameScene.GAMETILE_SIZE;
        yInGridUp = (y + 1) / GameScene.GAMETILE_SIZE;
        yInGridDown = (y + heigh - 1) / GameScene.GAMETILE_SIZE;
        ArrayList<FixedObject> result = new ArrayList<>();
        result.add(fixedObjectList[yInGridUp][xInGridLeft]);
        result.add(fixedObjectList[yInGridDown][xInGridLeft]);
        result.add(fixedObjectList[yInGridUp][xInGridRight]);
        result.add(fixedObjectList[yInGridDown][xInGridRight]);
        return result;
    }
    public void removeObject(GameObject obj){
        if(obj instanceof MovableObject){

        }
    }

    public Bomber getBomber() {
        return bomber;
    }
    public int getNumOfEnemy()
    {
        return numOfEnemy;
    }
    public void setNumOfEnemy(int numOfEnemy)
    {
        this.numOfEnemy=numOfEnemy;
    }
}
