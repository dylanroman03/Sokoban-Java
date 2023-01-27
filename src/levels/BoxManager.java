package levels;

import static utilities.Constants.PATH_BOX_LEVELS;
import static utilities.Constants.PlayerConstants.IDLE_DOWN;
import static utilities.Constants.PlayerConstants.IDLE_UP;
import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;
import static utilities.LoadSave.getInitBoxes;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import entities.Box;
import main.Game;
import utilities.LoadSave;

public class BoxManager {
  private BufferedImage levelBox;
  private Box[] boxes;
  private int[][] lvlData;
  private Graphics graphics;

  public BoxManager(int[][] lvlData) {
    this.lvlData = lvlData;
    addBoxes();
  }

  private void addBoxes() {
    boxes = getInitBoxes(lvlData);
  }

  public void render(Graphics g) {
    levelBox = LoadSave.getImage(PATH_BOX_LEVELS);

    for (Box box : boxes) {
      box.render(levelBox, g);
    }

    graphics = g;
  }

  public Box isBox(Rectangle2D hitboxPlayer) {
    for (Box box : boxes) {
      Rectangle2D hitBox = box.getHitBox();
      boolean intersects = hitboxPlayer.intersects(hitBox);

      if (intersects) return box;
    }

    return null;
  }

  public Box[] getBoxes() {
    return boxes;
  }

  public void update() {
    int inGoal = 0;

    for (Box boxes : boxes) {
      Rectangle2D hitBox = boxes.getHitBox();
      double x = hitBox.getX();
      double y = hitBox.getY();

      float xIndex = (float) x / Game.TILES_SIZE;
      float yIndex = (float) y / Game.TILES_SIZE;

      int value = lvlData[(int) yIndex][(int) xIndex];

      if (value == 3 || value == 4) {
        inGoal++;
      }
    }

    if (inGoal == boxes.length) {
      System.out.println("You Win!!!");
    }
  }

  public boolean intersectBox(Box box, int playerAction) {
    Rectangle2D hbBox = box.getHitBox();

    for (Box b : boxes) {
      if (b.id != box.id) {
        Rectangle2D hitBox = b.getHitBox();
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
    }

    return false;
  }

}
