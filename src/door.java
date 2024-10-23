


import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
public class door
{
    // instance variables - replace the example below with your own
    protected double x, y, length, closeddir, dir, swingamt;
    protected Point2D pivot;
    protected  boolean state;
    protected  int cr;
    protected  int cl;
    protected  int cb;
    protected  Line2D.Double mydoor;
    protected  int code, swing ;

    protected  final boolean CLOSED = false;
    protected static final boolean OPENED = true;
    public door(){
    }
    public door(double x,double y,double length, double dir,int swing, int code){
        this.x=x;
        this.y=y;
        this.dir = Math.toRadians(dir);
        this.length = length;
        this.swing = swing;
        this.code = code;
        this.state = CLOSED;
        mydoor = new Line2D.Double(this.x, this.y, x+length*Math.cos(this.dir),y+length*Math.sin(this.dir));
        swingamt = 90;
    }
    /**
     * Constructor for objects of class door
     */
    public Line2D.Double getDoor() {
        return mydoor;
    }
    public int getCode () {
        return code;
    }
    public void useDoor(int code) {
        int ff = 1;
        ff= ff * swing;
        if(this.code == code) {
            if (state) {
                ff = -ff;
            }



                dir += ff * Math.toRadians(swingamt);

                mydoor = new Line2D.Double(x, y, x + length * Math.cos(dir), y + length * Math.sin(dir));

            state = !state;

        }
        mydoor =  new Line2D.Double(x, y, x+length*Math.cos(dir),y+length*Math.sin(dir));
    }
    public void setLength(double length){
        this.length = length;
    }
    public void setSwingamt(double swingamt){
        this.swingamt = swingamt;
    }
    public void setPivot(double x,double y){
        this.x = x;
        this.y=y;
    }
    public void setSwing(int swing) {
        this.swing = swing;
    }
}