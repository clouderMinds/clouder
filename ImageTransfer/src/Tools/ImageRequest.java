package Tools;

public class ImageRequest {
	
	private int width;
	private int height;
	private int numberOfChar;
	public ImageRequest(int width, int height, int numberOfChar) {
		this.width = width;
		this.height = height;
		this.numberOfChar = numberOfChar;
	}
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getNumberOfChar() {
		return numberOfChar;
	}
	
}
