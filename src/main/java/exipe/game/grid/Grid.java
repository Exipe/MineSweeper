package exipe.game.grid;

import java.util.Optional;

import exipe.gui.Difficulty;
import exipe.gui.component.display.Rendering;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public final class Grid {

	private Tile[][] tiles;
	private int width, height;
	
	public void setBackground(int x, int y, Color color) {
		tiles[x][y].setBackground(color);
	}
	
	public void setSprite(int x, int y, Optional<Image> sprite) {
		tiles[x][y].setSprite(sprite);
	}
	
	public void lock(int x, int y) {
		tiles[x][y].setLocked(true);
	}
	
	public void unlock(int x, int y) {
		tiles[x][y].setLocked(false);
	}
	
	public boolean isLocked(int x, int y) {
		return tiles[x][y].isLocked();
	}
	
	public void initialize(Rendering display, Difficulty difficulty) {
		width = difficulty.width();
		height = difficulty.height();
		
		tiles = new Tile[width][height];
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				Tile tile = new Tile();
				tile.render(display, x * 32, y * 32);
				tiles[x][y] = tile;
			}
		}
	}
	
	public void render(Rendering display) {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				tiles[x][y].render(display, x * 32, y * 32);
			}
		}
	}
	
}
