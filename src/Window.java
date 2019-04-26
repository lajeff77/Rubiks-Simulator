
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


import javafx.stage.Stage;

/**
 * <h1>Window Class</h1>
 *
 * <p>This class controls the window and user interfacing aspects of the application.</p>
 *
 * @version 0.0.1
 * <p>created: 4/24/19</p>
 * <p>updated: 4/25/19</p>
 * @author Lauryn Jefferson
 */
public class Window extends Application {

    //objects
    private Cube c;

    /**
     * <h2>Window() default constructor</h2>
     *
     * <p>This is the default constructor.</p>
     */
    public Window() {
        //used for the launch of Application
        c = new Cube();
    }

    /**
     * <h2>Window() constructor</h2>
     *
     * <p>This constructor takes in the commandline arguments to satisfy JavaFX.</p>
     *
     * @param args command line arguments
     */
    public Window(String[] args) {
        this();
        launch(args);

    }

    /**
     * <h2>start() method</h2>
     *
     * <p>Inherited from JavaFX Application, this method initializes the window.</p>
     *
     * @param primaryStage given stage by JavaFX
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rubik's Simulator");
        Group root = new Group();
        Canvas canvas = new Canvas(800, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        render(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * <h2>render() method</h2>
     *
     * <p>This method draws everyhting happening in the application to the screen using the JavaFX GraphicsContext object.</p>
     *
     * @param gc graphics context of where you want to draw to
     */
    public void render(GraphicsContext gc) {
        c.render(gc);
    }

}
