package exipe.gui;

import java.util.Optional;

import exipe.gui.event.MouseClick;
import exipe.gui.component.GUIButton;
import exipe.gui.component.GUIIcon;
import exipe.gui.component.GUILabel;
import exipe.gui.component.display.GUIDisplay;
import exipe.gui.event.GUIEventListener;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class GUIBuilding {
	
	private GUIBuilding() {}
	
	public static final void perform(Stage stage, GUIEventListener eventListener) {
		BorderPane leftContainer = new BorderPane();
		
		VBox top = new VBox();
		Label timer = new Label("{timer}"), remainingMines = new Label("{remaining mines}");
		top.getChildren().addAll(timer, remainingMines);
		
		Image logo = new Image("logo.png");
		ImageView logoView = new ImageView(logo);
		
		VBox bottom = new VBox();
		HBox difficultyWrapper = new HBox();
		ImageView cogwheelView = new ImageView();
		ComboBox<String> difficultyOptions = new ComboBox<>();
		difficultyOptions.getItems().addAll("Beginner", "Intermediate", "Expert", "Custom");
		difficultyOptions.getSelectionModel().selectFirst();
		difficultyWrapper.getChildren().addAll(difficultyOptions, cogwheelView);
		Label gridSize = new Label("{grid size}");
		Label totalMines = new Label("{total mines}");
		Button restart = new Button("Restart");
		bottom.getChildren().addAll(difficultyWrapper, gridSize, totalMines, restart);
		
		leftContainer.setPrefWidth(160);
		leftContainer.setTop(top);
		BorderPane.setAlignment(logoView, Pos.CENTER_LEFT);
		leftContainer.setCenter(logoView);
		leftContainer.setBottom(bottom);
		BorderPane.setMargin(top, new Insets(10, 0, 0, 10));
		BorderPane.setMargin(bottom, new Insets(0, 0, 10, 10));
		
		//end of left container
		
		StackPane centreContainer = new StackPane();
		
		ImageView imageView = new ImageView();
		
		centreContainer.getChildren().add(imageView);
		
		//end of centre container
		
		BorderPane firstContainer = new BorderPane();
		firstContainer.setLeft(leftContainer);
		firstContainer.setCenter(centreContainer);
		
		Scene scene = new Scene(firstContainer);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("MineSweeper");
		stage.show();
		
		GUILabel 
			timerWrapper = new GUILabel(timer), 
			remainingMinesWrapper = new GUILabel(remainingMines),
			gridSizeWrapper = new GUILabel(gridSize),
			totalMinesWrapper = new GUILabel(totalMines);
		Image cogwheel = new Image("cogwheel.png");
		GUIIcon difficultyIcon = new GUIIcon(cogwheelView, cogwheel);
		GUIButton restartWrapper = new GUIButton(restart);
		GUIDisplay display = new GUIDisplay(imageView);
		
		GUI ui = 
		new GUI(
				new GUISizing(stage), timerWrapper, remainingMinesWrapper, gridSizeWrapper, totalMinesWrapper, restartWrapper, display
		);
		eventListener.initialize(ui);
		
		restart.setOnAction(e -> {
			eventListener.initialize(ui);
		});
		
		imageView.setOnMouseClicked(e -> {
			Optional<MouseClick> click = Optional.empty();
			
			if(e.getButton() == MouseButton.PRIMARY) {
				click = Optional.of(MouseClick.LEFT);
			}
			
			if(e.getButton() == MouseButton.SECONDARY) {
				click = Optional.of(MouseClick.RIGHT);
			}
			
			click.ifPresent
			(b -> { 
				eventListener.displayClicked(ui, b, (int) e.getX(), (int) e.getY()); 
			});
		});
		
		DifficultyWindow difficultyWindow = DifficultyWindow.create(ui, eventListener);
		cogwheelView.setOnMouseClicked(e -> {
			difficultyWindow.open();
		});
		
		difficultyOptions.setOnAction(e -> {
			int index = difficultyOptions.getSelectionModel().getSelectedIndex();
			if(index >= Difficulty.total()) {
				difficultyIcon.show();
				
				Optional<Difficulty> lastRequest = difficultyWindow.last();
				if(lastRequest.isPresent()) {
					eventListener.difficultySelected(ui, lastRequest.get());
				}
				
				return;
			} 
			
			if(difficultyIcon.shown()) {
				difficultyIcon.hide();
			}
			
			eventListener.difficultySelected(ui, Difficulty.get(index));
		});
	}
	
}
