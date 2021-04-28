package exipe.game.mines;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import exipe.SpriteHandler;
import exipe.game.grid.Grid;
import exipe.gui.Difficulty;
import exipe.gui.component.GUILabel;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public final class MineHandler {

	private final Grid grid;
	private final SpriteHandler sprites;
	private final Optional<Image> mineSprite;
	private boolean playing, started;
	
	private boolean[][] mines;
	private int width, height, totalMines, remaining;
	
	private final StopWatch stopWatch = new StopWatch();
	
	public MineHandler(Grid grid, SpriteHandler sprites) {
		this.grid = grid;
		this.sprites = sprites;
		mineSprite = Optional.of(sprites.getMine());
	}
	
	public void initialize(GUILabel timer, Difficulty difficulty) {
		stopWatch.initialize(timer);
		
		playing = true;
		started = false;
		
		width = difficulty.width();
		height = difficulty.height();
		totalMines = difficulty.mines();
		remaining = width * height - totalMines;
		
		mines = new boolean[width][height];
	}
	
	public void update(GUILabel timer, int x, int y) {
		if(grid.isLocked(x, y)) {
			return;
		}
		
		if(!started) {
			started = true;
			stopWatch.start(timer);
			placeMines(x, y);
		}
		
		if(mines[x][y]) {
			grid.setBackground(x, y, Color.RED);
			
			for(int i = 0; i < width; i++) {
				for(int j = 0; j < height; j++) {
					if(mines[i][j]) {
						grid.setSprite(i, j, mineSprite);
					}
				}
			}
			
			end("Oh dear!", "You lose!");
			return;
		}
		
		reveal(x, y);
		
		if(remaining == 0) {
			end("Congratulations!", "You win!");
		}
		
	}
	
	private void placeMines(int avoidX, int avoidY) {
		List<int[]> points = new ArrayList<>();
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(	x >= avoidX - 1 && x <= avoidX + 1 &&
					y >= avoidY - 1 && y <= avoidY + 1)
				{
					continue;
				}	
				
				points.add(new int[] {x, y});
			}
		}
		
		Random r = new Random();
		for(int i = 0; i < totalMines; i++) {
			int[] point = points.remove(r.nextInt(points.size()));
			
			int x = point[0];
			int y = point[1];
			mines[x][y] = true;
		}
	}
	
	public boolean playing() {
		return playing;
	}
	
	private void reveal(int clickX, int clickY) {
		if(grid.isLocked(clickX, clickY)) {
			return;
		}
		
		int count = 0;
		for(int x = clickX - 1; x <= clickX + 1; x++) {
			for(int y = clickY - 1; y <= clickY + 1; y++) {
				if(x < 0 || x >= width || y < 0 || y >= height) {
					continue;
				}
				
				if(mines[x][y]) {
					count++;
				}
			}
		}
		
		grid.setBackground(clickX, clickY, Color.LIGHTGREY);
		grid.lock(clickX, clickY);
		remaining--;
		
		if(count > 0) {
			Image sprite = sprites.getNumber(count);
			grid.setSprite(clickX, clickY, Optional.of(sprite));
		} else {
			revealSurrounding(clickX, clickY);
		}
	}
	
	private void revealSurrounding(int clickX, int clickY) {
		for(int x = clickX - 1; x <= clickX + 1; x++) {
			for(int y = clickY - 1; y <= clickY + 1; y++) {
				if(x < 0 || x >= width || y < 0 || y >= height || mines[x][y]) {
					continue;
				}
				
				reveal(x, y);
			}
		}
	}
	
	private void end(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.setHeaderText(null);
		alert.show();
		
		playing = false;
		stopWatch.stop();
	}
	
}
