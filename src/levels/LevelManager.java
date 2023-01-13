package levels;

import static utilities.Constants.PATH_BACKGROUND_LEVELS;
import static utilities.Constants.PATH_FLOOR_LEVELS;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.LoadSave;

public class LevelManager {
  private Game game;
  private BufferedImage levelBackground;
  private BufferedImage floorLevel;

  public LevelManager(Game game) {
    this.game = game;
    levelBackground = LoadSave.getImage(PATH_BACKGROUND_LEVELS);
    floorLevel = LoadSave.getImage(PATH_FLOOR_LEVELS);
  }

  public void render(Graphics g) {
    for (int j = 0; j < Game.TILES_HEIGTH; j++)
			for (int i = 0; i < Game.TILES_WIDTH; i++) {
				g.drawImage(floorLevel, Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}

    g.drawImage(levelBackground, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGTH, null);

    loadNivel();

  }

  private void loadNivel() {
    // Scanner scanner;

    // String path = PATH_FILE_LEVELS + 1 + ".txt";

    // try {
    //   InputStream is = new FileInputStream(path);
    //   scanner = new Scanner(is);
    //   int filas = scanner.nextInt();
    //   int columnas = scanner.nextInt();
    //   System.out.println(filas);
    //   System.out.println(columnas);

    //   int matriz[][] = new int[filas][columnas];
    //   System.out.println(scanner.nextInt());

    //   // for (int i = 0; i < filas; i++) {
    //   //   for (int j = 0; j < columnas; j++) {
    //   //     matriz[i][j] = scanner.nextInt();
    //   //   }
    //   // }

    //   scanner.close();

    // } catch (IOException e) {
    //   e.printStackTrace();
    //   System.out.println("Error reading");
    // }
  }

  public void update() {

  }

}
