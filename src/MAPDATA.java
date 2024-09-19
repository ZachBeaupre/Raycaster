import java.awt.geom.Line2D;
import java.util.ArrayList;

//this class is designed for importing SVG documents, pulling the points and puttiung them into an array of line2d objects for me to make map making a breeze.
//this is unoptimized
public class MAPDATA {

    String quot = "\"";
    public static Line2D.Double[] LN2DSVG(String SVG) {
        return LN2DSVG(SVG,1);
    }

    public static Line2D.Double[] LN2DSVG(String SVG,double scaleFactor){
        int lastPosition = 0;
//width and height
if(SVG.contains("width") && SVG.contains("height")) {
    double wid = Double.parseDouble(SVG.substring(SVG.indexOf("width=\"") + 7, SVG.indexOf("\"", SVG.indexOf("width=\"") + 7)));
    double hei = Double.parseDouble(SVG.substring(SVG.indexOf("height=\"") + 8, SVG.indexOf("\"", SVG.indexOf("height=\"") + 8)));
}

//set up the lists for the lines
        ArrayList<Double> x1 = new ArrayList<>();
        ArrayList<Double> y1 = new ArrayList<>();
        ArrayList<Double> x2 = new ArrayList<>();
        ArrayList<Double> y2 = new ArrayList<>();

        lastPosition = SVG.indexOf("\"",SVG.indexOf("height=\"")+8);
//parse line segments and convert
        if(SVG.contains("x1=\"")) {
            while (SVG.indexOf("x1=\"", lastPosition) != -1) {
                x1.add(Double.parseDouble(SVG.substring(SVG.indexOf("x1=\"", lastPosition) + 4, SVG.indexOf("\"", SVG.indexOf("x1=\"", lastPosition) + 4))) / scaleFactor); //apply transformations
                y1.add(Double.parseDouble(SVG.substring(SVG.indexOf("y1=\"", lastPosition) + 4, SVG.indexOf("\"", SVG.indexOf("y1=\"", lastPosition) + 4))) / scaleFactor);
                x2.add(Double.parseDouble(SVG.substring(SVG.indexOf("x2=\"", lastPosition) + 4, SVG.indexOf("\"", SVG.indexOf("x2=\"", lastPosition) + 4))) / scaleFactor);
                y2.add(Double.parseDouble(SVG.substring(SVG.indexOf("y2=\"", lastPosition) + 4, SVG.indexOf("\"", SVG.indexOf("y2=\"", lastPosition) + 4))) / scaleFactor);
                lastPosition = SVG.indexOf("\"", SVG.indexOf("y2=\"", lastPosition) + 4);
            }
        }


//        <polyline points="266,97.26279441628823 255,116.31535329954589 266,135.36791218280354 255,154.42047106606117" />
        if(SVG.contains("polyline points=\"")) {
            lastPosition = 0;
            while (SVG.indexOf("polyline points=\"", lastPosition) != -1) {
                //for polylines, find the word polyline points. point a point b point c    a,b b,c
                x1.add(Double.parseDouble(SVG.substring(SVG.indexOf("polyline points=\"", lastPosition) + 17, SVG.indexOf(",", SVG.indexOf("polyline points=\"", lastPosition) + 17))) / scaleFactor); //apply transformations
                lastPosition = SVG.indexOf(",", SVG.indexOf("polyline points=\"", lastPosition) + 17);
                y1.add(Double.parseDouble(SVG.substring(SVG.indexOf(",", lastPosition) + 1, SVG.indexOf(" ", SVG.indexOf(",", lastPosition) + 1))) / scaleFactor);
                lastPosition = SVG.indexOf(" ", SVG.indexOf(",", lastPosition) + 1);
                x2.add(Double.parseDouble(SVG.substring(SVG.indexOf(" ", lastPosition) + 1, SVG.indexOf(",", SVG.indexOf(" ", lastPosition) + 1))) / scaleFactor); //apply transformations
                lastPosition = SVG.indexOf(",", SVG.indexOf(" ", lastPosition) + 1);
                y2.add(Double.parseDouble(SVG.substring(SVG.indexOf(",", lastPosition) + 1, SVG.indexOf(" ", SVG.indexOf(",", lastPosition) + 1))) / scaleFactor);
                lastPosition = SVG.indexOf(" ", SVG.indexOf(",", lastPosition) + 1);

                while (SVG.charAt(lastPosition) != '"') {

                    //lastPosition = SVG.indexOf("polyline points=\"",lastPosition);
                    x1.add(x2.getLast());
                    y1.add(y2.getLast());
                    x2.add(Double.parseDouble(SVG.substring(SVG.indexOf(" ", lastPosition) + 1, SVG.indexOf(",", SVG.indexOf(" ", lastPosition) + 1))) / scaleFactor); //apply transformations
                    lastPosition = SVG.indexOf(",", SVG.indexOf(" ", lastPosition) + 1);
                    y2.add(Double.parseDouble(SVG.substring(SVG.indexOf(",", lastPosition) + 1, Math.min(SVG.indexOf(" ", SVG.indexOf(",", lastPosition) + 1), SVG.indexOf("\"", SVG.indexOf(",", lastPosition) + 1)))) / scaleFactor);
                    lastPosition = Math.min(SVG.indexOf(" ", SVG.indexOf(",", lastPosition) + 1), SVG.indexOf("\"", SVG.indexOf(",", lastPosition) + 1));

                }
            }
        }
        Line2D.Double[] LN2D = new Line2D.Double[x1.size()];//set the list size to the length of x1 to ensure adequate room.
        for(int i = 0; i < x1.size(); i++){
            LN2D[i] = new Line2D.Double(x1.get(i),y1.get(i),x2.get(i),y2.get(i));
        }



        //it prints the level file
        String falseobject = "new Line2D.Double(";
        System.out.print("{");
        for(int i = 0;i<x1.size(); i++) {
            System.out.print(falseobject);
            System.out.print(x1.get(i)+ "," + y1.get(i) + "," + x2.get(i) + "," + y2.get(i) + ")");

            if (i == 0){falseobject = ", " + falseobject;}
        }
        System.out.print("};");

        return LN2D;
    }
}

