package exipe.gui.component;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class GUIIcon {

	private final ImageView imageView;
	private final Image image;
	
	public GUIIcon(ImageView imageView, Image image) {
		this.imageView = imageView;
		this.image = image;
	}
	
	public boolean shown() {
		return imageView.getImage() != null;
	}
	
	public void show() {
		imageView.setImage(image);
	}
	
	public void hide() {
		imageView.setImage(null);
	}
	
}
