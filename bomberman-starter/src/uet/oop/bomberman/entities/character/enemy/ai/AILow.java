package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.character.Direction;

public class AILow extends AI {

	@Override
	public Direction calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi

		int randomDirection = random.nextInt(4);
		switch (randomDirection){
			case 0:
				return Direction.UP ;
			case 1:
				return Direction.RIGHT;
			case 2:
				return Direction.DOWN;
			default:
				return Direction.LEFT;
		}
	}

}
