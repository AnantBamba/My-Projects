package Cosmetics;

import static util.ImageLoader.loadImage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import salon.Girl;
import util.ImageLoader;
import util.Util;

//class for making steam helmet object
public class Cap extends Cosmetics{

	

	
	
	public Cap(double x, double y, double sca) {
		super(x,y,sca);
		img = loadImage("assets/cap.png");

	}
	
	
}
