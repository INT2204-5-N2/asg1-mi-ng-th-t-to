package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Direction;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.level.Coordinates;

public class Flame extends Entity {

	protected Board _board;
	Direction _direction;
	private int _radius;
	protected int xOrigin, yOrigin;
	protected FlameSegment[] _flameSegments = new FlameSegment[0];

	/**
	 *
	 * @param x hoành độ bắt đầu của Flame
	 * @param y tung độ bắt đầu của Flame
	 * @param direction là hướng của Flame
	 * @param radius độ dài cực đại của Flame
	 */
	public Flame(int x, int y, Direction direction, int radius, Board board) {
		xOrigin = x;
		yOrigin = y;
		_x = x;
		_y = y;
		_direction = direction;
		_radius = radius;
		_board = board;
		createFlameSegments();
	}

	/**
	 * Tạo các FlameSegment, mỗi segment ứng một đơn vị độ dài
	 */
	private void createFlameSegments() {
		/**
		 * tính toán độ dài Flame, tương ứng với số lượng segment
		 */
		_flameSegments = new FlameSegment[calculatePermitedDistance()];

		/**
		 * biến last dùng để đánh dấu cho segment cuối cùng
		 */
		int addX = 1, addY = 1;
		switch (_direction){
			case UP:
				addY--;
				break;
			case DOWN:
				addY++;
				break;
			case LEFT:
				addX--;
				break;
			case RIGHT:
				addX++;
				break;
		}
		// TODO: <DONE> tạo các segment dưới đây
		for (int i = 0; i < _flameSegments.length; i++){
			_flameSegments[i] = new FlameSegment(xOrigin + addX + i, yOrigin + addY + i, _direction, i == _flameSegments.length - 1);
		}

	}

	/**
	 * Tính toán độ dài của Flame, nếu gặp vật cản là Brick/Wall, độ dài sẽ bị cắt ngắn
	 * @return
	 */
	private int calculatePermitedDistance() {
		// TODO: <DONE> thực hiện tính toán độ dài của Flame
		switch (_direction){
			case RIGHT:
				for(int i = xOrigin + 1; i <= xOrigin + _radius; i++){
					if(_board.getEntityAt(i, yOrigin) instanceof Brick){
						return i - xOrigin - 1;
					}
				}
				break;
			case LEFT:
				for(int i = xOrigin - 1; i >= xOrigin - _radius; i--){
					if(_board.getEntityAt(i, yOrigin) instanceof Brick){
						return xOrigin - 1 - i;
					}
				}
				break;
			case DOWN:
				for(int i = yOrigin + 1; i <= yOrigin + _radius; i++){
					if(_board.getEntityAt(xOrigin, i) instanceof Brick){
						return i - yOrigin - 1;
					}
				}
				break;
			case UP:
				for(int i = yOrigin - 1; i >= yOrigin - _radius; i--){
					if(_board.getEntityAt(xOrigin, i) instanceof Brick){
						return yOrigin - 1 - i;
					}
				}
				break;
		}
		return _radius;
	}
	
	public FlameSegment flameSegmentAt(int x, int y) {
		for (int i = 0; i < _flameSegments.length; i++) {
			if(_flameSegments[i].getX() == x && _flameSegments[i].getY() == y)
				return _flameSegments[i];
		}
		return null;
	}

	@Override
	public void update() {}
	
	@Override
	public void render(Screen screen) {
		for (int i = 0; i < _flameSegments.length; i++) {
			_flameSegments[i].render(screen);
		}
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý va chạm với Bomber, Enemy. Chú ý đối tượng này có vị trí chính là vị trí của Bomb đã nổ
		return true;
	}
}
