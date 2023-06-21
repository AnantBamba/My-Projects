package Cosmetics;

import static util.ImageLoader.loadImage;

//class for making Concealer object
public class Concealer extends Cosmetics {

	public Concealer(double x, double y, double sca) {
		super(x,y,sca);
		img = loadImage("assets/Concealer.png");

	}
}
