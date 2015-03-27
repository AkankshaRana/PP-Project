package BoardClasses;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Formatter;

import javax.swing.JPanel;

import PlayerClass.Player;

public class Ball {
	
	public float x, y; // (x,y): ball's center   
	Player team1[];
	
	
		public int check;
	   public float speedX, speedY; // Ball speed in x and y (per step)
	   float radius;        
	   private Color color; 
	   public int count_team1 = 0,count_team2 =0;
	  
	   public Ball(float x, float y, float radius, float speed, float angleInDegree,
	         Color color) {
	      this.x = x;
	      this.y = y;
  this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree))); // speed in java Cartesian coordinates
	      this.speedY = (float)(-speed * (float)Math.sin(Math.toRadians(angleInDegree)));
	      this.radius = radius;
	      this.color = color;
	   }
	  	  
	   public void draw(Graphics g) {
	      g.setColor(color);
	      g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
	   }
	   
	   
	   public void collideAndMove(FoosBall_Tabel box) {
	      
	      float ballMinX = box.minX + radius;
	      float ballMinY = box.minY + radius;
	      float ballMaxX = box.maxX - radius; // Frame/Panel bounds
	      float ballMaxY = box.maxY - radius;
	      	      
	      x += speedX;
	      y += speedY;
	      
	    //Check for Goal
	      
	      check=0;
	      if(x <=71 && x>=0 && y>=140 && y<=260){
	    	  System.out.println("!!!!!!!! GOAL1 !!!!!!!");
	    	  count_team2++;
	    
	    	  System.out.println("count team2 = " + count_team2);
	    	  check=1;
	    	
	      }
	      
	      //check=0;
	      else if((x<=800 &&x>=733 && y<=260 && y>=140)){
	    	  System.out.println("!!!!!!!! GOAL2 !!!!!!!");
	    	  count_team1++;
	    	  System.out.println("count team 1 = " + count_team1);
	    	  check=1;
	    	 
	      }
	      
	   // if ball tries to move over the bounds, reposition it!
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
	      
	      //Collision with Attackers Red
	      
	      else if(x+radius>box.attacker1.initialX &&x+radius<box.attacker1.initialX+20 && y+radius>box.attacker1.initialY && y+radius<box.attacker1.initialY+20){
	    	  if(x+radius-1<box.attacker1.initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.attacker1.initialX-20;
	    	  y = box.attacker1.initialY-10;
	    	  }
	    	  else{
	    		 // speedX=-speedX;
	    		  speedY=-speedY;
	    		  x = box.attacker1.initialX-20;
		    	  y = box.attacker1.initialY-10;
	    	  }
	      }
	      
	     
	      else if(x+radius>box.attacker2.initialX &&x+radius<box.attacker2.initialX+20 && y+radius>box.attacker2.initialY && y+radius<box.attacker2.initialY+20){
	    	  if(x+radius-1<box.attacker2.initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-2 !!!!!!!!!!!!!!!!!!!");	    	  
	    	  
	    	  x = box.attacker2.initialX-20;
	    	  y = box.attacker2.initialY-10;
	    	  }
	    	  else{
	    		 // speedX=-speedX;
	    		  speedY=-speedY;
		    	  x = box.attacker2.initialX-20;
		    	  y = box.attacker2.initialY-10;
	    	  }
	      }
	      
	      
	      //Collision with Attackers Blue
	      
	      else if(x+radius>box.attacker3.initialX &&x+radius<box.attacker3.initialX+20 && y+radius>box.attacker3.initialY && y+radius<box.attacker3.initialY+20){
	    	  if(x+radius-1>box.attacker4.initialX+20){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.attacker3.initialX+20;
	    	  y = box.attacker3.initialY+10;
	      }
	    	  else{
	    		 // speedX=-speedX;
	    		  speedY = -speedY;
	    		  x = box.attacker3.initialX+20;
		    	  y = box.attacker3.initialY+10;
	    	  }
	      }
	      
	     
	      else if(x+radius>box.attacker4.initialX &&x+radius<box.attacker4.initialX+20 && y+radius>box.attacker4.initialY && y+radius<box.attacker4.initialY+20){
	    	  if(x+radius-1>box.attacker4.initialX+20){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-2 !!!!!!!!!!!!!!!!!!!");	    	  
	    	  
	    	  x = box.attacker4.initialX+20;
	    	  y = box.attacker4.initialY+10;}
	    	  else{
	    		 // speedX=-speedX;
	    		  speedY = -speedY;
	    		  x = box.attacker4.initialX+20;
		    	  y = box.attacker4.initialY+10;
		    	  }
	    	  
	      }
	      
	      
	      //Collision with MidFielders Red
	      
	      else if(x+radius>box.MidFielderRed[0].initialX &&x+radius<box.MidFielderRed[0].initialX+20 && y+radius>box.MidFielderRed[0].initialY && y+radius<box.MidFielderRed[0].initialY+20){
	    	  if(x+radius-1<box.MidFielderRed[0].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.MidFielderRed[0].initialX-20;
	    	  y = box.MidFielderRed[0].initialY-10;
	    	  }
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.MidFielderRed[0].initialX-20;
		    	  y = box.MidFielderRed[0].initialY-10;
	    	  }
	      }
	      
	     
	      else if(x+radius>box.MidFielderRed[1].initialX &&x+radius<box.MidFielderRed[1].initialX+20 && y+radius>box.MidFielderRed[1].initialY && y+radius<box.MidFielderRed[1].initialY+20){
	    	  if(x+radius-1<box.MidFielderRed[1].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.MidFielderRed[1].initialX-20;
	    	  y = box.MidFielderRed[1].initialY-10;}
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.MidFielderRed[1].initialX-20;
		    	  y = box.MidFielderRed[1].initialY-10;
	    	  }
	      }
	      else if(x+radius>box.MidFielderRed[2].initialX &&x+radius<box.MidFielderRed[2].initialX+20 && y+radius>box.MidFielderRed[2].initialY && y+radius<box.MidFielderRed[2].initialY+20){
	    	  if(x+radius-1<box.MidFielderRed[2].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.MidFielderRed[2].initialX-20;
	    	  y = box.MidFielderRed[2].initialY-10;
	    	  }
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.MidFielderRed[2].initialX-20;
		    	  y = box.MidFielderRed[2].initialY-10;
	    	  }
	    	  
	      }
	      else if(x+radius>box.MidFielderRed[3].initialX &&x+radius<box.MidFielderRed[3].initialX+20 && y+radius>box.MidFielderRed[3].initialY && y+radius<box.MidFielderRed[3].initialY+20){
	    	  if(x+radius-1<box.MidFielderRed[3].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.MidFielderRed[3].initialX-20;
	    	  y = box.MidFielderRed[3].initialY-10;
	    	  }
	      else{
    		  speedY = -speedY;
    		  x = box.MidFielderRed[3].initialX-20;
	    	  y = box.MidFielderRed[3].initialY-10;
    	  	}
	      }
	      
	      
	      //Collision with MidFielders Blue
	      
	      else if(x+radius>box.MidFielderBlue[0].initialX &&x+radius<box.MidFielderBlue[0].initialX+20 && y+radius>box.MidFielderBlue[0].initialY && y+radius<box.MidFielderBlue[0].initialY+20){
	    	  if(x+radius-1>box.MidFielderBlue[0].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.MidFielderBlue[0].initialX+20;
	    	  y = box.MidFielderBlue[0].initialY+10;
	    	  }
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.MidFielderBlue[0].initialX+20;
		    	  y = box.MidFielderBlue[0].initialY+10;
	    	  	}
	    	  
	      }
	      
	      else if(x+radius>box.MidFielderBlue[1].initialX &&x+radius<box.MidFielderBlue[1].initialX+20 && y+radius>box.MidFielderBlue[1].initialY && y+radius<box.MidFielderBlue[1].initialY+20){
	    	  if(x+radius-1>box.MidFielderBlue[1].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.MidFielderBlue[1].initialX+20;
	    	  y = box.MidFielderBlue[1].initialY+10;
	    	  }
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.MidFielderBlue[1].initialX+20;
		    	  y = box.MidFielderBlue[1].initialY+10;
	    	  	}
	      }
	      else if(x+radius>box.MidFielderBlue[2].initialX &&x+radius<box.MidFielderBlue[2].initialX+20 && y+radius>box.MidFielderBlue[2].initialY && y+radius<box.MidFielderBlue[2].initialY+20){
	    	  if(x+radius-1>box.MidFielderBlue[2].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.MidFielderBlue[2].initialX+20;
	    	  y = box.MidFielderBlue[2].initialY+10;
	    	  }
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.MidFielderBlue[2].initialX+20;
		    	  y = box.MidFielderBlue[2].initialY+10;
	    	  	}
	      }
	      else if(x+radius>box.MidFielderBlue[3].initialX &&x+radius<box.MidFielderBlue[3].initialX+20 && y+radius>box.MidFielderBlue[3].initialY && y+radius<box.MidFielderBlue[3].initialY+20){
	    	  if(x+radius-1>box.MidFielderBlue[3].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.MidFielderBlue[3].initialX+20;
	    	  y = box.MidFielderBlue[3].initialY+10;
	    	  }
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.MidFielderBlue[3].initialX+20;
		    	  y = box.MidFielderBlue[3].initialY+10;
	    	  	}
	      }
	      
	      
	      
	      //Collision with Defenders Red
	      
	      else if(x+radius>box.defendersRed[0].initialX &&x+radius<box.defendersRed[0].initialX+20 && y+radius>box.defendersRed[0].initialY && y+radius<box.defendersRed[0].initialY+20){
	    	  if(x+radius-1<box.defendersRed[0].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.defendersRed[0].initialX-20;
	    	  y = box.defendersRed[0].initialY-10;
	    	  }
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.defendersRed[0].initialX-20;
		    	  y = box.defendersRed[0].initialY-10;
	    	  }
	      }
	      
	     
	      else if(x+radius>box.defendersRed[1].initialX &&x+radius<box.defendersRed[1].initialX+20 && y+radius>box.defendersRed[1].initialY && y+radius<box.defendersRed[1].initialY+20){
	    	  if(x+radius-1<box.defendersRed[1].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	 // System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.defendersRed[1].initialX-20;
	    	  y = box.defendersRed[1].initialY-10;
	    	  }
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.defendersRed[1].initialX-20;
		    	  y = box.defendersRed[1].initialY-10;
	    	  }
	      }
	      else if(x+radius>box.defendersRed[2].initialX &&x+radius<box.defendersRed[2].initialX+20 && y+radius>box.defendersRed[2].initialY && y+radius<box.defendersRed[2].initialY+20){
	    	  if(x+radius-1<box.defendersRed[2].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.defendersRed[2].initialX-20;
	    	  y = box.defendersRed[2].initialY-10;
	    	  }
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.defendersRed[2].initialX-20;
		    	  y = box.defendersRed[2].initialY-10;
	    	  }
	      }
	      else if(x+radius>box.defendersRed[3].initialX &&x+radius<box.defendersRed[3].initialX+20 && y+radius>box.defendersRed[3].initialY && y+radius<box.defendersRed[3].initialY+20){
	    	  if(x+radius-1<box.defendersRed[3].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.defendersRed[3].initialX-20;
	    	  y = box.defendersRed[3].initialY-10;
	    	  }
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.defendersRed[3].initialX-20;
		    	  y = box.defendersRed[3].initialY-10;
	    	  }
	      }
	      
//Collision with Defenders Blue
	      
	      else if(x+radius>box.defendersBlue[0].initialX &&x+radius<box.defendersBlue[0].initialX+20 && y+radius>box.defendersBlue[0].initialY && y+radius<box.defendersBlue[0].initialY+20){
	    	  if(x+radius-1>box.defendersBlue[0].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.defendersBlue[0].initialX+20;
	    	  y = box.defendersBlue[0].initialY+10;
	    	  }
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.defendersBlue[0].initialX+20;
		    	  y = box.defendersBlue[0].initialY+10;
	    	  }
	    	  
	      }
	      
	     
	      else if(x+radius>box.defendersBlue[1].initialX &&x+radius<box.defendersBlue[1].initialX+20 && y+radius>box.defendersBlue[1].initialY && y+radius<box.defendersBlue[1].initialY+20){
	    	  if(x+radius-1>box.defendersBlue[1].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	 // System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.defendersBlue[1].initialX+20;
	    	  y = box.defendersBlue[1].initialY+10;
	    	  }
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.defendersBlue[1].initialX+20;
		    	  y = box.defendersBlue[1].initialY+10;
	    	  }
	    	  
	      }
	      else if(x+radius>box.defendersBlue[2].initialX &&x+radius<box.defendersBlue[2].initialX+20 && y+radius>box.defendersBlue[2].initialY && y+radius<box.defendersBlue[2].initialY+20){
	    	  if(x+radius-1>box.defendersBlue[2].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.defendersBlue[2].initialX+20;
	    	  y = box.defendersBlue[2].initialY+10;
	    	  }
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.defendersBlue[2].initialX+20;
		    	  y = box.defendersBlue[2].initialY+10;
	    	  }
	    	  
	      }
	      else if(x+radius>box.defendersBlue[3].initialX &&x+radius<box.defendersBlue[3].initialX+20 && y+radius>box.defendersBlue[3].initialY && y+radius<box.defendersBlue[3].initialY+20){
	    	  if(x+radius-1>box.defendersBlue[3].initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.defendersBlue[3].initialX+20;
	    	  y = box.defendersBlue[3].initialY+10;
	    	  }
	    	  else{
	    		  speedY = -speedY;
	    		  x = box.defendersBlue[3].initialX+20;
		    	  y = box.defendersBlue[3].initialY+10;
	    	  }
	      }
	      
	      
	      //Goal keeper red collide
	      
	      else if(x+radius>box.gk2.initialX &&x+radius<box.gk2.initialX+20 && y+radius>box.gk2.initialY && y+radius<box.gk2.initialY+20){
	    	  if(x+radius-1>box.gk2.initialX){
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.gk2.initialX-20;
	    	  y = box.gk2.initialY-10;
	    	  }
	    	  else{
	    		  speedY=-speedY;
	    		  x = box.gk2.initialX-20;
		    	  y = box.gk2.initialY-10;
	    	  }
	      }
	      
	      //Goal keeper Blue collide
	      
	      else if(x+radius>box.gk1.initialX &&x+radius<box.gk1.initialX+20 && y+radius>box.gk1.initialY && y+radius<box.gk1.initialY+20){
	    	  if(x+radius-1<box.gk1.initialX){ 
	    	  speedX=-speedX;
	    	  speedY=-speedY;
	    	  System.out.print(" !!!!!!!!!!!!!!! Collide-1 !!!!!!!!!!!!!!!!!!1");
	    	  
	    	  x = box.gk1.initialX+20;
	    	  y = box.gk1.initialY+10;
	    	  }
	    	  else{
	    		  speedY=-speedY;
	    		  x = box.gk1.initialX+20;
		    	  y = box.gk1.initialY+10;
	    	  }
	      }
	      
	      
	      
	      
	   }
	   
	   
	 	   
	   public int getX(){
		   return (int)this.x; //return x coordinate of the ball
	   }
	   
	   public int getY(){
		   return (int)this.y; //return y coordinate of the ball 
	   }
}
