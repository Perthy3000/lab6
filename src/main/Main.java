package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.Timer;

public class Main extends Application {
	
	public static Timer timer;
	
	@Override
	public void start(Stage primaryStage) {
		HBox rootBox = new HBox();
		Timer countdownTimer = new Timer("countDown", -1);
		timer = countdownTimer;
		Timer countupTimer1 = new Timer("countUp1", 1);
		Timer countupTimer2 = new Timer("countUp2", 1);
		rootBox.getChildren().addAll(countdownTimer.getTimerGUI(), countupTimer1.getTimerGUI(), countupTimer2.getTimerGUI());
		Scene scene1 = new Scene(rootBox, 500, 500);
		primaryStage.setScene(scene1);
		primaryStage.show();
	}
	
	public static void main (String [] args) {
		launch(args);
	}

	@Override
	public void stop() throws Exception {
		Platform.exit();
	}
	
	
}
