// -- PaintBrush class -- //
import javax.swing.*;
import java.awt.*;

public class haha extends JFrame {

    public static boolean mouseMode = true;
    public static boolean dingus = true;

    public haha() {
        super("RayKeyster");

        // You can set the content pane of the frame to your custom class.
        setContentPane(new DrawPane());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        //ImageIcon img = new ImageIcon("C:\\Users\\Zach\\Desktop\\raycaster-20230526T192747Z-001\\raycaster\\src\\keys\\goldkey.png\\");
        //super.setIconImage(img.getImage());
        //fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck fuck  shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit shit 

        setVisible(true);

    repaint(0, 0, width, 900);


    }
    public static void disablemousemode(){
        mouseMode = false;
    }
    public static void enablemousemode(){
        mouseMode = true;
    }
    public static void togglemousemode(){
        mouseMode = !mouseMode;


    }
    public static void main(String[] args) throws AWTException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();
        //boolean mouseMode = false;
        new haha();
        //new player(75, -50, 0);
        player.setPos(75,-50);
        Robot PL = new Robot();


    while (mouseMode) {
        PL.mouseMove(450, 450);

        PL.delay(5);
        player.setDir((MouseInfo.getPointerInfo().getLocation().getX() - 450) / 100 + player.getDirection());



    }

}





    }




