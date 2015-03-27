package BoardClasses;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.*;

import javax.swing.*;

public class Team1_Panel extends JPanel implements ActionListener{
    
	// private JTextArea Team;
	 //private JTextArea Score;
	 
	public JRadioButton Radio1;
	public JRadioButton Radio2,Radio3,Radio4,Radio5;
	public JTextField scoreField,teamName,textField3;
	public JLabel label1,label2,label3,label4;
	public JLabel label5,label6,label7,label8;
	public Team2_Panel panel2;
	
   public Team1_Panel(Team2_Panel panel2) {
	   setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
	  this.panel2=panel2; 
	  
	   Radio1 = new JRadioButton("Novice");
       Radio2 = new JRadioButton("Intermediate");
       Radio3 = new JRadioButton("Expert");
       Radio4 = new JRadioButton("PLAYER");
       Radio5 = new JRadioButton("COMPUTER");
       
       scoreField=new JTextField();
       teamName=new JTextField();
       textField3=new JTextField();
       
       label1=new JLabel("Score:");
       label2=new JLabel("Team Name:");
       label3=new JLabel("Result:");
       label4=new JLabel("Level: ");
       
       label5=new JLabel("                                                                    ");
       label6=new JLabel(" INVISIBLE");
       label7=new JLabel("INVISIBLE");
       label8=new JLabel("INVISIBLE");
      // label5.setVisible(false);
       label6.setVisible(false);
       label7.setVisible(false);
       label8.setVisible(false);
       
      
       
       Radio1.addActionListener(this);
      Radio2.addActionListener(this);
      Radio3.addActionListener(this);
      // textField.setBounds(100, 100, 100, 100);
       scoreField.setPreferredSize(new Dimension(50,30));
       teamName.setPreferredSize(new Dimension(80,30));
       teamName.setText("Team Blue");
       textField3.setPreferredSize(new Dimension(60,30));
       
        
      
       
      // add(Radio1);
       //add(Radio2);
       add(label1);
     add(scoreField);
     add(label2);
     add(teamName);
     add(label3);
     add(textField3);
     add(label4);
    add(Radio1);
     add(Radio2);
     add(Radio3);
     add(label5);
    // add(label6);
     add(Radio4);
     add(Radio5);
      Radio4.setSelected(true);
      Radio5.setSelected(false);
    // scoreField.addActionListener(this);
	   
       
	  /* Team = new JTextArea("Team 1");
       Score = new JTextArea("Score");*/
       // Specify 200 pixels wide and 300 high.
       setPreferredSize(new Dimension(400, 50));
       setBackground(Color.blue);
       
    // Create the buttons and add text to them.
       
        
       // Set the panel itself to listen to the buttons
       //Team.addActionListener(this);
       //Radio2.addActionListener(this);
   }
public String level;
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource() instanceof JRadioButton){
        JRadioButton radioButton = (JRadioButton) e.getSource();
        if(radioButton.isSelected()){
            level=radioButton.getText();
        }
        
        if(radioButton.equals(Radio1)){
        	Radio2.setSelected(false);
        	Radio3.setSelected(false);
        	
        	this.panel2.Radio1.setSelected(true);
        	this.panel2.Radio2.setSelected(false);
        	this.panel2.Radio3.setSelected(false);
        }
        
        else if(radioButton.equals(Radio2)){
        	Radio1.setSelected(false);
        	Radio3.setSelected(false);
        	
        	this.panel2.Radio1.setSelected(false);
        	this.panel2.Radio2.setSelected(true);
        	this.panel2.Radio3.setSelected(false);
        }
        else{
        	Radio1.setSelected(false);
        	Radio2.setSelected(false);
        	
        	this.panel2.Radio1.setSelected(false);
        	this.panel2.Radio2.setSelected(false);
        	this.panel2.Radio3.setSelected(true);
        }
    }
	
}

//@Override
/*public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
	 // We can use the event "source" to figure
    // out which button was pressed.
	JRadioButton source = (JRadioButton)e.getSource();
     
    if(source == Radio1) {
        System.out.println("Hello");
    }
    else if(source == Radio2) {
        System.out.println("Goodbye");
    }
	
}*/

}