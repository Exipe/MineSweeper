package exipe.game.grid;

import java.util.Optional;

import exipe.gui.component.display.Rendering;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public final class Tile {

	private Color background = Color.WHITE;
	private Optional<Image> sprite = Optional.empty();
	private boolean locked;
	private boolean updated = true;
	
	public void setBackground(Color background) {
		this.background = background;
		updated = true;
	}
	
	public void setSprite(Optional<Image> sprite) {
		this.sprite = sprite;
		updated = true;
	}
	
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public void render(Rendering display, int x, int y) {
		if(!updated) {
			return;
		}
		
		display.setColor(background);
		display.rectangle(x + 1, y + 1, 31, 31);
		sprite.ifPresent(s -> {
			display.sprite(x, y, s);
		});
		
		updated = false;
	}
	
}
