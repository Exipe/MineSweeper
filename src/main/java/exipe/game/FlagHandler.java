package exipe.game;

import java.util.Optional;

import exipe.SpriteHandler;
import exipe.game.grid.Grid;
import exipe.gui.Difficulty;
import exipe.gui.component.GUILabel;

import javafx.scene.image.Image;

public final class FlagHandler {

	private final Grid grid;
	private final Optional<Image> sprite;
	
	private boolean[][] flags;
	private int remaining;
	
	public FlagHandler(Grid grid, SpriteHandler sprites) {
		this.grid = grid;
		this.sprite = Optional.of(sprites.getFlag());
	}

	public void initialize(GUILabel label, Difficulty difficulty) {
		flags = new boolean[difficulty.width()][difficulty.height()];
		remaining = difficulty.mines();
		updateRemainingMines(label);
	}
	
	public void toggle(GUILabel label, int x, int y) {
		boolean flag = true;
		int add = 1;
		Optional<Image> sprite = this.sprite;

		if(flags[x][y]) //flag was placed
		{
			grid.unlock(x, y);
			flag = false;
			add = -1;
			sprite = Optional.empty();
		}
		
		if(grid.isLocked(x, y)) //this tile is already occupied
		{
			return;
		}
		
		flags[x][y] = flag;
		remaining -= add;
		grid.setSprite(x, y, sprite);
		updateRemainingMines(label);
		
		if(flag) {
			grid.lock(x, y);
		} else {
			grid.unlock(x, y);
		}
	}
	
	private void updateRemainingMines(GUILabel label) {
		label.update("Remaining mines: " + remaining);
	}
	
}
