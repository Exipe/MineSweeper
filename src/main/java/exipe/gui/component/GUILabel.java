package exipe.gui.component;

import javafx.scene.control.Label;

public final class GUILabel {

	private final Label label;
	
	public GUILabel(Label label) {
		this.label = label;
	}
	
	public void update(String text) {
		label.setText(text);
	}
	
}
