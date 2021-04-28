package exipe.gui.component;

import javafx.scene.control.Button;

public final class GUIButton {

	private final Button button;
	
	public GUIButton(Button button) {
		this.button = button;
	}
	
	public void enable() {
		button.setDisable(false);
	}
	
	public void disable() {
		button.setDisable(true);
	}
	
}
