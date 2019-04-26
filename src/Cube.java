import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * <h1>Cube Class</h1>
 *
 * <p>This is the cube class in which we will virtually represent the cube.</p>
 *
 * @version 0.0.1
 * <p>created: 4/24/19</p>
 * <p>updated: 4/25/19</p>
 * @author Lauryn Jefferson
 */
public class Cube {

    //variables
    private double cWidth, cHeight;
    private int xDim, yDim;
    private double arc;
    private int padding;

    /**
     * <h2>Cube() constructor</h2>
     *
     * <p>Here is where the cube is set up for use.</p>
     */
    public Cube()
    {
        cWidth = cHeight = 300;
        xDim = yDim = 3;
        arc = 10;
        padding = 2;
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
        drawFace(gc);
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
        gc.setFill(Color.RED);
        gc.setLineWidth(2);
        //gc.strokeRect(width/2 - cWidth/2,height/2 -cHeight/2, cWidth, cHeight);
        for(double x = width/2 - cWidth/2 - (padding*xDim)/2; x < width/2 + cWidth/2 + (padding*xDim)/2; x+= (cWidth/xDim) + padding)
        {
            for(double y = height/2 - cHeight/2 - (padding*yDim)/2; y < height/2 + cHeight/2 + (padding*yDim)/2; y+= (cHeight/yDim) + padding)
            {
                gc.fillRoundRect(x,y, cWidth/xDim,cHeight/yDim,arc,arc);
            }
        }
    }
}
