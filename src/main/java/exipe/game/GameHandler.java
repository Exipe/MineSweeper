package exipe.game;

import exipe.SpriteHandler;
import exipe.game.grid.Grid;
import exipe.game.mines.MineHandler;
import exipe.gui.Difficulty;
import exipe.gui.GUI;
import exipe.gui.component.GUILabel;
import exipe.gui.component.display.GUIDisplay;
import exipe.gui.component.display.Rendering;
import exipe.gui.event.GUIEventListener;
import exipe.gui.event.MouseClick;

import javafx.scene.paint.Color;

public class GameHandler implements GUIEventListener {
	
	private final Grid grid = new Grid();
	private final FlagHandler flags;
	private final MineHandler mines;
	private boolean running;
	private Rendering rendering;
	private Difficulty difficulty = Difficulty.get(0);
	private boolean initializeDisplay = true;
	
	public GameHandler(SpriteHandler sprites) {
		mines = new MineHandler(grid, sprites);
		flags = new FlagHandler(grid, sprites);
	}
	
	@Override
	public void initialize(GUI ui) {
		ui.restart().disable();
		updateDifficulty(ui.gridSize(), ui.totalMines());
		running = false;
		
		int width = difficulty.width(), height = difficulty.height();
		
		if(initializeDisplay) {
			GUIDisplay display = ui.display();
			display.initialize(width * 32, height * 32);
			rendering = display.rendering().get();
			initializeDisplay = false;
			
			rendering.setColor(Color.BLACK);
			
			for(int x = 0; x < width; x++) {
				rendering.verticalLine(x * 32, 0, height * 32);
			}
			
			for(int y = 0; y < height; y++) {
				rendering.horizontalLine(0, y * 32, width * 32);
			}
			
			ui.fixSize();
		}
		
		flags.initialize(ui.remainingMines(), difficulty);
		mines.initialize(ui.timer(), difficulty);
		grid.initialize(rendering, difficulty);
	}
	
	@Override
	public void displayClicked(GUI ui, MouseClick click, int x, int y) {
		if(!running) {
			ui.restart().enable();
			running = true;
		}
		
		if(!mines.playing()) {
			return;
		}
		
		int tileX = x / 32, tileY = y / 32;
		
		if(click == MouseClick.RIGHT) {
			flags.toggle(ui.remainingMines(), tileX, tileY);
		}
		
		if(click == MouseClick.LEFT) {
			mines.update(ui.timer(), tileX, tileY);
		}
		
		grid.render(ui.display().rendering().get());
	}

	@Override
	public void difficultySelected(GUI ui, Difficulty difficulty) {
		ui.restart().enable();
		this.difficulty = difficulty;
		updateDifficulty(ui.gridSize(), ui.totalMines());
		initializeDisplay = true;
	}

	private void updateDifficulty(GUILabel gridSize, GUILabel totalMines) {
		gridSize.update("Grid size: " + difficulty.width() + "x" + difficulty.height());
		totalMines.update("Total mines: " + difficulty.mines());
	}
	
}
