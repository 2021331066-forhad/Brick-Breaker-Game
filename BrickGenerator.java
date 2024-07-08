
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BrickGenerator {
    public int brick[][];
    public int brickLength;
    public int brickHeigth;
    public BrickGenerator(int row,int col){
        brick= new int [row][col];
        for (int i=0; i<brick.length; i++){
            for (int j=0; j<brick[0].length;j++){
                brick[i][j]=1;
            }
        }
        brickLength=550/col;
        brickHeigth=150/row;
    
    }
    public void draw(Graphics2D g){
        for (int i=0; i<brick.length; i++){
            for (int j=0; j<brick[0].length;j++){
                if (brick[i][j] > 0){
                    g.setColor(Color.orange);
                    g.fillRect(j * brickLength + 80, i * brickHeigth + 50, brickLength, brickHeigth);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickLength + 80, i * brickHeigth + 50, brickLength, brickHeigth);
                }
            }
        }

    }
    public void setBrickValue(int value, int row, int col){
        brick[row][col]=value;
    }
    
}
