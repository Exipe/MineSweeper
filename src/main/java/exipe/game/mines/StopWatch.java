package exipe.game.mines;

import exipe.gui.component.GUILabel;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public final class StopWatch {

	private Timeline timer;
	private int seconds;
	
	public void initialize(GUILabel label) {
		if(timer == null) {
			timer = new Timeline();
			KeyFrame frame = 
			new KeyFrame(Duration.seconds(1), e -> {
				seconds++;
				updateTimer(label);
			});
			timer.getKeyFrames().add(frame);
			timer.setCycleCount(Animation.INDEFINITE);
		} else {
			timer.stop();
		}
		
		seconds = 0;
		updateTimer(label);
	}
	
	public void start(GUILabel label) {
		timer.play();
	}
	
	public void stop() {
		timer.stop();
	}
	
	private void updateTimer(GUILabel label) {
		int minutes = seconds / 60;
		int extraSeconds = seconds % 60;
		
		String time = String.format("%02d:%02d", minutes, extraSeconds);
		label.update("Timer: " + time);
	}
	
}
