package levels;

import static utilities.Constants.PATH_BACKGROUND_LEVELS;
import static utilities.Constants.PATH_FLOOR_LEVELS;
import static utilities.Constants.PATH_GOAL_LEVELS;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.LoadSave;

public class LevelManager {
  private BufferedImage levelBackground, levelFloor, levelGoal;
  private int[][] lvlData;

  public LevelManager() {
    levelBackground = LoadSave.getImage(PATH_BACKGROUND_LEVELS);
    levelFloor = LoadSave.getImage(PATH_FLOOR_LEVELS);
    levelGoal = LoadSave.getImage(PATH_GOAL_LEVELS);
    lvlData = LoadSave.getLevelData();
  }

  public void render(Graphics g) {
    for (int i = 0; i < lvlData.length; i++) {
      for (int j = 0; j < lvlData[0].length; j++) {
        g.drawImage(levelFloor, (Game.TILES_SIZE * j), (Game.TILES_SIZE * i), Game.TILES_SIZE,
            Game.TILES_SIZE, null);
        if (lvlData[i][j] == 3 || lvlData[i][j] == 4) {
          g.drawImage(levelGoal, (Game.TILES_SIZE * j) + (Game.TILES_SIZE / 3),
              (Game.TILES_SIZE * i) + (Game.TILES_SIZE / 3), Game.TILES_SIZE / 3,
              Game.TILES_SIZE / 3, null);
        }
      }
    }
    g.drawImage(levelBackground, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGTH, null);
  }


  public int[][] getLvlData() {
    return lvlData;
  }

}
