// -- DrawPane class -- //

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;



public class DrawPane extends JPanel {
public static boolean portal = false;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int)screenSize.getWidth();
    int height = (int)screenSize.getHeight();

    public boolean imagesallowed = false;
   double screen = width;
   //double screen = 1800;
    double lastFrame = 0;
    double RIZZOLUTION =screen; //makes the room feel HUGE also makes it slower

    double strch = RIZZOLUTION/screen;
    public double fov   = 90;
    public double hfov  = fov/2;
    public double hrfov = Math.toRadians(fov/2);
    double riztodeg = RIZZOLUTION/fov;
    Image virtualMem;
    double abcd = 0;
    Graphics g;
    public String[] keynames = {"blankey","bluekey","tealkey","copperkey","glitchkey","goldkey", "greenkey", "hellkey", "oldkey", "specialkey", "stonekey", "stonekey2", "stonekey3", "superspecialkey","portalkey"};
    private static final double lvl3scale = 1.5;
    public static Line2D.Double[] mapthing = new Line2D.Double[]{ new Line2D.Double(0, 0, 0, -50), new Line2D.Double(50, -50, 50, 0), new Line2D.Double(40, -50, 50, -50), new Line2D.Double(0, -50, 10, -50), new Line2D.Double(10, 0, 0, 0), new Line2D.Double(50, 0, 40, 0), new Line2D.Double(0, 0, 0, 0), new Line2D.Double(0, -50, 0, -100), new Line2D.Double(0, -100, 100, -100), new Line2D.Double(100, -100, 100, 0), new Line2D.Double(50, 0, 100, 0), new Line2D.Double(30, 0, 60, 0), new Line2D.Double(60, 0, 60, 60), new Line2D.Double(60, 60, 40, 60), new Line2D.Double(40, 40, 40, 60), new Line2D.Double(20, 0, 0, 0), new Line2D.Double(50, 50, 30, 50), new Line2D.Double(30, 60, 0, 60), new Line2D.Double(0, 60, 0, 0), new Line2D.Double(10, 10, 20, 10), new Line2D.Double(40, 0, 40, 20), new Line2D.Double(40, 10, 50, 10), new Line2D.Double(20, 10, 20, 20), new Line2D.Double(0, 20, 20, 20), new Line2D.Double(30, 10, 30, 40), new Line2D.Double(10, 30, 50, 30), new Line2D.Double(0, 40, 30, 40), new Line2D.Double(20, 40, 20, 50), new Line2D.Double(10, 50, 10, 60), new Line2D.Double(50, 20, 60, 20), new Line2D.Double(50, 40, 60, 40)};
    public static Line2D.Double[] lev2 = {new Line2D.Double(30,0,60,0),new Line2D.Double(60,0,60,60),new Line2D.Double(60,60,40,60),new Line2D.Double(40,40,40,60),new Line2D.Double(20,0,0,0),new Line2D.Double(50,50,30,50),new Line2D.Double(30,60,0,60),new Line2D.Double(0,60,0,0),new Line2D.Double(10,10,20,10),new Line2D.Double(40,0,40,20),new Line2D.Double(40,10,50,10),new Line2D.Double(20,10,20,20),new Line2D.Double(0,20,20,20),new Line2D.Double(30,10,30,40),new Line2D.Double(10,30,50,30),new Line2D.Double(0,40,30,40),new Line2D.Double(20,40,20,50),new Line2D.Double(10,50,10,60),new Line2D.Double(50,20,60,20),new Line2D.Double(50,40,60,40),};
    public static Line2D.Double[] lev3 = {new Line2D.Double(2/lvl3scale,2/lvl3scale,146/lvl3scale,2/lvl3scale),  new Line2D.Double(162/lvl3scale,2/lvl3scale,322/lvl3scale,2/lvl3scale),  new Line2D.Double(18/lvl3scale,18/lvl3scale,34/lvl3scale,18/lvl3scale),   new Line2D.Double(50/lvl3scale,18/lvl3scale,114/lvl3scale,18/lvl3scale),/*continuity*/new Line2D.Double(130/lvl3scale,18/lvl3scale,146/lvl3scale,18/lvl3scale),   new Line2D.Double(178/lvl3scale,18/lvl3scale,194/lvl3scale,18/lvl3scale),   new Line2D.Double(226/lvl3scale,18/lvl3scale,242/lvl3scale,18/lvl3scale),   new Line2D.Double(34/lvl3scale,34/lvl3scale,50/lvl3scale,34/lvl3scale),   new Line2D.Double(114/lvl3scale,34/lvl3scale,162/lvl3scale,34/lvl3scale),   new Line2D.Double(194/lvl3scale,34/lvl3scale,226/lvl3scale,34/lvl3scale),   new Line2D.Double(258/lvl3scale,34/lvl3scale,290/lvl3scale,34/lvl3scale),   new Line2D.Double(18/lvl3scale,50/lvl3scale,50/lvl3scale,50/lvl3scale),   new Line2D.Double(66/lvl3scale,50/lvl3scale,82/lvl3scale,50/lvl3scale),   new Line2D.Double(98/lvl3scale,50/lvl3scale,146/lvl3scale,50/lvl3scale),   new Line2D.Double(178/lvl3scale,50/lvl3scale,242/lvl3scale,50/lvl3scale),   new Line2D.Double(290/lvl3scale,50/lvl3scale,306/lvl3scale,50/lvl3scale),   new Line2D.Double(2/lvl3scale,66/lvl3scale,18/lvl3scale,66/lvl3scale),   new Line2D.Double(34/lvl3scale,66/lvl3scale,98/lvl3scale,66/lvl3scale),   new Line2D.Double(114/lvl3scale,66/lvl3scale,130/lvl3scale,66/lvl3scale),   new Line2D.Double(162/lvl3scale,66/lvl3scale,226/lvl3scale,66/lvl3scale),   new Line2D.Double(242/lvl3scale,66/lvl3scale,274/lvl3scale,66/lvl3scale),   new Line2D.Double(306/lvl3scale,66/lvl3scale,322/lvl3scale,66/lvl3scale),   new Line2D.Double(18/lvl3scale,82/lvl3scale,34/lvl3scale,82/lvl3scale),   new Line2D.Double(50/lvl3scale,82/lvl3scale,82/lvl3scale,82/lvl3scale),   new Line2D.Double(98/lvl3scale,82/lvl3scale,114/lvl3scale,82/lvl3scale),   new Line2D.Double(130/lvl3scale,82/lvl3scale,162/lvl3scale,82/lvl3scale),   new Line2D.Double(194/lvl3scale,82/lvl3scale,210/lvl3scale,82/lvl3scale),    new Line2D.Double(226/lvl3scale,82/lvl3scale,258/lvl3scale,82/lvl3scale),   new Line2D.Double(274/lvl3scale,82/lvl3scale,306/lvl3scale,82/lvl3scale),   new Line2D.Double(2/lvl3scale,98/lvl3scale,18/lvl3scale,98/lvl3scale),   new Line2D.Double(50/lvl3scale,98/lvl3scale,66/lvl3scale,98/lvl3scale),   new Line2D.Double(98/lvl3scale,98/lvl3scale,130/lvl3scale,98/lvl3scale),   new Line2D.Double(162/lvl3scale,98/lvl3scale,178/lvl3scale,98/lvl3scale),   new Line2D.Double(210/lvl3scale,98/lvl3scale,226/lvl3scale,98/lvl3scale),   new Line2D.Double(242/lvl3scale,98/lvl3scale,274/lvl3scale,98/lvl3scale),   new Line2D.Double(306/lvl3scale,98/lvl3scale,322/lvl3scale,98/lvl3scale),   new Line2D.Double(34/lvl3scale,114/lvl3scale,50/lvl3scale,114/lvl3scale),   new Line2D.Double(114/lvl3scale,114/lvl3scale,194/lvl3scale,114/lvl3scale),   new Line2D.Double(210/lvl3scale,114/lvl3scale,226/lvl3scale,114/lvl3scale),   new Line2D.Double(290/lvl3scale,114/lvl3scale,306/lvl3scale,114/lvl3scale),   new Line2D.Double(18/lvl3scale,130/lvl3scale,34/lvl3scale,130/lvl3scale),   new Line2D.Double(66/lvl3scale,130/lvl3scale,98/lvl3scale,130/lvl3scale),   new Line2D.Double(242/lvl3scale,130/lvl3scale,274/lvl3scale,130/lvl3scale),   new Line2D.Double(34/lvl3scale,146/lvl3scale,114/lvl3scale,146/lvl3scale),   new Line2D.Double(194/lvl3scale,146/lvl3scale,242/lvl3scale,146/lvl3scale),   new Line2D.Double(274/lvl3scale,146/lvl3scale,290/lvl3scale,146/lvl3scale),   new Line2D.Double(50/lvl3scale,162/lvl3scale,98/lvl3scale,162/lvl3scale),   new Line2D.Double(194/lvl3scale,162/lvl3scale,242/lvl3scale,162/lvl3scale),   new Line2D.Double(290/lvl3scale,162/lvl3scale,306/lvl3scale,162/lvl3scale),   new Line2D.Double(18/lvl3scale,178/lvl3scale,50/lvl3scale,178/lvl3scale),   new Line2D.Double(66/lvl3scale,178/lvl3scale,98/lvl3scale,178/lvl3scale),   new Line2D.Double(210/lvl3scale,178/lvl3scale,226/lvl3scale,178/lvl3scale),   new Line2D.Double(290/lvl3scale,178/lvl3scale,306/lvl3scale,178/lvl3scale),   new Line2D.Double(34/lvl3scale,194/lvl3scale,98/lvl3scale,194/lvl3scale),   new Line2D.Double(114/lvl3scale,194/lvl3scale,146/lvl3scale,194/lvl3scale),   new Line2D.Double(162/lvl3scale,194/lvl3scale,210/lvl3scale,194/lvl3scale),   new Line2D.Double(242/lvl3scale,194/lvl3scale,258/lvl3scale,194/lvl3scale),   new Line2D.Double(274/lvl3scale,194/lvl3scale,290/lvl3scale,194/lvl3scale),   new Line2D.Double(18/lvl3scale,210/lvl3scale,34/lvl3scale,210/lvl3scale),   new Line2D.Double(50/lvl3scale,210/lvl3scale,130/lvl3scale,210/lvl3scale),   new Line2D.Double(146/lvl3scale,210/lvl3scale,242/lvl3scale,210/lvl3scale),    new Line2D.Double(258/lvl3scale,210/lvl3scale,274/lvl3scale,210/lvl3scale),   new Line2D.Double(290/lvl3scale,210/lvl3scale,306/lvl3scale,210/lvl3scale),   new Line2D.Double(34/lvl3scale,226/lvl3scale,50/lvl3scale,226/lvl3scale),   new Line2D.Double(66/lvl3scale,226/lvl3scale,98/lvl3scale,226/lvl3scale),   new Line2D.Double(114/lvl3scale,226/lvl3scale,162/lvl3scale,226/lvl3scale),   new Line2D.Double(226/lvl3scale,226/lvl3scale,290/lvl3scale,226/lvl3scale),   new Line2D.Double(2/lvl3scale,242/lvl3scale,34/lvl3scale,242/lvl3scale),   new Line2D.Double(98/lvl3scale,242/lvl3scale,114/lvl3scale,242/lvl3scale),   new Line2D.Double(210/lvl3scale,242/lvl3scale,290/lvl3scale,242/lvl3scale),   new Line2D.Double(34/lvl3scale,258/lvl3scale,82/lvl3scale,258/lvl3scale),   new Line2D.Double(114/lvl3scale,258/lvl3scale,130/lvl3scale,258/lvl3scale),   new Line2D.Double(146/lvl3scale,258/lvl3scale,162/lvl3scale,258/lvl3scale),   new Line2D.Double(194/lvl3scale,258/lvl3scale,210/lvl3scale,258/lvl3scale),   new Line2D.Double(258/lvl3scale,258/lvl3scale,322/lvl3scale,258/lvl3scale),   new Line2D.Double(18/lvl3scale,274/lvl3scale,34/lvl3scale,274/lvl3scale),   new Line2D.Double(50/lvl3scale,274/lvl3scale,66/lvl3scale,274/lvl3scale),   new Line2D.Double(82/lvl3scale,274/lvl3scale,98/lvl3scale,274/lvl3scale),   new Line2D.Double(146/lvl3scale,274/lvl3scale,178/lvl3scale,274/lvl3scale),   new Line2D.Double(210/lvl3scale,274/lvl3scale,258/lvl3scale,274/lvl3scale),   new Line2D.Double(290/lvl3scale,274/lvl3scale,306/lvl3scale,274/lvl3scale),   new Line2D.Double(2/lvl3scale,290/lvl3scale,18/lvl3scale,290/lvl3scale),   new Line2D.Double(34/lvl3scale,290/lvl3scale,66/lvl3scale,290/lvl3scale),   new Line2D.Double(82/lvl3scale,290/lvl3scale,98/lvl3scale,290/lvl3scale),   new Line2D.Double(114/lvl3scale,290/lvl3scale,146/lvl3scale,290/lvl3scale),   new Line2D.Double(162/lvl3scale,290/lvl3scale,194/lvl3scale,290/lvl3scale),   new Line2D.Double(242/lvl3scale,290/lvl3scale,274/lvl3scale,290/lvl3scale),   new Line2D.Double(306/lvl3scale,290/lvl3scale,322/lvl3scale,290/lvl3scale),   new Line2D.Double(18/lvl3scale,306/lvl3scale,50/lvl3scale,306/lvl3scale),  new Line2D.Double(130/lvl3scale,306/lvl3scale,162/lvl3scale,306/lvl3scale),  new Line2D.Double(194/lvl3scale,306/lvl3scale,242/lvl3scale,306/lvl3scale),   new Line2D.Double(274/lvl3scale,306/lvl3scale,306/lvl3scale,306/lvl3scale),   new Line2D.Double(2/lvl3scale,322/lvl3scale,322/lvl3scale,322/lvl3scale),   new Line2D.Double(2/lvl3scale,2/lvl3scale,2/lvl3scale,322/lvl3scale),   new Line2D.Double(18/lvl3scale,34/lvl3scale,18/lvl3scale,50/lvl3scale),   new Line2D.Double(18/lvl3scale,114/lvl3scale,18/lvl3scale,194/lvl3scale),   new Line2D.Double(18/lvl3scale,210/lvl3scale,18/lvl3scale,242/lvl3scale),   new Line2D.Double(18/lvl3scale,258/lvl3scale,18/lvl3scale,274/lvl3scale),   new Line2D.Double(34/lvl3scale,2/lvl3scale,34/lvl3scale,34/lvl3scale),   new Line2D.Double(34/lvl3scale,66/lvl3scale,34/lvl3scale,114/lvl3scale),   new Line2D.Double(34/lvl3scale,146/lvl3scale,34/lvl3scale,162/lvl3scale),   new Line2D.Double(34/lvl3scale,194/lvl3scale,34/lvl3scale,210/lvl3scale),   new Line2D.Double(34/lvl3scale,242/lvl3scale,34/lvl3scale,258/lvl3scale),   new Line2D.Double(34/lvl3scale,274/lvl3scale,34/lvl3scale,306/lvl3scale),   new Line2D.Double(50/lvl3scale,50/lvl3scale,50/lvl3scale,66/lvl3scale),   new Line2D.Double(50/lvl3scale,82/lvl3scale,50/lvl3scale,98/lvl3scale),   new Line2D.Double(50/lvl3scale,114/lvl3scale,50/lvl3scale,146/lvl3scale),   new Line2D.Double(50/lvl3scale,162/lvl3scale,50/lvl3scale,194/lvl3scale),   new Line2D.Double(50/lvl3scale,210/lvl3scale,50/lvl3scale,242/lvl3scale),   new Line2D.Double(50/lvl3scale,258/lvl3scale,50/lvl3scale,274/lvl3scale),   new Line2D.Double(66/lvl3scale,18/lvl3scale,66/lvl3scale,50/lvl3scale),   new Line2D.Double(66/lvl3scale,98/lvl3scale,66/lvl3scale,130/lvl3scale),   new Line2D.Double(66/lvl3scale,226/lvl3scale,66/lvl3scale,258/lvl3scale),   new Line2D.Double(66/lvl3scale,290/lvl3scale,66/lvl3scale,322/lvl3scale),   new Line2D.Double(82/lvl3scale,18/lvl3scale,82/lvl3scale,34/lvl3scale),   new Line2D.Double(82/lvl3scale,82/lvl3scale,82/lvl3scale,114/lvl3scale),   new Line2D.Double(82/lvl3scale,242/lvl3scale,82/lvl3scale,274/lvl3scale),   new Line2D.Double(82/lvl3scale,290/lvl3scale,82/lvl3scale,306/lvl3scale),   new Line2D.Double(98/lvl3scale,34/lvl3scale,98/lvl3scale,130/lvl3scale),   new Line2D.Double(98/lvl3scale,162/lvl3scale,98/lvl3scale,178/lvl3scale),   new Line2D.Double(98/lvl3scale,226/lvl3scale,98/lvl3scale,258/lvl3scale),   new Line2D.Double(98/lvl3scale,274/lvl3scale,98/lvl3scale,290/lvl3scale),   new Line2D.Double(98/lvl3scale,306/lvl3scale,98/lvl3scale,322/lvl3scale),   new Line2D.Double(114/lvl3scale,18/lvl3scale,114/lvl3scale,34/lvl3scale),    new Line2D.Double(114/lvl3scale,114/lvl3scale,114/lvl3scale,210/lvl3scale),   new Line2D.Double(114/lvl3scale,274/lvl3scale,114/lvl3scale,306/lvl3scale),   new Line2D.Double(130/lvl3scale,2/lvl3scale,130/lvl3scale,18/lvl3scale),   new Line2D.Double(130/lvl3scale,66/lvl3scale,130/lvl3scale,82/lvl3scale),   new Line2D.Double(130/lvl3scale,226/lvl3scale,130/lvl3scale,274/lvl3scale),   new Line2D.Double(146/lvl3scale,50/lvl3scale,146/lvl3scale,66/lvl3scale),   new Line2D.Double(146/lvl3scale,82/lvl3scale,146/lvl3scale,114/lvl3scale),   new Line2D.Double(146/lvl3scale,194/lvl3scale,146/lvl3scale,226/lvl3scale),   new Line2D.Double(146/lvl3scale,242/lvl3scale,146/lvl3scale,290/lvl3scale),   new Line2D.Double(162/lvl3scale,2/lvl3scale,162/lvl3scale,66/lvl3scale),   new Line2D.Double(162/lvl3scale,226/lvl3scale,162/lvl3scale,242/lvl3scale),   new Line2D.Double(162/lvl3scale,290/lvl3scale,162/lvl3scale,306/lvl3scale),   new Line2D.Double(178/lvl3scale,18/lvl3scale,178/lvl3scale,50/lvl3scale),   new Line2D.Double(178/lvl3scale,66/lvl3scale,178/lvl3scale,98/lvl3scale),   new Line2D.Double(178/lvl3scale,210/lvl3scale,178/lvl3scale,274/lvl3scale),   new Line2D.Double(178/lvl3scale,306/lvl3scale,178/lvl3scale,322/lvl3scale),   new Line2D.Double(194/lvl3scale,98/lvl3scale,194/lvl3scale,194/lvl3scale),   new Line2D.Double(194/lvl3scale,226/lvl3scale,194/lvl3scale,306/lvl3scale),   new Line2D.Double(210/lvl3scale,2/lvl3scale,210/lvl3scale,34/lvl3scale),   new Line2D.Double(210/lvl3scale,66/lvl3scale,210/lvl3scale,82/lvl3scale),   new Line2D.Double(210/lvl3scale,114/lvl3scale,210/lvl3scale,130/lvl3scale),   new Line2D.Double(210/lvl3scale,210/lvl3scale,210/lvl3scale,258/lvl3scale),   new Line2D.Double(210/lvl3scale,274/lvl3scale,210/lvl3scale,290/lvl3scale),   new Line2D.Double(226/lvl3scale,82/lvl3scale,226/lvl3scale,114/lvl3scale),   new Line2D.Double(226/lvl3scale,130/lvl3scale,226/lvl3scale,146/lvl3scale),   new Line2D.Double(226/lvl3scale,178/lvl3scale,226/lvl3scale,210/lvl3scale),   new Line2D.Double(226/lvl3scale,258/lvl3scale,226/lvl3scale,274/lvl3scale),   new Line2D.Double(226/lvl3scale,290/lvl3scale,226/lvl3scale,306/lvl3scale),   new Line2D.Double(242/lvl3scale,18/lvl3scale,242/lvl3scale,66/lvl3scale),   new Line2D.Double(242/lvl3scale,114/lvl3scale,242/lvl3scale,130/lvl3scale),   new Line2D.Double(242/lvl3scale,162/lvl3scale,242/lvl3scale,194/lvl3scale),   new Line2D.Double(242/lvl3scale,210/lvl3scale,242/lvl3scale,226/lvl3scale),   new Line2D.Double(242/lvl3scale,242/lvl3scale,242/lvl3scale,258/lvl3scale),   new Line2D.Double(258/lvl3scale,2/lvl3scale,258/lvl3scale,18/lvl3scale),   new Line2D.Double(258/lvl3scale,34/lvl3scale,258/lvl3scale,50/lvl3scale),   new Line2D.Double(258/lvl3scale,82/lvl3scale,258/lvl3scale,114/lvl3scale),   new Line2D.Double(258/lvl3scale,130/lvl3scale,258/lvl3scale,210/lvl3scale),   new Line2D.Double(258/lvl3scale,258/lvl3scale,258/lvl3scale,274/lvl3scale),   new Line2D.Double(258/lvl3scale,290/lvl3scale,258/lvl3scale,322/lvl3scale),   new Line2D.Double(274/lvl3scale,18/lvl3scale,274/lvl3scale,34/lvl3scale),   new Line2D.Double(274/lvl3scale,50/lvl3scale,274/lvl3scale,82/lvl3scale),   new Line2D.Double(274/lvl3scale,98/lvl3scale,274/lvl3scale,130/lvl3scale),   new Line2D.Double(274/lvl3scale,146/lvl3scale,274/lvl3scale,210/lvl3scale),   new Line2D.Double(274/lvl3scale,274/lvl3scale,274/lvl3scale,290/lvl3scale),   new Line2D.Double(290/lvl3scale,2/lvl3scale,290/lvl3scale,66/lvl3scale),   new Line2D.Double(290/lvl3scale,82/lvl3scale,290/lvl3scale,114/lvl3scale),   new Line2D.Double(290/lvl3scale,130/lvl3scale,290/lvl3scale,146/lvl3scale),   new Line2D.Double(290/lvl3scale,162/lvl3scale,290/lvl3scale,178/lvl3scale),   new Line2D.Double(290/lvl3scale,210/lvl3scale,290/lvl3scale,226/lvl3scale),   new Line2D.Double(290/lvl3scale,258/lvl3scale,290/lvl3scale,306/lvl3scale),   new Line2D.Double(306/lvl3scale,18/lvl3scale,306/lvl3scale,50/lvl3scale),   new Line2D.Double(306/lvl3scale,66/lvl3scale,306/lvl3scale,82/lvl3scale),   new Line2D.Double(306/lvl3scale,114/lvl3scale,306/lvl3scale,162/lvl3scale),   new Line2D.Double(306/lvl3scale,178/lvl3scale,306/lvl3scale,210/lvl3scale),   new Line2D.Double(306/lvl3scale,226/lvl3scale,306/lvl3scale,258/lvl3scale),   new Line2D.Double(322/lvl3scale,2/lvl3scale,322/lvl3scale,322/lvl3scale)};
    public static Line2D.Double[] lev4 = {new Line2D.Double(1.0,1.0,73.0,1.0), new Line2D.Double(81.0,1.0,161.0,1.0), new Line2D.Double(9.0,9.0,17.0,9.0), new Line2D.Double(41.0,9.0,65.0,9.0), new Line2D.Double(89.0,9.0,121.0,9.0), new Line2D.Double(137.0,9.0,153.0,9.0), new Line2D.Double(1.0,17.0,17.0,17.0), new Line2D.Double(25.0,17.0,73.0,17.0), new Line2D.Double(97.0,17.0,105.0,17.0), new Line2D.Double(129.0,17.0,137.0,17.0), new Line2D.Double(145.0,17.0,161.0,17.0), new Line2D.Double(17.0,25.0,73.0,25.0), new Line2D.Double(81.0,25.0,89.0,25.0), new Line2D.Double(97.0,25.0,113.0,25.0), new Line2D.Double(137.0,25.0,153.0,25.0), new Line2D.Double(1.0,33.0,9.0,33.0), new Line2D.Double(41.0,33.0,65.0,33.0), new Line2D.Double(81.0,33.0,105.0,33.0), new Line2D.Double(113.0,33.0,121.0,33.0), new Line2D.Double(129.0,33.0,137.0,33.0), new Line2D.Double(9.0,41.0,17.0,41.0), new Line2D.Double(25.0,41.0,49.0,41.0), new Line2D.Double(73.0,41.0,81.0,41.0), new Line2D.Double(121.0,41.0,129.0,41.0), new Line2D.Double(145.0,41.0,153.0,41.0), new Line2D.Double(9.0,49.0,25.0,49.0), new Line2D.Double(33.0,49.0,41.0,49.0), new Line2D.Double(49.0,49.0,57.0,49.0), new Line2D.Double(81.0,49.0,113.0,49.0), new Line2D.Double(137.0,49.0,153.0,49.0), new Line2D.Double(1.0,57.0,25.0,57.0), new Line2D.Double(41.0,57.0,49.0,57.0), new Line2D.Double(65.0,57.0,73.0,57.0), new Line2D.Double(81.0,57.0,105.0,57.0), new Line2D.Double(145.0,57.0,153.0,57.0), new Line2D.Double(49.0,65.0,73.0,65.0), new Line2D.Double(81.0,65.0,89.0,65.0), new Line2D.Double(105.0,65.0,113.0,65.0), new Line2D.Double(121.0,65.0,129.0,65.0), new Line2D.Double(145.0,65.0,161.0,65.0), new Line2D.Double(25.0,73.0,49.0,73.0), new Line2D.Double(57.0,73.0,65.0,73.0), new Line2D.Double(81.0,73.0,105.0,73.0), new Line2D.Double(129.0,73.0,153.0,73.0), new Line2D.Double(1.0,81.0,17.0,81.0), new Line2D.Double(25.0,81.0,33.0,81.0), new Line2D.Double(57.0,81.0,81.0,81.0), new Line2D.Double(89.0,81.0,97.0,81.0), new Line2D.Double(105.0,81.0,129.0,81.0), new Line2D.Double(9.0,89.0,17.0,89.0), new Line2D.Double(33.0,89.0,41.0,89.0), new Line2D.Double(49.0,89.0,73.0,89.0), new Line2D.Double(81.0,89.0,89.0,89.0), new Line2D.Double(97.0,89.0,105.0,89.0), new Line2D.Double(113.0,89.0,121.0,89.0), new Line2D.Double(129.0,89.0,153.0,89.0), new Line2D.Double(17.0,97.0,33.0,97.0), new Line2D.Double(57.0,97.0,81.0,97.0), new Line2D.Double(89.0,97.0,129.0,97.0), new Line2D.Double(153.0,97.0,161.0,97.0), new Line2D.Double(9.0,105.0,17.0,105.0), new Line2D.Double(25.0,105.0,41.0,105.0), new Line2D.Double(97.0,105.0,121.0,105.0), new Line2D.Double(137.0,105.0,153.0,105.0), new Line2D.Double(1.0,113.0,9.0,113.0), new Line2D.Double(25.0,113.0,33.0,113.0), new Line2D.Double(41.0,113.0,57.0,113.0), new Line2D.Double(65.0,113.0,97.0,113.0), new Line2D.Double(113.0,113.0,121.0,113.0), new Line2D.Double(145.0,113.0,161.0,113.0), new Line2D.Double(9.0,121.0,41.0,121.0), new Line2D.Double(57.0,121.0,65.0,121.0), new Line2D.Double(81.0,121.0,89.0,121.0), new Line2D.Double(121.0,121.0,153.0,121.0), new Line2D.Double(17.0,129.0,65.0,129.0), new Line2D.Double(73.0,129.0,81.0,129.0), new Line2D.Double(89.0,129.0,105.0,129.0), new Line2D.Double(121.0,129.0,129.0,129.0), new Line2D.Double(145.0,129.0,161.0,129.0), new Line2D.Double(1.0,137.0,9.0,137.0), new Line2D.Double(17.0,137.0,49.0,137.0), new Line2D.Double(65.0,137.0,73.0,137.0), new Line2D.Double(89.0,137.0,105.0,137.0), new Line2D.Double(113.0,137.0,153.0,137.0), new Line2D.Double(9.0,145.0,17.0,145.0), new Line2D.Double(41.0,145.0,49.0,145.0), new Line2D.Double(57.0,145.0,73.0,145.0), new Line2D.Double(81.0,145.0,97.0,145.0), new Line2D.Double(105.0,145.0,121.0,145.0), new Line2D.Double(153.0,145.0,161.0,145.0), new Line2D.Double(17.0,153.0,25.0,153.0), new Line2D.Double(33.0,153.0,41.0,153.0), new Line2D.Double(49.0,153.0,73.0,153.0), new Line2D.Double(81.0,153.0,97.0,153.0), new Line2D.Double(137.0,153.0,153.0,153.0), new Line2D.Double(1.0,161.0,81.0,161.0), new Line2D.Double(89.0,161.0,161.0,161.0), new Line2D.Double(1.0,1.0,1.0,161.0), new Line2D.Double(9.0,17.0,9.0,25.0), new Line2D.Double(9.0,41.0,9.0,49.0), new Line2D.Double(9.0,57.0,9.0,73.0), new Line2D.Double(9.0,89.0,9.0,105.0), new Line2D.Double(9.0,121.0,9.0,137.0), new Line2D.Double(9.0,145.0,9.0,153.0), new Line2D.Double(17.0,1.0,17.0,9.0), new Line2D.Double(17.0,25.0,17.0,41.0), new Line2D.Double(17.0,65.0,17.0,81.0), new Line2D.Double(17.0,105.0,17.0,121.0), new Line2D.Double(17.0,137.0,17.0,145.0), new Line2D.Double(17.0,153.0,17.0,161.0), new Line2D.Double(25.0,9.0,25.0,17.0), new Line2D.Double(25.0,33.0,25.0,41.0), new Line2D.Double(25.0,57.0,25.0,97.0), new Line2D.Double(25.0,145.0,25.0,153.0), new Line2D.Double(33.0,1.0,33.0,9.0), new Line2D.Double(33.0,25.0,33.0,41.0), new Line2D.Double(33.0,49.0,33.0,65.0), new Line2D.Double(33.0,113.0,33.0,121.0), new Line2D.Double(33.0,137.0,33.0,161.0), new Line2D.Double(41.0,49.0,41.0,65.0), new Line2D.Double(41.0,81.0,41.0,113.0), new Line2D.Double(41.0,121.0,41.0,129.0), new Line2D.Double(49.0,41.0,49.0,49.0), new Line2D.Double(49.0,57.0,49.0,105.0), new Line2D.Double(49.0,113.0,49.0,121.0), new Line2D.Double(49.0,137.0,49.0,153.0), new Line2D.Double(57.0,33.0,57.0,41.0), new Line2D.Double(57.0,49.0,57.0,57.0), new Line2D.Double(57.0,73.0,57.0,81.0), new Line2D.Double(57.0,97.0,57.0,121.0), new Line2D.Double(57.0,129.0,57.0,137.0), new Line2D.Double(65.0,1.0,65.0,9.0), new Line2D.Double(65.0,33.0,65.0,65.0), new Line2D.Double(65.0,105.0,65.0,113.0), new Line2D.Double(65.0,129.0,65.0,145.0), new Line2D.Double(73.0,9.0,73.0,49.0), new Line2D.Double(73.0,65.0,73.0,73.0), new Line2D.Double(73.0,97.0,73.0,105.0), new Line2D.Double(73.0,113.0,73.0,129.0), new Line2D.Double(81.0,1.0,81.0,17.0), new Line2D.Double(81.0,33.0,81.0,41.0), new Line2D.Double(81.0,57.0,81.0,65.0), new Line2D.Double(81.0,73.0,81.0,81.0), new Line2D.Double(81.0,89.0,81.0,97.0), new Line2D.Double(81.0,105.0,81.0,113.0), new Line2D.Double(81.0,129.0,81.0,145.0), new Line2D.Double(81.0,153.0,81.0,161.0), new Line2D.Double(89.0,9.0,89.0,33.0), new Line2D.Double(89.0,41.0,89.0,49.0), new Line2D.Double(89.0,81.0,89.0,113.0), new Line2D.Double(89.0,121.0,89.0,137.0), new Line2D.Double(97.0,17.0,97.0,25.0), new Line2D.Double(97.0,41.0,97.0,49.0), new Line2D.Double(97.0,57.0,97.0,73.0), new Line2D.Double(97.0,113.0,97.0,121.0), new Line2D.Double(97.0,145.0,97.0,153.0), new Line2D.Double(105.0,33.0,105.0,41.0), new Line2D.Double(105.0,73.0,105.0,97.0), new Line2D.Double(105.0,105.0,105.0,129.0), new Line2D.Double(105.0,137.0,105.0,161.0), new Line2D.Double(113.0,17.0,113.0,73.0), new Line2D.Double(113.0,113.0,113.0,137.0), new Line2D.Double(113.0,153.0,113.0,161.0), new Line2D.Double(121.0,1.0,121.0,33.0), new Line2D.Double(121.0,41.0,121.0,73.0), new Line2D.Double(121.0,89.0,121.0,97.0), new Line2D.Double(121.0,105.0,121.0,113.0), new Line2D.Double(121.0,121.0,121.0,129.0), new Line2D.Double(121.0,145.0,121.0,153.0), new Line2D.Double(129.0,9.0,129.0,17.0), new Line2D.Double(129.0,25.0,129.0,57.0), new Line2D.Double(129.0,81.0,129.0,89.0), new Line2D.Double(129.0,97.0,129.0,121.0), new Line2D.Double(129.0,137.0,129.0,153.0), new Line2D.Double(137.0,9.0,137.0,25.0), new Line2D.Double(137.0,33.0,137.0,81.0), new Line2D.Double(137.0,89.0,137.0,113.0), new Line2D.Double(137.0,129.0,137.0,137.0), new Line2D.Double(137.0,145.0,137.0,153.0), new Line2D.Double(145.0,25.0,145.0,33.0), new Line2D.Double(145.0,57.0,145.0,65.0), new Line2D.Double(145.0,81.0,145.0,89.0), new Line2D.Double(145.0,97.0,145.0,105.0), new Line2D.Double(145.0,137.0,145.0,145.0), new Line2D.Double(153.0,25.0,153.0,49.0), new Line2D.Double(153.0,73.0,153.0,89.0), new Line2D.Double(153.0,145.0,153.0,153.0), new Line2D.Double(161.0,1.0,161.0,161.0)};
    public static Line2D.Double[] lev5 = {new Line2D.Double(200.0,40.10511776651529,222.0,40.10511776651529), new Line2D.Double(211.0,97.26279441628824,233.0,97.26279441628824), new Line2D.Double(200.0,116.31535329954589,222.0,116.31535329954589), new Line2D.Double(244.0,116.31535329954589,266.0,116.31535329954589), new Line2D.Double(189.0,135.36791218280354,211.0,135.36791218280354), new Line2D.Double(233.0,135.36791218280354,277.0,135.36791218280354), new Line2D.Double(222.0,154.4204710660612,244.0,154.4204710660612), new Line2D.Double(123.0,173.47302994931883,189.0,173.47302994931883), new Line2D.Double(255.0,173.47302994931883,277.0,173.47302994931883), new Line2D.Double(101.0,211.57814771583415,123.0,211.57814771583415), new Line2D.Double(233.0,211.57814771583415,255.0,211.57814771583415), new Line2D.Double(277.0,211.57814771583415,299.0,211.57814771583415), new Line2D.Double(266.0,230.63070659909178,288.0,230.63070659909178), new Line2D.Double(68.0,268.7358243656071,90.0,268.7358243656071), new Line2D.Double(156.0,268.7358243656071,178.0,268.7358243656071), new Line2D.Double(288.0,268.7358243656071,310.0,268.7358243656071), new Line2D.Double(145.0,287.78838324886476,255.0,287.78838324886476), new Line2D.Double(90.0,306.84094213212234,112.0,306.84094213212234), new Line2D.Double(134.0,306.84094213212234,156.0,306.84094213212234), new Line2D.Double(288.0,306.84094213212234,310.0,306.84094213212234), new Line2D.Double(332.0,306.84094213212234,354.0,306.84094213212234), new Line2D.Double(79.0,325.89350101538,123.0,325.89350101538), new Line2D.Double(145.0,325.89350101538,167.0,325.89350101538), new Line2D.Double(189.0,325.89350101538,211.0,325.89350101538), new Line2D.Double(233.0,325.89350101538,255.0,325.89350101538), new Line2D.Double(343.0,325.89350101538,409.0,325.89350101538), new Line2D.Double(46.0,344.9460598986377,112.0,344.9460598986377), new Line2D.Double(134.0,344.9460598986377,156.0,344.9460598986377), new Line2D.Double(200.0,344.9460598986377,222.0,344.9460598986377), new Line2D.Double(310.0,344.9460598986377,332.0,344.9460598986377), new Line2D.Double(354.0,344.9460598986377,376.0,344.9460598986377), new Line2D.Double(398.0,344.9460598986377,420.0,344.9460598986377), new Line2D.Double(35.0,363.9986187818953,79.0,363.9986187818953), new Line2D.Double(123.0,363.9986187818953,145.0,363.9986187818953), new Line2D.Double(211.0,363.9986187818953,233.0,363.9986187818953), new Line2D.Double(277.0,363.9986187818953,299.0,363.9986187818953), new Line2D.Double(321.0,363.9986187818953,343.0,363.9986187818953), new Line2D.Double(24.0,344.9460598986377,35.0,363.9986187818954), new Line2D.Double(46.0,306.84094213212234,35.0,325.89350101538), new Line2D.Double(57.0,287.78838324886476,46.0,306.84094213212245), new Line2D.Double(46.0,306.84094213212234,57.0,325.89350101538), new Line2D.Double(68.0,306.84094213212234,79.0,325.89350101538), new Line2D.Double(90.0,230.6307065990918,79.0,249.68326548234944), new Line2D.Double(79.0,287.78838324886476,90.0,306.84094213212245), new Line2D.Double(101.0,211.57814771583415,90.0,230.63070659909178), new Line2D.Double(101.0,363.9986187818953,90.0,383.051177665153), new Line2D.Double(123.0,173.47302994931886,112.0,192.5255888325765), new Line2D.Double(123.0,211.57814771583415,112.0,230.63070659909178), new Line2D.Double(112.0,230.6307065990918,123.0,249.68326548234944), new Line2D.Double(112.0,344.9460598986377,123.0,363.9986187818954), new Line2D.Double(134.0,154.4204710660612,123.0,173.47302994931883), new Line2D.Double(134.0,192.5255888325765,123.0,211.57814771583412), new Line2D.Double(123.0,287.78838324886476,134.0,306.84094213212245), new Line2D.Double(145.0,135.36791218280354,134.0,154.42047106606117), new Line2D.Double(134.0,192.5255888325765,145.0,211.57814771583412), new Line2D.Double(145.0,211.57814771583415,134.0,230.63070659909178), new Line2D.Double(134.0,268.7358243656071,145.0,287.78838324886476), new Line2D.Double(156.0,116.31535329954589,145.0,135.36791218280354), new Line2D.Double(145.0,173.47302994931886,156.0,192.5255888325765), new Line2D.Double(145.0,211.57814771583415,156.0,230.63070659909178), new Line2D.Double(156.0,230.6307065990918,145.0,249.68326548234944), new Line2D.Double(145.0,249.6832654823494,156.0,268.7358243656071), new Line2D.Double(145.0,363.9986187818953,156.0,383.051177665153), new Line2D.Double(167.0,97.26279441628823,156.0,116.31535329954589), new Line2D.Double(167.0,135.36791218280354,156.0,154.42047106606117), new Line2D.Double(156.0,192.5255888325765,167.0,211.57814771583412), new Line2D.Double(156.0,306.84094213212234,167.0,325.89350101538), new Line2D.Double(156.0,344.9460598986377,167.0,363.9986187818954), new Line2D.Double(178.0,78.21023553303058,167.0,97.26279441628824), new Line2D.Double(178.0,116.31535329954589,167.0,135.36791218280354), new Line2D.Double(167.0,211.57814771583415,178.0,230.63070659909178), new Line2D.Double(178.0,230.6307065990918,167.0,249.68326548234944), new Line2D.Double(167.0,363.9986187818953,178.0,383.051177665153), new Line2D.Double(189.0,59.157676649772945,178.0,78.2102355330306), new Line2D.Double(189.0,97.26279441628823,178.0,116.31535329954589), new Line2D.Double(189.0,135.36791218280354,178.0,154.42047106606117), new Line2D.Double(189.0,173.47302994931886,178.0,192.5255888325765), new Line2D.Double(189.0,211.57814771583415,178.0,230.63070659909178), new Line2D.Double(189.0,249.6832654823494,178.0,268.7358243656071), new Line2D.Double(200.0,40.10511776651529,189.0,59.15767664977294), new Line2D.Double(200.0,78.21023553303058,189.0,97.26279441628824), new Line2D.Double(200.0,192.5255888325765,189.0,211.57814771583412), new Line2D.Double(200.0,230.6307065990918,189.0,249.68326548234944), new Line2D.Double(200.0,344.9460598986377,189.0,363.9986187818954), new Line2D.Double(211.0,21.05255888325764,200.0,40.10511776651529), new Line2D.Double(211.0,59.157676649772945,200.0,78.2102355330306), new Line2D.Double(200.0,154.4204710660612,211.0,173.47302994931883), new Line2D.Double(211.0,173.47302994931886,200.0,192.5255888325765), new Line2D.Double(211.0,211.57814771583415,200.0,230.63070659909178), new Line2D.Double(211.0,287.78838324886476,200.0,306.84094213212245), new Line2D.Double(211.0,363.9986187818953,200.0,383.051177665153), new Line2D.Double(222.0,1.9999999999999911,211.0,21.05255888325764), new Line2D.Double(222.0,78.21023553303058,211.0,97.26279441628824), new Line2D.Double(211.0,135.36791218280354,222.0,154.42047106606117), new Line2D.Double(222.0,154.4204710660612,211.0,173.47302994931883), new Line2D.Double(222.0,192.5255888325765,211.0,211.57814771583412), new Line2D.Double(222.0,1.9999999999999911,233.0,21.05255888325764), new Line2D.Double(222.0,40.10511776651529,233.0,59.15767664977294), new Line2D.Double(222.0,306.84094213212234,233.0,325.89350101538), new Line2D.Double(233.0,21.05255888325764,244.0,40.10511776651529), new Line2D.Double(233.0,59.157676649772945,244.0,78.2102355330306), new Line2D.Double(244.0,78.21023553303058,233.0,97.26279441628824), new Line2D.Double(233.0,97.26279441628823,244.0,116.31535329954589), new Line2D.Double(233.0,211.57814771583415,244.0,230.63070659909178), new Line2D.Double(244.0,40.10511776651529,255.0,59.15767664977294), new Line2D.Double(244.0,78.21023553303058,255.0,97.26279441628824), new Line2D.Double(244.0,154.4204710660612,255.0,173.47302994931883), new Line2D.Double(255.0,173.47302994931886,244.0,192.5255888325765), new Line2D.Double(244.0,230.6307065990918,255.0,249.68326548234944), new Line2D.Double(255.0,287.78838324886476,244.0,306.84094213212245), new Line2D.Double(255.0,59.157676649772945,266.0,78.2102355330306), new Line2D.Double(266.0,192.5255888325765,255.0,211.57814771583412), new Line2D.Double(266.0,230.6307065990918,255.0,249.68326548234944), new Line2D.Double(255.0,249.6832654823494,266.0,268.7358243656071), new Line2D.Double(266.0,306.84094213212234,255.0,325.89350101538), new Line2D.Double(266.0,344.9460598986377,255.0,363.9986187818954), new Line2D.Double(266.0,78.21023553303058,277.0,97.26279441628824), new Line2D.Double(266.0,154.4204710660612,277.0,173.47302994931883), new Line2D.Double(266.0,192.5255888325765,277.0,211.57814771583412), new Line2D.Double(277.0,325.89350101538,266.0,344.9460598986377), new Line2D.Double(277.0,173.47302994931886,288.0,192.5255888325765), new Line2D.Double(277.0,287.78838324886476,288.0,306.84094213212245), new Line2D.Double(288.0,116.31535329954589,299.0,135.36791218280354), new Line2D.Double(299.0,173.47302994931886,288.0,192.5255888325765), new Line2D.Double(288.0,230.6307065990918,299.0,249.68326548234944), new Line2D.Double(288.0,344.9460598986377,299.0,363.9986187818954), new Line2D.Double(299.0,135.36791218280354,310.0,154.42047106606117), new Line2D.Double(299.0,287.78838324886476,310.0,306.84094213212245), new Line2D.Double(299.0,325.89350101538,310.0,344.9460598986377), new Line2D.Double(310.0,154.4204710660612,321.0,173.47302994931883), new Line2D.Double(321.0,211.57814771583415,310.0,230.63070659909178), new Line2D.Double(310.0,230.6307065990918,321.0,249.68326548234944), new Line2D.Double(310.0,268.7358243656071,321.0,287.78838324886476), new Line2D.Double(310.0,306.84094213212234,321.0,325.89350101538), new Line2D.Double(310.0,344.9460598986377,321.0,363.9986187818954), new Line2D.Double(321.0,363.9986187818953,310.0,383.051177665153), new Line2D.Double(321.0,173.47302994931886,332.0,192.5255888325765), new Line2D.Double(321.0,287.78838324886476,332.0,306.84094213212245), new Line2D.Double(332.0,192.5255888325765,343.0,211.57814771583412), new Line2D.Double(343.0,211.57814771583415,332.0,230.63070659909178), new Line2D.Double(332.0,230.6307065990918,343.0,249.68326548234944), new Line2D.Double(343.0,211.57814771583415,354.0,230.63070659909178), new Line2D.Double(343.0,287.78838324886476,354.0,306.84094213212245), new Line2D.Double(354.0,230.6307065990918,365.0,249.68326548234944), new Line2D.Double(365.0,249.6832654823494,354.0,268.7358243656071), new Line2D.Double(354.0,344.9460598986377,365.0,363.9986187818954), new Line2D.Double(365.0,363.9986187818953,354.0,383.051177665153), new Line2D.Double(365.0,249.6832654823494,376.0,268.7358243656071), new Line2D.Double(365.0,287.78838324886476,376.0,306.84094213212245), new Line2D.Double(376.0,268.7358243656071,387.0,287.78838324886476), new Line2D.Double(376.0,306.84094213212234,387.0,325.89350101538), new Line2D.Double(387.0,287.78838324886476,398.0,306.84094213212245), new Line2D.Double(387.0,363.9986187818953,398.0,383.051177665153), new Line2D.Double(409.0,363.9986187818953,420.0,383.051177665153), new Line2D.Double(420.0,344.9460598986377,431.0,363.9986187818954), new Line2D.Double(431.0,363.9986187818953,442.0,383.051177665153), new Line2D.Double(442.0,383.051177665153,2.0,383.051177665153), new Line2D.Double(2.0,383.051177665153,13.0,363.9986187818953), new Line2D.Double(13.0,363.9986187818954,24.0,344.9460598986377), new Line2D.Double(24.0,344.9460598986377,35.0,325.89350101538), new Line2D.Double(57.0,287.78838324886476,68.0,268.7358243656071), new Line2D.Double(68.0,268.7358243656071,79.0,249.6832654823494), new Line2D.Double(101.0,249.6832654823494,90.0,268.7358243656071), new Line2D.Double(90.0,268.7358243656071,101.0,287.78838324886476), new Line2D.Double(112.0,268.7358243656071,101.0,287.78838324886476), new Line2D.Double(101.0,287.78838324886476,112.0,306.84094213212245), new Line2D.Double(134.0,306.84094213212234,123.0,325.89350101538), new Line2D.Double(123.0,325.89350101538,134.0,344.9460598986377), new Line2D.Double(178.0,268.7358243656071,167.0,287.78838324886476), new Line2D.Double(167.0,287.78838324886476,178.0,306.84094213212245), new Line2D.Double(178.0,306.84094213212234,189.0,325.89350101538), new Line2D.Double(189.0,325.89350101538,178.0,344.9460598986377), new Line2D.Double(178.0,344.9460598986377,189.0,363.9986187818954), new Line2D.Double(189.0,97.26279441628823,200.0,116.31535329954589), new Line2D.Double(200.0,116.31535329954589,189.0,135.36791218280354), new Line2D.Double(233.0,173.47302994931886,222.0,192.5255888325765), new Line2D.Double(222.0,192.5255888325765,233.0,211.57814771583412), new Line2D.Double(233.0,325.89350101538,244.0,344.9460598986377), new Line2D.Double(244.0,344.9460598986377,233.0,363.9986187818954), new Line2D.Double(277.0,249.6832654823494,266.0,268.7358243656071), new Line2D.Double(266.0,268.7358243656071,277.0,287.78838324886476), new Line2D.Double(277.0,97.26279441628823,288.0,116.31535329954589), new Line2D.Double(288.0,116.31535329954589,277.0,135.36791218280354), new Line2D.Double(277.0,135.36791218280354,288.0,154.42047106606117), new Line2D.Double(288.0,306.84094213212234,277.0,325.89350101538), new Line2D.Double(277.0,325.89350101538,288.0,344.9460598986377), new Line2D.Double(321.0,173.47302994931886,310.0,192.5255888325765), new Line2D.Double(310.0,192.5255888325765,321.0,211.57814771583412), new Line2D.Double(343.0,249.6832654823494,332.0,268.7358243656071), new Line2D.Double(332.0,268.7358243656071,343.0,287.78838324886476), new Line2D.Double(398.0,306.84094213212234,409.0,325.89350101538), new Line2D.Double(409.0,325.89350101538,420.0,344.9460598986377)};
    public static circle[] circleG = {new circle(60, -21,8)};
    //public static Line2D.Double[] lev6 = MAPDATA.LN2DSVG(""); //use this again dipshit you wrote it so use it
    public static int level = 1;
    public static ArrayList<Integer> kinve = new ArrayList<Integer>(3);
    public masterkey[] keygeom= {new masterkey(5,55,5),new masterkey(72,-21,2),new masterkey(40,165,14)}; //14
    public door[] doormat = {new door(10.0, -50.0, 30.0,0,-1,2)};
    //public door[] lvl3doors = {new door(10.0, 50.0, 30.0,0,-1,13),new door(10, 0.0, 10.0,0,-1,4)};
    //public door[] lv2door = {new door(10.0, 50.0, 30.0,0,-1,13)};

    double[] dists = new double[(int)RIZZOLUTION+1];
    double[] boxdists = new double[(int)RIZZOLUTION+1];
    double[] keydists = new double[(int)RIZZOLUTION+1];
    double[] circdists = new double[(int)RIZZOLUTION+1];

    int[] dcolor = new int[(int)RIZZOLUTION+1];
    int[] blank = new int[(int)RIZZOLUTION+1];
    Line2D.Double[] rayst = new Line2D.Double[0];
    public double speed_desired =7;
    public double speed;
    public double snap = 1;
    public double col = snap+speed;
    public boolean usescollision = true;
    Graphics gBuffer;
    //{131, 131, 131, 131, 131, 131, 131, 131, 131, 125, 125, 125, 125, 125, 126, 126, 125, 125, 126, 126, 125, 125, 125, 126, 126, 126, 126, 125, 126, 125, 125, 126, 121, 120, 120, 121, 121, 120, 121, 120, 121, 120, 120, 121, 121, 121, 121, 121, 121, 120, 121, 120, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116, 112, 112, 112, 112, 112, 112, 112, 112, 112, 112, 112, 112, 112, 112, 112, 112, 112, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 100, 100, 100, 100, 101, 101, 101, 100, 101, 101, 101, 100, 101, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 89, 89, 89, 89, 89, 89, 89, 89, 89, 86, 86, 86, 86, 86, 86, 86, 86, 86, 84, 84, 84, 84, 84, 84, 84, 84, 82, 82, 82, 82, 82, 82, 82, 82, 79, 79, 79, 79, 79, 79, 79, 77, 77, 77, 77, 77, 77, 77, 75, 76, 76, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 54, 54, 54, 54, 54, 57, 57, 57, 57, 57, 57, 57, 59, 59, 59, 59, 59, 59, 59, 59, 59, 60, 60, 61, 60, 61, 61, 61, 60, 60, 60, 62, 62, 62, 62, 63, 63, 63, 63, 63, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 62, 62, 62, 61, 60, 60, 60, 61, 61, 61, 60, 61, 60, 58, 58, 58, 58, 58, 58, 58, 58, 56, 56, 56, 56, 56, 56, 56, 38, 38, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 79, 79, 79, 79, 79, 79, 79, 79, 79, 82, 82, 82, 82, 82, 82, 82, 82, 82, 82, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 86, 86, 86, 86, 86, 86, 86, 86, 86, 86, 86, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 94, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 101, 100, 100, 101, 101, 100, 100, 101, 100, 101, 101, 101, 101, 101, 101, 100, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 112, 112, 112, 112};// Create a component that you can actually draw on.
    public static boolean isrendering = false;
    public boolean usekey = false;
    public boolean wkey = false;
    public boolean akey = false;
    public boolean skey = false;
    public boolean dkey = false;
    public boolean leftkey = false;
    public boolean rightkey = false;
    boolean tog = true;
    public int fishEyeToggler = 1;
    public int kr = 0;
    public int keyring = 0;
    public DrawPane() {
        kinve.add(1);
        kinve.add(3);
        keyring = kinve.get(kr);
        setFocusable(true);
        addMouseMotionListener(new MouseAdapter() {

            public void mouseMoved(MouseEvent e) {
                repaint(0,0,(int)screen,900);


            }
        });
        addMouseWheelListener(new MouseAdapter() {


            public void mouseWheelMoved(MouseWheelEvent mw) {
                // String message;
                kr=Math.abs((kr + mw.getWheelRotation())%kinve.size());
                keyring=kinve.get(kr);
                repaint(0,0,(int)screen,900);
            }
        });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                //System.out.println(e.getKeyCode() + " " + e.getKeyChar());

                if (e.getKeyCode() == KeyEvent.VK_E && tog) {
                    usekey = true;
                    repaint(0,0,(int)screen,900);
                    tog = false;

                }
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    wkey = true;
                    repaint(0,0,(int)screen,900);
                }




                if (e.getKeyCode() == KeyEvent.VK_A) {
                    akey = true;
                    repaint(0,0,(int)screen,900);

                }

                if (e.getKeyCode() == KeyEvent.VK_S) {
                    skey = true;
                    repaint(0,0,(int)screen,900);

                }


                if (e.getKeyCode() == KeyEvent.VK_D) {
                    dkey = true;
                    repaint(0,0,(int)screen,900);
                }


                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    leftkey = true;
                    repaint(0,0,(int)screen,900);
                }


                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rightkey = true;
                    repaint(0,0,(int)screen,900);
                }


            }




            // @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_E) {
                    usekey = false;
                    tog = true;
                    //repaint();
                }
                if (e.getKeyCode() == KeyEvent.VK_F) {
                    fishEyeToggler = (fishEyeToggler +1)%2;
                    repaint(0,0,(int)screen,900);
                }

                if (e.getKeyCode() == KeyEvent.VK_W) {
                    wkey = false;
                    // player.setPos(player.getPos().getX() + speed * Math.sin(player.getDirection()), player.getPos().getY() + speed * Math.cos(player.getDirection()));
                    //repaint(0,0,(int)screen,900);
                    //scan((Graphics2D) g);

                }


                if (e.getKeyCode() == KeyEvent.VK_A) {
                    akey = false;
                    // player.setPos(player.getPos().getX() - speed * Math.cos(player.getDirection()), player.getPos().getY() + speed * Math.sin(player.getDirection()));
                    //repaint(0,0,(int)screen,900);
                    //scan((Graphics2D) g);

                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    skey = false;
                    //player.setPos(player.getPos().getX() - speed * Math.sin(player.getDirection()), player.getPos().getY() - speed * Math.cos(player.getDirection()));
                    repaint(0,0,(int)screen,900);
                    //scan((Graphics2D) g);
                }

                if (e.getKeyCode() == KeyEvent.VK_D) {
                    dkey = false;
                    //player.setPos(player.getPos().getX() + speed * Math.cos(player.getDirection()), player.getPos().getY() - speed * Math.sin(player.getDirection()));
                    repaint(0,0,(int)screen,900);
                    //scan((Graphics2D) g);
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    leftkey = false;
                    repaint(0,0,(int)screen,900);
                    //scan((Graphics2D) g);
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    //paddle2vel = 0;
                    rightkey = false;
                    repaint(0,0,(int)screen,900);
                    //scan((Graphics2D) g);
                }
                if (e.getKeyCode() == KeyEvent.VK_Q) {

                    kr=((kr + 1)%kinve.size());
                    keyring=kinve.get(kr);
                    System.out.println(keyring);
                    repaint(0,0,(int)screen,900);
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    haha.togglemousemode();
                }

            }});
        //getKeyListeners();

    };

    String filedir = "C:\\Users\\Zach\\Desktop\\raycaster-20230526T192747Z-001\\raycaster\\src\\keys\\";

    public ImageIcon Key(int a){
        if(imagesallowed) {
            return new ImageIcon(filedir + keynames[a] + ".png");
        }else{
            return null;
        }
    }
    //public Rectangle2D.Double lineWithWidth(double cx,double c)


    //void mickeymouse();

    public void paintComponent(Graphics g){

    }
    public void movelis() {
        double timeSince = ((double)System.currentTimeMillis()/1000) / (lastFrame/1000);
        speed = (speed_desired/timeSince)/100;
        Point2D plast = new Point2D.Double(player.getPos().getX(), player.getPos().getY());
        Point2D pnext = new Point2D.Double(player.getPos().getX(), player.getPos().getY());
        if (wkey) {
            // Point2D plast = new Point2D.Double(player.getPos().getX(),player.getPos().getY());
            player.setPos(player.getPos().getX() + speed * Math.sin(player.getDirection()), player.getPos().getY() + speed * Math.cos(player.getDirection()));
            //Point2D pnext = new Point2D.Double(player.getPos().getX() + (speed+5) * Math.sin(player.getDirection()), player.getPos().getY() + (speed+5) * Math.cos(player.getDirection()));
            pnext.setLocation(new Point2D.Double(pnext.getX() + snap * Math.sin(player.getDirection()), pnext.getY() + snap * Math.cos(player.getDirection())));

            //repaint(0,0,(int)screen,900);
            // scan((Graphics2D) g);
        }
        if (akey) {
            //Point2D plast = new Point2D.Double(player.getPos().getX(),player.getPos().getY());
            player.setPos(player.getPos().getX() - speed * Math.cos(player.getDirection()), player.getPos().getY() + speed * Math.sin(player.getDirection()));
            // Point2D pnext = new Point2D.Double(player.getPos().getX() - snap * Math.cos(player.getDirection()), player.getPos().getY() + snap * Math.sin(player.getDirection()));
            pnext.setLocation(new Point2D.Double(pnext.getX() - snap * Math.cos(player.getDirection()), pnext.getY() + snap * Math.sin(player.getDirection())));
            //
            // repaint(0,0,(int)screen,900);
            //scan((Graphics2D) g);

        }
        if (skey) {
            // Point2D plast = new Point2D.Double(player.getPos().getX(),player.getPos().getY());
            player.setPos(player.getPos().getX() - speed * Math.sin(player.getDirection()), player.getPos().getY() - speed * Math.cos(player.getDirection()));
            // Point2D pnext = new Point2D.Double(player.getPos().getX() - snap * Math.sin(player.getDirection()), player.getPos().getY() - snap * Math.cos(player.getDirection()));
            pnext.setLocation(new Point2D.Double(pnext.getX() - snap * Math.sin(player.getDirection()), pnext.getY() - snap * Math.cos(player.getDirection())));

            // repaint(0,0,(int)screen,900);
            // scan((Graphics2D) g);
        }
        if (dkey) {
            //Point2D plast = new Point2D.Double(player.getPos().getX(),player.getPos().getY());
            player.setPos(player.getPos().getX() + speed * Math.cos(player.getDirection()), player.getPos().getY() - speed * Math.sin(player.getDirection()));
            // Point2D pnext = new Point2D.Double(player.getPos().getX() + snap * Math.cos(player.getDirection()), player.getPos().getY() - snap * Math.sin(player.getDirection()));
            pnext.setLocation(new Point2D.Double(pnext.getX() + snap * Math.cos(player.getDirection()), pnext.getY() - snap * Math.sin(player.getDirection())));

            // repaint(0,0,(int)screen,900);
            // scan((Graphics2D) g);
        }

        if (rightkey) {
            //paddle2vel = 0;
            player.setDir((player.getDirection() + toRad(1)));

            repaint(0,0,(int)screen,900);
            //scan((Graphics2D) g);
        }
        if (leftkey) {
            player.setDir((player.getDirection() + toRad(-1)));

            repaint(0,0,(int)screen,900);
            //scan((Graphics2D) g);
        }

        if (usescollision) {
            for (int j = 0; j < mapthing.length; j++) {
                if (new Line2D.Double(pnext, plast).intersectsLine(mapthing[j])) {
                    player.setPos(plast.getX(), plast.getY());
                    break;
                }
            }
            for (int j = 0; j < doormat.length; j++) {
                if (new Line2D.Double(pnext, plast).intersectsLine(doormat[j].getDoor())) {
                    player.setPos(plast.getX(), plast.getY());
                    break;
                }
            }
            for (int j = 0; j < circleG.length; j++) {
                Point2D.Double centerCirc = circleG[j].getCenter();
                if (centerCirc.distance(pnext) <= circleG[j].getRadius()) {
                    player.setPos(plast.getX(), plast.getY());
                    break;
                }
            }

        }

        repaint(0,0,(int)screen,900);

    }

    public void paint(Graphics g) {
        movelis();
        // getKeyListeners();
        //isrendering=true;
        g.setColor(Color.white);
        g.fillRect(0, 0, (int)screen, 900);
        isrendering = true;
        //getKeyListeners();
        scan((Graphics2D) g);
        drawlvlgeom((Graphics2D) g);

        // getKeyListeners();
        g.setColor(Color.black);

        g.drawString("X Position : " + player.getPos().getX(), 10, 10);
        g.drawString("Y Position : " + player.getPos().getY(), 10, 20);
        g.drawString("P Direction  : " + toDeg(player.getDirection()) % 361, 10, 30);
        g.drawString("Press ESC to release mouse control", 10 , 50);



        isrendering = false;

        //  if (haha.mouseMode){repaint(0,0,(int)screen,900);}
        if(imagesallowed) {
            g.drawImage(Key(keyring).getImage(), 40, 20, 46 * 3, 76 * 3, Key(keyring).getImageObserver());
        }
        g.drawString(keynames[keyring], 46, 40);
        pain_t((Graphics2D)g);
        getKeyListeners();




        //g=this.g;
        //new haha();


        //g.fillRect(0,0,900,900);
        //player.jerk();

        //new player(player.getPos().getX(),player.getPos().getY(),player.getDirection());
        //scan();


        //    for (int i = 0; i < dists.length; i++) {
        //        //System.out.println(dists[i]);
        //        System.out.println("no");
        //        g.setColor(new Color(0, 0, 3000 / (dists[i] + 1) % 256));
        //        g.drawLine(i / 2, (4000 / (dists[i] + 1)) + 200, i / 2, (4000 / (-dists[i] + 1)) + 200);
        //    }
        //
        //this.g = g;



        //g.dispose();
    }


    // abstract mickeymouse();

    public static int clamp(int val, int min, int max) {
        return Math.min(max, Math.max(min, val));
    }
    public static double clamp(double val, double min, double max) {
        return Math.min(max, Math.max(min, val));
    }

    //KEYWORDS
    //MAP
    //MINIMAP

    public void pain_t (Graphics2D mP){
        double pla_X = player.getPos().getX();
        double pla_Y = player.getPos().getY();
        double pla_D = player.getDirection();


        mP.translate(900,500);
        mP.scale(1,-1);
        mP.setColor(Color.white);
        mP.fillRect(0,0,5   ,5);
        mP.setColor(Color.black);
        for(int i = 0; i<DrawPane.getMap().length;i++) {
            mP.draw(DrawPane.getMap()[i]);

        }
        for (int j = 0; j < doormat.length; j++) {
            mP.draw(doormat[j].getDoor());
        }
        for (int j = 0; j < keygeom.length; j++) {
            mP.draw(keygeom[j].getKeyGeom());
        }
        for(int i = 0; i<circleG.length;i++) {
            mP.draw(circleG[i].getGeom());
        }

        double lowdists;
        for (int j = 0; j < RIZZOLUTION; j++) {
//                        mP.draw(new Line2D.Double(pla_X,pla_Y,pla_X+boxdists[j]*Math.sin(pla_D-toRad(45)+toRad(j/riztodeg)),pla_Y+boxdists[j]*Math.cos(pla_D-toRad(45)+toRad(j/riztodeg))));
//                        mP.draw(new Line2D.Double(pla_X,pla_Y,pla_X+keydists[j]*Math.sin(pla_D-toRad(45)+toRad(j/riztodeg)),pla_Y+keydists[j]*Math.cos(pla_D-toRad(45)+toRad(j/riztodeg))));
//                      mP.draw(new Line2D.Double(pla_X,pla_Y,pla_X+dists[j]*Math.sin(pla_D-toRad(45)+toRad(j/riztodeg)),pla_Y+dists[j]*Math.cos(pla_D-toRad(45)+toRad(j/riztodeg))));

            lowdists = Math.min(circdists[j],Math.min(dists[j],Math.min(boxdists[j],keydists[j])));

            if(lowdists>56789){
                lowdists = clamp(lowdists,0,70);
            }

            mP.draw(new Line2D.Double(pla_X,pla_Y,pla_X+lowdists*Math.sin(pla_D-hrfov+toRad(j/riztodeg)),pla_Y+lowdists*Math.cos(pla_D-hrfov+toRad(j/riztodeg))));
        }
        //mP.fill3DRect((int)player.getPos().getX(),(int)player.getPos().getY(),100,100 ,true);

        lastFrame = System.currentTimeMillis();

    }
    //KEYWORDS
    //RENDERING
    //LEVEL GEOMETRY
    //DRAWWALLS
    //WALLS
    //try replacing 2000 with the rizzolution or screen or something
    //or 400 with 2000/2
    public void drawlvlgeom(Graphics2D g){
        //lastFrame = System.currentTimeMillis();
        new player(player.getPos().getX(),player.getPos().getY(),player.getDirection());
        if(strch<=1){
            for(int FS = 0; FS<=RIZZOLUTION;FS++){

                double rayngle = ((player.getDirection() - hrfov) + toRad(FS / riztodeg));
                double fsh = Math.pow(Math.cos(rayngle - player.getDirection()), fishEyeToggler); //fish without the eye
                int colored = clamp((int) Math.ceil((2000 / (dists[FS] * fsh))), 0, 240);
               int coloredcirc = clamp((int) Math.ceil((2000 / (circdists[FS] * fsh))), 0, 240);
                if(dists[FS]!=123456789) {
                    g.setColor(new Color(colored, colored, 0));

                    g.draw(new Line2D.Double(FS * strch, 2000 / (dists[FS] * fsh) + 400, FS * strch, 2000 / ((-dists[FS]) * fsh) + 400));
                }
                if(circdists[FS]!=123456789){
                    g.setColor(new Color(coloredcirc,coloredcirc,0));

                    g.draw(new Line2D.Double(FS * strch, 2000 / (circdists[FS] * fsh) + 400, FS * strch, (2000 / ((-circdists[FS]) * fsh)) + 400));
                }
                if(keydists[FS]!=123456789) {
                    g.setColor(new Color(0, 0, colored));

                    g.draw(new Line2D.Double(FS * strch, 2000 / (keydists[FS] * fsh) + 400, FS * strch, (2000 / ((-keydists[FS]) * fsh)) + 400));
                }
                if(boxdists[FS]!=123456789) {
                    g.setColor(new Color(0, colored, colored));


                    g.draw(new Line2D.Double(FS * strch, 2000 / (boxdists[FS] * fsh) + 400, FS * strch, (2000 / ((-boxdists[FS]) * fsh)) + 400));
                }

            }

        }else{
            for(int FS = 0; FS<=RIZZOLUTION;FS++){

                double rayngle = ((player.getDirection() - hrfov) + toRad(FS / riztodeg));
                double fsh = Math.pow(Math.cos(rayngle - player.getDirection()), fishEyeToggler); //fish without the eye
                int colored = clamp((int) Math.ceil((2000 / (dists[FS] * fsh))), 0, 200);
                int coloredcirc = clamp((int) Math.ceil((2000 / (circdists[FS] * fsh))), 0, 200);
                for(int t =0; t<(int)strch+1;t++) {
                    if(dists[FS]!=123456789) {
                        g.setColor(new Color(colored, colored, 0));
                        g.draw(new Line2D.Double(FS * strch + t, 2000 / ((dists[FS]) * fsh) + 400, FS * strch + t, 2000 / ((-dists[FS]) * fsh) + 400));
                    }
                    if(circdists[FS]!=123456789) {
                        g.setColor(new Color(coloredcirc, coloredcirc,0));


                        g.draw(new Line2D.Double(FS * strch + t, 2000 / ((circdists[FS]) * fsh) + 400, FS * strch + t, (2000 / ((-circdists[FS]) * fsh)) + 400));
                    }
                    if(keydists[FS]!=123456789) {
                        g.setColor(new Color(0, 0, colored));

                        g.draw(new Line2D.Double(FS * strch + t, 2000 / ((keydists[FS]) * fsh) + 400, FS * strch + t, (2000 / ((-keydists[FS]) * fsh))+400));
                    }
                    if(boxdists[FS]!=123456789) {
                        g.setColor(new Color(0, colored, colored));


                        g.draw(new Line2D.Double(FS * strch + t, 2000 / ((boxdists[FS]) * fsh) + 400, FS * strch + t, (2000 / ((-boxdists[FS]) * fsh)) + 400));
                    }

                }
            }
        }

    }
    public double toRad(double fg) {

        return Math.toRadians(fg);
    }
    public Point2D.Double pointIntersect(Line2D.Double L1,  Line2D.Double L2){
        //Function written by Zach Beaupre - 2023
        //Equation retrieved from https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection
        //i was too lazy to find its source but i did dig around the site a little.
        //but with confidence it was probably made by some wierd greek guy in the 1000 bce's or something
        double X1 = L1.getX1();
        double Y1 = L1.getY1();
        double X2 = L1.getX2();
        double Y2 = L1.getY2();

        double X3 = L2.getX1();
        double Y3 = L2.getY1();
        double X4 = L2.getX2();
        double Y4 = L2.getY2();

        double intsectdenom = ((X1-X2)*(Y3-Y4)-(Y1-Y2)*(X3-X4));
        double numerp1 = (X1*Y2-Y1*X2);
        double numerp2 = (X3*Y4-Y3*X4);
        //return new Point2D.Double(((X1*Y2-Y1*X2)*(X3-X4)-(X1-X2)*(X3*Y4-Y3*X4))/((X1-X2)*(Y3-Y4)-(Y1-Y2)*(X3-X4)),((X1*Y2-Y1*X2)*(Y3-Y4)-(Y1-Y2)*(X3*Y4-Y3*X4))/((X1-X2)*(Y3-Y4)-(Y1-Y2)*(X3-X4)));
        return new Point2D.Double((numerp1*(X3-X4)-(X1-X2)*numerp2)/intsectdenom,(numerp1*(Y3-Y4)-(Y1-Y2)*numerp2)/intsectdenom); // we only have to do things once

        //I could simplify this more but meh
    }


    public double toDeg(double fg) {


        return Math.toDegrees(fg);
    }
    public void scan(Graphics2D g) {

        for(int i = 0;i<keygeom.length;i++) {
            keygeom[i].keycol();
        }
        int thisdoor=0;
        if(player.getPos().getY()>0.2&&level == 1) {
            mapthing = lev2;
            doormat = new door[]{new door(10.0, 40.0, 10.0,90,-1,2),new door(30, 60, 10.0,0,1,5)};

            doormat[0].setSwingamt(45);



            level = 2;
        }
        if(player.getPos().getY()>60.5&&level == 2) {
            for(int iwantadog = 0; iwantadog<lev3.length;iwantadog++){
                lev3[iwantadog].setLine(lev3[iwantadog].getX1()-65,lev3[iwantadog].getY1()+60,lev3[iwantadog].getX2()-65,lev3[iwantadog].getY2()+60);
            }
            mapthing = lev3;



            doormat = new door[]{doormat[0],new door(0, 0.0, 10.0,0,-1,5)};
            level = 3;
        }
        if((player.getPos().getY() < -60.5 && level == 3)||(portal&&level == 3)){
            mapthing = lev4;
            doormat = new door[]{};
            level = 4;
        }
        if((player.getPos().getY() > 60.5 && level == 4)||(portal && level == 3)){
            mapthing = lev5;
            doormat = new door[]{};
            level = 5;
            portal = false;
        }

        g.setColor(Color.white);

        g.fillRect(0, 0, 900, 900);
        double[] dists = new double[(int)RIZZOLUTION+1];
        double[] boxdists = new double[(int)RIZZOLUTION+1];
        double[] keydists = new double[(int)RIZZOLUTION+1];
        double[] circdists = new double[(int)RIZZOLUTION+1];
        //if ((isrendering = true)) {
        // g.dispose();
        //new player(player.getPos().getX(),player.getPos().getY(),player.getDirection());
        double S = 0;
        boolean happening = false;
        int FS = 0;
        double renderDistance = 5000;
        double pla_X = player.getPos().getX();
        double pla_Y = player.getPos().getY();
        double pla_D = player.getDirection();
        Point2D getPos = player.getPos();

        // renderDistance = renderDistance * RIZZOLUTION;
        double rzn = 1/RIZZOLUTION;

        double p45 = (pla_D - hrfov);

        while (FS <= RIZZOLUTION) { //90 degrees
            int thatdoor =0;

            double rayngle = ((pla_D - hrfov) + toRad(S / riztodeg)); //900 --> 90
            double xStep = Math.sin(rayngle);
            double yStep =Math.cos(rayngle);
            Line2D.Double in = new Line2D.Double(new Point2D.Double(pla_X, pla_Y), new Point2D.Double(pla_X + renderDistance * xStep, pla_Y + renderDistance * yStep));

            double lowestnew = 100000;

            for (int j = 0; j < mapthing.length; j++) {
                if (in.intersectsLine(mapthing[j])) {

                    double len = getPos.distance(pointIntersect(in,mapthing[j]));

                    if(lowestnew>=len){
                        dists[FS] = len;
                        lowestnew = len;

                    }

                }

            }
            if(dists[FS]==0){dists[FS]=123456789;}
            for (int j = 0; j < circleG.length; j++) {
                Point2D.Double closestPoint = circle.pointIntersectCircle(in, circleG[j]);
                Point2D.Double furthestPoint = circle.pointIntersectCircle(in, circleG[j],false);
                if ((in.getP2().distance(furthestPoint) < in.getP2().distance(closestPoint))){ //circleG[j].intersectsWith(in) && && in.intersects(circleG[j].getBounds())

                    double len = getPos.distance(closestPoint);

                    if(lowestnew>=len){
                        circdists[FS] = len;
                        lowestnew = len;

                    }

                }

            }
            if(circdists[FS]==0){circdists[FS]=123456789;}


            for (int j = 0; j < doormat.length; j++) {
                if (in.intersectsLine(doormat[j].getDoor())) {
                    double len = getPos.distance(pointIntersect(in,doormat[j].getDoor()));
                    if(lowestnew>=len){
                        //dists[FS] = len;
                        boxdists[FS] = len;
                        lowestnew = len;
                    }
                    if(FS ==RIZZOLUTION/2) {
                        thisdoor = j;
                        thatdoor = thisdoor;
                        if (usekey == true &&(boxdists[FS]<dists[FS])){
                            happening = true;
                        }
                    }

                }

            }
            if(boxdists[FS]==0){boxdists[FS]=123456789;}
            for (int j = 0; j < keygeom.length; j++) {
                if (in.intersectsLine(keygeom[j].getKeyGeom())) {
                    double len = getPos.distance(pointIntersect(in,keygeom[j].getKeyGeom()));


                    if(lowestnew>=len){
                        //dists[FS] = len;
                        //boxdists[FS] = len;
                        keydists[FS] = len;
                        lowestnew = len;
                    }

                }

            }

            if(keydists[FS]==0){keydists[FS]=123456789;}
            S += 1;
            FS += 1;

        }
        //repaint();
        //this.g = g;        isrendering = false;
        if (happening &&(boxdists[(int)RIZZOLUTION/2]<dists[(int)RIZZOLUTION/2])){
            if (keyring == doormat[thisdoor].getCode()) {
                doormat[thisdoor].useDoor(doormat[thisdoor].getCode());


                repaint(0,0,(int)screen,900);
                happening = false;
                tog = false;
                usekey = false;
            }else {
                //repaint();
                g.setColor(Color.black);
                g.drawString("You'll need a " + keynames[doormat[thisdoor].getCode()]+ " to open this door...", 100, 800);
                happening = false;

                repaint(0,0,(int)screen,900);
            }



        }
        getKeyListeners();
        this.dists = dists;
        this.boxdists = boxdists;
        this.keydists =keydists;
        this.circdists = circdists;



    }


    @Override
    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);

    }
    public static Line2D.Double[] getMap(){
        return mapthing;
    }

}


