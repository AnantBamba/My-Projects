package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Timer;
import util.MinimHelper;

import BackgroundDecor.BackgroundInterface;
import BackgroundDecor.ClockDecorator;
import BackgroundDecor.PaintingDecorator;
import BackgroundDecor.PlainBack;
import BackgroundDecor.PlantDecorator;
import Cosmetics.BLipstick;
import Cosmetics.Blush;
import Cosmetics.Cap;
import Cosmetics.Concealer;
import Cosmetics.Contour;
import Cosmetics.Cosmetics;
import Cosmetics.Eyeshadow;
import Cosmetics.Facemask;
import Cosmetics.Lipstick;
import Cosmetics.NudeEyeshadow;
import util.ImageLoader;
import salon.Girl;
import salon.Steam;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import operators.Buttonop;
import operators.Instructions;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/* main panel where everything takes place Drag and drop to interact, user can reach up to 4 different endings */
/*ECO:
 * all 32 Images were drawn and created by me 
 * Design pattern is used which decorates 3 different decorations onto the background
 * There are up to 4 different makeup combinations that the user can achieve 
 * i.e 4 different ending results based on what the user chooses, thus giving a more creative simulation power to the user too
 * The environment changes as the user interacts and uses up objects, they dissappear and are no longer available to use
 */
public class SalonPanel extends JPanel implements ActionListener {

	private Timer timer;
	
	private double mouseX;
	private double mouseY;

	public static int W_WIDTH = 1050;
	public static int W_HEIGHT = 750;
	
	private Instructions ins;
	
	private int x=10,y=10;
    
	private Girl girl;
	private Facemask mask;
	private Cap helm;
	private Steam steam;
	private Lipstick stick;
	private Blush blush;
	private Contour contour;
	private Concealer concealer;
	private Eyeshadow shadow;
	private BLipstick b_stick;
	private NudeEyeshadow n_shadow;
	
	private int state = -1;
	private double offsetX, offsetY;
	private boolean offsetSet = false;
	BackgroundInterface backG;
	
	private Minim minim;
	private AudioPlayer bkmusic, click,dryer;
	
	private Buttonop start;
	private Buttonop restart,backdrop,TT;
	private BufferedImage im1 = ImageLoader.loadImage("assets/Startbutton.png");
	private BufferedImage im2 = ImageLoader.loadImage("assets/Restartbutton.png");
	private BufferedImage back = ImageLoader.loadImage("assets/background.png");
	private BufferedImage title = ImageLoader.loadImage("assets/Title1.png");
	
	SalonPanel(JFrame frame) {
		
		setPreferredSize(new Dimension(W_WIDTH, W_HEIGHT));

		
		girl = new Girl(W_WIDTH / 2 - 230, W_HEIGHT / 2 + 100, 0.2);
		mask = new Facemask(750,450, 0.04);
		helm = new Cap(900,560, 0.15);
		steam = new Steam((float) girl.getX() - 100, (float) girl.getY()-150 , 180, 120);
        stick = new Lipstick(750,320, 0.04);
        blush = new Blush(1000,200, 0.04);
        contour = new Contour(1000,80, 0.04);
        concealer = new Concealer(750,80, 0.04);
        shadow = new Eyeshadow(750,200, 0.04);
        b_stick = new BLipstick(850,320, 0.04);
        n_shadow = new NudeEyeshadow(850,200, 0.04);
        
        //sound;
        minim = new Minim(new MinimHelper());
        bkmusic = minim.loadFile("Nexttoyou.wav");
        dryer = minim.loadFile("dryer.wav");
		click = minim.loadFile("Click.wav");
		
		restart = new Buttonop(W_WIDTH / 2, W_HEIGHT / 2 + 100, 0.1,im2);
start = new Buttonop(W_WIDTH / 2, W_HEIGHT / 2 + 100, 0.1,im1);
backdrop = new Buttonop(500,800, 0.5,back);
TT = new Buttonop(500,700, 0.25,title);
ins = new Instructions(100,100,350,30,"Drag and drop items to interact");
		
        //decorators;
        backG = new PlainBack(525,375);
		
		backG = new PlantDecorator(backG,700,620,0.075);
		backG = new PaintingDecorator(backG,500,250,0.085);
		backG = new ClockDecorator(backG,500,80,0.045);
		
		//mouse;
		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);
		
		MyMouseMotionListener mml = new MyMouseMotionListener();
		addMouseMotionListener(mml);
		
		timer = new Timer(30, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.pink);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (state == -1) { //opening page
			bkmusic.loop();
			backdrop.drawButton(g2);
			TT.drawButton(g2);
			start.drawButton(g2);
			
String st1 = "Welcome to salon simulator, please drag and drop items and click to interact in order to give The model a makeover";
			
ins.setText("Instructions panel", 200, 30);
			Font f = new Font("Arial", Font.PLAIN, 30);
			

			

			g2.setColor(new Color(255, 255, 255, 60));
			
			
			g.setColor(Color.white);
			g.drawString(st1, 120,550);
		}
		else if (state == 0||state ==1) { //acne stage
			
			backG.decorate(g2);
			g2.setColor(new Color(0, 0, 0, 20));
			g2.fill(new Rectangle2D.Double(0, 0, W_WIDTH, W_HEIGHT));
			girl.drawButton(g2);
			mask.drawButton(g2);
			helm.drawButton(g2);
			stick.drawButton(g2);
			b_stick.drawButton(g2);
			n_shadow.drawButton(g2);
			blush.drawButton(g2);
			shadow.drawButton(g2);
			contour.drawButton(g2);
			concealer.drawButton(g2);
			ins.setText("Please drag and drop the Face mask on the model", 500, 30);
			//g2.setColor(new Color(0, 0, 0, 150));
			//g2.fill(new Rectangle2D.Double(0, 0, W_WIDTH, W_HEIGHT));
			
			

			

		
		} else if (state == 2) { //mask state
			backG.decorate(g2);
			dryer.play(0);

			
			
			g2.setColor(new Color(0, 0, 0, 20));
			g2.fill(new Rectangle2D.Double(0, 0, W_WIDTH, W_HEIGHT));
			girl.drawButton(g2);
			helm.drawButton(g2);
			stick.drawButton(g2);
			blush.drawButton(g2);
			shadow.drawButton(g2);
			contour.drawButton(g2);
			concealer.drawButton(g2);
			b_stick.drawButton(g2);
			n_shadow.drawButton(g2);
		}
		else if (state == 3) { //steamer state
			backG.decorate(g2);

			g2.setColor(new Color(0, 0, 0, 20));
			g2.fill(new Rectangle2D.Double(0, 0, W_WIDTH, W_HEIGHT));
			girl.drawButton(g2);
		steam.drawSteam(g2);
		stick.drawButton(g2);
		blush.drawButton(g2);
		shadow.drawButton(g2);
		contour.drawButton(g2);
		concealer.drawButton(g2);
		b_stick.drawButton(g2);
		n_shadow.drawButton(g2);
		}
		
		else if(state == 4){ //clean state
			backG.decorate(g2);
			dryer.pause();

			g2.setColor(new Color(0, 0, 0, 20));
			g2.fill(new Rectangle2D.Double(0, 0, W_WIDTH, W_HEIGHT));
			girl.drawButton(g2);
			stick.drawButton(g2);
			blush.drawButton(g2);
			shadow.drawButton(g2);
			contour.drawButton(g2);
			concealer.drawButton(g2);
			b_stick.drawButton(g2);
			n_shadow.drawButton(g2);
		}
		else if(state == 5){ //concealer state
			backG.decorate(g2);

			g2.setColor(new Color(0, 0, 0, 20));
			g2.fill(new Rectangle2D.Double(0, 0, W_WIDTH, W_HEIGHT));
			girl.drawButton(g2);
			stick.drawButton(g2);
			blush.drawButton(g2);
			shadow.drawButton(g2);
			contour.drawButton(g2);
			b_stick.drawButton(g2);
			n_shadow.drawButton(g2);
			//concealer.drawButton(g2);
		}
		
		else if(state == 6){ //contour state
			backG.decorate(g2);

			g2.setColor(new Color(0, 0, 0, 20));
			g2.fill(new Rectangle2D.Double(0, 0, W_WIDTH, W_HEIGHT));
			girl.drawButton(g2);
			stick.drawButton(g2);
			blush.drawButton(g2);
			shadow.drawButton(g2);
			//contour.drawButton(g2);
			//concealer.drawButton(g2);
			b_stick.drawButton(g2);
			n_shadow.drawButton(g2);
		}
		else if(state == 7){ //blush state
			backG.decorate(g2);

			g2.setColor(new Color(0, 0, 0, 20));
			g2.fill(new Rectangle2D.Double(0, 0, W_WIDTH, W_HEIGHT));
			girl.drawButton(g2);
			stick.drawButton(g2);
			//blush.drawButton(g2);
			shadow.drawButton(g2);
			//contour.drawButton(g2);
			//concealer.drawButton(g2);
			b_stick.drawButton(g2);
			n_shadow.drawButton(g2);
		}
		else if(state == 8){ //lipstick state
			backG.decorate(g2);

			g2.setColor(new Color(0, 0, 0, 20));
			g2.fill(new Rectangle2D.Double(0, 0, W_WIDTH, W_HEIGHT));
			girl.drawButton(g2);
			stick.drawButton(g2);
			b_stick.drawButton(g2);
			n_shadow.drawButton(g2);
			
		}
		else if (state== -8){ //lipstick state nude eyeshadow
			backG.decorate(g2);

			g2.setColor(new Color(0, 0, 0, 20));
			g2.fill(new Rectangle2D.Double(0, 0, W_WIDTH, W_HEIGHT));
			girl.drawButton(g2);
			stick.drawButton(g2);
			b_stick.drawButton(g2);
			shadow.drawButton(g2);
			
		}
		else if(state == 9){ //ending state
			backG.decorate(g2);

			g2.setColor(new Color(0, 0, 0, 20));
			g2.fill(new Rectangle2D.Double(0, 0, W_WIDTH, W_HEIGHT));
			girl.drawButton(g2);
			
			
		}
		
		else if(state ==10) { //restart page
			
			
			restart.drawButton(g2);
		}
		
		ins.draw(g2);
		
	}
	
	public class MyMouseListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();

			if (girl.clicked(mouseX, mouseY)&& state == 3) {
				girl.setFaceImg(2);
				ins.setText("Please drag and drop the Concealer on the model", 500, 30);
				state = 4; 
			}
			
			else if  (girl.clicked(mouseX, mouseY)&& state == 9) {
				girl.setFaceImg(0);
				ins.setText("Click restart if you wanna try different makeup combo", 550, 30);
				state = 10; 
			}
			
			else if (restart.clicked(mouseX, mouseY)&& state == 10) {
				//after restarting
				bkmusic.loop();
				
				
				mask = new Facemask(750,450, 0.04);
				helm = new Cap(900,560, 0.15);
				//steam = new Steam((float) girl.getX() - 100, (float) girl.getY()-150 , 180, 120);
		        stick = new Lipstick(750,320, 0.04);
		        blush = new Blush(1000,200, 0.04);
		        contour = new Contour(1000,80, 0.04);
		        concealer = new Concealer(750,80, 0.04);
		        shadow = new Eyeshadow(750,200, 0.04);
		        b_stick = new BLipstick(850,320, 0.04);
		        n_shadow = new NudeEyeshadow(850,200, 0.04);
				state =0;
			}
			else if (start.clicked(mouseX, mouseY)&& state ==-1) {
				state =0;
			}
				
		}
		public void mouseReleased(MouseEvent e) {
			offsetSet = false;
		}
	}
	
public class MyMouseMotionListener extends MouseMotionAdapter{
		
		public void mouseDragged(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			if (state == 0||state == 1) { 
				if (mask.clicked(mouseX, mouseY)) {
					if (offsetSet == false) {
						click.play(0);
						offsetX = mouseX - mask.getX();
						offsetY = mouseY - mask.getY();
						offsetSet = true;
					}
					mask.setPos(mouseX - offsetX, mouseY - offsetY);
					if (mask.hit(girl)) {
						state = 2;
						ins.setText("Please drag and drop the Hair steamer on the model", 500, 30);
						girl.setFaceImg(1);
					}
				}
			}
			if (state == 2) { 
				if (helm.clicked(mouseX, mouseY)) {
					if (offsetSet == false) {
						click.play(0);
						offsetX = mouseX - helm.getX();
						offsetY = mouseY - helm.getY();
						offsetSet = true;
					}
					helm.setPos(mouseX - offsetX, mouseY - offsetY);
					if (helm.hit(girl)) {
						state = 3;
						ins.setText("Please click on the model to remove items", 450, 30);
						girl.setFaceImg(3);
					}
				}
			}
			
			if (state == 4) { 
				if (concealer.clicked(mouseX, mouseY)) {
					if (offsetSet == false) {
						click.play(0);
						offsetX = mouseX - concealer.getX();
						offsetY = mouseY - concealer.getY();
						offsetSet = true;
					}
					concealer.setPos(mouseX - offsetX, mouseY - offsetY);
					if (concealer.hit(girl)) {
						state = 5;
						ins.setText("Please drag and drop the Contour on the model", 500, 30);
						girl.setFaceImg(4);
					}
				}
			}
			
			if (state == 5) { 
				if (contour.clicked(mouseX, mouseY)) {
					if (offsetSet == false) {
						click.play(0);
						offsetX = mouseX - contour.getX();
						offsetY = mouseY - contour.getY();
						offsetSet = true;
					}
					contour.setPos(mouseX - offsetX, mouseY - offsetY);
					if (contour.hit(girl)) {
						state =6;
						ins.setText("Please drag and drop the Blush on the model", 500, 30);
						girl.setFaceImg(5);
					}
				}
			}

			if (state == 6) { 
				if (blush.clicked(mouseX, mouseY)) {
					if (offsetSet == false) {
						click.play(0);
						offsetX = mouseX - blush.getX();
						offsetY = mouseY - blush.getY();
						offsetSet = true;
					}
					blush.setPos(mouseX - offsetX, mouseY - offsetY);
					if (blush.hit(girl)) {
						state =7;
						ins.setText("Please drag and drop the EyeShadow on the model", 500, 30);
						girl.setFaceImg(6);
					}
				}
			}

			if (state == 7) { //two choices here brown or pink eyeshadow
				if (shadow.clicked(mouseX, mouseY)) {
					if (offsetSet == false) {
						click.play(0);
						offsetX = mouseX - shadow.getX();
						offsetY = mouseY - shadow.getY();
						offsetSet = true;
					}
					shadow.setPos(mouseX - offsetX, mouseY - offsetY);
					if (shadow.hit(girl)) {
						state =8;
						ins.setText("Please drag and drop the Lipstick on the model", 500, 30);
						girl.setFaceImg(7);
					}
				}
				
				if (n_shadow.clicked(mouseX, mouseY)) {
					if (offsetSet == false) {
						click.play(0);
						offsetX = mouseX - n_shadow.getX();
						offsetY = mouseY - n_shadow.getY();
						offsetSet = true;
					}
					n_shadow.setPos(mouseX - offsetX, mouseY - offsetY);
					if (n_shadow.hit(girl)) {
						state =-8;
						ins.setText("Please drag and drop the Lipstick on the model", 500, 30);
						girl.setFaceImg(-7);
					}
				}
			}
			if (state == 8) { // if pink eyeshadow is chosen
				if (stick.clicked(mouseX, mouseY)) {
					if (offsetSet == false) {
						click.play(0);
						offsetX = mouseX - stick.getX();
						offsetY = mouseY - stick.getY();
						offsetSet = true;
					}
					stick.setPos(mouseX - offsetX, mouseY - offsetY);
					if (stick.hit(girl)) {
						state =9;
						ins.setText("Click on model if you wish to end simulation", 500, 30);
						girl.setFaceImg(8); //pink lips
					}
				}
				if (b_stick.clicked(mouseX, mouseY)) {
					if (offsetSet == false) {
						click.play(0);
						offsetX = mouseX - b_stick.getX();
						offsetY = mouseY - b_stick.getY();
						offsetSet = true;
					}
					b_stick.setPos(mouseX - offsetX, mouseY - offsetY);
					if (b_stick.hit(girl)) {
						state =9;
						ins.setText("Click on model if you wish to end simulation", 500, 30);
						girl.setFaceImg(-6); //black lips
					}
				}
				
			}
			
			if (state == -8) { 
				if (stick.clicked(mouseX, mouseY)) { //if brown eyeshadow is chosen
					if (offsetSet == false) {
						click.play(0);
						offsetX = mouseX - stick.getX();
						offsetY = mouseY - stick.getY();
						offsetSet = true;
					}
					stick.setPos(mouseX - offsetX, mouseY - offsetY);
					if (stick.hit(girl)) {
						state =9;
						ins.setText("Click on model if you wish to end simulation", 500, 30);
						girl.setFaceImg(-9);
					}
				}
				if (b_stick.clicked(mouseX, mouseY)) {
					if (offsetSet == false) {
						click.play(0);
						offsetX = mouseX - b_stick.getX();
						offsetY = mouseY - b_stick.getY();
						offsetSet = true;
					}
					b_stick.setPos(mouseX - offsetX, mouseY - offsetY);
					if (b_stick.hit(girl)) {
						state =9;
						ins.setText("Click on model if you wish to end simulation", 500, 30);
						girl.setFaceImg(-8);
					}
				}
				
			}
			
			
		}
	}
//fractals
public void drawCircle(Graphics2D g2, int x , int y , int sz) {
	g2.fillOval(x, y, sz, sz);
	x+=50;
	y+=50;
	for(int i =sz;sz>=0;sz-=10) {
		drawCircle(g2,x,y,sz);
	}
	
	//drawCircle(g2);
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

}
