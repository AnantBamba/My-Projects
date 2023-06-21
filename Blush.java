package Cosmetics;

import static util.ImageLoader.loadImage;

//class for making blush object
public class Blush extends Cosmetics{


	public Blush(double x, double y, double sca) {
		super(x,y,sca);
		img = loadImage("assets/Blush.png");

	}
}
