package main;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuBar extends JMenuBar {

  public MenuBar() {
    JMenuItem item1 = new JMenuItem("Instructions");
    JMenuItem item2 = new JMenuItem("New Game");
    JMenuItem item3 = new JMenuItem("Summary");
    JMenuItem item4 = new JMenuItem("Exit");
    JMenu menu1 = new JMenu("Options");
    JMenu menu2 = new JMenu("Help");

    item4.addActionListener(e -> {
      int resp = JOptionPane.showConfirmDialog(new JFrame(), "Are You Sure", "Choose One Option",
          JOptionPane.YES_NO_OPTION);
      if (resp == JOptionPane.YES_OPTION) {
        System.exit(0);
      }
    });

    menu1.add(item2);
    menu1.add(item3);
    menu1.add(item4);
    menu2.add(item1);

    add(menu1);
    add(menu2);
  }

}
