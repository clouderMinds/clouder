package Tools;

import java.awt.image.BufferedImage;

public class ImageResponese {
	private BufferedImage image1;
	private BufferedImage image2;
	
	public ImageResponese(BufferedImage image1, BufferedImage image2) {
		this.image1 = image1;
		this.image2 = image2;
	}
	
	public BufferedImage getImage1(){
		return image1;
	}
	
	public BufferedImage getImage2(){
		return image2;
	}
}
