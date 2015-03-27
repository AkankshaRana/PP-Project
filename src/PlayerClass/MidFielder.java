package PlayerClass;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class MidFielder extends Player{

public int initialX;
public int initialY;
public int maxY;
public int minY;
	
	public MidFielder(int initialX, int initialY,int minY,int maxY, Color c) {
		super(initialX, initialY);
		this.maxY = maxY;
		this.minY=minY;
		this.teamColor=c;
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
