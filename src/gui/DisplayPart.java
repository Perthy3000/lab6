package gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;

public class DisplayPart extends Label{
	
	public DisplayPart() {
		setPadding(new Insets(10));
		setStyle("-fx-font-size : 18;");
	}
	
	public void update(String text) {
		setText(text);
	}
	
}
