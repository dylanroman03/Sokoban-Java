package levels;

import static utilities.Constants.PATH_BACKGROUND_LEVELS;
import static utilities.Constants.PATH_BLOCK_LEVELS;
import static utilities.Constants.PATH_BOX_LEVELS;
import static utilities.Constants.PATH_FLOOR_LEVELS;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.LoadSave;

public class LevelManager {
  private Game game;
  private BufferedImage levelBackground, levelFloor, levelBlock, levelBox;
  private int[][] lvlData;

  public LevelManager(Game game) {
    this.game = game;
    levelBackground = LoadSave.getImage(PATH_BACKGROUND_LEVELS);
    levelFloor = LoadSave.getImage(PATH_FLOOR_LEVELS);
    levelBlock = LoadSave.getImage(PATH_BLOCK_LEVELS);
    levelBox = LoadSave.getImage(PATH_BOX_LEVELS);
    lvlData = LoadSave.getLevelData();
  }

  public void render(Graphics g) {
    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[0].length; j++) {
        switch (lvlData[i][j]) {
          case 1:
            g.drawImage(levelFloor, (Game.TILES_SIZE * j), (Game.TILES_SIZE * i), Game.TILES_SIZE,
                Game.TILES_SIZE, null);
            g.drawImage(levelBlock, (Game.TILES_SIZE * j), (Game.TILES_SIZE * i), Game.TILES_SIZE,
                Game.TILES_SIZE, null);
            break;
          default:
            g.drawImage(levelFloor, (Game.TILES_SIZE * j), (Game.TILES_SIZE * i), Game.TILES_SIZE,
                Game.TILES_SIZE, null);
            break;
        }
      }
    }

    g.drawImage(levelBackground, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGTH, null);
  }

  public void update() {

  }

  public int[][] getLvlData() {
    return lvlData;
  }

}
