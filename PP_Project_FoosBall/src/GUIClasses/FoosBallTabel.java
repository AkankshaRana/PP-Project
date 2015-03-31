package GUIClasses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import TeamFormations.Attacker;
import TeamFormations.Defender;
import TeamFormations.GoalKeeper;
import TeamFormations.MidFielder;
import TeamFormations.Player;
import TeamFormations.Team1;
import TeamFormations.Team2;
import TeamFormations.TeamFactory;

public class FoosBallTabel extends JPanel{

	int maxX, maxY, minX, minY, boardWidth, boardHeight;
	Team1 team1;
	Team2 team2;
	TeamFactory factory;
	Ball ball;
	
	public FoosBallTabel(){
		
		
		setPreferredSize(new Dimension(820, 500));
		setBackground(Color.GREEN);
	}
	
	public void paintComponent(Graphics g){

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.WHITE);
		g.drawLine(100, 0, 100, 500); 

		g.setColor(Color.WHITE);
		g.drawLine(180, 0, 180, 500);

		g.setColor(Color.WHITE);
		g.drawLine(260, 0, 260, 500);

		g.setColor(Color.WHITE);
		g.drawLine(340, 0, 340, 500);

		g.setColor(Color.YELLOW);
		g.drawLine(400, 0, 400, 500);

		g.setColor(Color.WHITE);
		g.drawLine(460, 0, 460, 500);

		g.setColor(Color.WHITE);
		g.drawLine(540, 0, 540, 500);

		g.setColor(Color.WHITE);
		g.drawLine(620, 0, 620, 500);

		g.setColor(Color.WHITE);
		g.drawLine(700, 0, 700, 500);

		
	}
	
	public void gameStart(){
		
	}
	
	public void gameReset(){
		
	}
	
	public void gameUpdate(){
		
	}
	
	public void movePlayer(FoosBallTabel tabel, Player player){
		
	}
	
	public void moveAttackerRod(Attacker[] attackers){
		
	}
	
	public void moveDefenderRod(Defender[] defenders){
		
	}
	
	public void moveMidFielderRod(MidFielder[] midFielders){
		
	}
	
	
	public void moveGoalKeeperRod(GoalKeeper goalKeeper){
		
	}
	
	
}
