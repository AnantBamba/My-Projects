package Cosmetics;

import static util.ImageLoader.loadImage;

//class for making Contour object
public class Contour extends Cosmetics {


	public Contour(double x, double y, double sca) {
		super(x,y,sca);
		img = loadImage("assets/Contour.png");

	}
}
