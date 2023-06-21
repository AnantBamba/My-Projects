package main;

import javax.swing.JFrame;

public class SalonApp extends JFrame {

	private static final long serialVersionUID = 1L;

	public SalonApp(String title) {
		super(title);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SalonPanel bpnl = new SalonPanel(this);
		this.add(bpnl); 
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main (String[] args){
		new SalonApp("SalonApp");
		
	}
}
