package Cosmetics;

import static util.ImageLoader.loadImage;

//class for making pink lipstick object
public class Lipstick extends Cosmetics {


	public Lipstick(double x, double y, double sca) {
		super(x,y,sca);
		img = loadImage("assets/Lipstick.png");

	}
}
