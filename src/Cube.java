import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * <h1>Cube Class</h1>
 *
 * <p>This is the cube class in which we will virtually represent the cube.</p>
 *
 * @version 0.0.2
 * <p>created: 4/24/19</p>
 * <p>updated: 5/3/19</p>
 * @author Lauryn Jefferson
 */
public class Cube {

    //constants
    private final Color[] COLORS = {Color.WHITE,Color.ORANGE, Color.GREEN, Color.YELLOW, Color.RED, Color.BLUE};

    //variables
    private double cWidth, cHeight;
    private int xDim, yDim;
    private double arc;
    private int padding;
    private int faceCount;

    //objects
    ArrayList<Face> faces;

    /**
     * <h2>Cube() constructor</h2>
     *
     * <p>Here is where the cube is set up for use.</p>
     */
    public Cube()
    {
        cWidth = cHeight = 300;
        faceCount = 1;
        xDim = yDim = 3;
        arc = 10;
        padding = 2;
        faces = new ArrayList<Face>(faceCount);
        faces.add(new Face());
    }

    /**
     * <h2>render() method</h2>
     *
     * <p>This method draws the cube to the screen using a JavaFX GraphicsContext object.</p>
     *
     * @param gc graphics context of where you want to draw to
     */
    public void render(GraphicsContext gc)
    {
        for(Face f: faces)
            f.drawFace(gc);
    }


    /**
     * <h1>Face Class</h1>
     *
     * <p>This class represents the face of the cube. There will be six faces total on the cube.</p>
     */
    class Face
    {
        private int[][] colors;
        //Faces nodes to be implemented later
        /*private Face left;
        private Face right;
        private Face top;
        private Face bottom;*/

        public Face()
        {
            solidFace(4);
            //randomFace();
        }

        /**
         * <h2>randomFace() method</h2>
         *
         * <p>This method randomly generates the colors of the face.</p>
         */
        private void randomFace()
        {
            colors = new int[xDim][yDim];
            for(int x = 0; x < colors.length; x++)
                for(int y = 0; y < colors[0].length; y++)
                    colors[x][y] = (int)(Math.random() * 6);
        }

        /**
         * <h2>solidFace() method</h2>
         *
         * <p>This method solidly colors the face of the cube with col as the color.</p>
         *
         * @param col col is the color in which the solid face will be colored
         */
        private void solidFace(int col)
        {
            if(col > 6)
                col = (int)(Math.random() * 6);
            colors = new int[xDim][yDim];
            for(int x = 0; x < colors.length; x++)
                for(int y = 0; y < colors[0].length; y++)
                    colors[x][y] = col;
        }

        /**
         * <h2>drawFace() method</h2>
         *
         * <p>This method draws the current side of the cube that is being faced to the screen using a javaFX GraphicsContext object.</p>
         *
         * @param gc graphics context of where you want to draw to
         */
        private void drawFace(GraphicsContext gc)
        {
            double width = gc.getCanvas().getWidth();
            double height = gc.getCanvas().getHeight();
            int cx = 0;
            int cy = 0;

            gc.fillRoundRect(width/2 - cWidth/2 - (padding*xDim),height/2 - cHeight/2 - (padding*yDim),cWidth + padding*5, cHeight + padding*5,arc,arc);

            for(double x = width/2 - cWidth/2 - (padding*xDim)/2; x < width/2 + cWidth/2 + (padding*xDim)/2; x+= (cWidth/xDim) + padding)
            {
                for(double y = height/2 - cHeight/2 - (padding*yDim)/2; y < height/2 + cHeight/2 + (padding*yDim)/2; y+= (cHeight/yDim) + padding)
                {
                    gc.setFill(COLORS[colors[cx][cy]]);
                    gc.fillRoundRect(x,y, cWidth/xDim,cHeight/yDim,arc,arc);
                    cy++;
                }
                cx++;
                cy = 0;
            }
        }
    }
}
