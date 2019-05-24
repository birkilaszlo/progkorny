package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Birki László
 */
public class Main extends Application {

    /**
     * Slf4j logger.
     */
    public final static Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Program start metódusa.
     * @param primaryStage alap stage
     * @throws Exception kivétel dobása
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        logger.info("program indítása");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("Angol-Magyar teszt");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Program main metódus.
     * @param args parancssori argumentumok
     */
    public static void main(String[] args) {
        launch(args);
    }


}
