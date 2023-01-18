package utilities;

import static utilities.Constants.PlayerConstants.IDLE_DOWN;
import static utilities.Constants.PlayerConstants.IDLE_UP;
import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;

import entities.Box;
import entities.Player;
import levels.BoxManager;
import main.Game;

public class Helpers {

  public static boolean canMove(Player player, float x, float y) {
    int[][] lvlData = player.lvlData;
    BoxManager boxManager = player.boxManager;
    float height = player.getHitBox().height;
    float width = player.getHitBox().width;


    switch (player.getPlayerAction()) {
      case RUNNING_LEFT:
        if (!isSolid(x, y, lvlData, player))
          if (!isSolid(x, y + height, lvlData, player))
            return true;
        break;
      case RUNNING_RIGHT:
        if (!isSolid(x + width, y, lvlData, player))
          if (!isSolid(x + width, y + height, lvlData, player))
            return true;
        break;
      case IDLE_UP:
        if (!isSolid(x, y, lvlData, player))
          if (!isSolid(x + width, y, lvlData, player))
            return true;
        break;
      case IDLE_DOWN:
        if (!isSolid(x, y + height, lvlData, player))
          if (!isSolid(x + width, y + height, lvlData, player))
            return true;
        break;
    }

    return false;
  }

  private static boolean isSolid(float x, float y, int[][] lvlDate, Player player) {
    float xIndex = x / Game.TILES_SIZE;
    float yIndex = y / Game.TILES_SIZE;

    if (y < 0 || yIndex >= lvlDate.length)
      return true;
    if (x < 0 || xIndex >= lvlDate[0].length)
      return true;

    int value = lvlDate[(int) yIndex][(int) xIndex];

    if (value == 1)
      return true;


    Box box = player.boxManager.isBox(player.getHitBox());

    if (box != null) {
      int playerAction = player.getPlayerAction();
      box.move(playerAction, player.getHitBox());

      return false;
    }

    return false;
  }
}
