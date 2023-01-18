package levels;

import static utilities.Constants.PATH_BOX_LEVELS;
import static utilities.LoadSave.getInitBoxes;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import entities.Box;
import utilities.LoadSave;

public class BoxManager {
  private BufferedImage levelBox;
  private Box[] boxes;
  private int[][] lvlData;

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

  }

  public Box isBox(Rectangle2D hitboxPlayer) {
    for (Box box : boxes) {
      Rectangle2D hitBox = box.getHitBox();
      boolean intersects = hitboxPlayer.intersects(hitBox);

      if (intersects) return box;
    }

    return null;
  }

  // public void moveBox(int x, int y, int playerAction) {
  //   for (Box box : boxes) {
  //     int xIndex = (int) (box.getHitBox().x / Game.TILES_SIZE);
  //     int yIndex = (int) (box.getHitBox().y / Game.TILES_SIZE);

  //     if (xIndex == x && yIndex == y) {
  //       box.move(playerAction);
  //     }
  //   }
  // }

  public Box[] getBoxes() {
    return boxes;
  }

}
