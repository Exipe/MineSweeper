package exipe.gui.event;

import exipe.gui.Difficulty;
import exipe.gui.GUI;

public interface GUIEventListener {

	public void initialize(GUI ui);
	
	public void displayClicked(GUI ui, MouseClick click, int x, int y);
	
	public void difficultySelected(GUI ui, Difficulty difficulty);
	
}
