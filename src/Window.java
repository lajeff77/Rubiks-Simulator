
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * <h1>Window Class</h1>
 *
 * <p>This class controls the window and user interfacing aspects of the application.</p>
 *
 * @version 0.0.8
 * <p>created: 4/24/19</p>
 * <p>updated: 5/26/20</p>
 * @author Lauryn Jefferson
 */
public class Window extends Application {

    //objects
    private Cube c;

    private HBox cRotations, fRotations, slices, wides, solveState, algoField;
    private VBox buttons;
    private BorderPane cubePane;

    private ArrayList<Button> allButtons;
    private Button x, xprime, y, yprime, z, zprime;//cube rotation buttons
    private Button r, rprime, l, lprime, f, fprime, b, bprime, u, uprime, d, dprime;//face rotation buttons
    private Button m,s,e, mprime, sprime, eprime;//slice move buttons
    private Button rw, rwprime, lw, lwprime, fw, fwprime,bw, bwprime, uw, uwprime, dw, dwprime;//wide move buttons

    private Label cRotLabel, fRotLabel, sliceLabel,widesLabel;
    private Label solved;

    private TextField algortihmField;
    private Button execute,pause,resume,stop;

    private Button reset;

    private final String SOLVED = "State: Solved";
    private final String UNSOLVED = "State: Unsolved";

    /**
     * <h2>Window() default constructor</h2>
     *
     * <p>This is the default constructor.</p>
     */
    public Window() {
        //used for the launch of Application
        c = new Cube();

        //set up layout boxes
        cRotations = new HBox();
        fRotations = new HBox();
        slices = new HBox();
        wides = new HBox();
        solveState = new HBox();
        algoField = new HBox();
        buttons = new VBox();
        cubePane = new BorderPane();

        //add padding and space to the layout boxes
        cRotations.setPadding(new Insets(15, 12, 15, 12));
        cRotations.setSpacing(10);

        fRotations.setPadding(new Insets(15, 12, 15, 12));
        fRotations.setSpacing(10);

        slices.setPadding(new Insets(15, 12, 15, 12));
        slices.setSpacing(10);

        wides.setPadding(new Insets(15, 12, 15, 12));
        wides.setSpacing(10);

        solveState.setPadding(new Insets(15, 12, 15, 12));
        solveState.setSpacing(10);

        algoField.setPadding(new Insets(15, 12, 15, 12));
        algoField.setSpacing(10);

        //cube rotation buttons
        x = new Button("x");
        xprime = new Button("x'");

        y = new Button("y");
        yprime = new Button("y'");

        z = new Button("z");
        zprime = new Button("z'");


        //face rotation buttons
        r = new Button("r");
        rprime = new Button("r'");

        l = new Button("l");
        lprime = new Button("l'");

        f = new Button("f");
        fprime = new Button("f'");

        b = new Button("b");
        bprime = new Button("b'");

        u = new Button("u");
        uprime = new Button("u'");

        d = new Button("d");
        dprime = new Button("d'");

        //slice move buttons
        m = new Button("m");
        mprime = new Button("m'");

        e = new Button("e");
        eprime = new Button("e'");

        s = new Button("s");
        sprime = new Button("s'");

        // wide turn buttons
        rw = new Button("rw");
        rwprime = new Button("rw'");

        lw = new Button("lw");
        lwprime = new Button("lw'");

        fw = new Button("fw");
        fwprime = new Button("fw'");

        bw = new Button("bw");
        bwprime = new Button("bw'");

        uw = new Button("uw");
        uwprime = new Button("uw'");

        dw = new Button("dw");
        dwprime = new Button("dw'");

        //group labels
        cRotLabel = new Label("Cube Rotations");
        fRotLabel = new Label("Face Rotations");
        sliceLabel = new Label("Slice Moves");
        widesLabel = new Label("Wide Face Rotations");

        //solved label
        solved = new Label(SOLVED);

        //algorithm text field
        algortihmField = new TextField();
        algortihmField.setPromptText("Enter algorithm here");

        //execute control buttons
        execute = new Button("Execute");
        resume = new Button("Resume");
        pause = new Button("Pause");
        stop = new Button("Stop");

        pause.setDisable(true);
        stop.setDisable(true);

        //reset button
        reset = new Button("reset");

        //add all the buttons to the list
        allButtons = new ArrayList<>();
        allButtons.addAll(Arrays.asList(x, xprime, y, yprime, z, zprime, r, rprime, rw, rwprime, l, lprime, lw, lwprime, f, fprime, fw, fwprime, b, bprime, bw, bwprime, u, uprime, uw, uwprime, d, dprime, dw, dwprime, m, mprime, e, eprime, s, sprime, execute, reset));
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

        Canvas canvas = new Canvas(1000, 700);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        render(gc);

        //set button event handlers
        x.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                c.xMove();
            }
        });

        xprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                c.xPrimeMove();
            }
        });

        y.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                c.yMove();
            }
        });

        yprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                c.yPrimeMove();
            }
        });

        z.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.zMove();
            }
        });

        zprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.zPrimeMove();
            }
        });

        r.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.rMove();
            }
        });

        rprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.rPrimeMove();
            }
        });

        l.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.lMove();
            }
        });

        lprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.lPrimeMove();
            }
        });

        f.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.fMove();
            }
        });

        fprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.fPrimeMove();
            }
        });

        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.bMove();
            }
        });

        bprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.bPrimeMove();
            }
        });

        u.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.uMove();
            }
        });

        uprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.uPrimeMove();
            }
        });

        d.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.dMove();
            }
        });

        dprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.dPrimeMove();
            }
        });

        m.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.mMove();
            }
        });

        mprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.mPrimeMove();
            }
        });

        e.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.eMove();
            }
        });

        eprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.ePrimeMove();
            }
        });

        s.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.sMove();
            }
        });

        sprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.sPrimeMove();
            }
        });

        rw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.rwMove();
            }
        });

        rwprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.rwPrimeMove();
            }
        });

        lw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.lwMove();
            }
        });

        lwprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.lwPrimeMove();
            }
        });

        fw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.fwMove();
            }
        });

        fwprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.fwPrimeMove();
            }
        });

        bw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.bwMove();
            }
        });

        bwprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.bwPrimeMove();
            }
        });

        uw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.uwMove();
            }
        });

        uwprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.uwPrimeMove();
            }
        });

        dw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.dwMove();
            }
        });

        dwprime.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.dwPrimeMove();
            }
        });

        execute.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                disableAllButtons();
                pause.setDisable(false);
                stop.setDisable(false);
                c.executeAlgorithm(algortihmField.getText());
            }
        });

        resume.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                disableAllButtons();
                pause.setDisable(false);
                stop.setDisable(false);
                c.resumeExecution();
            }
        });

        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pause.setDisable(true);
                stop.setDisable(true);
                c.pauseExecution();
            }
        });

        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pause.setDisable(true);
                stop.setDisable(true);
                c.stopExecution();
            }
        });

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c.reset();
            }
        });

        //add the buttons and canvas to the window

        cRotations.getChildren().addAll(x,xprime,y,yprime,z,zprime);
        fRotations.getChildren().addAll(r,rprime,l,lprime,f,fprime,b,bprime,u,uprime,d,dprime);
        slices.getChildren().addAll(m,mprime,e,eprime,s,sprime);
        wides.getChildren().addAll(rw,rwprime,lw,lwprime,fw,fwprime,bw,bwprime,uw,uwprime,dw,dwprime);
        solveState.getChildren().addAll(solved,reset);
        algoField.getChildren().addAll(algortihmField,execute, pause, resume,stop);
        buttons.getChildren().addAll(cRotLabel,cRotations,fRotLabel,fRotations,sliceLabel,slices,widesLabel,wides,solveState,algoField);
        cubePane.setRight(buttons);
        cubePane.setCenter(canvas);
        root.getChildren().addAll(cubePane);
        primaryStage.setScene(new Scene(root));
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                //do updates
                update();
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
     * <p>This method draws everything happening in the application to the screen using the JavaFX GraphicsContext object.</p>
     *
     * @param gc graphics context of where you want to draw to
     */
    public void render(GraphicsContext gc) {
        c.render(gc);
    }

    /**
     *<h2>update() method</h2>
     *
     * <p>This method updates the program based on changes happening in the cube.</p>
     */
    public void update()
    {
        c.update();
        //check state of cube
        if(c.isSolved())
            solved.setText(SOLVED);
        else
            solved.setText(UNSOLVED);
        if(!c.isExecuting()) {
            enableAllButtons();
            stop.setDisable(true);
            pause.setDisable(true);
            if(c.hasMovesToExecute())
                resume.setDisable(false);
            else
                resume.setDisable(true);
        }

    }

    /**
     * <h2>disableAllButtons() method</h2>
     *
     * <p>This method disables all the buttons on the window.</p>
     */
    public void disableAllButtons()
    {
        for(Button b:allButtons)
        {
            b.setDisable(true);
        }
    }

    /**
     * <h2>enableAllButtons() method</h2>
     *
     * <p>This method enables all the buttons on the window.</p>
     */
    public void enableAllButtons()
    {
        for(Button b:allButtons)
        {
            b.setDisable(false);
        }
    }
}
