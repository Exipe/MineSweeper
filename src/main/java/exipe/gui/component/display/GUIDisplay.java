package exipe.gui.component.display;

import java.util.Optional;

import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public final class GUIDisplay {

	private final ImageView imageView;
	private Optional<Rendering> rendering = Optional.empty();
	
	public GUIDisplay(ImageView imageView) {
		this.imageView = imageView;
	}
	
	public void initialize(int width, int height) {
		WritableImage image = new WritableImage(width, height);
		rendering = Optional.of(new Rendering(image.getPixelWriter(), width, height));
		imageView.setImage(image);
	}
	
	public Optional<Rendering> rendering() {
		return rendering;
	}
	
}
