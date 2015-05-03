package GUIClasses;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TimerPanel extends JPanel{

	private JLabel timer;
	public static JButton timerDisplay;
	private JLabel level;
	private JButton levelDisplay;

	public TimerPanel(){
		timer=new JLabel("Timer: ");
		timer.setForeground(Color.YELLOW);
        timerDisplay=(new JButton("5 Goals"));
        timerDisplay.setHorizontalAlignment(SwingConstants.LEFT);
        timerDisplay.setOpaque(true);
        timerDisplay.setBackground(Color.BLACK);
        timerDisplay.setForeground(Color.YELLOW);
        timerDisplay.setFocusable(false);
        
        level=new JLabel("Level: ");
        level.setForeground(Color.CYAN);
        levelDisplay=(new JButton("BEGINNER"));
        levelDisplay.setHorizontalAlignment(SwingConstants.LEFT);
        levelDisplay.setOpaque(true);
        levelDisplay.setBackground(Color.BLACK);
        levelDisplay.setForeground(Color.CYAN);
        levelDisplay.setFocusable(false);
        
        
        formPanel();
	}
	
	public void formPanel(){
		this.add(level);
        this.add(levelDisplay);
       
        this.add(timer);
        this.add(timerDisplay);
      

	}
	
	public void setLevel(String level){
		this.levelDisplay.setText(level);
	}
	
	public static void setTimer(int time){
		
	}
	
}
