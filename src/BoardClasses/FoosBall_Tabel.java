package BoardClasses;


import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import java.awt.Graphics;
import java.util.Random;


import javax.swing.JPanel;

import LevelClasses.ExpertLevel;
import LevelClasses.IntermediateLevel;
import LevelClasses.NoviceLevel;
import PlayerClass.Attacker;
import PlayerClass.Defender;
import PlayerClass.Goalkeeper;
import PlayerClass.MidFielder;
import PlayerClass.Player;

public class FoosBall_Tabel extends JPanel {

	//private Ellipse2D.Double ball_draw;
	public Team1_Panel team1Panel;
	public Team2_Panel team2Panel;
	NoviceLevel novice;
	IntermediateLevel inter;
	ExpertLevel expert;
	public int Midflag=0,Defflag=0,Attackflag=0,Gkflag=0;
	private static final int UPDATE_RATE = 30;
	int dx=46,dy=45;


	//Thread t;
	public int minX=0,minY=0,maxX=800,maxY=450;
	private Ball ball;  
	public Attacker attackersRed[]=new Attacker[2];
	public Attacker attackersBlue[]=new Attacker[2];
	public Attacker attacker1=new Attacker(250,160,0,340,Color.red);
	public Attacker attacker2=new Attacker(250,250,110,430,Color.red);
	public Attacker attacker3=new Attacker(530,160,0,340,Color.blue);
	public Attacker attacker4=new Attacker(530,250,110,430,Color.blue);

	public Goalkeeper gk1,gk2;
	public Defender defendersRed[]=new Defender[4];

	public Defender defendersBlue[]=new Defender[4];
	public MidFielder MidFielderRed[]=new MidFielder[4];
	public MidFielder MidFielderBlue[]=new MidFielder[4];
	//private ContainerBox box; 

	private int canvasWidth=820;
	private int canvasHeight=600;
	Thread gameThread;

	public FoosBall_Tabel(Team1_Panel panel,Team2_Panel panel2) {
		// Specify 200 pixels wide and 300 high.
		setPreferredSize(new Dimension(820, 450));
		this.team1Panel=panel;
		this.team2Panel=panel2;
		setBackground(Color.black);
		
		 novice=new NoviceLevel();
		 inter= new IntermediateLevel();
		 expert= new ExpertLevel();
		
		attackersRed[0]=attacker1;
		attackersRed[1]=attacker2;

		attackersBlue[0]=attacker3;
		attackersBlue[1]=attacker4;

		defendersRed[0]=new Defender(612, 70,0,160,Color.red);
		defendersRed[1]=new Defender(612, 160,110,250,Color.red);
		defendersRed[2]=new Defender(612, 250,220,340,Color.red);
		defendersRed[3]=new Defender(612, 340,330,430,Color.red);

		defendersBlue[0]=new Defender(170, 70,0,160,Color.blue);
		defendersBlue[1]=new Defender(170, 160,110,250,Color.blue);
		defendersBlue[2]=new Defender(170, 250,220,340,Color.blue);
		defendersBlue[3]=new Defender(170, 340,330,430,Color.blue);

		MidFielderRed[0]=new MidFielder(450, 70,0,160,Color.red);
		MidFielderRed[1]=new MidFielder(450, 160,110,250,Color.red);
		MidFielderRed[2]=new MidFielder(450, 250,220,340,Color.red);
		MidFielderRed[3]=new MidFielder(450, 340,330,430,Color.red);

		MidFielderBlue[0]=new MidFielder(330, 70,0,160,Color.blue);
		MidFielderBlue[1]=new MidFielder(330, 160,110,250,Color.blue);
		MidFielderBlue[2]=new MidFielder(330, 250,220,340,Color.blue);
		MidFielderBlue[3]=new MidFielder(330, 340,330,430,Color.blue);


		gk1=new Goalkeeper(90, 190,120,260,Color.blue);	
		gk2=new Goalkeeper(692, 190,120,260,Color.red);



		Random rand = new Random();
		int angleInDegree = rand.nextInt(360);
		int radius = 7;
		int x = 393;
		int y = 210;
		
		
		int speed= 5;
		

		ball = new Ball(x, y, radius, speed, angleInDegree, Color.white);
		
/*if(team1Panel.level==novice.getLevel()){
			
			
			//System.out.println(speed);
		}
		
		else if(team1Panel.level==inter.getLevel()){
			ball.speedX*=inter.getBallSpeed();
			ball.speedY*=inter.getBallSpeed();
		}
		else if(team1Panel.level==expert.getLevel()){
			ball.speedX*=expert.getBallSpeed();
			ball.speedY*=expert.getBallSpeed();
		}*/
		


		// box.set(0, 0, canvasWidth, canvasHeight);
		gameStart();
		//   t=new Thread(this);
		//add(new Player());
		// paint();
		//  t.start();
	}

	/** Start the ball bouncing. */
	public void gameStart() {
		// Run the game logic in its own thread.
		gameThread = new Thread() {
			public void run() {
				//System.out.println("in run");
				while (true) {

					String str1=""+ball.count_team1;
					String str2=""+ball.count_team2;
					team1Panel.scoreField.setText(str1);
					team2Panel.scoreField.setText(str2);
					if(ball.count_team1==5 || ball.count_team2==5){
						if(ball.count_team1==5){
							System.out.println("Winner is Team 1");
							team1Panel.textField3.setText("Winner");
							team2Panel.textField3.setText("Loser");
						}
						else
						{
							System.out.println("Winner is Team 2");
							team1Panel.textField3.setText("Loser");
							team2Panel.textField3.setText("Winner");
						}
						break;
					}
					// Execute one time-step for the game 
					//System.out.println("in while");
					//gameReset();
					gameUpdate();

					//  repaint();
					//movePlayer();

					// System.out.println("after gameupdate");
					// Refresh the display
					repaint();
					gameReset();
					// CollideGKwithbox(gk2);
					//CollideMidFealderwithbox(MidFielderRed);
					//CollideAttackerwithbox(attackersRed);
					movePlayerwithbox(attackersRed);
					moveDefenderwithbox(defendersRed);
					moveMidfealderwithbox(MidFielderRed);
					moveGKwithbox(gk2);

					//   for(int i=0;i<2;i++){


					//  movePlayerwithbox(attacker1); //}
					//   movePlayerwithbox(attacker2);

					//repaint();

					// Delay and give other thread a chance
					try {
						Thread.sleep(1000 / UPDATE_RATE);
					} catch (InterruptedException ex) {}
				}
			}
		};
		gameThread.start();  // Invoke GaemThread.run()
	}

	public void gameUpdate() {
		ball.collideAndMove(this);
	}

	public void gameReset(){
		if(ball.check==1){
			if((ball.count_team1>0 &&ball.count_team1<5) ||( ball.count_team2>0 && ball.count_team2<5)){
				ball.x=393;
				ball.y=210;

			}
		}
	}

	public void paintComponent(Graphics g){

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		// draw a rectangle
		//angleInDegree = rand.nextInt(360);


		/* Rectangle2D rect = new Rectangle2D.Double(0, 140, 70, 120);
    g2.setColor(Color.blue);
    g2.fill(rect);*/



		g.setColor(Color.GREEN);
		g.drawLine(100, 0, 100, 450); 

		g.setColor(Color.GREEN);
		g.drawLine(180, 0, 180, 450);

		g.setColor(Color.GREEN);
		g.drawLine(260, 0, 260, 450);

		g.setColor(Color.GREEN);
		g.drawLine(340, 0, 340, 450);

		g.setColor(Color.cyan);
		g.drawLine(400, 0, 400, 450);

		g.setColor(Color.GREEN);
		g.drawLine(460, 0, 460, 450);

		g.setColor(Color.GREEN);
		g.drawLine(540, 0, 540, 450);

		g.setColor(Color.GREEN);
		g.drawLine(620, 0, 620, 450);

		g.setColor(Color.GREEN);
		g.drawLine(700, 0, 700, 450);

		g.setColor(Color.BLUE);
		g.drawRect(0, 140, 70, 120); 

		g.setColor(Color.red);
		g.drawRect(733, 140, 70, 120);

		//g.setColor(Color.BLUE); /// GK blue
		//g.fillRect(90, 190, 20, 20); 

		//Rectangle2D rect = new Rectangle2D.Double(90, 190, 20, 20);//////////////////
		//g2.setColor(Color.blue);
		//g2.fill(rect);

		gk1=new Goalkeeper(90, gk1.initialY,120,260,Color.blue);
		gk1.draw(g);

		//g.setColor(Color.red);  // GK red
		//g.fillRect(692, 190, 20, 20); 

		gk2=new Goalkeeper(692, gk2.initialY,120,260,Color.red);
		gk2.draw(g);

		//g.setColor(Color.blue);  // defenders blue
		//	g.fillRect(170, 70, 20, 20); 
		defendersBlue[0]=new Defender(170, defendersBlue[0].initialY,0,160,Color.blue);
		defendersBlue[0].draw(g);

		//g.setColor(Color.blue);  // defenders blue
		//g.fillRect(170, 160, 20, 20); 
		defendersBlue[1]=new Defender(170, defendersBlue[1].initialY,110,250,Color.blue);
		defendersBlue[1].draw(g);

		//g.setColor(Color.blue);  // defenders blue
		//g.fillRect(170, 250, 20, 20); 
		defendersBlue[2]=new Defender(170, defendersBlue[2].initialY,220,340,Color.blue);
		defendersBlue[2].draw(g);

		//g.setColor(Color.blue);  // defenders blue
		//g.fillRect(170, 350, 20, 20); 
		defendersBlue[3]=new Defender(170, defendersBlue[3].initialY,330,430,Color.blue);
		defendersBlue[3].draw(g);


		//g.setColor(Color.red);  // defenders red
		//g.fillRect(612, 70, 20, 20); 
		defendersRed[0]=new Defender(612, defendersRed[0].initialY,0,160,Color.red);
		defendersRed[0].draw(g);

		//g.setColor(Color.red);  // defenders red
		//g.fillRect(612, 160, 20, 20); 
		defendersRed[1]=new Defender(612, defendersRed[1].initialY,110,250,Color.red);
		defendersRed[1].draw(g);

		//g.setColor(Color.red);  // defenders red
		//g.fillRect(612, 250, 20, 20); 
		defendersRed[2]=new Defender(612, defendersRed[2].initialY,220,340,Color.red);
		defendersRed[2].draw(g);

		//g.setColor(Color.red);  // defenders red
		//g.fillRect(612, 350, 20, 20); 
		defendersRed[3]=new Defender(612, defendersRed[3].initialY,330,430,Color.red);
		defendersRed[3].draw(g);



		//g.setColor(Color.red);  // attacker red
		//g.fillRect(250, 160, 20, 20); 

		//attacker1=new Attacker(250,160,Color.red);
		attacker1=new Attacker(250,attacker1.initialY,0,340,Color.red);
		//Rectangle2D attack=attacker.getPlayer();
		attacker1.draw(g);

		//g.setColor(Color.red);  // attacker red
		//g.fillRect(250, 250, 20, 20); 
		attacker2=new Attacker(250,attacker2.initialY,110,430 ,Color.red);
		attacker2.draw(g);

		attackersRed[0]=attacker1;
		attackersRed[1]=attacker2;

		//g.setColor(Color.blue);  // attacker blue
		//g.fillRect(530, 160, 20, 20); 
		attacker3=new Attacker(530, 160,0,340,Color.blue);
		attacker3.draw(g);

		//	g.setColor(Color.blue);  // attacker blue
		//g.fillRect(530, 250, 20, 20); 
		attacker4=new Attacker(530, 250,110,430,Color.blue);
		attacker4.draw(g);


		//	g.setColor(Color.blue);  // midfielders blue
		//g.fillRect(330, 70, 20, 20); 
		MidFielderBlue[0]=new MidFielder(330, MidFielderBlue[0].initialY,0,160,Color.blue);
		MidFielderBlue[0].draw(g);

		//g.setColor(Color.blue);  // midfielders blue
		//g.fillRect(330, 160, 20, 20); 
		MidFielderBlue[1]=new MidFielder(330, MidFielderBlue[1].initialY,110,250,Color.blue);
		MidFielderBlue[1].draw(g);

		//	g.setColor(Color.blue);  // midfielders blue
		//g.fillRect(330, 250, 20, 20); 
		MidFielderBlue[2]=new MidFielder(330, MidFielderBlue[2].initialY,220,340,Color.blue);
		MidFielderBlue[2].draw(g);

		//g.setColor(Color.blue);  // midfielders blue
		//g.fillRect(330, 350, 20, 20); 
		MidFielderBlue[3]=new MidFielder(330, MidFielderBlue[3].initialY,330,430,Color.blue);
		MidFielderBlue[3].draw(g);


		//g.setColor(Color.red);  // midfielders red
		//g.fillRect(450, 70, 20, 20); 
		MidFielderRed[0]=new MidFielder(450, MidFielderRed[0].initialY,0,160,Color.red);
		MidFielderRed[0].draw(g);

		//	g.setColor(Color.red);  // midfielders red
		//g.fillRect(450, 160, 20, 20); 
		MidFielderRed[1]=new MidFielder(450, MidFielderRed[1].initialY,110,250,Color.red);
		MidFielderRed[1].draw(g);

		//g.setColor(Color.red);  // midfielders red
		//g.fillRect(450, 250, 20, 20); 
		MidFielderRed[2]=new MidFielder(450, MidFielderRed[2].initialY,220,340,Color.red);
		MidFielderRed[2].draw(g);

		//	g.setColor(Color.red);  // midfielders red
		//g.fillRect(450, 350, 20, 20); 
		MidFielderRed[3]=new MidFielder(450, MidFielderRed[3].initialY,330,430,Color.red);
		MidFielderRed[3].draw(g);


		//g.setColor(Color.white);  // ball
		//g.fillOval(393, 210, 15, 15); 
		//ball = new Ball(ball.x, ball.y, 7, 5, angleInDegree);
		ball.draw(g);



		//t.start();

	}
	//int yini=attacker1.getX();
	//int y=attacker1.getY();
	//int speedY=7;

	public void movePlayer(FoosBall_Tabel box,Attacker player){
		int x=player.getX();
		int y=player.getY();
		int speedY=0;//=5;
		
		if(team1Panel.level==novice.getLevel()){
			speedY=novice.getPlayerSpeed();
		}
		
		else if(team1Panel.level==inter.getLevel()){
			speedY=inter.getPlayerSpeed();
		}
		else if(team1Panel.level==expert.getLevel()){
			speedY=expert.getPlayerSpeed();
		}

		if(Attackflag==0&&y>box.minY && y<player.maxy){	
			y+=speedY;
			player.initialY=y;
			//attacker1=new Attacker(250,y,Color.red);


			//System.out.println("x = "+x);
			//System.out.println("y = " +y);
			//repaint();
		}
		else if(y<=player.maxy){
			speedY = -speedY;
			y +=speedY;
			player.initialY = y;
			Attackflag=1;
			if(y==player.minY){
				Attackflag=0;
			}

		}
	}
	public void moveDefender(FoosBall_Tabel box,Defender player){
		int x=player.getX();
		int y=player.getY();
		int speedY=0;//5;
		
		if(team1Panel.level==novice.getLevel()){
			speedY=novice.getPlayerSpeed();
		}
		
		else if(team1Panel.level==inter.getLevel()){
			speedY=inter.getPlayerSpeed();
		}
		else if(team1Panel.level==expert.getLevel()){
			speedY=expert.getPlayerSpeed();
		}
		
		if(player.initialY>player.maxy){
			y=player.maxy;
		}
		else if(player.initialY<box.minY){
			y=box.minY;
		}

		if(Defflag==0&&y>box.minY && y<player.maxy){	
			y+=speedY;
			player.initialY=y;
			
			
		}
		
		else if(y<=player.maxy){
			speedY = -speedY;
			y +=speedY;
			
			player.initialY = y;
			Defflag=1;
			if(y==player.minY){
				System.out.println("************* minY: "+player.minY + "**************");
				Defflag=0;
			}

		}
	}

	public void moveMidFielder(FoosBall_Tabel box,MidFielder player){
		int x=player.getX();
		int y=player.getY();
		int speedY=0;//5;
		
		if(team1Panel.level==novice.getLevel()){
			speedY=novice.getPlayerSpeed();
		}
		
		else if(team1Panel.level==inter.getLevel()){
			speedY=inter.getPlayerSpeed();
		}
		else if(team1Panel.level==expert.getLevel()){
			speedY=expert.getPlayerSpeed();
		}
		
		if(player.initialY>player.maxY){
			y=player.maxY;
		}
		else if(player.initialY<box.minY){
			y=box.minY;
		}

		if(Midflag==0&&y>box.minY && y<player.maxY){	
			y+=speedY;
			player.initialY=y;
			
		}
		else if(y<=player.maxY){
			speedY = -speedY;
			y +=speedY;
			player.initialY = y;
			Midflag=1;
			if(y==player.minY){
				System.out.println("************* minY: "+player.minY + "**************");
				Midflag=0;
			}

		}

	}

	public void moveGK(FoosBall_Tabel box,Goalkeeper player){
		int x=player.getX();
		int y=player.getY();
		int speedY=0;//5;
		
		if(team1Panel.level==novice.getLevel()){
			speedY=novice.getPlayerSpeed();
		}
		
		else if(team1Panel.level==inter.getLevel()){
			speedY=inter.getPlayerSpeed();
		}
		else if(team1Panel.level==expert.getLevel()){
			speedY=expert.getPlayerSpeed();
		}
		
		if(player.initialY>player.maxy){
			y=player.maxy;
		}
		else if(player.initialY<box.minY){
			y=box.minY;
		}

		if(Gkflag==0&&y>box.minY && y<player.maxy){	
			y+=speedY;
			player.initialY=y;
			
		}
		else if(y<=player.maxy){
			speedY = -speedY;
			y +=speedY;
			player.initialY = y;
			Gkflag=1;
			if(y==player.minY){
				System.out.println("************* minY: "+player.minY + "**************");
				Gkflag=0;
			}

		}


	}


	public void movePlayerwithbox(Attacker[] player){
		for(int i=0;i<player.length;i++){
			movePlayer(this,player[i]);}
	}

	public void moveDefenderwithbox(Defender[] player){
		for(int i=0;i<player.length;i++){
			moveDefender(this,player[i]);}
	}

	public void moveMidfealderwithbox(MidFielder[] player){
		for(int i=0;i<player.length;i++){
			moveMidFielder(this,player[i]);}
	}

	public void moveGKwithbox(Goalkeeper player){

		moveGK(this,player);
	}
}
