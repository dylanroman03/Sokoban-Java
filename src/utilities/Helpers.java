package utilities;

import static utilities.Constants.PlayerConstants.IDLE_DOWN;
import static utilities.Constants.PlayerConstants.IDLE_UP;
import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;

import entities.Box;
import entities.Player;
import main.Game;

public class Helpers {

  public static boolean canMove(Player player, float x, float y) {
    int[][] lvlData = player.lvlData;
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

  public static boolean isSolid(float x, float y, int[][] lvlDate, Player player) {
    float xIndex = x / Game.TILES_SIZE;
    float yIndex = y / Game.TILES_SIZE;

    if(isBush(xIndex, yIndex, lvlDate)) return true;

    Box box = player.boxManager.isBox(player.getHitBox());

    if (box != null) {
      int playerAction = player.getPlayerAction();

      boolean cantMoveBox = player.bushManger.intersectBox(box.getHitBox(), playerAction);
      if(cantMoveBox)
        return true;
      
      box.move(playerAction, player.getHitBox());
    }

    return false;
  }

  public static boolean isBush(float xIndex, float yIndex, int[][] lvlDate) {
    int value = lvlDate[(int) yIndex][(int) xIndex];

    if (value == 1)
      return true;
    else
      return false;
  }
}
