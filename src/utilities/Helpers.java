package utilities;

import static utilities.Constants.PlayerConstants.IDLE_DOWN;
import static utilities.Constants.PlayerConstants.IDLE_UP;
import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;

import main.Game;

public class Helpers {

  public static boolean canMove(float x, float y, float width, float height, int[][] lvlDate, int playerAction) {
    switch (playerAction) {
      case RUNNING_LEFT:
        if (!isSolid(x, y, lvlDate))
          if (!isSolid(x, y + height, lvlDate))
            return true;
        break;
      case RUNNING_RIGHT:
        if (!isSolid(x + width, y, lvlDate))
          if (!isSolid(x + width, y + height, lvlDate))
            return true;
        break;
      case IDLE_UP:
        if (!isSolid(x, y, lvlDate))
          if (!isSolid(x + width, y, lvlDate))
            return true;
        break;
      case IDLE_DOWN:
        if (!isSolid(x, y + height, lvlDate))
          if (!isSolid(x + width, y + height, lvlDate))
            return true;
        break;
    }

    return false;
  }

  private static boolean isSolid(float x, float y, int[][] lvlDate) {
    // if (x < 0 || x >= Game.GAME_HEIGTH)
    // return true;
    // if (y < 0 || y >= Game.GAME_HEIGTH)
    // return true;

    float xIndex = x / Game.TILES_SIZE;
    float yIndex = y / Game.TILES_SIZE;

    if (y < 0 || yIndex >= lvlDate.length)
      return true;
    if (x < 0 || xIndex >= lvlDate[0].length)
      return true;

    int value = lvlDate[(int) yIndex][(int) xIndex];

    if (value == 1)
      return true;

    return false;
  }

}
