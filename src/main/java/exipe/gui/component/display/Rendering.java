package exipe.gui.component.display;


import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public final class Rendering {

	private final PixelWriter writer;
	
	private final int width, height;
	private Color color = Color.BLACK;
	
	public Rendering(PixelWriter writer, int width, int height) {
		this.writer = writer;
		this.width = width;
		this.height = height;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void rectangle(int x, int y, int width, int height) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				pixel(x + i, y + j);
			}
		}
	}
	
	public void verticalLine(int x, int y, int height) {
		for(int i = 0; i < height; i++) {
			pixel(x, y + i);
		}
	}
	
	public void horizontalLine(int x, int y, int width) {
		for(int i = 0; i < width; i++) {
			pixel(x + i, y);
		}
	}
	
	private void pixel(int x, int y) {
		writer.setColor(x, y, color);
	}
	
	public void sprite(int x, int y, Image sprite) {
		int width = (int) sprite.getWidth(), height = (int) sprite.getHeight();
		PixelReader reader = sprite.getPixelReader();
		
		for(int i = 0; i < width && i < this.width; i++) {
			for(int j = 0; j < height && j < this.height; j++) {
				Color color = reader.getColor(i, j);
				if(color.getOpacity() == 0) {
					continue;
				}
				
				writer.setColor(x + i, y + j, reader.getColor(i, j));
			}
		}
	}
	
	public int width() {
		return width;
	}
	
	public int height() {
		return height;
	}
	
}
