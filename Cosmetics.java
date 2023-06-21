package Cosmetics;

import static util.ImageLoader.loadImage;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import salon.Girl;
import util.Util;

// main superclass that helps to control all the cosmetic objects and their interactivity and functions
public class Cosmetics {

	protected double xPos;
	protected double yPos;
	protected double scale;

	protected BufferedImage img;
	
	public Cosmetics(double x, double y, double sca) {
		xPos = x;
		yPos = y;
		scale = sca;
		img = loadImage("assets/Aloemask.png");

	}
	
	public boolean clicked(double x, double y){
		boolean clicked = false;
		
		if(x > (xPos - ((double) img.getWidth())/2*scale) && x < (xPos + ((double) img.getWidth())/2*scale) && y > (yPos - ((double) img.getHeight())/2*scale) && y < (yPos + ((double) img.getHeight())/2*scale)) 
			clicked = true;
		
		return clicked;
	}
	
	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}
	
	public void setPos(double x, double y) { // For setting the pizza position when the mouse is dragged over it
		xPos = x;
		yPos = y;
	}
	
	public double getX() {
		return xPos;
	}
	
	public double getY() {
		return yPos;
	}
	
	public boolean hit(Girl girl) {
		if (Util.dist(xPos+100, yPos, girl.getX(), girl.getY()) < girl.getWidth() / 2) {
			return true;
		}
		return false;
	}
}
