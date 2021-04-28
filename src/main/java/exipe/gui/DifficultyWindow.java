package exipe.gui;

import java.util.Optional;

import exipe.gui.event.GUIEventListener;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public final class DifficultyWindow {

	public static DifficultyWindow create(GUI ui, GUIEventListener eventListener) {
		Stage stage = new Stage();
		
		TextField width = new TextField(), height = new TextField(), mines = new TextField();
		GridPane.setConstraints(width, 1, 0);
		GridPane.setConstraints(height, 1, 1);
		GridPane.setConstraints(mines, 1, 2);
		
		Label widthLabel = new Label("Width: ");
		Label heightLabel = new Label("Height: ");
		Label minesLabel = new Label("Mines: ");
		GridPane.setConstraints(widthLabel, 0, 0);
		GridPane.setConstraints(heightLabel, 0, 1);
		GridPane.setConstraints(minesLabel, 0, 2);
		
		GridPane grid = new GridPane();
		grid.getChildren().addAll(width, height, mines, widthLabel, heightLabel, minesLabel);
		
		Button confirm = new Button("Confirm");
		
		HBox firstContainer = new HBox();
		firstContainer.getChildren().addAll(grid, confirm);
		
		Scene scene = new Scene(firstContainer);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Custom Difficulty");
		stage.sizeToScene();
		
		DifficultyWindow difficultyWindow = new DifficultyWindow(stage);
		
		confirm.setOnAction(e -> {
			stage.hide();
			
			Optional<Integer> 
				widthRequest = parse(width.getText()),
				heightRequest = parse(height.getText()),
				minesRequest = parse(mines.getText());
			if(!widthRequest.isPresent() || !heightRequest.isPresent() || !minesRequest.isPresent()) {
				return;
			}
			
			int properWidth = widthRequest.get(), properHeight = heightRequest.get(), properMines = minesRequest.get();
			if(properWidth > 30) { properWidth = 30; }
			if(properWidth < 1) { properWidth = 1; }
			if(properHeight * properWidth < 9) {
				properHeight = (int) Math.ceil(9d / properWidth);
			}
			if(properHeight > 20) { properHeight = 20; }
			if(properHeight < 1) { properHeight = 1; }
			if(properMines > properWidth * properHeight - 9) {
				properMines = properWidth * properHeight - 9; 
			}
			if(properMines < 0) { properMines = 0; }
			
			Difficulty difficulty = new Difficulty(properMines, properWidth, properHeight);
			eventListener.difficultySelected(ui, difficulty);
			difficultyWindow.last = Optional.of(difficulty);
		});
		
		return difficultyWindow;
	}
	
	private static Optional<Integer> parse(String input) {
		try {
			return Optional.of(Integer.parseInt(input));
		} catch(NumberFormatException e) {
			return Optional.empty();
		}
	}
	
	private final Stage stage;
	private Optional<Difficulty> last = Optional.empty();
	
	private DifficultyWindow(Stage stage) {
		this.stage = stage;
	}
	
	public Optional<Difficulty> last() {
		return last;
	}
	
	public void open() {
		stage.show();
	}
	
}
