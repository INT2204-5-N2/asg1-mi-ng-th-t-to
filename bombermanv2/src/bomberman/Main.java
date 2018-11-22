package bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private Game game;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bomberman");
        game = Game.getInstance();
        game.start(primaryStage);
        primaryStage.show();
        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                game.update();
            }
        }.start();

    }
    public static void main(String[] args){
        launch(args);
    }
}
