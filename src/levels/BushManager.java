package levels;

import static utilities.Constants.PATH_BLOCK_LEVELS;
import static utilities.Constants.PlayerConstants.IDLE_DOWN;
import static utilities.Constants.PlayerConstants.IDLE_UP;
import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;
import static utilities.LoadSave.getInitBushes;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import entities.Bush;
import utilities.LoadSave;

public class BushManager {
  private BufferedImage levelBox;
  private Bush[] bushes;
  private int[][] lvlData;

  public BushManager(int[][] lvlData) {
    this.lvlData = lvlData;
    addBushes();
  }

  private void addBushes() {
    bushes = getInitBushes(lvlData);
  }

  public void render(Graphics g) {
    levelBox = LoadSave.getImage(PATH_BLOCK_LEVELS);

    for (Bush bush : bushes) {
      bush.render(levelBox, g);
    }

  }

  public boolean intersectBox(Rectangle2D hbBox, int playerAction) {
    for (Bush bush : bushes) {
      Rectangle2D hitBox = bush.getHitBox();
      double yThird = hitBox.getHeight() / 3;
      double xThird = hitBox.getWidth() / 3;
      double width = hitBox.getWidth();
      double height = hitBox.getHeight();

      boolean byLeft = hbBox.intersects(hitBox.getX(), hitBox.getY() + yThird, 1, height - yThird);
      boolean byRight = hbBox.intersects(hitBox.getX() + width, hitBox.getY() + yThird, 1, height - yThird);

      boolean byBottom = hbBox.intersects(hitBox.getX() + xThird, hitBox.getY() + height, width - xThird, 1);
      boolean byTop = hbBox.intersects(hitBox.getX() + xThird, hitBox.getY(), width - xThird, 1);


      if (byRight && playerAction == RUNNING_LEFT)
        return true;
      if (byLeft && playerAction == RUNNING_RIGHT)
        return true;
      if (byBottom && playerAction == IDLE_UP)
        return true;
      if (byTop && playerAction == IDLE_DOWN)
        return true;
    }

    return false;
  }
}
