package TeamFormations;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;


public abstract class Player extends JComponent {

	
	
	public abstract int getX();
	public abstract int getY();
	public abstract void setX(int x);
	public abstract void setY(int y);
	public abstract int getMinY();
	public abstract int getMaxY();
	public abstract void setMinY(int y);
	public abstract void setMaxY(int y);
	public abstract void setImage(String imgPath);
	public abstract String getTeamType();
	@Override
	public abstract void paintComponent(Graphics g);
	
}
