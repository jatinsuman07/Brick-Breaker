package mypkg;

import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Game gamePanel = new Game(); // Create an instance of Game panel
        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Brick Breaker");
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePanel); // Add the Game panel to the frame
        obj.setVisible(true);
    }
}