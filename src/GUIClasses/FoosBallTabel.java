package GUIClasses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import BackendClasses.Database;
import BackendClasses.GameTimer;
import BackendClasses.Music;
import BackendClasses.Status;
import TeamFormations.Attacker;
import TeamFormations.Defender;
import TeamFormations.GoalKeeper;
import TeamFormations.MidFielder;
import TeamFormations.Player;
import TeamFormations.TeamFactory;

public class FoosBallTabel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int maxX, maxY, minX, minY, boardWidth, boardHeight;
	int RegionLength;
	//Team1 team1;
	//Team2 team2;
	TeamFactory factory;
	Ball ball;
	Music music;
	private Team1DisplayPanel team1Panel;
	private Team2DisplayPanel team2Panel;
	private TimerPanel timerPanel;
	int Attackflag;
	Database database;
	Thread gameThread;
	private DownAction downAction;
	private UPAction upAction;
	private CAction cAction;
	private SpaceAction spaceAction;
	private static final int UPDATE_RATE = 30;
	private Status status;
	String level;
	String gameMode;
	String gameType;
	public FoosBallTabel(){
		this.music=Music.getInstance();
		//this.database=Database.getInstance();
		setPreferredSize(new Dimension(960, 500));
		setBackground(Color.GREEN);
		factory=TeamFactory.getInstance();
		this.maxX=960;
		this.maxY=500;
		this.minX=0;
		this.minY=0;
		this.Attackflag=0;
		this.RegionLength=maxX/8;
		
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
	
	public void setStatus(Team1DisplayPanel team1Panel, Team2DisplayPanel team2Panel, TimerPanel timerPanel){
		this.team1Panel=team1Panel;
		this.team2Panel=team2Panel;
		this.timerPanel=timerPanel;
		this.status=Status.getInstance(this.team1Panel, this.team2Panel, this.timerPanel);
		//this.ball=Ball.getBallInstance();
		this.ball.setStatus(this.status);
	}
	
	public void paintComponent(Graphics g){

		super.paintComponent(g);

		//Graphics2D g2 = (Graphics2D) g;
		int mid=maxX/2;
		g.setColor(Color.WHITE);
		g.drawLine(mid/5, 0, mid/5, maxY); 

		g.setColor(Color.WHITE);
		g.drawLine((2*mid)/5, 0, (2*mid)/5, maxY);

		g.setColor(Color.WHITE);
		g.drawLine((3*mid)/5, 0, (3*mid)/5, maxY);

		g.setColor(Color.WHITE);
		g.drawLine((4*mid)/5, 0, (4*mid)/5, maxY);

		g.setColor(Color.YELLOW);
		g.drawLine(maxX/2, 0, maxX/2, maxY);
		
		g.setColor(Color.WHITE);
		g.drawLine(mid+(mid/5), 0,mid+(mid/5) , maxY);

		g.setColor(Color.WHITE);
		g.drawLine(mid+2*(mid/5), 0, mid+2*(mid/5), maxY);

		g.setColor(Color.WHITE);
		g.drawLine(mid+3*(mid/5), 0, mid+3*(mid/5), maxY);

		g.setColor(Color.WHITE);
		g.drawLine(mid+4*(mid/5), 0, mid+4*(mid/5), maxY);
		
		g.setColor(Color.BLUE);
		g.fillRect(0, 180, 50, 120);
		//g.drawRect(0, 140, 50, 120); 
	

		g.setColor(Color.red);
		g.fillRect(910, 180, 50, 120);
		
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
	
	public void gameStart(LevelFactory levelFactory,final String GameMode,final String gameType){
		this.music.startMusic();
		this.level=levelFactory.getLevel();
		this.gameMode=GameMode;
		this.gameType=gameType;
		if(gameType.equals("Timer"))
		{	GameTimer timer=new GameTimer(this,this.ball,this.team1Panel,this.team2Panel);
			timer.setStatus(this.status);
			timer.startTimer();
			
		}
		gameThread = new Thread() {
			public void run() {
				//System.out.println("in run");
				//factory=TeamFactory.getInstance();
				while (true) {
					factory=TeamFactory.getInstance();
					gameUpdate(); 

					repaint();
					
					gameReset(gameType); 
					if(GameMode.equals("Single"))
					{
						if(level.equals("Novice"))
						{
							moveAttackerRod(factory.team1.attackers,5,"Novice");  //5 is ball speed
							moveDefenderRod(factory.team1.defenders,5,"Novice");
							moveMidFielderRod(factory.team1.midFielders,5,"Novice");
							moveGoalKeeperRod(factory.team1.goalKeeper,5,"Novice");
						}
						else if(level.equals("Intermediate"))
						{
							moveAttackerRod(factory.team1.attackers,10,"Intermediate");  //10 is ball speed
							moveDefenderRod(factory.team1.defenders,10,"Intermediate");
							moveMidFielderRod(factory.team1.midFielders,10,"Intermediate");
							moveGoalKeeperRod(factory.team1.goalKeeper,10,"Intermediate");
						}
						else if(level.equals("Advance"))
							moveTeam1();
						
					}
					//TeamFactory factory=TeamFactory.getInstance();
					//moveTeam1();
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
	
	public void gameReset(String gameTypeSelected){
		
		if(ball.check==1){
			
				if(gameTypeSelected.equals("Goal"))
				{
					if((ball.team1GoalCount>0 &&ball.team1GoalCount<2) ||( ball.team2GoalCount>0 && ball.team2GoalCount<2))
					{
						ball.setX(maxX/2);
						ball.setY(maxY/2);
					}
					else 
					{
						if(ball.team1GoalCount==2)
						{
							this.team1Panel.setResult1("Winner");
							this.team2Panel.setResult2("Loser");
							
							music.stopMusic();
							
							if(team1Panel.nameDisplay.getText().equalsIgnoreCase("Computer")==false)  //player has won
							{
								JPanel confirmPanel = new JPanel();
								JLabel msg=new JLabel("Aim of 5 goals achieved!!!"+team1Panel.nameDisplay.getText()+" Won!!");
								JLabel question = new JLabel("Would you like to save this score in our database?");
								confirmPanel.add(msg);
								confirmPanel.add(question);
								playSound();
								int answer = JOptionPane.showConfirmDialog(null,confirmPanel, "Game Over",
										JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
								if (answer == JOptionPane.OK_OPTION) 
								{
									//Database.updateDatabase("Highest", Integer.valueOf(team1Panel.scoreDisplay.getText()));
									Database.updateDatabase(team1Panel.nameDisplay.getText(), Integer.valueOf(team1Panel.scoreDisplay.getText()));
									System.out.println(team1Panel.nameDisplay.getText());
									System.exit(0);
								} 
								else if(answer==JOptionPane.NO_OPTION)
								{
									System.exit(0);
								}						
								else if(answer==JOptionPane.CLOSED_OPTION)
								{
									System.exit(0);
								}
							}
							
							else  //computer has won
							{
								JOptionPane.showMessageDialog(null, "You lose, Game over!");
								System.exit(0);
							}
						}
						if(ball.team2GoalCount==2)//team2goal count is 5
						{
							////  add more to this shreya-like database , window close etc.
							this.team2Panel.setResult2("Winner");
							this.team1Panel.setResult1("Loser");
							music.stopMusic();
						//	status=Status.getInstance(team1Panel, team2Panel, timerPanel);
							JPanel confirmPanel = new JPanel();
			            	JLabel msg=new JLabel("Aim of 5 goals achieved!!!"+this.team2Panel.nameDisplay.getText()+" Won!!");
			            	JLabel question = new JLabel("Would you like to save this score in our database?");
			            	confirmPanel.add(msg);
			            	confirmPanel.add(question);
			            	playSound();
			            	System.out.println("PLayer 2 wonnnnnnnn");
			            	int answer = JOptionPane.showConfirmDialog(null,confirmPanel, "Game Over",
					            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
							
			            	if (answer == JOptionPane.OK_OPTION) 
			            	{
			            		//Database.updateDatabase("Highest", Integer.valueOf(this.team2Panel.scoreDisplay.getText()));
			            		Database.updateDatabase(this.team2Panel.nameDisplay.getText(), Integer.valueOf(this.team2Panel.scoreDisplay.getText()));
								System.out.println(team2Panel.nameDisplay.getText());
								System.exit(0);
					        } 
			            	else if(answer==JOptionPane.NO_OPTION){
			            		System.exit(0);
			            	}
														
							else if(answer==JOptionPane.CLOSED_OPTION)
							{
								System.exit(0);
							}
						}		
					}
				}
				else if(gameTypeSelected.equals("Timer"))
				{
					if((ball.team1GoalCount>0) || (ball.team2GoalCount>0))
					{
						ball.setX(maxX/2);
						ball.setY(maxY/2);
					}
				}
		}
	}
	
	public void gameUpdate(){
		this.ball.checkCollisionAndMove(factory, this);
	}
	
	
	
public void movePlayer(Player player, int speed){   //novice level
		
		int speedY=speed;
		int player_pos=player.getY();
		player_pos+=speedY;
		
		if((Attackflag==0)&&((player_pos>=player.getMinY())&&(player_pos<=player.getMaxY())))
		{
			player.setY(player_pos);
		}
		else if (player_pos>player.getMaxY())
		{
			speedY=-speedY;
			player.setY(player.getMinY()+speedY);
			Attackflag=1;
			if(player_pos==player.getMinY())
			{
				Attackflag=0;
			}
		}
}
	
public void movePlayerComputer(FoosBallTabel tabel, Player player,int speed){   //region based -intermediate
	
	int ball_pos= ball.getY();
	
	
	int player_pos = player.getY();
	//System.out.print("old Player pos is "+player_pos+" min is "+player.getMinY()+"\n");
	
    //ball is in region 1 
	if(((ball_pos>0)&&(ball_pos<=RegionLength))||((ball_pos>2*RegionLength)&&(ball_pos<=3*RegionLength))||((ball_pos>4*RegionLength)&&(ball_pos<=5*RegionLength)&&((ball_pos>6*RegionLength)&&(ball_pos<=7*RegionLength)))){
		//player_pos = player.getY();
		speed =-speed;
		
		player_pos+=speed;
		
		if((player_pos>=player.getMinY())&&(player_pos<=player.getMaxY()))
		{
			player.setY(player_pos);
			if((player_pos<player.getMinY())||(player_pos>player.getMaxY()))
			System.out.print("  wrong 1 "+player_pos+"\n");
			
		}
		else if ((player_pos<player.getMinY())&&(player_pos<=player.getMaxY()))
		{
			player.setY(player.getMinY());
			if((player_pos<player.getMinY())||(player_pos>player.getMaxY()))
				System.out.print("  wrong 2 "+player_pos+"\n");
			//System.out.print("  new Player pos is "+player_pos+"\n");
		}
		else if((player_pos>player.getMaxY())&&(player_pos>=player.getMinY()))
			{
				player.setY(player.getMaxY());
				if((player_pos<player.getMinY())||(player_pos>player.getMaxY()))
					System.out.print("  wrong 4 "+player_pos+"\n");
			}

	}
	
	else if(((ball_pos>RegionLength)&&(ball_pos<=2*RegionLength))||((ball_pos>3*RegionLength)&&(ball_pos<=4*RegionLength))||((ball_pos>7*RegionLength)&&(ball_pos<=8*RegionLength))){
		
		//player_pos = player.getY();
		player_pos+=speed;
		if((player_pos>=player.getMinY())&&(player_pos<=player.getMaxY()))
		{
			player.setY(player_pos);
			if((player_pos<player.getMinY())||(player_pos>player.getMaxY()))
			System.out.print("  wrong 1 "+player_pos+"\n");
			
		}
		else if ((player_pos<player.getMinY())&&(player_pos<=player.getMaxY()))
		{
			player.setY(player.getMinY());
			if((player_pos<player.getMinY())||(player_pos>player.getMaxY()))
				System.out.print("  wrong 2 "+player_pos+"\n");
			//System.out.print("  new Player pos is "+player_pos+"\n");
		}
		else if((player_pos>player.getMaxY())&&(player_pos>=player.getMinY()))
			{
				player.setY(player.getMaxY());
				if((player_pos<player.getMinY())||(player_pos>player.getMaxY()))
					System.out.print("  wrong 4 "+player_pos+"\n");
			}	
	}	
}
public void moveAll(int speed)
{
	moveAttackerRod(factory.team1.attackers,speed,"Advance");
	moveDefenderRod(factory.team1.defenders,speed,"Advance");
	moveMidFielderRod(factory.team1.midFielders,speed,"Advance");
	moveGoalKeeperRod(factory.team1.goalKeeper,speed,"Advance");
}

public void movePlayerStrategy(Player player,int speed)
{
	
	int player_pos = player.getY();
	player_pos+=speed;
	if((player_pos>=player.getMinY())&&(player_pos<=player.getMaxY()))
	{
		player.setY(player_pos);
		if((player_pos<player.getMinY())||(player_pos>player.getMaxY()))
			System.out.print("  wrong 1 "+player_pos+"\n");
		
	}
	else if ((player_pos<player.getMinY())&&(player_pos<=player.getMaxY()))
	{
		player.setY(player.getMinY());
		if((player_pos<player.getMinY())||(player_pos>player.getMaxY()))
			System.out.print("  wrong 2 "+player_pos+"\n");
		//System.out.print("  new Player pos is "+player_pos+"\n");
	}
	else if((player_pos>player.getMaxY())&&(player_pos>=player.getMinY()))
		{
			player.setY(player.getMaxY());
			if((player_pos<player.getMinY())||(player_pos>player.getMaxY()))
				System.out.print("  wrong 4 "+player_pos+"\n");
		}	
	
	
}
public void moveTeam1()    //------------------------->
{
	int ballX=ball.getX();
	int ballY=ball.getY();
	int ballPrevX=(int)ball.getPrevX();
	int ballPrevY=(int)ball.getPrevY();
	int mid=maxX/2;
	int region=mid/5;
	if(((ballX>(region-10))&&(ballX<=(2*region-10))))
	{
		String activeRod=getActiveRodTeam1(1,ballPrevX,ballX);//90,170,
		Player activePlayer;
		if(activeRod.equals("Defender"))
			activePlayer=getActivePlayer(factory.team1.defenders,ballY);
		else if(activeRod.equals("Attacker"))
			activePlayer=getActivePlayer(factory.team1.attackers,ballY);
		else if(activeRod.equals("MidFielder"))
			activePlayer=getActivePlayer(factory.team1.midFielders,ballY);
		else
			activePlayer= factory.team1.goalKeeper;
		//Player activePlayer=getActivePlayer(activeRod);
		int speed=ballY-activePlayer.getY();
		moveAll(speed);
	}
	else if((ballX>(2*region-10)&&(ballX<=(4*region-10))))
	{
		String activeRod=getActiveRodTeam1(2,ballPrevX,ballX);//170,330,
		Player activePlayer;
		if(activeRod.equals("Defender"))
			activePlayer=getActivePlayer(factory.team1.defenders,ballY);
		else if(activeRod.equals("Attacker"))
			activePlayer=getActivePlayer(factory.team1.attackers,ballY);
		else if(activeRod.equals("MidFielder"))
			activePlayer=getActivePlayer(factory.team1.midFielders,ballY);
		else
			activePlayer= factory.team1.goalKeeper;
		int speed=ballY-activePlayer.getY();
		moveAll(speed);
	}
	if((ballX>((4*region-10))&&(ballX<(mid+2*region-10))))
	{
		String activeRod=getActiveRodTeam1(3,ballPrevX,ballX);//330,530
		Player activePlayer;
		if(activeRod.equals("Defender"))
			activePlayer=getActivePlayer(factory.team1.defenders,ballY);
		else if(activeRod.equals("Attacker"))
			activePlayer=getActivePlayer(factory.team1.attackers,ballY);
		else if(activeRod.equals("MidFielder"))
			activePlayer=getActivePlayer(factory.team1.midFielders,ballY);
		else
			activePlayer= factory.team1.goalKeeper;
		int speed=ballY-activePlayer.getY();
		moveAll(speed);
	}
	if((ballX>(mid+2*region-10))&&(ballX<maxX))
	{
		String activeRod=getActiveRodTeam1(4,ballPrevX,ballX);//530,tabel.maxX,
		Player activePlayer;
		if(activeRod.equals("Defender"))
			activePlayer=getActivePlayer(factory.team1.defenders,ballY);
		else if(activeRod.equals("Attacker"))
			activePlayer=getActivePlayer(factory.team1.attackers,ballY);
		else if(activeRod.equals("MidFielder"))
			activePlayer=getActivePlayer(factory.team1.midFielders,ballY);
		else
			activePlayer= factory.team1.goalKeeper;
		int speed=ballY-activePlayer.getY();
		moveAll(speed);
	}
		
}
	

private Player getActivePlayer(Player[] activeRod,int ballY) {
	// TODO Auto-generated method stub
	int i;
	int min=Math.abs(ballY-activeRod[0].getY());
	int minat=0;
	for(i=0;i<activeRod.length;i++)
	{
		int temp=Math.abs(ballY-activeRod[i].getY());
		if(temp<min)
		{
			minat=i;
			min=temp;
		}	
	}
	return activeRod[minat];
	
}

private String getActiveRodTeam1(int regionNumber,int prevBallX,int ballX) 
{
	int ballSpeed=ballX-prevBallX;
	if(regionNumber==1)
	{
		if(ballSpeed>0)
			return "GoalKeeper";
		else
			return "Defender";
	}
	else if(regionNumber==2)
	{
		if(ballSpeed>0)
			return "Defender";
		else
			return "MidFielder";
	}
	else if(regionNumber==3)
	{
		if(ballSpeed>0)
			return "MidFielder";
		else
			return "Attacker";
	}
	else //if(regionNumber==4)
	{
		return "Attacker";
	}
}
	

public void movePlayerUP(FoosBallTabel tabel, Player player){
	int speedY_UP;
		if(level.equals("Novice"))
			speedY_UP=-5;
		else 
			speedY_UP=-10;
		int y_UP=player.getY();
		y_UP+=speedY_UP;
		
		if((y_UP>=player.getMinY()) && (y_UP<=player.getMaxY()))
		{	
			//System.out.println("IN MOVE PLAYER UP");
			//speedY=2;
			player.setY(y_UP);	
			
		}
		
		else if(y_UP<player.getMinY())
		{
			player.setY(player.getMinY());
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
		
		int speedY_DOWN;
		if(level.equals("Novice"))
			speedY_DOWN=5;
		else 
			speedY_DOWN=10;
		int y_DOWN=player.getY();
		
		if((y_DOWN>=player.getMinY()) && (y_DOWN<player.getMaxY()))
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
	
	
	public void moveAttackerRod(Attacker[] attackers,int speed,String level)
	{
		if(level.equals("Novice"))
		{
			for(int i=0;i<attackers.length;i++)
			{
				movePlayerComputer(this,attackers[i],speed);
			}
		}
		else if(level.equals("Intermediate"))
		{
			for(int i=0;i<attackers.length;i++){
				movePlayerComputer(this,attackers[i],speed);
			}
		}
		else if(level.equals("Advance"))
		{
			for(int i=0;i<attackers.length;i++){
				movePlayerStrategy(attackers[i],speed);
			}
		}
		
	}
	
	public void moveDefenderRod(Defender[] defenders,int speed,String level){
		if(level.equals("Novice"))
		{
			for(int i=0;i<defenders.length;i++){
				movePlayerComputer(this,defenders[i],speed);
			}
		}
		else if(level.equals("Intermediate"))
		{
			for(int i=0;i<defenders.length;i++){
				movePlayerComputer(this,defenders[i],speed);
			}
		}
		else if(level.equals("Advance"))
		{
			for(int i=0;i<defenders.length;i++){
				movePlayerStrategy(defenders[i],speed);
			}
		}
			
	}
	
	public void moveMidFielderRod(MidFielder[] midFielders,int speed,String level){
		
		if(level.equals("Novice"))
		{
			for(int i=0;i<midFielders.length;i++){
				movePlayerComputer(this,midFielders[i],speed);
			}
		}
		else if(level.equals("Intermediate"))
		{
			for(int i=0;i<midFielders.length;i++){
				movePlayerComputer(this,midFielders[i],speed);
			}
		}
		else if(level.equals("Advance"))
		{
			for(int i=0;i<midFielders.length;i++){
				movePlayerStrategy(midFielders[i],speed);
			}
		}
	}
	
	
	public void moveGoalKeeperRod(GoalKeeper goalKeeper,int speed,String level){
		
		if(level.equals("Novice"))
		{
			
			movePlayerComputer(this,goalKeeper,speed);
			
		}
		else if(level.equals("Intermediate"))
		{
			
				movePlayerComputer(this, goalKeeper,speed);
		}
		else if(level.equals("Advance"))
		{
			
				movePlayerStrategy(goalKeeper,speed);
		
		}
		
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
	
	
	public void readyGameField(int numAttackers,int numDefenders,int numMidFielder){
		this.factory.formTeam1(numAttackers, numDefenders, numMidFielder,"team1");
		this.factory.formTeam2(numAttackers, numDefenders, numMidFielder,"team2");
	}
	
	
	public static void playSound() {
	    new Thread(new Runnable() {

	        @Override
	        public void run() {
	        	Clip soundClip;
	        	try {
	                
	        		File soundFile = new File("oddjazzy.wav");
	                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
	                
	                 soundClip = AudioSystem.getClip();
	                
	                soundClip.open(audioInput);
	                soundClip.start();
	                
	                
	             } catch (UnsupportedAudioFileException e) {
	                e.printStackTrace();
	             } catch (IOException e) {
	                e.printStackTrace();
	             } catch (LineUnavailableException e) {
	                e.printStackTrace();
	             }
	        }
	    }).start();
	}
	
	  class DownAction extends AbstractAction

	    {

	        public void actionPerformed( ActionEvent tf )

	        {
	        	
	        	factory=TeamFactory.getInstance();
				gameUpdate(); 

				repaint();
				
				gameReset(gameType); 

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

	    {	private boolean onKeyRelease;
	    	
	    	//upTimer=new Timer();
	    

	        public void actionPerformed( ActionEvent tf )

	        {
	        
	                
	        	factory=TeamFactory.getInstance();
				gameUpdate(); 

				repaint();
				
				gameReset(gameType); 
	           

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
				
				gameReset(gameType); 

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

	        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

			public void actionPerformed( ActionEvent tf )

	        {
	        	factory=TeamFactory.getInstance();
				gameUpdate(); 

				repaint();
				
				gameReset(gameType); 
	           

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

