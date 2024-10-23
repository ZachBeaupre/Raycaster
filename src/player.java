


import java.awt.geom.Point2D;
public class player
{
    // instance variables - replace the example below with your own
    private static double x;
    private static double y;
    private static double dir;

    private static double holding;

    /**
     * Constructor for objects of class player
     */


public player(){
    new player(getPos().getX(),player.getPos().getY(),player.getDirection());
}

    public player(double x, double y, double dir)
    {

        // initialise instance variables

        player.x   = x;
        player.y   = y;
        player.dir = dir;
        //player.dir = toRad(dir);


    }

    public static double toRad(double fg) {

        //Math.toRadians(fg);

        System.out.print(fg);
        System.out.print(Math.toRadians(fg));
        return Math.toRadians(fg);
    }





    public static double getDirection(){
        return player.dir;
    }
    public static void setDir(double dir){
        player.dir = dir;

    }
    public static void setX(double no){
        player.x = no;

    }
    public static void setY(double no){
        player.y = no;

    }
    public static void setPos(double x, double y){
        player.x = x;
        player.y = y;

    }
    public static void setPos(Point2D newPoint){
        player.x = newPoint.getX();
        player.y = newPoint.getY();


    }

    public static Point2D getPos(){
        return new Point2D.Double(x,y);
    }
}
