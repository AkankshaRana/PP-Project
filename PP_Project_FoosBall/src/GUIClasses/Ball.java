package GUIClasses;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import TeamFormations.Player;
import TeamFormations.TeamFactory;

import BackendClasses.Status;

public class Ball {

	private float x, y;
	int check;
	private float speedX, speedY; 
	private float radius;        
	private Color color; 
	public int team1GoalCount = 0,team2GoalCount =0;
	private Status status;
	private int angleInDegree;
	private static Ball ballInstance=new Ball(393,210,7,5,Color.WHITE);
	   
	 public Ball(float x, float y, float radius, float speed, Color color) {
	      this.x = x;
	      this.y = y;
	      
	      Random rand = new Random();
	      this.angleInDegree = rand.nextInt(360);
	      
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
	   
	   public int getX(){
		   return (int)this.x; 
	   }
	   
	   public void setX(float x){
		   this.x=x;
	   }
	   
	   
	   public int getY(){
		   return (int)this.y; 
	   }
	   
	   public void setY(float y){
		   this.y=y;
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
		      if(x <=71 && x>=0 && y>=140 && y<=260){
		    	  //System.out.println("!!!!!!!! GOAL1 !!!!!!!");
		    	  this.team2GoalCount++;
		    
		    	  //System.out.println("count team2 = " + team2GoalCount);
		    	  check=1;
		    	
		      }
		      
		      //check=0;
		      else if((x<=800 &&x>=733 && y<=260 && y>=140)){
		    	 // System.out.println("!!!!!!!! GOAL2 !!!!!!!");
		    	  this.team1GoalCount++;
		    	 // System.out.println("count team 1 = " + team1GoalCount);
		    	  check=1;
		    	 
		      }
	   }
	   
	   public void checkPlayerCollision(Player player){
		   
		   if(player.getTeamType().equalsIgnoreCase("Red")){
		   if(x+radius>player.getX() && x+radius<player.getX()+20 && y+radius>player.getY() && y+radius<player.getY()+20){
		    	  if(x+radius-1<player.getX()){
		    	  speedX=-speedX;
		    	  speedY=-speedY;
		    	 // System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
		    	  
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
	
	   else{
		   if(x+radius>player.getX() && x+radius<player.getX()+20 && y+radius>player.getY() && y+radius<player.getY()+20){
		    	  if(x+radius-1<player.getX()){
		    	  speedX=-speedX;
		    	  speedY=-speedY;
		    	 // System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
		    	  
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
}
	   	
}
