


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
        if(new Rectangle2D.Double(x-4.0,y-4.0,8.0,8.0).contains(player.getPos())&&!used){
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
        final double halfPi = Math.PI / 2;


            double theta = Math.atan2(player.getPos().getY()-y,player.getPos().getX()-x); //gets the angle from the players position to the key. Making it always face you, creepy but cool
            //I do intend to use this to make sprites and cokes work. maybe just sprites.
            //it reminds me of gmod nextbots or the people in crowds in forza horizon 4 or just old raycasters
            //i accidentally messed up order of x and y again lol
            



            return new Line2D.Double(x + 2.5 * Math.cos(theta+halfPi), y + 2.5 * Math.sin(theta+halfPi), x - 2.5 * Math.cos(theta + halfPi) , y - 2.5 * Math.sin(theta + halfPi));
//            return new Line2D.Double(x - 2.5, y - 2.5, x, y); //my old method of making the keys. Before 9/20/2024 I didnt know that the Math.atan2() functions parameters are ( y, x ) instead of ( x, y ). It is the stupidest thing ever and I hate it with every fibre and fiber of my being.
        }else{
            return new Line2D.Double(0,0,0,0);
        }
    }
}
