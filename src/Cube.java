import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * <h1>Cube Class</h1>
 *
 * <p>This is the cube class in which we will virtually represent the cube.</p>
 *
 * @version 0.0.4
 * <p>created: 4/24/19</p>
 * <p>updated: 5/19/20</p>
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
    private double faceWidth, faceLength;
    private double cubieWidth, cubieHeight;

    //objects
    private Face curr,left,right,top,bottom,opp;//nodes to represent orientation of the cube

    /**
     * <h2>Cube() constructor</h2>
     *
     * <p>Here is where the cube is set up for use.</p>
     */
    public Cube()
    {
        cWidth = 150;
        cHeight = 150;
        faceCount = 1;
        xDim = yDim = 3;
        arc = 10;
        padding = 2;
        faceWidth = padding*(xDim -1)+ padding*3 + cWidth;
        faceLength = padding*(yDim -1)+ padding*3 + cHeight;
        cubieWidth = (cWidth / xDim);
        cubieHeight = (cHeight / yDim);


        //set all the face nodes
        curr = new Face(2);
        left = new Face(1);
        right = new Face(4);
        top = new Face(0);
        bottom = new Face(3);
        opp = new Face(5);
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
        //curr.drawFace(gc);
        drawCube(gc);
    }

    /**
     * <h2>xMove() method</h2>
     *
     * <p>This method rotates the cube to the bottom face.</p>
     */
    public void xMove()
    {
        Face temp = curr;
        curr = bottom;
        bottom = opp;
        opp = top;
        top = temp;
    }

    /**
     * <h2>xPrimeMove() method</h2>
     *
     * <p>This method rotates the cube to the top face.</p>
     */
    public void xPrimeMove()
    {
        Face temp = curr;
        curr = top;
        top = opp;
        opp = bottom;
        bottom = temp;
    }

    /**
     * <h2>yMove() method</h2>
     *
     * <p>Thie method rotates the cube to the right face.</p>
     */
    public void yMove()
    {
        Face temp = curr;
        curr = right;
        right = opp;
        opp = left;
        left = temp;
    }

    /**
     * <h2>yPrimeMove() method</h2>
     *
     * <p>This method rotates the cube to the left face.</p>
     */
    public void yPrimeMove()
    {
        Face temp = curr;
        curr = left;
        left = opp;
        opp = right;
        right = temp;
    }

    /**
     * <h2>zMove() method</h2>
     *
     * <p>This method rotates the right, top, left, and bottom faces to the right.</p>
     */
    public void zMove()
    {
        Face temp = left;
        left = bottom;
        bottom = right;
        right = top;
        top = temp;
    }

    /**
     * <h2>zMove() method</h2>
     *
     * <p>This method rotates the right, top, left, and bottom faces to the left.</p>
     */
    public void zPrimeMove()
    {
        Face temp = left;
        left = top;
        top = right;
        right = bottom;
        bottom = temp;
    }

    /**
     * <h1>Face Class</h1>
     *
     * <p>This class represents the face of the cube. There will be six faces total on the cube.</p>
     */
    class Face
    {
        //objects
        private int[][] colors;

        public Face()
        {
            this(4);
        }

        public Face(int col)
        {
            solidFace(col);
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
         * <p>This method draws the current face of the cube that is being faced to the screen using a javaFX GraphicsContext object.</p>
         *
         * @param gc graphics context of where you want to draw to
         */
        public void drawFace(GraphicsContext gc, double startX, double startY) {
            //vars to keep track of the color of the cubie
            int cx = 0;
            int cy = 0;

            //the black background for the cube
            gc.setFill(Color.BLACK);
            gc.fillRoundRect(startX, startY, cWidth + padding * 4 + padding *(xDim-1), cHeight + padding * 4 + padding*(yDim-1), arc, arc);

            for (double x = startX + padding*2; x < startX + faceWidth; x += cubieWidth + padding) {
                for (double y = startY + padding*2; y < startY + faceLength; y += cubieHeight + padding) {
                    //draw each square of the cube according to their color
                    gc.setFill(COLORS[colors[cx][cy]]);
                    gc.fillRoundRect(x, y, cWidth / xDim, cHeight / yDim, arc, arc);
                    cy++;
                }
                cx++;
                cy = 0;
            }
        }
    }

    /**
     * <h2>drawCube() method</h2>
     *
     * <p>This method draws all of the faces in the cube using a JavaFX GraphicsContext object.</p>
     *
     * @param gc graphics context of where you want to draw to
     */
    private void drawCube(GraphicsContext gc)
    {
        //set up starting coords
        double midX = gc.getCanvas().getWidth()/2;
        double midY = gc.getCanvas().getHeight()/2;
        double midCubeX = cWidth/2;
        double midCubeY = cHeight/2;
        double currStartX = midX - midCubeX;//- (padding * xDim) / 2;
        double currStartY = midY - midCubeY;//- (padding * yDim) / 2;

        //draw cube by face
        curr.drawFace(gc,currStartX,currStartY);
        left.drawFace(gc, currStartX - faceWidth - padding*2, currStartY);
        right.drawFace(gc, currStartX + faceWidth + padding*2, currStartY);
        opp.drawFace(gc, currStartX + faceWidth*2 + padding*4, currStartY);
        top.drawFace(gc, currStartX, currStartY - faceLength - padding*2);
        bottom.drawFace(gc, currStartX, currStartY + faceLength + padding*2);

    }
}
