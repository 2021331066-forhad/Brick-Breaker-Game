import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
    private boolean play=false;
    private int score=0;
    private int totalBricks=24;
    private final Timer time;
    private final int delay=10;

    private int playerX=320;

    private int ballX=120;
    private int ballY=350;
    private int ballXdir=-1;
    private int ballYdir=-2;
    private BrickGenerator brick;

    public Gameplay(){
        brick = new BrickGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time=new Timer(delay,this);
        time.start();
    }

    @Override
    public void paint(Graphics g){
        //backgrounds
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        brick.draw((Graphics2D) g);
        //borders
        g.setColor(Color.orange);
        //left
        g.fillRect(0, 0, 5, 592);
        //Top
        g.fillRect(0, 0, 692, 6);
        //Right
        g.fillRect(691, 0, 3, 592);
        //g.fillRect(0, 691, 692, 3);

        //paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        //ball
        g.setColor(Color.yellow);
        g.fillOval(ballX, ballY, 20, 20);

        //scores
        g.setFont(new Font("serif",Font.BOLD,25));
        g.setColor(Color.white);
        g.drawString(""+score, 590, 30);

        if (ballY > 570){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over ! Scores :"+score, 190, 300);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press 'Enter' to restart  :", 190, 350);

        }
        if (totalBricks == 0){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You WOn !", 260, 300);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press 'Enter' to restart  :", 230, 350);

        }

        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        time.start();
        if (play){
        if (new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(playerX,550,100,8)))
        {
            ballYdir = -ballYdir;
        }
        for (int i=0; i<brick.brick.length; i++){
            for (int j=0; j<brick.brick[0].length; j++){
                if(brick.brick[i][j] > 0){
                    int brickX=j* brick.brickLength+80;
                    int brickY=i* brick.brickHeigth+50;
                    int brickLength=brick.brickLength;
                    int brickHeigth=brick.brickHeigth;

                    Rectangle rect= new Rectangle(brickX,brickY,brickLength,brickHeigth);
                    Rectangle ballRect= new Rectangle(ballX,ballY,20,20);
                    Rectangle brickRect= rect;

                    if (ballRect.intersects(brickRect)){
                        brick.setBrickValue(0, i, j);
                        totalBricks--;
                        score+=10;
                    
                    if (ballX + 10 <= brickRect.x || ballX + 1 >= brickRect.x + brickRect.width) {
                        ballXdir = -ballXdir;
                    } else {
                        ballYdir = -ballYdir;
                    }
                       break ; 


                }
            }
        }
    }
}
      
        
            ballX += ballXdir;
            ballY += ballYdir;
            if (ballX < 0)
            {
                ballXdir = -ballXdir;
            }
            if (ballY < 0)
            {
                ballYdir = -ballYdir;
            }
        
            if (ballX > 670)
            {
                ballXdir = -ballXdir;
            }                 
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent k){
            if (k.getKeyCode() == KeyEvent.VK_RIGHT){
                if (playerX >= 600){
                    playerX=600;
                }
                else {
                    moveRight();
                }
            }
            if (k.getKeyCode() == KeyEvent.VK_LEFT){
                if (playerX < 10){
                    playerX=10;
                }
            else {
                moveLeft();
            }   
            }
            if (k.getKeyCode() == KeyEvent.VK_ENTER){
                if (!play){
                    play=true;
                    ballX=120;
                    ballY=350;
                    ballXdir=-1;
                    ballYdir=-2;
                    playerX=310;
                    score=0;
                    totalBricks=24;
                    brick= new BrickGenerator(3,7);
                }
            }
             

}
    public void moveRight(){
        play=true;
        playerX+=30;
    }
    public void moveLeft(){
        play=true;
        playerX-=30;
    }


    }
