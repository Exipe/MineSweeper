package exipe;

import exipe.game.GameHandler;
import exipe.gui.GUIBuilding;
import exipe.gui.event.GUIEventListener;

import javafx.application.Application;
import javafx.stage.Stage;

public final class SweeperApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		System.err.println("Starting MineSweeper...");
		
		System.err.println(" - Loading sprites");
		SpriteHandler sprites = SpriteHandler.load();
		
		GUIEventListener eventListener = new GameHandler(sprites);
		
		System.err.println(" - Building GUI");
		GUIBuilding.perform(stage, eventListener);
		
		System.err.println("...successfully started!");
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
