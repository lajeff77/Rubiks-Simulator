import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <h1>Cube Class</h1>
 *
 * <p>This is the cube class in which we will virtually represent the cube.</p>
 *
 * @version 0.0.8
 * <p>created: 4/24/19</p>
 * <p>updated: 5/26/20</p>
 * @author Lauryn Jefferson
 */
public class Cube {

    //constants
    private final Color[] COLORS = {Color.WHITE, Color.ORANGE, Color.GREEN, Color.YELLOW, Color.RED, Color.BLUE};
    private final long animTime = 1000;//one second
    //variables
    private double cWidth, cHeight;
    private int xDim, yDim;
    private double arc;
    private int padding;
    private int faceCount;
    private double faceWidth, faceLength;
    private double cubieWidth, cubieHeight;
    private boolean executing;
    private long moveStart;

    //objects
    private Face curr, left, right, top, bottom, opp;//nodes to represent orientation of the cube
    private Queue<String> moveQueue;

    /**
     * <h2>Cube() constructor</h2>
     *
     * <p>Here is where the cube is set up for use.</p>
     */
    public Cube() {
        cWidth = 150;
        cHeight = 150;
        faceCount = 1;
        xDim = yDim = 3;
        arc = 10;
        padding = 2;
        faceWidth = padding * (xDim - 1) + padding * 3 + cWidth;
        faceLength = padding * (yDim - 1) + padding * 3 + cHeight;
        cubieWidth = (cWidth / xDim);
        cubieHeight = (cHeight / yDim);

        executing = false;
        moveQueue = new LinkedList<>();


        //set all the face nodes
        reset();
    }

    /**
     * <h2>render() method</h2>
     *
     * <p>This method draws the cube to the screen using a JavaFX GraphicsContext object.</p>
     *
     * @param gc graphics context of where you want to draw to
     */
    public void render(GraphicsContext gc) {
        //curr.drawFace(gc);
        drawCube(gc);
    }

    /**
     * <h2>xMove() method</h2>
     *
     * <p>This method rotates the cube clockwise by 90 degrees in the x direction.</p>
     */
    public void xMove() {
        Face temp = curr;
        curr = bottom;
        bottom = opp;
        opp = top;
        top = temp;

        bottom.clockwiseTwist();
        bottom.clockwiseTwist();

        opp.clockwiseTwist();
        opp.clockwiseTwist();

        right.clockwiseTwist();
        left.counterClockwiseTwist();
    }

    /**
     * <h2>xPrimeMove() method</h2>
     *
     * <p>This method rotates the cube counter-clockwise by 90 degrees in the x direction.</p>
     */
    public void xPrimeMove() {
        Face temp = curr;
        curr = top;
        top = opp;
        opp = bottom;
        bottom = temp;

        top.clockwiseTwist();
        top.clockwiseTwist();

        opp.clockwiseTwist();
        opp.clockwiseTwist();

        right.counterClockwiseTwist();
        left.clockwiseTwist();
    }

    /**
     * <h2>yMove() method</h2>
     *
     * <p>This method rotates the cube clockwise by 90 degrees in the y direction.</p>
     */
    public void yMove() {
        Face temp = curr;
        curr = right;
        right = opp;
        opp = left;
        left = temp;

        top.clockwiseTwist();
        bottom.counterClockwiseTwist();
    }

    /**
     * <h2>yPrimeMove() method</h2>
     *
     * <p>This method rotates the cube counter-clockwise by 90 degrees in the y direction.</p>
     */
    public void yPrimeMove() {
        Face temp = curr;
        curr = left;
        left = opp;
        opp = right;
        right = temp;

        top.counterClockwiseTwist();
        bottom.clockwiseTwist();
    }

    /**
     * <h2>zMove() method</h2>
     *
     * <p>This method rotates the cube clockwise by 90 degrees in the z direction.</p>
     */
    public void zMove() {
        Face temp = left;
        left = bottom;
        bottom = right;
        right = top;
        top = temp;

        left.clockwiseTwist();
        bottom.clockwiseTwist();
        right.clockwiseTwist();
        top.clockwiseTwist();
    }

    /**
     * <h2>zMove() method</h2>
     *
     * <p>This method rotates the cube counter-clockwise by 90 degrees in the z direction.</p>
     */
    public void zPrimeMove() {
        Face temp = left;
        left = top;
        top = right;
        right = bottom;
        bottom = temp;

        left.counterClockwiseTwist();
        bottom.counterClockwiseTwist();
        right.counterClockwiseTwist();
        top.counterClockwiseTwist();
    }

    /**
     * <h2>rMove() method</h2>
     *
     * <p>This method turns the right face clockwise by 90 degrees.</p>
     */
    public void rMove() {
        bottom.swapCol(2,curr);
        opp.swapCol(0,2,bottom);
        top.swapCol(2,0,opp);
        bottom.transposeCol(2);
        opp.transposeCol(0);
        right.clockwiseTwist();
    }

    /**
     * <h2>rPrimeMove() method</h2>
     *
     * <p>This method turns the right face counter-clockwise by 90 degrees.</p>
     */
    public void rPrimeMove()
    {
        curr.swapCol(2,top);
        top.swapCol(2,0,opp);
        opp.swapCol(0,2,bottom);
        top.transposeCol(2);
        opp.transposeCol(0);
        right.counterClockwiseTwist();
    }

    /**
     * <h2>lMove() method</h2>
     *
     * <p>This method turns the left face clockwise by 90 degrees.</p>
     */
    public void lMove()
    {
        curr.swapCol(0,top);
        top.swapCol(0,2,opp);
        opp.swapCol(2,0,bottom);
        top.transposeCol(0);
        opp.transposeCol(2);
        left.clockwiseTwist();
    }

    /**
     * <h2>lPrimeMove() method</h2>
     *
     * <p>This method turns the left face counter-clockwise by 90 degrees.</p>
     */
    public void lPrimeMove()
    {
        bottom.swapCol(0,curr);
        opp.swapCol(2,0,bottom);
        top.swapCol(0,2,opp);
        bottom.transposeCol(0);
        opp.transposeCol(2);
        left.counterClockwiseTwist();
    }

    /**
     * <h2>fMove() method</h2>
     *
     * <p>This method turns the front face clockwise by 90 degrees.</p>
     */
    public void fMove()
    {
        top.swapRowCol(2,2,left);
        bottom.swapRowCol(0,2,left);
        bottom.swapRowCol(0,0,right);

        bottom.transposeRow(0);
        top.transposeRow(2);
        curr.clockwiseTwist();
    }

    /**
     * <h2>fPrimeMove() method</h2>
     *
     * <p>This method turns the front face counter-clockwise by 90 degrees.</p>
     */
    public void fPrimeMove()
    {
        top.swapRowCol(2,2,left);
        top.swapRowCol(2,0,right);
        bottom.swapRowCol(0,0,right);


       left.transposeCol(2);
       right.transposeCol(0);
       curr.counterClockwiseTwist();
    }

    /**
     * <h2>bMove() method</h2>
     *
     * <p>This method turns the back face clockwise by 90 degrees.</p>
     */
    public void bMove()
    {
        top.swapRowCol(0,0,left);
        top.swapRowCol(0,2,right);
        bottom.swapRowCol(2,2,right);

        left.transposeCol(0);
        right.transposeCol(2);
        opp.clockwiseTwist();
    }

    /**
     * <h2>bPrimeMove() method</h2>
     *
     * <p>This method turns the back face counter-clockwise by 90 degrees.</p>
     */
    public void bPrimeMove()
    {
        top.swapRowCol(0,0,left);
        bottom.swapRowCol(2,0,left);
        bottom.swapRowCol(2,2,right);

        bottom.transposeRow(2);
        top.transposeRow(0);
        opp.counterClockwiseTwist();
    }

    /**
     * <h2>uMove() method</h2>
     *
     * <p>This method turns the upward face clockwise by 90 degrees.</p>
     */
    public void uMove()
    {
        curr.swapRow(0,right);
        right.swapRow(0,opp);
        opp.swapRow(0,left);
        top.clockwiseTwist();

    }

    /**
     * <h2>uPrimeMove() method</h2>
     *
     * <p>This method turns the upward face counter-clockwise by 90 degrees.</p>
     */
    public void uPrimeMove()
    {
        curr.swapRow(0,left);
        left.swapRow(0,opp);
        opp.swapRow(0,right);
        top.counterClockwiseTwist();
    }

    /**
     * <h2>dMove() method</h2>
     *
     * <p>This method turns the downward face clockwise by 90 degrees.</p>
     */
    public void dMove()
    {
        curr.swapRow(2,left);
        left.swapRow(2,opp);
        opp.swapRow(2,right);
        bottom.clockwiseTwist();
    }

    /**
     * <h2>dPrimeMove() method</h2>
     *
     * <p>This method turns the downward face counter-clockwise by 90 degrees.</p>
     */
    public void dPrimeMove()
    {
        curr.swapRow(2,right);
        right.swapRow(2,opp);
        opp.swapRow(2,left);
        bottom.counterClockwiseTwist();
    }

    /**
     * <h2>mMove() method</h2>
     *
     * <p>This method slices the middle layer 90 degrees clockwise in the x direction.</p>
     */
    public void mMove()
    {
        rMove();
        lPrimeMove();
        xPrimeMove();
    }

    /**
     * <h2>mPrimeMove() method</h2>
     *
     * <p>This method slices the middle layer 90 degrees counter-clockwise in the x direction.</p>
     */
    public void mPrimeMove()
    {
        rPrimeMove();
        lMove();
        xMove();
    }

    /**
     * <h2>eMove() method</h2>
     *
     * <p>This method slices the middle layer 90 degrees clockwise in the y direction.</p>
     */
    public void eMove()
    {
        uMove();
        dPrimeMove();
        yPrimeMove();
    }

    /**
     * <h2>ePrimeMove() method</h2>
     *
     * <p>This method slices the middle layer 90 degrees counter-clockwise in the y direction.</p>
     */
    public void ePrimeMove()
    {
        uPrimeMove();
        dMove();
        yMove();
    }

    /**
     * <h2>sMove() method</h2>
     *
     * <p>This method slices the middle layer 90 degrees clockwise in the z direction.</p>
     */
    public void sMove()
    {
        fPrimeMove();
        bMove();
        zMove();
    }

    /**
     * <h2>sPrimeMove() method</h2>
     *
     * <p>This method slices the middle layer 90 degrees counter-clockwise in the z direction.</p>
     */
    public void sPrimeMove()
    {
        fMove();
        bPrimeMove();
        zPrimeMove();
    }

    /**
     * <h2>rwMove() method</h2>
     *
     * <p>This method turns the right face and middle layer clockwise by 90 degrees.</p>
     */
    public void rwMove()
    {
        lMove();
        xMove();
    }

    /**
     * <h2>rwPrimeMove() method</h2>
     *
     * <p>This method turns the right face and middle layer counter-clockwise by 90 degrees.</p>
     */
    public void rwPrimeMove()
    {
        lPrimeMove();
        xPrimeMove();
    }

    /**
     * <h2>lwMove() method</h2>
     *
     * <p>This method turns the left face and middle layer clockwise by 90 degrees.</p>
     */
    public void lwMove()
    {
        rMove();
        xPrimeMove();
    }

    /**
     * <h2>lwPrimeMove() method</h2>
     *
     * <p>This method turns the left face and middle layer counter-clockwise by 90 degrees.</p>
     */
    public void lwPrimeMove()
    {
        rPrimeMove();
        xMove();
    }

    /**
     * <h2>fwMove() method</h2>
     *
     * <p>This method turns the front face and middle layer clockwise by 90 degrees.</p>
     */
    public void fwMove()
    {
        bMove();
        zMove();
    }

    /**
     * <h2>fwPrimeMove() method</h2>
     *
     * <p>This method turns the front face and middle layer counter-clockwise by 90 degrees.</p>
     */
    public void fwPrimeMove()
    {
        bPrimeMove();
        zPrimeMove();
    }

    /**
     * <h2>bwMove() method</h2>
     *
     * <p>This method turns the back face and middle layer clockwise by 90 degrees.</p>
     */
    public void bwMove()
    {
        fMove();
        zPrimeMove();
    }

    /**
     * <h2>bwPrimeMove() method</h2>
     *
     * <p>This method turns the back face and middle layer counter-clockwise by 90 degrees.</p>
     */
    public void bwPrimeMove()
    {
        fPrimeMove();
        zMove();
    }

    /**
     * <h2>uwMove() method</h2>
     *
     * <p>This method turns the upward face and middle layer clockwise by 90 degrees.</p>
     */
    public void uwMove()
    {
        dMove();
        yMove();
    }

    /**
     * <h2>uwPrimeMove() method</h2>
     *
     * <p>This method turns the upward face and middle layer counter-clockwise by 90 degrees.</p>
     */
    public void uwPrimeMove()
    {
        dPrimeMove();
        yPrimeMove();
    }

    /**
     * <h2>dwMove() method</h2>
     *
     * <p>This method turns the downward face and middle layer clockwise by 90 degrees.</p>
     */
    public void dwMove()
    {
        uMove();
        yPrimeMove();
    }

    /**
     * <h2>dwPrimeMove() method</h2>
     *
     * <p>This method turns the downward face and middle layer counter-clockwise by 90 degrees.</p>
     */
    public void dwPrimeMove()
    {
        uPrimeMove();
        yMove();
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
         * <p>This method solidly colors the face of the cube with an integer representing the color.</p>
         *
         * @param color color is the color in which the solid face will be colored
         */
        private void solidFace(int color)
        {
            if(color > 6)
                color = (int)(Math.random() * 6);
            colors = new int[xDim][yDim];
            for(int x = 0; x < colors.length; x++)
                for(int y = 0; y < colors[0].length; y++)
                    colors[x][y] = color;
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

        /**
         * <h2>swapRow() method</h2>
         *
         * <h>This method swaps the specified row between this face and the specified face.</h>
         *
         * @param rowNum row index to swap
         * @param otherFace the face that this face will swap rows with
         */
        public void swapRow(int rowNum, Face otherFace)
        {
            for(int i = 0; i < xDim; i++)
            {
                int temp = colors[i][rowNum];
                colors[i][rowNum] = otherFace.colors[i][rowNum];
                otherFace.colors[i][rowNum] = temp;
            }
        }

        /**
         * <h2>swapRow() method</h2>
         *
         * <h>This method swaps two different rows of between this face and the specified face.</h>
         *
         * @param rowNum row index of this face
         * @param otherRow row index of other face
         * @param otherFace the face that this face will swap rows with
         */
        public void swapRow(int rowNum, int otherRow, Face otherFace)
        {
            for(int i = 0; i < xDim; i++)
            {
                int temp = colors[i][rowNum];
                colors[i][rowNum] = otherFace.colors[i][otherRow];
                otherFace.colors[i][otherRow] = temp;
            }
        }

        /**
         * <h2>swapCol() method</h2>
         *
         * <h>This method swaps the specified column between this face and the specified face.</h>
         *
         * @param colNum column index to swap
         * @param otherFace the face that this face will swap columns with
         */
        public void swapCol(int colNum, Face otherFace)
        {
            for(int i = 0; i < yDim; i++)
            {
                int temp = colors[colNum][i];
                colors[colNum][i] = otherFace.colors[colNum][i];
                otherFace.colors[colNum][i] = temp;
            }
        }

        /**
         * <h2>swapCol() method</h2>
         *
         * <h>This method swaps two different columns of between this face and the specified face.</h>
         *
         * @param colNum column index of this face
         * @param otherCol column index of other face
         * @param otherFace the face that this face will swap columns with
         */
        public void swapCol(int colNum, int otherCol, Face otherFace)
        {
            for(int i = 0; i < yDim; i++)
            {
                int temp = colors[colNum][i];
                colors[colNum][i] = otherFace.colors[otherCol][i];
                otherFace.colors[otherCol][i] = temp;
            }
        }

        /**
         * <h2>swapRowCol() method</h2>
         *
         * <p>This method swaps the specified row of this face with the specified column of the other face.</p>
         *
         * @param rowNum row index of this face
         * @param otherCol column index of other face
         * @param otherFace the face that will be swap it's column with the row
         */
        public void swapRowCol(int rowNum, int otherCol,Face otherFace)
        {
            for(int i = 0; i < yDim; i++)
            {
                int temp = colors[i][rowNum];
                colors[i][rowNum] = otherFace.colors[otherCol][i];
                otherFace.colors[otherCol][i] = temp;
            }
        }

        /**
         * <h2>transposeRow()method</h2>
         *
         * <p>This method transposes the given row of the face.</p>
         *
         * @param rowNum row number to be transposed
         */
        public void transposeRow(int rowNum)
        {
            int temp = colors[0][rowNum];
            colors[0][rowNum] = colors[2][rowNum];
            colors[2][rowNum] = temp;
        }

        /**
         * <h2>transposeCol()method</h2>
         *
         * <p>This method transposes the given column of the face.</p>
         *
         * @param colNum column number to be transposed
         */
        public void transposeCol(int colNum)
        {
            int temp = colors[colNum][0];
            colors[colNum][0] = colors[colNum][2];
            colors[colNum][2] = temp;
        }


        /**
         * <h2>clockwiseTwist() method</h2>
         *
         * <p>This method twists all the corners and edges of the face clockwise.</p>
         */
        public void clockwiseTwist()
        {
            int temp = colors[0][0];
            //flip corners
            colors[0][0] = colors[0][2];
            colors[0][2] = colors[2][2];
            colors[2][2] = colors[2][0];
            colors[2][0] = temp;

            //flip centers
            temp = colors[1][0];
            colors[1][0] = colors[0][1];
            colors[0][1] = colors[1][2];
            colors[1][2] = colors[2][1];
            colors[2][1] = temp;
        }

        /**
         * <h2>counterClockwiseTwist() method</h2>
         *
         * <p>This method twists all the corners and edges of the face counter-clockwise.</p>
         */
        public void counterClockwiseTwist()
        {
            int temp = colors[0][0];
            //flip corners
            colors[0][0] = colors[2][0];
            colors[2][0] = colors[2][2];
            colors[2][2] = colors[0][2];
            colors[0][2] = temp;

            //flip centers
            temp = colors[1][0];
            colors[1][0] = colors[2][1];
            colors[2][1] = colors[1][2];
            colors[1][2] = colors[0][1];
            colors[0][1] = temp;
        }

        /**
         * <h2>isSolid() method</h2>
         *
         * <p>This method checks to see if the face is one solid color.</p>
         *
         * @return whether the face is solid
         */
        public boolean isSolid()
        {
            int color = colors[0][0];
            for(int i = 0; i < xDim; i++)
                for(int j = 0; j < yDim; j++)
                    if(color != colors[i][j])
                        return false;
            return true;
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

    /**
     * <h2>isSolved() method</h2>
     *
     * <p>This method checks if all the sides are one solid color to determine whether the cube is solved.</p>
     *
     * @return whether the cube is solved
     */
    public boolean isSolved()
    {
        return curr.isSolid() && top.isSolid() && bottom.isSolid() && left.isSolid() && right.isSolid() && opp.isSolid();
    }

    /**
     * <h2>reset() method</h2>
     *
     * <p>This method resets the cube into a solved state.</p>
     */
    public void reset()
    {
        curr = new Face(2);
        left = new Face(1);
        right = new Face(4);
        top = new Face(0);
        bottom = new Face(3);
        opp = new Face(5);
    }

    /**
     * <h2>executeAlgorithm()</h2>
     *
     * <p>This method takes a string representing an algorithm and executes it move by move.</p>
     *
     * @param alg a string representing the algorithm
     */
    public void executeAlgorithm(String alg)
    {
        //set up tokenizer
        String delimiters = ", ";
        StringTokenizer tokenizer = new StringTokenizer(alg,delimiters);
        executing = true;

        //tokenize the algorithm
        while(tokenizer.hasMoreElements()){
            String temp = tokenizer.nextToken();
            if(!temp.equals(""))
            {
                if(temp.contains("2"))
                {
                    temp = temp.replace("2","");
                    moveQueue.add(temp);
                    moveQueue.add(temp);
                }
                else
                    moveQueue.add(temp);
            }
        }

        //animate the first move
        moveStart = System.currentTimeMillis();
        if(moveQueue.size() > 0)
            decideMove(moveQueue.remove());
    }

    /**
     * <h2>pauseExecution() method</h2>
     *
     * <p>This method pauses the execution of the algorithm entered without clearing it.</p>
     */
    public void pauseExecution()
    {
        executing = false;
    }

    /**
     * <h2>resumeExecution() method</h2>
     *
     * <p>This method resumes the exexcution of an algorithm after it's been paused.</p>
     */
    public void resumeExecution()
    {
        executing = true;
    }

    /**
     * <h2>stopExecution() method</h2>
     *
     * <p>This method stops the execution of the algorithm and clears it.</p>
     */
    public void stopExecution()
    {
        moveQueue.clear();
        executing = false;
    }

    /**
     * <h2>isExecuting() method</h2>
     *
     * <p>This method returns true if the cube is executing an algorithm and false if it isn't.</p>
     *
     * @return boolean representing execution
     */
    public boolean isExecuting()
    {
        return executing;
    }

    /**
     * <h2>hasMovesToExecute()</h2>
     *
     * <p>This method returns true if there are more moves left to be executed and false if there are no moves to be executed.</p>
     *
     * @return whether there are moves to execute left
     */
    public boolean hasMovesToExecute()
    {
        return moveQueue.size()>0;
    }

    /**
     * <h2>update() method</h2>
     *
     * <p>This method updates the status of the cube.</p>
     */
    public void update()
    {
        if(executing)
        {
            if(moveQueue.size() == 0)
            {
                //no moves left
                executing = false;
            }
            else
            {
                //still moves
                if(System.currentTimeMillis() - moveStart > animTime)
                {
                    //next move
                    decideMove(moveQueue.remove());
                    moveStart = System.currentTimeMillis();
                }
            }

        }
    }

    /**
     * <h2>decideMove() method</h2>
     *
     * <p>This method is a helper method that chooses a move based off of the given string/</p>
     * @param move string dictating what move to perform
     */
    private void decideMove(String move)
    {
        switch(move)
        {
            case("R"):
                rMove();
                break;
            case("R'"):
                rPrimeMove();
                break;
            case("L"):
                lMove();
                break;
            case("L'"):
                lPrimeMove();
                break;
            case("F"):
                fMove();
                break;
            case("F'"):
                fPrimeMove();
                break;
            case("B"):
                bMove();
                break;
            case("B'"):
                bPrimeMove();
                break;
            case("U"):
                uMove();
                break;
            case("U'"):
                uPrimeMove();
                break;
            case("D"):
                dMove();
                break;
            case("D'"):
                dPrimeMove();
                break;
            case("X"):
                xMove();
                break;
            case("X'"):
                xPrimeMove();
                break;
            case("Y"):
                yMove();
                break;
            case("Y'"):
                yPrimeMove();
                break;
            case("Z"):
                zMove();
                break;
            case("Z'"):
                zPrimeMove();
                break;
            case("M"):
                mMove();
                break;
            case("M'"):
                mPrimeMove();
                break;
            case("E"):
                eMove();
                break;
            case("E'"):
                ePrimeMove();
                break;
            case("S"):
                sMove();
                break;
            case("S'"):
                sPrimeMove();
                break;
            case("r"):
                rwMove();
                break;
            case("r'"):
                rwPrimeMove();
                break;
            case("l"):
                lwMove();
                break;
            case("l'"):
                lwPrimeMove();
                break;
            case("f"):
                fwMove();
                break;
            case("f'"):
                fwPrimeMove();
                break;
            case("b"):
                bwMove();
                break;
            case("b'"):
                bwPrimeMove();
                break;
            case("u"):
                uwMove();
                break;
            case("u'"):
                uwPrimeMove();
                break;
            case("d"):
                dwMove();
                break;
            case("d'"):
                dwPrimeMove();
                break;
            default:
                System.out.println("Erroneous letter: " + move);
                break;
        }
    }
}
