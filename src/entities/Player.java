package entities;

import static utilities.Constants.PATH_WARRIOR_LIST;
import static utilities.Constants.PlayerConstants.IDLE;
import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilities.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 10;
	private int playerAction = IDLE;
	private boolean moving = false;
	private boolean left, up, right, down;
	private float playerSpeed = 2.0f;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
	}

	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, width, height, null);
	}

	private void updateAnimationTick() {
		if (moving) {
			aniTick++;
			if (aniTick >= aniSpeed) {
				aniTick = 0;
				aniIndex++;
				if (aniIndex >= 14) {
					aniIndex = 0;
				}

			}
		} else {
			aniIndex = 0;
		}
	}

	private void setAnimation() {
		int startAni = playerAction;

		if (moving) {
			if (left)
				playerAction = RUNNING_LEFT;
			else if (right)
				playerAction = RUNNING_RIGHT;
			else
        playerAction = IDLE;
			
		} else {
			playerAction = IDLE;
		}

		if (startAni != playerAction)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		moving = false;

		if (left && !right) {
			x -= playerSpeed;
			moving = true;
		} else if (right && !left) {
			x += playerSpeed;
			moving = true;
		}

		if (up && !down) {
			y -= playerSpeed;
			moving = true;
		} else if (down && !up) {
			y += playerSpeed;
			moving = true;
		}
	}

	private void loadAnimations() {
		animations = new BufferedImage[3][14];

		for (int j = 0; j < animations.length; j++) {
			for (int i = 0; i < animations[j].length; i++) {
				String index = "0";

				if (i < 10) {
					index += index + i;
				} else {
					index += i;
				}

				animations[j][i] = LoadSave.getImage(PATH_WARRIOR_LIST[j] + "Run/0_Warrior_Run_" + index + ".png");
			}
		}
	}


	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
		this.up = this.down = false;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		left = right = false;
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		up = down = false;
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		left = right = false;
		this.down = down;
	}

  public void resetDirection() {
		left = false;
		right = false;
		down = false;
		up = false;
		moving = false;
  }

}
