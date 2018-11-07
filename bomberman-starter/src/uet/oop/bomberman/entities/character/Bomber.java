package uet.oop.bomberman.entities.character;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;
import uet.oop.bomberman.level.Coordinates;

import java.util.Iterator;
import java.util.List;

public class Bomber extends Character {

    private List<Bomb> _bombs;
    protected Keyboard _input;

    /**
     * nếu giá trị này < 0 thì cho phép đặt đối tượng Bomb tiếp theo,
     * cứ mỗi lần đặt 1 Bomb mới, giá trị này sẽ được reset về 0 và giảm dần trong mỗi lần update()
     */
    protected int _timeBetweenPutBombs = 0;

    public Bomber(int x, int y, Board board) {
        super(x, y, board);
        _bombs = _board.getBombs();
        _input = _board.getInput();
        _sprite = Sprite.player_right;
    }

    @Override
    public void update() {
        clearBombs();
        if (!_alive) {
            afterKill();
            return;
        }

        if (_timeBetweenPutBombs < -7500) _timeBetweenPutBombs = 0;
        else _timeBetweenPutBombs--;

        animate();

        calculateMove();

        detectPlaceBomb();
    }

    @Override
    public void render(Screen screen) {
        calculateXOffset();

        if (_alive)
            chooseSprite();
        else
            _sprite = Sprite.player_dead1;

        screen.renderEntity((int) _x, (int) _y - _sprite.SIZE, this);
    }

    public void calculateXOffset() {
        int xScroll = Screen.calculateXOffset(_board, this);
        Screen.setOffset(xScroll, 0);
    }

    /**
     * Kiểm tra xem có đặt được bom hay không? nếu có thì đặt bom tại vị trí hiện tại của Bomber
     */
    private void detectPlaceBomb() {
        // TODO: kiểm tra xem phím điều khiển đặt bom có được gõ và giá trị _timeBetweenPutBombs, Game.getBombRate() có thỏa mãn hay không
        // TODO:  Game.getBombRate() sẽ trả về số lượng bom có thể đặt liên tiếp tại thời điểm hiện tại
        // TODO: <KHÔNG CHẮC CHẮN> _timeBetweenPutBombs dùng để ngăn chặn Bomber đặt 2 Bomb cùng tại 1 vị trí trong 1 khoảng thời gian quá ngắn
        // TODO: nếu 3 điều kiện trên thỏa mãn thì thực hiện đặt bom bằng placeBomb()
        // TODO: sau khi đặt, nhớ giảm số lượng Bomb Rate và reset _timeBetweenPutBombs về 0
        if(_input.space) {
            if (_timeBetweenPutBombs < 0 && Game.getBombRate() > 0) {
                placeBomb(getXTile(), getYTile());
                Game.addBombRate(-1);
                _timeBetweenPutBombs = 0;
            }
        }
    }

    protected void placeBomb(int x, int y) {
        // TODO: <DONE> thực hiện tạo đối tượng bom, đặt vào vị trí (x, y)
        Game.addBombRate(-1);
        // TODO: bỏ hardcode
        _timeBetweenPutBombs = 30;
        _board.addEntity(x + y * _board.getWidth(), new Bomb(x, y, _board));
    }

    private void clearBombs() {
        Iterator<Bomb> bs = _bombs.iterator();

        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.isRemoved()) {
                bs.remove();
                Game.addBombRate(1);
            }
        }

    }

    @Override
    public void kill() {
        if (!_alive) return;
        _alive = false;
    }

    @Override
    protected void afterKill() {
        if (_timeAfter > 0) --_timeAfter;
        else {
            _board.endGame();
        }
    }

    @Override
    protected void calculateMove() {
        // TODO: <DONE> xử lý nhận tín hiệu điều khiển hướng đi từ _input và gọi move() để thực hiện di chuyển
        // TODO: <DONE> nhớ cập nhật lại giá trị cờ _moving khi thay đổi trạng thái di chuyển
        if(_input.down){
            move(0, Game.getBomberSpeed());
            _moving = true;
        } else if(_input.up){
            move(0, -Game.getBomberSpeed());
            _moving = true;
        } else if(_input.left){
            move(-Game.getBomberSpeed(), 0);
            _moving = true;
        } else if(_input.right){
            move(Game.getBomberSpeed(), 0);
            _moving = true;
        } else {
            _moving = false;
        }

    }

    @Override
    public boolean canMove(double x, double y) {
        // TODO: <DONE> kiểm tra có đối tượng tại vị trí chuẩn bị di chuyển đến và có thể di chuyển tới đó hay không
        Entity other = _board.getEntity(getXTile(), getYTile(), this);
        return (other == null || collide(other) || other.collide(this));
    }

    @Override
    public void move(double xa, double ya) {
        // TODO: <DONE> sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không và thực hiện thay đổi tọa độ _x, _y
        // TODO: <DONE> nhớ cập nhật giá trị _direction sau khi di chuyển
        // TODO: di chuyển vào chính giữa ô
        if(_alive && canMove(_x + xa,_y + ya)){
            _x += xa;
            _y += ya;
            if(xa < 0){
                _direction = Direction.LEFT;
            }
            else if(xa > 0){
                _direction = Direction.RIGHT;
            } else if(ya < 0){
                _direction = Direction.UP;
            } else {
                _direction = Direction.DOWN;
            }
        }
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: <DONE> xử lý va chạm với Flame
        // TODO: <DONE> xử lý va chạm với Enemy
        if(e instanceof Flame || e instanceof Enemy){
            kill();
            return true;
        }
        return false;
    }

    private void chooseSprite() {
        switch (_direction) {
            case UP:
                _sprite = Sprite.player_up;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                }
                break;
            case RIGHT:
                _sprite = Sprite.player_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
            case DOWN:
                _sprite = Sprite.player_down;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                }
                break;
            case LEFT:
                _sprite = Sprite.player_left;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                }
                break;
            default:
                _sprite = Sprite.player_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
        }
    }
}
