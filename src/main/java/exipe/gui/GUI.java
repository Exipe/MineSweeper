package exipe.gui;

import exipe.gui.component.GUIButton;
import exipe.gui.component.GUILabel;
import exipe.gui.component.display.GUIDisplay;

public final class GUI {

	private final GUISizing sizing;
	private final GUILabel timer, remainingMines, gridSize, totalMines;
	private final GUIButton restart;
	private final GUIDisplay display;
	
	public GUI(GUISizing sizing, GUILabel timer, GUILabel remainingMines, GUILabel gridSize, GUILabel totalMines, GUIButton restart, GUIDisplay display) {
		this.sizing = sizing;
		this.timer = timer;
		this.remainingMines = remainingMines;
		this.gridSize = gridSize;
		this.totalMines = totalMines;
		this.restart = restart;
		this.display = display;
	}
	
	public void fixSize() {
		sizing.fix();
	}
	
	public GUILabel timer() {
		return timer;
	}
	
	public GUILabel remainingMines() {
		return remainingMines;
	}
	
	public GUILabel gridSize() {
		return gridSize;
	}
	
	public GUILabel totalMines() {
		return totalMines;
	}
	
	public GUIButton restart() {
		return restart;
	}
	
	public GUIDisplay display() {
		return display;
	}
	
}
