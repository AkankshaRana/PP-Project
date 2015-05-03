package GUIClasses;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import TeamFormations.Attacker;
import TeamFormations.Defender;
import TeamFormations.GoalKeeper;
import TeamFormations.MidFielder;
import TeamFormations.Player;
import TeamFormations.Team1;
import TeamFormations.Team2;
import TeamFormations.TeamFactory;

public class FoosBallTabel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int maxX, maxY, minX, minY, boardWidth, boardHeight;
	//Team1 team1;
	//Team2 team2;
	TeamFactory factory;
	Ball ball;
	int Attackflag;
	Thread gameThread;
	private DownAction downAction;
	private UPAction upAction;
	private CAction cAction;
	private SpaceAction spaceAction;
	private static final int UPDATE_RATE = 30;
	
	public FoosBallTabel(){
		
		
		setPreferredSize(new Dimension(820, 500));
		setBackground(Color.GREEN);
		factory=TeamFactory.getInstance();
		this.maxX=820;
		this.maxY=500;
		this.minX=0;
		this.minY=0;
		this.Attackflag=0;
		
		this.ball=Ball.getBallInstance();
		
		this.downAction = new DownAction();
		this.upAction=new UPAction();
		this.cAction = new CAction ();
		this.spaceAction=new SpaceAction();
		
		this.getInputMap().put( KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0),

                "doDownAction" );
		this.getActionMap().put( "doDownAction", this.downAction );
		
		this.getInputMap().put( KeyStroke.getKeyStroke(KeyEvent.VK_UP,0),

                "doUpAction" );
		
		this.getActionMap().put( "doUpAction", this.upAction );
		
		this.getInputMap().put( KeyStroke.getKeyStroke(KeyEvent.VK_C,0),

                "doCAction" );
		this.getActionMap().put( "doCAction", this.cAction );
		
		this.getInputMap().put( KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0),

                "doSpaceAction" );
		
		this.getActionMap().put( "doSpaceAction", this.spaceAction );



		//gameStart();
	}
	
	public void paintComponent(Graphics g){

		super.paintComponent(g);

		//Graphics2D g2 = (Graphics2D) g;
		
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
		
		g.setColor(Color.BLUE);
		g.drawRect(0, 140, 70, 120); 

		g.setColor(Color.red);
		g.drawRect(733, 140, 70, 120);
		
		for(int i=0; i<this.factory.getTeam1().length;i++){
			if(this.factory.getTeam1()[i] instanceof GoalKeeper){
				((GoalKeeper)(this.factory.getTeam1()[i])).paintComponent(g);
			}
			
			else if(this.factory.getTeam1()[i] instanceof Attacker){
				((Attacker)(this.factory.getTeam1()[i])).paintComponent(g);
			}
			if(this.factory.getTeam1()[i] instanceof MidFielder){
				((MidFielder)(this.factory.getTeam1()[i])).paintComponent(g);
			}
			if(this.factory.getTeam1()[i] instanceof Defender){
				((Defender)(this.factory.getTeam1()[i])).paintComponent(g);
			}
			//this.factory.getTeam1()[i].paintComponents(g);
		}
		
		for(int i=0; i<this.factory.getTeam2().length;i++){
			//this.factory.getTeam2()[i].paintComponents(g);
			if(this.factory.getTeam2()[i] instanceof GoalKeeper){
				((GoalKeeper)(this.factory.getTeam2()[i])).paintComponent(g);
			}
			
			else if(this.factory.getTeam2()[i] instanceof Attacker){
				((Attacker)(this.factory.getTeam2()[i])).paintComponent(g);
			}
			if(this.factory.getTeam2()[i] instanceof MidFielder){
				((MidFielder)(this.factory.getTeam2()[i])).paintComponent(g);
			}
			if(this.factory.getTeam2()[i] instanceof Defender){
				((Defender)(this.factory.getTeam2()[i])).paintComponent(g);
			}
		}
		
		this.ball.draw(g);
		
	}
	
	public void gameStart(){
		
		gameThread = new Thread() {
			public void run() {
				//System.out.println("in run");
				//factory=TeamFactory.getInstance();
				while (true) {
					factory=TeamFactory.getInstance();
					gameUpdate(); 

					repaint();
					
					gameReset(); 
					
					//TeamFactory factory=TeamFactory.getInstance();
					
					//moveAttackerRod(factory.team1.attackers);
					//moveAttackerRod(factory.team2.attackers);
					//moveDefenderRod(factory.team1.defenders);
					//moveDefenderRod(factory.team2.defenders);
					//moveMidFielderRod(factory.team1.midFielders);
					//moveMidFielderRod(factory.team2.midFielders);
					//moveGoalKeeperRod(factory.team1.goalKeeper);
					//moveGoalKeeperRod(factory.team2.goalKeeper);
					
					try {
						Thread.sleep(1000 / UPDATE_RATE);
					} catch (InterruptedException ex) {}
				}
			}
		};
		gameThread.start();
		
	}
	
	public void gameReset(){
		if(ball.check==1){
			if((ball.team1GoalCount>0 &&ball.team1GoalCount<5) ||( ball.team2GoalCount>0 && ball.team2GoalCount<5)){
				ball.setX(393);
				ball.setY(210);

			}
		}
	}
	
	public void gameUpdate(){
		this.ball.checkCollisionAndMove(factory, this);
	}
	
	
	
	public void movePlayer(FoosBallTabel tabel, Player player){
		
		int speedY=5;
		int y=player.getY();
		
		if(Attackflag==0 && (y>tabel.minY) && (y<player.getMaxY()))
		{	
			//System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
			//speedY=2;
			y+=speedY;
			player.setY(y);	
			
		}
		
		else if(y<=player.getMaxY())
		{
			//System.out.println("************************************************************");
			speedY = -speedY;
			y+=speedY;
			player.setY(y);
			Attackflag=1;
			if(y==player.getMinY()){
				Attackflag=0;
			}
			
		}
			
	}
	
public void movePlayerUP(FoosBallTabel tabel, Player player){
		
		int speedY_UP=-5;
		int y_UP=player.getY();
		
		if(Attackflag==0 &&(y_UP>player.getMinY()) && (y_UP<=player.getMaxY()))
		{	
			//System.out.println("IN MOVE PLAYER UP");
			//speedY=2;
			y_UP+=speedY_UP;
			player.setY(y_UP);	
			
		}
		
		else if(y_UP==player.getMinY())
		{
			//System.out.println("IN MOVE PLAYER UP ELSEIF CONDITION");
			//speedY_UP = -speedY_UP;
			/*y_UP=player.getY();
			player.setY(y_UP);
			Attackflag=1;
			if(y_UP==player.getMinY()){
				Attackflag=0;
			}*/
			
		}
			
	}public void movePlayerDOWN(FoosBallTabel tabel, Player player){
		
		int speedY_DOWN=5;
		int y_DOWN=player.getY();
		
		if(Attackflag==0 &&(y_DOWN>=player.getMinY()) && (y_DOWN<player.getMaxY()))
		{	
			//System.out.println("IN MOVE PLAYER DOWN");
			//speedY=2;
			y_DOWN+=speedY_DOWN;
			player.setY(y_DOWN);	
			
		}
		
		else if(y_DOWN==player.getMaxY())
		{
			//System.out.println("IN MOVE PLAYER DOWN ELSEIF CONDITION");
			//speedY_UP = -speedY_UP;
			/*y_DOWN=player.getY();
			player.setY(y_DOWN);
			Attackflag=1;
			if(y_DOWN==player.getMinY()){
				Attackflag=0;
			}*/
			
		}
			
	}
	
	
	public void moveAttackerRod(Attacker[] attackers){
		for(int i=0;i<attackers.length;i++){
			movePlayer(this,attackers[i]);
		}
	}
	
	public void moveDefenderRod(Defender[] defenders){
		for(int i=0;i<defenders.length;i++){
			movePlayer(this,defenders[i]);
		}
	}
	
	public void moveMidFielderRod(MidFielder[] midFielders){
		for(int i=0;i<midFielders.length;i++){
			movePlayer(this,midFielders[i]);
		}
	}
	
	
	public void moveGoalKeeperRod(GoalKeeper goalKeeper){
		
			movePlayer(this,goalKeeper);
		
	}
	
	public void moveAttackerRod_UP(Attacker[] attackers){
		for(int i=0;i<attackers.length;i++){
			movePlayerUP(this,attackers[i]);
		}
	}
	
	public void moveDefenderRod_UP(Defender[] defenders){
		for(int i=0;i<defenders.length;i++){
			movePlayerUP(this,defenders[i]);
		}
	}
	
	public void moveMidFielderRod_UP(MidFielder[] midFielders){
		for(int i=0;i<midFielders.length;i++){
			movePlayerUP(this,midFielders[i]);
		}
	}
	
	
	public void moveGoalKeeperRod_UP(GoalKeeper goalKeeper){
		
			movePlayerUP(this,goalKeeper);
		
	}
	
	public void moveAttackerRod_DOWN(Attacker[] attackers){
		for(int i=0;i<attackers.length;i++){
			movePlayerDOWN(this,attackers[i]);
		}
	}
	
	public void moveDefenderRod_DOWN(Defender[] defenders){
		for(int i=0;i<defenders.length;i++){
			movePlayerDOWN(this,defenders[i]);
		}
	}
	
	public void moveMidFielderRod_DOWN(MidFielder[] midFielders){
		for(int i=0;i<midFielders.length;i++){
			movePlayerDOWN(this,midFielders[i]);
		}
	}
	
	
	public void moveGoalKeeperRod_DOWN(GoalKeeper goalKeeper){
		
		movePlayerDOWN(this,goalKeeper);
		
	}
	
	public void movePlayersUp(){
		 
		
	}
	
	public void movePlayersDown(){
		
	}
	
	public void readyGameField(int numAttackers,int numDefenders,int numMidFielder,String teamType){
		this.factory.formTeam1(numAttackers, numDefenders, numMidFielder, teamType);
		this.factory.formTeam2(numAttackers, numDefenders, numMidFielder, teamType);
	}
	
	
	  class DownAction extends AbstractAction

	    {

	        public void actionPerformed( ActionEvent tf )

	        {
	        	
	        	factory=TeamFactory.getInstance();
				gameUpdate(); 

				repaint();
				
				gameReset(); 

	           // System.out.println( "The Enter key has been pressed." );
	        	moveAttackerRod_DOWN(factory.team2.attackers);
	        	moveDefenderRod_DOWN(factory.team2.defenders);
	        	moveMidFielderRod_DOWN(factory.team2.midFielders);
	        	moveGoalKeeperRod_DOWN(factory.team2.goalKeeper);
	        	
	        	try {
					Thread.sleep(1000 / UPDATE_RATE);
				} catch (InterruptedException ex) {}

	             

	        } 

	         

	    } 

	  
	  class UPAction extends AbstractAction

	    {

	        public void actionPerformed( ActionEvent tf )

	        {
	        	factory=TeamFactory.getInstance();
				gameUpdate(); 

				repaint();
				
				gameReset(); 
	           

	           // System.out.println( "The Enter key has been pressed." );
	        	moveAttackerRod_UP(factory.team2.attackers);
	        	moveDefenderRod_UP(factory.team2.defenders);
	        	moveMidFielderRod_UP(factory.team2.midFielders);
	        	moveGoalKeeperRod_UP(factory.team2.goalKeeper);
	        	
	        	try {
					Thread.sleep(1000 / UPDATE_RATE);
				} catch (InterruptedException ex) {}
	        	
	           

	             

	        } 

	         

	    } 
	  class SpaceAction extends AbstractAction

	    {

	        public void actionPerformed( ActionEvent tf )

	        {
	        	
	        	factory=TeamFactory.getInstance();
				gameUpdate(); 

				repaint();
				
				gameReset(); 

	           // System.out.println( "The Enter key has been pressed." );
	        	moveAttackerRod_DOWN(factory.team1.attackers);
	        	moveDefenderRod_DOWN(factory.team1.defenders);
	        	moveMidFielderRod_DOWN(factory.team1.midFielders);
	        	moveGoalKeeperRod_DOWN(factory.team1.goalKeeper);
	        	
	        	try {
					Thread.sleep(1000 / UPDATE_RATE);
				} catch (InterruptedException ex) {}

	             

	        } 

	         

	    } 

	  
	  class CAction extends AbstractAction

	    {

	        public void actionPerformed( ActionEvent tf )

	        {
	        	factory=TeamFactory.getInstance();
				gameUpdate(); 

				repaint();
				
				gameReset(); 
	           

	           // System.out.println( "The Enter key has been pressed." );
	        	moveAttackerRod_UP(factory.team1.attackers);
	        	moveDefenderRod_UP(factory.team1.defenders);
	        	moveMidFielderRod_UP(factory.team1.midFielders);
	        	moveGoalKeeperRod_UP(factory.team1.goalKeeper);
	        	
	        	try {
					Thread.sleep(1000 / UPDATE_RATE);
				} catch (InterruptedException ex) {}
	        	
	           

	             

	        } 

	         

	    } 

	
	
}
