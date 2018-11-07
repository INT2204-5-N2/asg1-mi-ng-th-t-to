package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Message;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.ai.AI;
import uet.oop.bomberman.entities.character.enemy.ai.AILow;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;

import java.awt.*;

public abstract class Enemy extends Character {

	protected int _points;
	
	protected double _speed;
	protected AI _ai;

	protected final double MAX_STEPS;
	protected final double rest;
	protected double _steps;
	
	protected int _finalAnimation = 30;
	protected Sprite _deadSprite;
	
	public Enemy(int x, int y, Board board, Sprite dead, double speed, int points) {
		super(x, y, board);
		
		_points = points;
		_speed = speed;
		
		MAX_STEPS = Game.TILES_SIZE / _speed;
		rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
		_steps = MAX_STEPS;
		
		_timeAfter = 20;
		_deadSprite = dead;
	}
	
	@Override
	public void update() {
		animate();
		
		if(!_alive) {
			afterKill();
			return;
		}
		
		if(_alive)
			calculateMove();
	}
	
	@Override
	public void render(Screen screen) {
		
		if(_alive)
			chooseSprite();
		else {
			if(_timeAfter > 0) {
				_sprite = _deadSprite;
				_animate = 0;
			} else {
				_sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 60);
			}
				
		}
			
		screen.renderEntity((int)_x, (int)_y - _sprite.SIZE, this);
	}
	
	@Override
	public void calculateMove() {
		// TODO: <DONE> Tính toán hướng đi và di chuyển Enemy theo _ai và cập nhật giá trị cho _direction
		// TODO: <DONE> sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không
		// TODO: <DONE> sử dụng move() để di chuyển
		// TODO: <DONE> nhớ cập nhật lại giá trị cờ _moving khi thay đổi trạng thái di chuyển

        double xa = 0, ya = 0;
        switch (_direction){
            case UP:
                ya = -_speed;
                break;
            case DOWN:
                ya = _speed;
                break;
            case LEFT:
                xa = -_speed;
                break;
            case RIGHT:
                xa = _speed;
                break;
            default:
                break;
        }
		move(xa, ya);
	}
	
	@Override
	public void move(double xa, double ya) {
		_y += ya;
		_x += xa;
		if(_alive && canMove(_x, _y)){
			_moving = true;
		} else {
			_moving = false;
			_x -= xa;
			_y -= ya;
            _direction = _ai.calculateDirection();
		}
	}
	
	@Override
	public boolean canMove(double x, double y) {
		// TODO: <DONE> kiểm tra có đối tượng tại vị trí chuẩn bị di chuyển đến và có thể di chuyển tới đó hay không
        Entity other = _board.getEntity(getXTile(), getYTile(), this);
        return (other == null || collide(other) || other.collide(this));
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: <DONE> xử lý va chạm với Flame
		// TODO: <DONE> xử lý va chạm với Bomber
		if(e instanceof  Flame){
			kill();
			return true;
		}
		if(e instanceof Bomber){
			((Bomber) e).kill();
			return true;
		}
		return false;
	}
	
	@Override
	public void kill() {
		if(!_alive) return;
		_alive = false;
		
		_board.addPoints(_points);

		Message msg = new Message("+" + _points, getXMessage(), getYMessage(), 2, Color.white, 14);
		_board.addMessage(msg);
	}
	
	
	@Override
	protected void afterKill() {
		if(_timeAfter > 0) --_timeAfter;
		else {
			if(_finalAnimation > 0) --_finalAnimation;
			else
				remove();
		}
	}
	
	protected abstract void chooseSprite();
}
