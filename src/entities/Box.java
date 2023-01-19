package entities;

import static utilities.Constants.PlayerConstants.IDLE_DOWN;
import static utilities.Constants.PlayerConstants.IDLE_UP;
import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.Game;

public class Box extends Entity {
  int xInit, yInit;
  private float boxSpeed = 1f;

  public Box(float x, float y) {
    super(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
    initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
  }

  public void render(BufferedImage levelBox, Graphics g) {
    g.drawImage(levelBox, (int) getHitBox().x, (int) getHitBox().y, Game.TILES_SIZE, Game.TILES_SIZE, null);

    if (Game.DEBUG) {
      showHitBox(g);
    }
  }

  public void move(int playerAction, Rectangle2D hitboxPlayer) {
    float xSpeed = 0, ySpeed = 0;
    double yThird = hitBox.getHeight() / 3;
    double xThird = hitBox.getWidth() / 3;
    double width = hitBox.getWidth();
    double height = hitBox.getHeight();

    boolean byLeft = hitboxPlayer.intersects(hitBox.getX(), hitBox.getY() + yThird, 1, height - yThird);
    boolean byRight = hitboxPlayer.intersects(hitBox.getX() + hitBox.width, hitBox.getY() + yThird, 1, height - yThird);

    boolean byBottom = hitboxPlayer.intersects(hitBox.getX() + xThird, hitBox.getY() + height, width - xThird, 1);
    boolean byTop = hitboxPlayer.intersects(hitBox.getX() + xThird, hitBox.getY(), width - xThird, 1);

    if (byRight && playerAction == RUNNING_LEFT)
      xSpeed = -boxSpeed;
    else if (byLeft && playerAction == RUNNING_RIGHT)
      xSpeed = boxSpeed;
    else if (byBottom && playerAction == IDLE_UP)
      ySpeed = -boxSpeed;
    else if (byTop && playerAction == IDLE_DOWN)
      ySpeed = boxSpeed;

    hitBox.x += xSpeed;
    hitBox.y += ySpeed;
  }
}
