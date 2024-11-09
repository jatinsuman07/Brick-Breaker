package mypkg;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements KeyListener, ActionListener{


    private boolean play = false;
    private int score = 0;

    private int totalBricks = 60;

    private Timer timer;
    private final int delay = 5;
    
    private int playerX = 310;
    private int playerY = 555;
    private int ballPositionX = 120;
    private int ballPositionY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private brick map;


    public Game(){
        map = new brick(4, 15);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        setVisible(true);
    }

    public void paint(Graphics g){
        // BG
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(1, 1, 692, 592);
        //repaint();

        // bricks 
        map.draw((Graphics2D)g);

        // border
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(683, 0, 3, 592);
        //repaint();
        //not making border in down side


        // timer
        g.setColor(Color.darkGray);
        g.setFont(new Font("sherif", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);


        // Padddle design
        g.setColor(Color.GREEN);
        g.fillRect(playerX, playerY, 100, 10);
        //repaint();

        // ball design
        g.setColor(Color.RED);
        g.fillOval(ballPositionX, ballPositionY, 20, 20);
        //repaint();

        // Game over message
        if(ballPositionY > 570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("sherif", Font.BOLD, 30));
            g.drawString("Game Over, Score: "+score, 190, 300);

            g.setFont(new Font("sherif", Font.BOLD, 20));
            g.drawString("Press 'Enter' to Restart", 230, 350);

        }
        // you won message
        if(totalBricks <= 0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.green);
            g.setFont(new Font("sherif", Font.BOLD, 30));
            g.drawString("YEAH, You Won!", 190, 300);

            g.setFont(new Font("sherif", Font.BOLD, 20));
            g.drawString("Press 'Enter' to Restart", 230, 350);
        }
        g.dispose();
    }
// FOR BALL MOVEMENT
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (play) {

            if(new Rectangle(ballPositionX,ballPositionY,20,20).intersects (new Rectangle(playerX,playerY,100,10))){
                ballYdir = -ballYdir;
            }

            // blocks
            A: for(int i = 0;i<map.map.length;i++){     //label A
                for(int j = 0; j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int brickX = j * map. brickWidth+80;
                        int brickY = i * map.brickHeight+50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRect = new Rectangle(ballPositionX,ballPositionY,20,20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0,i,j);
                            totalBricks--;
                            score+=5;

                        // for left and right hand side
                        if(ballPositionX+19 <= brickRect.x || ballPositionX+1 >= brickRect.x + brickRect.width){
                            ballXdir = -ballXdir;
                        }else{
                            ballYdir = -ballYdir;
                        }
                        break A;
                    }
                }
            }
        }

            ballPositionX += ballXdir;
            ballPositionY += ballYdir;
            if (ballPositionX<0) {
                ballXdir = -ballXdir;
            }
            if (ballPositionY<0) {
                ballYdir = -ballYdir;
            }
            if (ballPositionX>670) {
                ballXdir = -ballXdir;
            }

            /*  ball out of bounds
            if (ballPositionY > 570){
                play = false;
                ballXdir = 0;
                ballYdir = 0;
                //System.out.println("game over");
            }*/
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}
// FOR PADDLE MOVEMENT
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600){   // if it go above 600 it will equal it to 600
                playerX=600;
            }
            else{
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX <= 8){
                playerX = 8;
            }
            else{
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            if(playerY <= 300){   // if it go above 600 it will equal it to 600
                playerY=300;
            }
            else{
                moveUp();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            if(playerY >= 555){   // if it go above 600 it will equal it to 600
                playerY=555;
            }
            else{
                moveDown();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            play = true;
            playerX = 310;
            ballPositionX = 120;
            ballPositionY = 350;
            ballXdir = -1;
            ballYdir = -2;
            totalBricks = 60;
            score = 0;
            map = new brick(4, 15);
        }

    }
    public void moveRight(){
        play = true;
        playerX += 20;
    }
    public void moveLeft(){
        play = true;
        playerX -= 20;
    }
    public void moveUp(){
        play = true;
        playerY -= 8;
    }
    public void moveDown(){
        play = true;
        playerY += 8;
    }
     
}
