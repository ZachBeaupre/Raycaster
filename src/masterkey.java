
/**
 * Write a description of class player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
public class masterkey
{
    // instance variables - replace the example below with your own
    protected double x;
    protected double y;
    protected int grant;
    protected boolean used;
    public masterkey(double x,double y,int grant){
        this.x =x;
        this.y=y;
        this.grant=grant;
    }
    public masterkey(){
    }
    public void keycol(){
        if(new Rectangle2D.Double(x-2.5,y-2.5,5.0,5.0).contains(player.getPos())&&!used){
           if(grant == 14){
                DrawPane.portal = true;
               used = true;
           }else {
               DrawPane.kinve.add(grant);
               used = true;
           }
        }

    }
    public Line2D.Double getKeyGeom(){
        if(!used) {
            return new Line2D.Double(x - 2.5, y - 2.5, x, y);
        }else{
            return new Line2D.Double(1000,1000,1000,1000);
        }
    }
}
