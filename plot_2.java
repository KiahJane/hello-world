// My second plot
//import classes & packages
import javax.swing.*;                    // for JButtons, JPanel, JLabel
import java.awt.*;                       // Abstract Window Toolkit for graphical user interface for front-ent of project
import java.awt.geom.*;                  // to perform 2-dimensional geometry (2d graphs)

public class Plot extends JPanel{
    // initialize coordinates
    int[] cord = { 4, 5, 6, 3, 16, 2, 3, 7, 1 };
    int marg = 50;

    // initialize for y-axis labels
    private static final Font fnt = new Font(Font.MONOSPACED, Font.BOLD, 14);
    int[] numArray = new int[i];
    for (int i = 0; i < Plot.getMax(); i++){
        numArray[i] = i+1;
    }
    //System.out.println(Arrays.toString(numArray));

    // define graph to appear in JFrame
    protected void paintComponent(Graphics grf) {
        //create instance of the Graphics to use its methods
        super.paintComponent(grf);
        Graphics2D graph = (Graphics2D) grf;

        //Sets the value of a single preference for the rendering algorithms
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // get width and height
        int width = getWidth();
        int height = getHeight();

        // draw graph -- x & y axes
        graph.draw(new Line2D.Double(marg, marg - 20, marg, height - marg));
        graph.draw(new Line2D.Double(marg, height - marg, width - marg + 20, height - marg));

        // label y-axis
        String text = String.valueOf(number);
        g.setFont(fnt);
        FontMetrics fontMetrics = g.getFontMetrics();
        int textY = y + fontMetrics.getHeight() + TEXT_GAP;
        g.drawString(text, textX, textY);

        //find value of x and scale to plot points
        double x = (double) (width - 2 * marg) / (cord.length - 1);
        double scale = (double) (height - 2 * marg) / getMax();

        //set color for points
        graph.setPaint(Color.RED);

        // set points to the graph
        for (int i = 0, pos = 10; i < cord.length; i++, pos++) {
            double x1 = (marg + i * x) + 10;
            double y1 = height - marg - scale * cord[i];
            graph.fill(new Ellipse2D.Double(x1 - 2, y1 - 2, 4, 4));

            // draw dashed lines
            drawDashedLine(grf, x1, y1, x1, height - marg);
        }
    }

    // add dashed lines from points to x-axis
    private void drawDashedLine(Graphics grf, double x1, double y1, double x2, int y2){
        //creates a copy of the Graphics instance
        Graphics2D graph = (Graphics2D) grf.create();

        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        graph.setStroke(dashed);
        Shape l = new Line2D.Double(x1, y1, x2, y2);
        graph.draw(l);

        //gets rid of the copy
        graph.dispose();
    }

    //create getMax() method to find maximum value
    private static int getMax(){
        int max = -Integer.MAX_VALUE;
        for(int i=0; i<cord.length; i++){
            if(cord[i]>max)
                max = cord[i];
        }
        return max;
    }

    //main() method start -- runs the class Plot
    public static void main(String args[]){
        // get user input


        //create an instance of JFrame class
        JFrame frame = new JFrame();
        // set size, layout and location for frame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Plot());
        frame.setSize(400, 400);
        frame.setLocation(200, 200);
        frame.setVisible(true);
    }
}