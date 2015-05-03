package GUIClasses;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Team2DisplayPanel extends JPanel{

	public  static JButton timerDisplay;
	public JButton scoreDisplay,smiley, nameDisplay;
	public JLabel score,level,name,result;

	public Team2DisplayPanel(){
		
		score=new JLabel("Goals: ");
		scoreDisplay=(new JButton("0"));
		scoreDisplay.setHorizontalTextPosition(SwingConstants.RIGHT);
		scoreDisplay.setOpaque(true);
		scoreDisplay.setBackground(Color.BLACK);
		scoreDisplay.setForeground(Color.RED);
		scoreDisplay.setFocusable(false);
		
		
       
        name=new JLabel("Player Name: ");
        nameDisplay=(new JButton(""));
        nameDisplay.setHorizontalTextPosition(SwingConstants.RIGHT);
        nameDisplay.setOpaque(true);
        nameDisplay.setBackground(Color.BLACK);
        nameDisplay.setForeground(Color.ORANGE);
        nameDisplay.setFocusable(false);

               
        result=new JLabel("Result: ");
        smiley=(new JButton(new ImageIcon("smiley.png")));
        smiley.setHorizontalAlignment(SwingConstants.CENTER);
        smiley.setOpaque(true);
		smiley.setBackground(Color.BLACK);
		smiley.setDisabledIcon(new ImageIcon("smiley.png"));
		smiley.setEnabled(false);

		formPanel();

	}
	
	public void formPanel(){
		
        this.add(name);
        this.add(nameDisplay);
        
        this.add(result);
        this.add(smiley);
        
        this.add(score);
        this.add(scoreDisplay);

	}
	
public void incrementScore2(){
		String score=this.scoreDisplay.getText();
		int goals=Integer.parseInt(score);
		score=String.valueOf(goals+1);
		this.scoreDisplay.setText(score);
	}
	
	//public int getScore1(){
		
//	}
	
	
	
	public void setPlayerName2(String name){
		this.nameDisplay.setText(name);
	}
	
	
	
	
	
	public void setResult2(String result){
		//ImageIcon icon
		if(result.equalsIgnoreCase("Winner")){
			this.smiley.setIcon(new ImageIcon("smiley.png"));
			
		}
		else{
			this.smiley.setIcon(new ImageIcon("sad.png"));
		}
	}
	
}
