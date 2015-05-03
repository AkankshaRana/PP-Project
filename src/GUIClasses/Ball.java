package GUIClasses;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import TeamFormations.Player;
import TeamFormations.TeamFactory;
import BackendClasses.Status;

public class Ball {

	private float x, y;
	 int check;
	static int tableMaxY = 500;
	static int tableMaxX = 960;
	private float prevX,prevY;
	private float speedX, speedY,speed; 
	private float radius;        
	private Color color; 
	public int team1GoalCount = 0,team2GoalCount =0;
	private Status status;
	private int angleInDegree;
	private static Ball ballInstance=new Ball((tableMaxX/2),(tableMaxY/2),7,5,Color.WHITE);
	   
	 public Ball(float x, float y, float radius, float speed, Color color) {
	      this.x = x;
	      this.y = y;
	      this.prevX=0;
	      this.prevY=0;
	      Random rand = new Random();
	      this.angleInDegree = rand.nextInt(360);
	      this.speed=speed;
	      this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree))); 
	      this.speedY = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
	      this.radius = radius;
	      this.color = color;
	   }
	  	  
	 	
	 public static Ball getBallInstance(){
		 return ballInstance;
	 }
	 
	   public void draw(Graphics g) {
	      g.setColor(color);
	      g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
	   }
	   
	   public void changeAngle(){
			 Random rand = new Random();
		      this.angleInDegree = rand.nextInt(360);
		      
		     while((this.angleInDegree<105&& this.angleInDegree>70) ||(this.angleInDegree<290 && this.angleInDegree>250) ){
		    	  this.angleInDegree=rand.nextInt();
		      }
		      
		      this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree))); 
		      this.speedY = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
		 }
	   
	  
	   
	   public int getX(){
		   return (int)this.x; 
	   }
	   
	   public void setX(float x){
		   float prev=getX();
		   setPrevX(prev);
		   this.x=x;
	   }
	   public void setPrevX(float prevX)
	   {
		   this.prevX=prevX;
	   }
	   public float getPrevX()
	   {
		   return this.prevX;
	   }
	   public float getPrevY()
	   {
		   return this.prevY;
	   }
	   public int getY(){
		   return (int)this.y; 
	   }
	   
	   public void setY(float y){
		   float prev=getY();
		   setPrevY(prev);
		   this.y=y;
	   }
	   public void setPrevY(float prevY)
	   {
		   this.prevY=prevY;
	   }
	   
	   public float getRadius(){
		   return this.radius;
	   }
	   
	   public void setRadius(float radius){
		   this.radius=radius;
	   }
	   
	   public float getSpeedX(){
		   return this.speedX;
	   }
	   
	   public void setSpeedX(float xSpeed){
		   this.speedX=xSpeed;
	   }
	   
	   public float getSpeedY(){
		   return this.speedY;
	   }
	   
	   public void setSpeedY(float ySpeed){
		   this.speedY=ySpeed;
	   }
	   
	   public Status getStatus(){
		   return this.status;
	   }
	   
	   public void setStatus(Status status){
		   this.status=status;
	   }
	   
	   public void checkCollisionAndMove(TeamFactory teams, FoosBallTabel tabel){
		   
		   float ballMinX = tabel.minX + radius;
		      float ballMinY = tabel.minY + radius;
		      float ballMaxX = tabel.maxX - radius; 
		      float ballMaxY = tabel.maxY - radius;
		      	      
		      x += speedX;
		      y += speedY;
		      
		      checkIfGoal(tabel);
		      
		      System.out.println("check if goal ");
		      
		      if (x < ballMinX) {
			         speedX = -speedX; // Reflect along normal
			         x = ballMinX;     // Repositioning the ball
			      } else if (x > ballMaxX) {
			         speedX = -speedX;
			         x = ballMaxX;
			      }
			      
			      if (y < ballMinY) {
			         speedY = -speedY;
			         y = ballMinY;
			      } else if (y > ballMaxY) {
			         speedY = -speedY;
			         y = ballMaxY;
			      }
			      
			      for(int i=0;i<teams.team1.getTeam().length;i++){
			    	  checkPlayerCollision(teams.team1.getTeam()[i]);
			      }
			      
			      for(int i=0;i<teams.team2.getTeam().length;i++){
			    	  checkPlayerCollision(teams.team2.getTeam()[i]);
			      }
		   
	   }
	   
	   public void checkIfGoal(FoosBallTabel tabel){
		   	  check=0;
		      if(((x-radius<=50)&&(x>=0))&&((y+radius>=180)&&( y-radius<=300))){
		    	  //System.out.println("!!!!!!!! GOAL1 !!!!!!!");
		    	  playGoalSound();
		    	  this.team2GoalCount++;
		    	  this.status.setTeam2Score();
		    	  //System.out.println("count team2 = " + team2GoalCount);
		    	  check=1;
		    	 
		    	  changeAngle();
		      }
		      
		      //check=0;
		      else if(((x-radius<=960)&&(x+radius>=910))&&((y+radius>=180) && (y-radius<=300))){
		    	 // System.out.println("!!!!!!!! GOAL2 !!!!!!!");
		    	  playGoalSound();
		    	  this.team1GoalCount++;
		    	  this.status.setTeam1Score();
		    	 // System.out.println("count team 1 = " + team1GoalCount);
		    	  check=1;
		    	
		    	  changeAngle();
		      }
	   }
	   
	   public void checkPlayerCollision(Player player){
		   
		   
		   //team red
		   
		   if(player.getTeamType().equalsIgnoreCase("Team1")){
		   if(x+radius>player.getX() && x+radius<player.getX()+20 && y+radius>player.getY() && y+radius<player.getY()+20){
		    	  if(x+radius-1>player.getX()){
		    	  speedX=-speedX;
		    	  speedY=-speedY;
		    	 System.out.println(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
		    	  
		    	  x = player.getX()+20;
		    	  y = player.getY()+10;
		    	  }
		    	  else{
		    		 
		    		  speedY=-speedY;
		    		  x = player.getX()+20;
			    	  y = player.getY()+10;
		    	  }
		      }
	   
	   }
		   //team blue
	
	   else if(player.getTeamType().equalsIgnoreCase("Team2")){
		   if(x+radius>player.getX() && x+radius<player.getX()+40 && y+radius>player.getY() && y+radius<player.getY()+40){
		    	  if(x+radius-1>player.getX()){
		    	  speedX=-speedX;
		    	
		    	  System.out.println(" !!!!!!!!!!!!!!! Collide- team !!!!!!!!!!!!!!!!!!1");
		    	  
		    	  x = player.getX()-20;
		    	  y = player.getY()-10;
		    	  }
		    	  else{
		    		
		    		  speedY=-speedY;
		    		  x = player.getX()-20;
			    	  y = player.getY()-10;
		    	  }
		      
		      }
	   }
}
	   
	   public static void playGoalSound() {
		    new Thread(new Runnable() {

		        @Override
		        public void run() {
		        	Clip soundClip;
		        	try {
		                
		        		File soundFile = new File("crowdapplaud.wav");
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
	   	
}
