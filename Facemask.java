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

//class for making face mask object
public class Facemask extends Cosmetics {
	
	

	
	
	public Facemask(double x, double y, double sca) {
		super(x,y,sca);
		img = loadImage("assets/Aloemask1.png");

	}
	
	
}
