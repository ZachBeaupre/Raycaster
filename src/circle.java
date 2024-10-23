import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class circle {

//ideas

    protected double x;
    protected double y;
    protected double d;
    protected double r;
    public Ellipse2D.Double circ;


    public circle(double x, double y, double d) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.circ = new Ellipse2D.Double(x, y, d, d);

    }

    public double getDiameter() {
        return this.d;
    }

    public double getRadius() {
        return this.d / 2;
    }

    public boolean intersectsWith(Line2D.Double L) {

        double X1 = L.getX1();
        double Y1 = L.getY1();
        double X2 = L.getX2();
        double Y2 = L.getY2();
        double A2 = Math.pow(Y2 - Y1, 2);
        double B2 = Math.pow(X1 - X2, 2);
        double C2 = Math.pow(Y1 * (X2 - X1) - (Y2 - Y1) * X1, 2);

        double R2 = Math.pow(this.getRadius(), 2);


        return (R2 * (A2 + B2) - C2) >= 0;

    }


    public circle getCirc() {
        return new circle(this.x, this.y, this.d);
    }

    public Ellipse2D.Double getGeom() {
        return new Ellipse2D.Double(this.x-this.d/2, this.y-this.d/2, this.d, this.d);
    }
    public Point2D.Double getCenter() {
        return new Point2D.Double(this.x, this.y);
    }

    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(this.x, this.y, this.d, this.d);
    }

    public static Point2D.Double pointIntersectCircle(Line2D.Double L, circle c) {
        return pointIntersectCircle(L, c, true);
    }
    public void setCenter(double x, double y){
        this.x = x;
        this.y = y;
    }


public static Point2D.Double pointIntersectCircle(Line2D.Double L, circle c, boolean closestToLX1) {
    //https://en.wikipedia.org/wiki/Intersection_(geometry)
    //the eq listed here wanted a line equation in standard form, but my lines would need to be converted to 2 point lines
    // https://www.omnicalculator.com/math/line-equation-from-two-points#:~:text=To%20compute%20the%20standard%20form%20equation%20of%20the,0%20with%20A%2C%20B%2C%20and%20C%20as%20above.
//this offered some help
    //for some reason the omni calculator gave me a conversion equation that equals ax + by + c = 0 or ax + by = -c
    //i need it to equal ax + by - c = 0 or ax + by = c for my equation, but i never caught that
    //Observations : Circle is much more like a circle and less like a parabola that you are inside of all the time
    //unfortunately the circles are translated by their radius or diameter or something -_-
    double C1 = c.getGeom().getX()+c.getRadius();
    double C2 = c.getGeom().getY()+c.getRadius();
    double R2 = Math.pow(c.getRadius(), 2);
    double X1 = L.getX1() - C1;
    double Y1 = L.getY1() - C2;
    double X2 = L.getX2() - C1;
    double Y2 = L.getY2() - C2;
Point2D.Double start = new Point2D.Double(X1+C1, Y1+C2);
    Point2D.Double end = new Point2D.Double(X2+C1, Y2+C2);

    double A = Y2 - Y1;
    double B = X1 - X2;
    double C = -1 * (Y1 * (X2 - X1) - A * X1);
    //double C = -1 * (Y1 * (X2 - X1) - (Y2 - Y1) * X1); //golly gee willikers one less calculation
    double AC = A * C;
    double BC = B * C;
    double A2B2 = Math.pow(A, 2) + Math.pow(B, 2);
    double csqrt = Math.sqrt(R2 * A2B2 - Math.pow(C, 2));
    Point2D.Double c1 = new Point2D.Double(((AC + B * csqrt) / A2B2)+C1, ((BC - A * csqrt) / A2B2)+C2);
    Point2D.Double c2 = new Point2D.Double(((AC - B * csqrt) / A2B2)+C1, ((BC + A * csqrt) / A2B2)+C2);
boolean isc1closest = Math.min(start.distance(c1), start.distance(c2)) == start.distance(c1);

    if(!closestToLX1){
        Point2D.Double c3 = c2;
        c2 = c1;
        c1 = c3;
    }
    if(isc1closest){

        return c1;

    }else{
        return c2;
    }


}



}
