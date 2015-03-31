package GUIClasses;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Game extends JFrame{

	Team1DisplayPanel team1Panel;
	Team2DisplayPanel team2Panel;
	FoosBallTabel tabel;
	
	public Game(){
		
		super("FoosBall");
		//this.setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		this.team1Panel=new Team1DisplayPanel();
		this.team2Panel=new Team2DisplayPanel();
		this.tabel=new FoosBallTabel();
		
		this.team1Panel=new Team1DisplayPanel();
		this.team2Panel=new Team2DisplayPanel();
		this.add(team1Panel,BorderLayout.WEST);
		this.add(team2Panel,BorderLayout.EAST);
		this.add(tabel,BorderLayout.SOUTH);
		team1Panel.setLayout(new FlowLayout());
		team2Panel.setLayout(new FlowLayout());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(820, 600);
		setVisible(true);
		
	}
	
}
