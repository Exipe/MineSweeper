package exipe.gui;

public final class Difficulty {

	private static final Difficulty[] difficulties = { 
		new Difficulty(10, 10, 10),
		new Difficulty(35, 14, 14),
		new Difficulty(60, 18, 18)
	};
	
	public static final Difficulty get(int index) {
		return difficulties[index];
	}
	
	public static final int total() {
		return difficulties.length;
	}
	
	private final int mines, width, height;
	
	public Difficulty(int mines, int width, int height) {
		this.mines = mines;
		this.width = width;
		this.height = height;
	}
	
	public int mines() {
		return mines;
	}
	
	public int width() {
		return width;
	}
	
	public int height() {
		return height;
	}
	
}
