package Cosmetics;

import static util.ImageLoader.loadImage;

// class for making nude eyeshadow object
public class NudeEyeshadow extends Cosmetics{


	public NudeEyeshadow(double x, double y, double sca) {
		super(x,y,sca);
		img = loadImage("assets/EyeShadowN.png");

	}
}

