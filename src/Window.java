
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * <h1>Window Class</h1>
 *
 * <p>This class controls the window and user interfacing aspects of the application.</p>
 *
 * @version 0.0.4
 * <p>created: 4/24/19</p>
 * <p>updated: 5/19/20</p>
 * @author Lauryn Jefferson
 */
public class Window extends Application {

    //objects
    private Cube c;
    private Button left, right, top, bottom;

    /**
     * <h2>Window() default constructor</h2>
     *
     * <p>This is the default constructor.</p>
     */
    public Window() {
        //used for the launch of Application
        c = new Cube();

        //face turning buttons
        left = new Button("Left");
        right = new Button("Right");
        top = new Button("Top");
        bottom = new Button("Bottom");
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

        //box for the buttons
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);
        Canvas canvas = new Canvas(1000, 700);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        render(gc);

        //face turning buttons
        left = new Button("Left");
        right = new Button("Right");
        top = new Button("Top");
        bottom = new Button("Bottom");
        left.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                c.yPrimeMove();
            }
        });

        right.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                c.yMove();
            }
        });

        top.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                c.xPrimeMove();
            }
        });

        bottom.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                c.xMove();
            }
        });

        //add the buttons and canvas to the window
        root.getChildren().add(canvas);
        hBox.getChildren().addAll(left,right,top,bottom);
        root.getChildren().add(hBox);
        primaryStage.setScene(new Scene(root));
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Clear the canvas
                gc.clearRect(0, 0, 512,512);
                c.render(gc);
            }
        }.start();

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
