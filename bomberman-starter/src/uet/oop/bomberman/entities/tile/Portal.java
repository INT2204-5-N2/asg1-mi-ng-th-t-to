package uet.oop.bomberman.entities.tile;


import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Iterator;
import java.util.List;

public class Portal extends Tile {

	public Portal(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: <DONE> xử lý khi Bomber đi vào
		if(e instanceof Bomber){
			List<Character> characters = ((Bomber) e).getBoard()._characters;
			Character cur;
			Iterator<Character> itr = characters.iterator();
			while (itr.hasNext()){
				cur = itr.next();
				if(cur instanceof Enemy){
					return true;
				}
			}
			((Bomber) e).getBoard().nextLevel();
			return true;
		}
		return false;
	}

}
