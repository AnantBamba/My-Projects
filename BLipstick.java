package Cosmetics;

import static util.ImageLoader.loadImage;

//class for making Black lipstick object
public class BLipstick extends Cosmetics {


	public BLipstick(double x, double y, double sca) {
		super(x,y,sca);
		img = loadImage("assets/Lipblack.png");

	}
}
