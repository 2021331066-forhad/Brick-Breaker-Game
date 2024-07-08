import javax.swing.JFrame;

public class Main {
    public static  void main (String [] args){
        JFrame bg=new JFrame();
        Gameplay gamePlay= new Gameplay();
        bg.setBounds(10,10,700,600);
        bg.setTitle("Brick Breaker");
        bg.setResizable(false);
        bg.setVisible(true);
        bg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bg.add(gamePlay);

        

    }
    
}
