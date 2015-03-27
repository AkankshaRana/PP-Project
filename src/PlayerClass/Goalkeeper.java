package PlayerClass;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Goalkeeper extends Player{

public int initialX;
public int initialY;
public int maxy;
public int minY;
	
	public Goalkeeper(int initialX, int initialY,int minY,int maxy, Color c) {
		super(initialX, initialY);
		this.teamColor=c;
		this.maxy = maxy;
		this.minY=minY;
		this.initialX=initialX;
		this.initialY=initialY;
		
	}

	Rectangle2D player;
	Color teamColor;
	
	@Override
	public void draw(Graphics g) {
		//Graphics2D g2 = (Graphics2D) g;
		//player=super.getPlayer();
		g.setColor(this.teamColor);  // attacker red
		g.fillRect(initialX, initialY, 20, 20); 
	}
	
	@Override
	public int getX() {
		
		return this.initialX;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.initialY;
	}
}
