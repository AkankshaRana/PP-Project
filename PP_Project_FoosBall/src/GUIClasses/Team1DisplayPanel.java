package GUIClasses;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Team1DisplayPanel extends JPanel{

	public  static JButton timerDisplay;
	public JButton scoreDisplay,smiley,levelDisplay,playerDisplay, nameDisplay;
	public JLabel score,timer,level,name,playerType,result;
	
	public Team1DisplayPanel(){
		
		score=new JLabel("Goals: ");
		scoreDisplay=(new JButton("0"));
		scoreDisplay.setHorizontalTextPosition(SwingConstants.RIGHT);
		scoreDisplay.setOpaque(true);
		scoreDisplay.setBackground(Color.BLACK);
		scoreDisplay.setForeground(Color.RED);
		scoreDisplay.setFocusable(false);
		
		timer=new JLabel("Timer: ");
        timerDisplay=(new JButton("Starting..."));
        timerDisplay.setHorizontalAlignment(SwingConstants.LEFT);
        timerDisplay.setOpaque(true);
        timerDisplay.setBackground(Color.BLACK);
        timerDisplay.setForeground(Color.YELLOW);
        timerDisplay.setFocusable(false);

        level=new JLabel("Level: ");
        levelDisplay=(new JButton("Beginner"));
        levelDisplay.setHorizontalAlignment(SwingConstants.LEFT);
        levelDisplay.setOpaque(true);
        levelDisplay.setBackground(Color.BLACK);
        levelDisplay.setForeground(Color.CYAN);
        levelDisplay.setFocusable(false);

        name=new JLabel("Player Name: ");
        nameDisplay=(new JButton());
        nameDisplay.setHorizontalTextPosition(SwingConstants.RIGHT);
        nameDisplay.setOpaque(true);
        nameDisplay.setBackground(Color.BLACK);
        nameDisplay.setForeground(Color.ORANGE);
        nameDisplay.setFocusable(false);

        playerType=new JLabel("Player type: ");
        playerDisplay=(new JButton());
        playerDisplay.setHorizontalTextPosition(SwingConstants.RIGHT);
        playerDisplay.setOpaque(true);
        playerDisplay.setBackground(Color.BLACK);
        playerDisplay.setForeground(Color.BLUE);
        playerDisplay.setFocusable(false);
        
        result=new JLabel("Result: ");
        smiley=(new JButton(new ImageIcon("")));
        smiley.setHorizontalAlignment(SwingConstants.CENTER);
        smiley.setOpaque(true);
		smiley.setBackground(Color.BLACK);
		smiley.setDisabledIcon(new ImageIcon(""));
		smiley.setEnabled(false);

		formPanel();

	}
	
	public void formPanel(){
		this.add(level);
        this.add(levelDisplay);
        //this.add(name);
        //this.add(nameDisplay);
        //this.add(timer);
        //this.add(timerDisplay);
        this.add(playerType);
        this.add(playerDisplay);
        this.add(result);
        this.add(smiley);
        
        this.add(score);
        this.add(scoreDisplay);

	}
	
	public void setScore1(){
		
	}
	
	//public int getScore1(){
		
//	}
	
	public void setTimer(int time){
		
	}
	
	public void setPlayerName1(String name){
		
	}
	
	public void setUser1(String type){
		
	}
	
	public void setLevel1(String level){
		
	}
	
	public void setIcon1(ImageIcon icon){
		
	}
	
}
