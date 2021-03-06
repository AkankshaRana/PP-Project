package TeamFormations;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Defender extends Player{

	private int x, y,maxY,minY;
	private String teamType;
	private Image img ;
	
	
	public Defender(int initialX, int initialY,int minY,int maxY, String teamType) {
		this.x=initialX;
		this.y=initialY;
		this.maxY = maxY;
		this.minY=minY;
		this.teamType=teamType;
		
		
		try {
			if(this.teamType.equalsIgnoreCase("team1")){
				img = ImageIO.read(new File("red.jpg")); //CHANGE IT!
				}
				else if(this.teamType.equalsIgnoreCase("team2")){
					img = ImageIO.read(new File("blue.png"));
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void setX(int x) {
		this.x=x;
		
	}

	@Override
	public void setY(int y) {
		this.y=y;
		
	}

	@Override
	public int getMinY() {
		return this.minY;
	}

	@Override
	public int getMaxY() {
		return this.maxY;
	}

	@Override
	public void setMinY(int y) {
		this.minY=y;
		
	}

	@Override
	public void setMaxY(int y) {
		this.maxY=y;
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, x, y, 20, 20, null, null);
	}
	
	@Override
	public void setImage(String imgPath) {
		try {
			this.img=ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String getTeamType() {
		
		return this.teamType;
	}

}
