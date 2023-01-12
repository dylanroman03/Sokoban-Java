package main;

import javax.swing.*;
import java.awt.Color;
import java.awt.*;

public class NewGameScreen extends JPanel {

  public NewGameScreen() {
    super();
    JLabel title = new JLabel("Welcome to Sokoban!");
    title.setFont(new Font("TimesRoman", Font.BOLD,14));
    title.setForeground(Color.BLUE);
    add(title);
  }
  
}
