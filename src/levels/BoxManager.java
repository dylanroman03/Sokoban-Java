package levels;

import static utilities.Constants.PATH_BOX_LEVELS;
import static utilities.LoadSave.getBoxes;

import java.awt.Graphics;
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
    boxes = getBoxes(lvlData);
  }

  public void render(Graphics g) {
    levelBox = LoadSave.getImage(PATH_BOX_LEVELS);

    for (Box box : boxes) {
      box.render(levelBox, g);
      // g.drawImage(levelBox, (int) box.getHitBox().x, (int) box.getHitBox().y, Game.TILES_SIZE, Game.TILES_SIZE, null);
    }

  }
}
