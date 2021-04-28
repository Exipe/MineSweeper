package exipe;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public final class SpriteHandler {

	public static final SpriteHandler load() {
		Image source = new Image("resources.png");
		int amount = (int) (source.getHeight() / 32);
		PixelReader reader = source.getPixelReader();
		
		Image[] sprites = new Image[amount];
		
		for(int i = 0; i < amount; i++) {
			WritableImage image = new WritableImage(32, 32);
			sprites[i] = image;
			PixelWriter writer = image.getPixelWriter();
			
			for(int x = 0; x < 32; x++) {
				for(int y = 0; y < 32; y++) {
					Color color = reader.getColor(x, i * 32 + y);
					writer.setColor(x, y, color);
				}
			}
		}
		
		return new SpriteHandler(sprites);
	}
	
	private final Image[] sprites;
	
	private SpriteHandler(Image[] sprites) {
		this.sprites = sprites;
	}
	
	public Image getNumber(int i) {
		if(i > 8 || i < 1) {
			throw new IllegalArgumentException("Must be a number between 1-8.");
		}

		return sprites[i - 1];
	}
	
	public Image getFlag() {
		return sprites[8];
	}
	
	public Image getMine() {
		return sprites[9];
	}
	
}
