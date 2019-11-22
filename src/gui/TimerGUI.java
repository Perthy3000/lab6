package gui;

import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TimerGUI extends VBox{

	private Label nameLabel;
	private DisplayPart displayPart;
	private ControlPart controlPart;

	public TimerGUI(String name) {
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10));
		setSpacing(10);
		nameLabel = new Label(name);
		nameLabel.setStyle("-fx-font-size : 22;");
		displayPart = new DisplayPart();
		controlPart = new ControlPart();
		getChildren().addAll(nameLabel, displayPart, controlPart);
	}
	
	public String getName() {
		return nameLabel.getText().trim();
	}

	public DisplayPart getDisplayPart() {
		return displayPart;
	}

	public ControlPart getControlPart() {
		return controlPart;
	}
	
}
