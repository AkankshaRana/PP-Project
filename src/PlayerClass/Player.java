package PlayerClass;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JApplet;
import javax.swing.JComponent;

public abstract class Player extends JComponent 
{
	Rectangle2D player;
	double maxKickSpeed;
	double minKickSpeed;
	double kickSpeed;
	double horizontalSpeed; 
	int teamName;
	public int initialX;
	public int initialY;
	
	public Player(int initialX, int initialY){
		//player = new Rectangle2D.Double(initialX, initialY, 20, 20);
		this.initialX=initialX;
		this.initialY=initialY;
	}
	
	
	public  Rectangle2D getPlayer(){
		return player;
	}
	
	public abstract void draw(Graphics g);
	
	public abstract int getX();
	public abstract int getY();
	public void setX(int x){
		this.initialX=x;
	}
	
	public void setY(int y){
		this.initialY=y;
	}
}

