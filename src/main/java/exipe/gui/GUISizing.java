package exipe.gui;

import javafx.stage.Stage;

public final class GUISizing {

	private final Stage stage;
	
	public GUISizing(Stage stage) {
		this.stage = stage;
	}
	
	public void fix() {
		stage.sizeToScene();
	}
	
}
