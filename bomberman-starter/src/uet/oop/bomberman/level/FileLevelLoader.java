package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.GameException;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	private static char[][] _map;
	
	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}
	
	@Override
	public void loadLevel(int level) throws LoadLevelException {
		// TODO: <DONE> đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
		Scanner infoScaner;
		try {
			infoScaner = new Scanner(new File(getClass().getResource("/levels/Level" + level + ".txt").getFile()));
		} catch (FileNotFoundException e) {
			throw new LoadLevelException("Không tìm thấy file cấu hình màn chơi");
		}

		// TODO: <DONE> cập nhật các giá trị đọc được vào _width, _height, _level, _map
		_level = infoScaner.nextInt();
		_height = infoScaner.nextInt();
		_width = infoScaner.nextInt();
		infoScaner.nextLine();
		_map = new char[_height][_width];
		for (int i = 0; i < _height; i++){
			String line = infoScaner.nextLine();
			for (int j = 0; j < _width; j++){
				_map[i][j] = line.charAt(j);
			}
		}
	}

	@Override
	public void createEntities() {
		for (int y = 0; y < _height; y++){
			for (int x = 0; x < _width; x++){
				int pos = x + y * _width;
				switch (_map[y][x]){
					case '#':
						_board.addEntity(pos, new Wall(x, y, Sprite.wall));
						break;
					case '*':
						_board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new Brick(x, y, Sprite.brick)));
						break;
					case 'x':
						_board.addEntity(pos, new Portal(x, y, Sprite.portal));
						break;
					case 'p':
						_board.addCharacter(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
						break;
					case '1':
						_board.addCharacter(new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
						break;
					case '2':
						_board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
						break;
					case 'b':
						_board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass),
																	  new BombItem(x, y, Sprite.bomb),
																	  new Brick(x, y, Sprite.brick)));
						break;
					case 'f':
						_board.addEntity(pos, new LayeredEntity(x, y,
												new Grass(x, y, Sprite.grass),
												new FlameItem(x, y, Sprite.powerup_flames),
												new Brick(x, y, Sprite.brick)));
						break;
					case 's':
						_board.addEntity(pos, new LayeredEntity(x, y,
												new Grass(x, y, Sprite.grass),
												new SpeedItem(x, y, Sprite.powerup_speed),
												new Brick(x, y, Sprite.brick)));
						break;
					default:
						_board.addEntity(pos, new Grass(x, y, Sprite.grass));
						break;
				}
			}
		}
	}

}
