package BackendClasses;


	import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GUIClasses.Ball;
import GUIClasses.FoosBallTabel;
import GUIClasses.Team1DisplayPanel;
import GUIClasses.Team2DisplayPanel;
import GUIClasses.TimerPanel;
import TeamFormations.TeamFactory;
	
	
	public class GameTimer 
	{
	    private Timer timer ;
	    Ball ball;
	    private FoosBallTabel tabel;
	    Team1DisplayPanel panel1;
	    Team2DisplayPanel panel2;
	    Music music;
	   // TimerPanel timerPanel;
	    int interval=61;
	    Status status;
	    Database database;
	    public GameTimer(FoosBallTabel tabel,Ball ball,Team1DisplayPanel panel1,Team2DisplayPanel panel2)
	    {
	    	this.tabel=tabel;
	    	this.ball=ball;
	    	this.panel1=panel1;
	    	this.panel2=panel2;
	    	this.music=Music.getInstance();
	     timer= new Timer();
	     //this.database=Database.getInstance();
	}
	    
	    public void setStatus(Status status){ ///CALL
	    	this.status=status;
	    }
	     public void startTimer()
	    {
	        TimerTask task = this.new Task();
	        timer.schedule(task,0,1*1000);     
	    }
	     public void stopTimer()
	     {
	         timer.cancel();
	     }
	     class Task extends TimerTask
	{
	     
	         @Override       
	        public void run() 
	         {
	            if (interval==1)
	            {   timer.cancel();
	               // JOptionPane.showMessageDialog(null, "Time over");	    
	            
	            //  add more to this shreya-like database , window close etc.
				//JOptionPane.showMessageDialog(null, "Game over.Team 1 won");	
	            if(ball.team1GoalCount>ball.team2GoalCount)
	            {	music.stopMusic();
	            panel1.setResult1("Winner");
				panel2.setResult2("Loser");
	            	if(panel1.nameDisplay.getText().equalsIgnoreCase("Computer")==false){
	            	JPanel confirmPanel = new JPanel();
	            	JLabel msg=new JLabel("Time Over!!!"+panel1.nameDisplay.getText()+" Won!!");
	            	JLabel question = new JLabel("Would you like to save this score in our database?");
	            	confirmPanel.add(msg);
	            	confirmPanel.add(question);
	            	playSound();
	            	int answer = JOptionPane.showConfirmDialog(null,confirmPanel, "Game Over",
			            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
	            	if (answer == JOptionPane.OK_OPTION) {
	            		//database.updateDatabase("Highest", Integer.valueOf(panel1.scoreDisplay.getText()));
						Database.updateDatabase(panel1.nameDisplay.getText(), Integer.valueOf(panel1.scoreDisplay.getText()));
						System.out.println(panel1.nameDisplay.getText());
						System.exit(0);
						
						
						
			        } 
	            	else if(answer==JOptionPane.NO_OPTION){
	            		System.exit(0);
	            	}
												
					else if(answer==JOptionPane.CLOSED_OPTION){
						System.exit(0);
					}
	            	}
	            	
	            	else{
	            	/*	JOptionPane pane=new JOptionPane("You lose. Timer over, Game over!",JOptionPane.INFORMATION_MESSAGE,JOptionPane.DEFAULT_OPTION,null,new Object[]{"OK"},null);
						final JDialog dialog =pane.createDialog(null,"Game Over");
						dialog.setModal(true);
						dialog.setContentPane(pane);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.pack();
						dialog.setVisible(true);
						playSound();
						
						if(pane.getValue().toString().equalsIgnoreCase("OK")){
							System.exit(0);
						}*/
	            		
	            		JOptionPane.showMessageDialog(null, "You lose. Timer over, Game over!");
						
						System.exit(0);
					}
	            	}
	            
	            if(ball.team1GoalCount<ball.team2GoalCount)
	            {	music.stopMusic();
	            panel2.setResult2("Winner");
				panel1.setResult1("Loser");
	            	JPanel confirmPanel = new JPanel();
	            	JLabel msg=new JLabel("Time Over!!!"+panel2.nameDisplay.getText()+" Won!!");
	            	JLabel question = new JLabel("Would you like to save this score in our database?");
	            	confirmPanel.add(msg);
	            	confirmPanel.add(question);
	            	playSound();
	            	int answer = JOptionPane.showConfirmDialog(null,confirmPanel, "Game Over",
			            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
	            	if (answer == JOptionPane.OK_OPTION) {
						//Database.updateDatabase("Highest", Integer.valueOf(panel2.scoreDisplay.getText()));
						Database.updateDatabase(panel2.nameDisplay.getText(), Integer.valueOf(panel2.scoreDisplay.getText()));
						System.out.println(panel2.nameDisplay.getText());
						System.exit(0);
						
						
						
						
						
			        } 
	            	else if(answer==JOptionPane.NO_OPTION){
	            		System.exit(0);
	            	}
												
					else if(answer==JOptionPane.CLOSED_OPTION){
						System.exit(0);
					}
	            }
	            else
	            {
	            	
	            	//interval=61;
	            	//startTimer();
	            	JOptionPane.showMessageDialog(null, "Game draw! Timer over, Game over!");
					
					System.exit(0);
	            }
				
				
	            
	            
	            }
	            interval--;
	            TimerPanel.timerDisplay.setText(String.valueOf(interval));
            	
	            
			}
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
	     
	     
	     
}

