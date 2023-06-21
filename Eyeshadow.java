package Cosmetics;

import static util.ImageLoader.loadImage;
//class for making pink eyeshadow object
public class Eyeshadow extends Cosmetics{


	public Eyeshadow(double x, double y, double sca) {
		super(x,y,sca);
		img = loadImage("assets/EyeShadow.png");

	}
}
