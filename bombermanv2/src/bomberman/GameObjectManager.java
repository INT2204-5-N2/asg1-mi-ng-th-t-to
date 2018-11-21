package bomberman;

import bomberman.Entity.FixedObject;
import bomberman.Entity.GameObject;
import bomberman.Entity.MovableObject;

import java.util.ArrayList;

public class GameObjectManager {
    private ArrayList<MovableObject> characters = new ArrayList<MovableObject>();
    private FixedObject[][] fixedObjectList;
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
        }
        else {
            FixedObject fixedRef = (FixedObject) obj;
            fixedObjectList[fixedRef.getyInGrid()][fixedRef.getxInGrid()] = fixedRef;
        }
    }
}
