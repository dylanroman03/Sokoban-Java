package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Bush extends Entity {
  int xInit, yInit;

  public Bush(float x, float y) {
    super(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
    initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
  }

  public void render(BufferedImage levelBox, Graphics g) {
    g.drawImage(levelBox, (int) getHitBox().x, (int) getHitBox().y, Game.TILES_SIZE, Game.TILES_SIZE, null);

    if (Game.DEBUG) {
      showHitBox(g);
    }
  }
}
