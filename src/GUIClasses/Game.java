package GUIClasses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import BackendClasses.Music;
import BackendClasses.Status;


public class Game extends JFrame{

	Team1DisplayPanel team1Panel;
	Team2DisplayPanel team2Panel;
	TimerPanel timerPanel;
	FoosBallTabel tabel;
	String gameModeSelected, gameTypeSelected, gameLevelSelected;
	int teamTypeSelected,teamFormationSelected;
	int numAttackers, numDefenders, numMidFielders;
	Music music;
	
	private JOptionPane startMsg;
	Status status;
	
	public Game(){
		
		super("FoosBall");
		//this.setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		this.gameModeSelected="Single";
		this.gameTypeSelected="Goal";
		this.gameLevelSelected="Novice";
		
		this.teamTypeSelected=1;
		this.music=Music.getInstance();
		
		
		this.numAttackers=3;
		this.numMidFielders=4;
		this.numDefenders=3;
		
		
		this.team1Panel=new Team1DisplayPanel();
		this.team2Panel=new Team2DisplayPanel();
		this.tabel=new FoosBallTabel();
		
		this.team1Panel=new Team1DisplayPanel();
		this.team2Panel=new Team2DisplayPanel();
		
		this.timerPanel=new TimerPanel();
		
		this.add(team1Panel,BorderLayout.WEST);
		this.add(this.timerPanel);
		this.add(team2Panel,BorderLayout.EAST);
		this.add(tabel,BorderLayout.SOUTH);
		
		team1Panel.setLayout(new FlowLayout());
		this.team1Panel.setBackground(Color.red);
		this.team1Panel.setMaximumSize(this.team1Panel.getSize());
		
		team2Panel.setLayout(new FlowLayout());
		this.team2Panel.setBackground(Color.blue);
		this.team2Panel.setMaximumSize(this.team2Panel.getSize());
		
		this.timerPanel.setLayout(new FlowLayout());
		this.timerPanel.setBackground(Color.BLACK);
		this.timerPanel.setMaximumSize(this.timerPanel.getSize());
		
		//tabel.setStatus(team1Panel, team2Panel, this.timerPanel);
		//tabel.readyGameField(2, 4, 4);
		//tabel.gameStart();
		//tabel.paintComponent(g);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(960, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		//setVisible(true);
		
		
	}
	
	public void startGame(){
		
		this.status=Status.getInstance(team1Panel, team2Panel, timerPanel);
		tabel.setStatus(team1Panel, team2Panel, timerPanel);
		this.setVisible(true);
		askForMode();
		askForPlayerName("Player Information");
		askForGameType();
		System.out.println("game type:"+this.gameTypeSelected);
		
		
			chooseTeamType();
		
			//chooseTeamTypeMultiplayer();
			if(this.teamTypeSelected==2){
			customizeTeamFormations();
			}
		
		
		
		chooseLevel();
		LevelFactory levelFactory;
		if(gameLevelSelected.equals("Novice"))
			levelFactory=new NoviceLevelFactory();
		else if(gameLevelSelected.equals("Intermediate"))
			levelFactory=new IntermediateLevelFactory();
		else
			levelFactory=new AdvanceLevelFactory();
		System.out.println("game level:"+this.gameLevelSelected);
		//this.music.startMusic();
		//this.setVisible(true);
		tabel.setStatus(team1Panel, team2Panel, this.timerPanel);
		tabel.readyGameField(this.numAttackers, this.numDefenders, this.numMidFielders);
		tabel.gameStart(levelFactory,gameModeSelected,gameTypeSelected);
	}
	
	public void askForMode(){
		
		
		JLabel question=new JLabel("How do you wish to play the game?");
		final JRadioButton single=new JRadioButton("Single Player Game");
		single.setSelected(true);
		final JRadioButton multiplayer=new JRadioButton("2-Player Game");
		JLabel question2=new JLabel("If the game mode is final, shall we proceed?");
		
		//int answerCheck=0;
		single.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				multiplayer.setSelected(false);
				single.setSelected(true);
				gameModeSelected="Single";
				
			}
			
		});
		
		multiplayer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				single.setSelected(false);
				multiplayer.setSelected(true);
				gameModeSelected="Multiplayer";
			}
			
		});
		
		JPanel confirmPanel = new JPanel(new GridLayout(0, 1));
		confirmPanel.add(question);
		confirmPanel.add(single);
		confirmPanel.add(multiplayer);
		confirmPanel.add(question2);
		
		
		//////////////////////////
		//playSound();
		int answer = JOptionPane.showConfirmDialog(null, confirmPanel, "Game Mode?",
	            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (answer == JOptionPane.OK_OPTION) {
			//this.setVisible(true);
			
			//askForPlayerName("Player Information");
			
			
        } 
		
		else if(answer==JOptionPane.NO_OPTION){
			System.exit(0);
		}
		
		else if(answer==JOptionPane.CLOSED_OPTION){
			System.exit(0);
		}
		
		
	}
	
	public void askForPlayerName(String info){
		
		JLabel player1=new JLabel("Player1 name:");
		JLabel player2=new JLabel("Player2 name:");
		JLabel player=new JLabel("Player name:");
		
		
		JTextField player1Text=new JTextField("");
		JTextField player2Text=new JTextField("");
		JTextField playerText=new JTextField("");
		
		JLabel proceed=new JLabel("Do you wish to proceed?");
		
		JPanel confirmPanel = new JPanel(new GridLayout(0, 1));
		
		if(this.gameModeSelected.endsWith("Multiplayer")){
			confirmPanel.add(player1);
			confirmPanel.add(player1Text);
			confirmPanel.add(player2);
			confirmPanel.add(player2Text);
		}
		
		else{
			
			confirmPanel.add(player);
			confirmPanel.add(playerText);
						
		}
		
		
		System.out.println(this.gameModeSelected);
		confirmPanel.add(proceed);
		startMsg = new JOptionPane(confirmPanel, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{"Proceed","Quit"}, null);
		
		final JDialog dialog = startMsg.createDialog(null, info);
		
		dialog.setModal(true);
		dialog.setContentPane(startMsg);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.pack();
		dialog.setVisible(true);
		
		try{
		
		if(startMsg.getValue().toString().equalsIgnoreCase("Proceed")){
    		//this.setVisible(true);
		
			if(this.gameModeSelected.equals("Multiplayer")){
				
				if(player1Text.getText().length()>8 || (player2Text.getText().length()>10)){
					askForPlayerName("Name too big (>8)! Enter again...");
				}
				
				if(player1Text.getText().length()<9 &&  player1Text.getText().equalsIgnoreCase("")==false && player2Text.getText().length()<11 &&  player2Text.getText().equalsIgnoreCase("")==false){
					this.status.setTeam1PlayerName(player1Text.getText());
					this.status.setTeam2PlayerName(player2Text.getText());
					this.setVisible(true);
					}
					if(player2Text.getText().equalsIgnoreCase("")){
						this.status.setTeam2PlayerName("Player2");
						
					}
					if(player1Text.getText().equalsIgnoreCase("")){
						this.status.setTeam1PlayerName("Player1");
					}
					
				//this.setVisible(true);
			}
			
			else{
				this.status.setTeam1PlayerName("Computer");
				
				if(playerText.getText().length()>8){
					askForPlayerName("Name too big (>8)! Enter again...");
				}
				
				if(playerText.getText().length()<9 &&  playerText.getText().equalsIgnoreCase("")==false){
				this.status.setTeam2PlayerName(playerText.getText());
				//this.setVisible(true);
				}
				else if(playerText.getText().equalsIgnoreCase("")){
					this.status.setTeam2PlayerName("Player");
					//this.setVisible(true);
				}
				
			}
			
    	}
		
		else if(startMsg.getValue().toString().equalsIgnoreCase("Quit")){
			System.exit(1);
		}
		
		}
		catch(Exception e){
			System.exit(1);
		}
	

	}
	
	
	public void askForGameType(){
		JLabel question=new JLabel("Which type of game challenge would you like to play?");
		final JRadioButton goalsChallenge=new JRadioButton("5 goals challenge");
		goalsChallenge.setSelected(true);
		final JRadioButton timerChallenge=new JRadioButton("Timer challenge");
		JLabel question2=new JLabel("If the type of game challenge is final, shall we proceed?");
		
		
		goalsChallenge.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				timerChallenge.setSelected(false);
				goalsChallenge.setSelected(true);
				gameTypeSelected="Goal";
				
			}
			
		});
		
		timerChallenge.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				goalsChallenge.setSelected(false);
				timerChallenge.setSelected(true);
				gameTypeSelected="Timer";
			}
			
		});
		
		JPanel confirmPanel = new JPanel(new GridLayout(0, 1));
		confirmPanel.add(question);
		confirmPanel.add(goalsChallenge);
		confirmPanel.add(timerChallenge);
		confirmPanel.add(question2);
		
		
		//////////////////////////
		//playSound();
		int answer = JOptionPane.showConfirmDialog(null, confirmPanel, "Game Challenge Type?",
	            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (answer == JOptionPane.OK_OPTION) {
			//this.setVisible(true);
			
			
			
			
        } 
		
		else if(answer==JOptionPane.NO_OPTION){
			System.exit(0);
		}
		
		else if(answer==JOptionPane.CLOSED_OPTION){
			System.exit(0);
		}
		
	}

	
	public void chooseLevel(){
		
		JLabel question=new JLabel("Choose the level you want to play the game at...");
		final JRadioButton beginner=new JRadioButton("Beginner");
		beginner.setSelected(true);
		final JRadioButton intermediate=new JRadioButton("Intermediate");
		final JRadioButton expert=new JRadioButton("Expert");
		JLabel question2=new JLabel("If you are done choosing the game level, shall we start the game?");
		
		
		beginner.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				intermediate.setSelected(false);
				
				expert.setSelected(false);
				beginner.setSelected(true);
				gameLevelSelected="Novice";
				status.setLevel("Novice");
				
			}
			
		});
		
		intermediate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				beginner.setSelected(false);
				expert.setSelected(false);
				intermediate.setSelected(true);
				gameLevelSelected="Intermediate";
				status.setLevel("Intermediate");
			}
			
		});
		
		expert.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				beginner.setSelected(false);
				intermediate.setSelected(false);
				expert.setSelected(true);
				gameLevelSelected="Advance";
				status.setLevel("Advance");
			}
			
		});
		
		JPanel confirmPanel = new JPanel(new GridLayout(0, 1));
		confirmPanel.add(question);
		confirmPanel.add(beginner);
		confirmPanel.add(intermediate);
		confirmPanel.add(expert);
		confirmPanel.add(question2);
		
		
		//////////////////////////
		//playSound();
		int answer = JOptionPane.showConfirmDialog(null, confirmPanel, "Game Level?",
	            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (answer == JOptionPane.OK_OPTION) {
			//this.setVisible(true);
			
			
			
			
        } 
		
		else if(answer==JOptionPane.NO_OPTION){
			System.exit(0);
		}
		
		else if(answer==JOptionPane.CLOSED_OPTION){
			System.exit(0);
		}
		
	}
	
	public void chooseTeamType(){
		
		JPanel confirmPanel = new JPanel(new GridLayout(0, 1));
		
		JLabel question=new JLabel("Would you like to chose your own team formation? Click YES to customize the team formation and NO to go with the default one.");
		
		confirmPanel.add(question);
		
		//////////////////////////
		//playSound();
		int answer = JOptionPane.showConfirmDialog(null, confirmPanel, "Team Formation",
	            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (answer == JOptionPane.YES_OPTION) {
			teamTypeSelected=2;
        } 
		else if(answer == JOptionPane.NO_OPTION){
			teamTypeSelected=1;
        }
		
		else if(answer == JOptionPane.CLOSED_OPTION){
			System.exit(0);
		}
	}
	
	
	
	public void customizeTeamFormations(){
		JLabel question=new JLabel("Please select your preferred team formation.");
		
		
		
		
		final JRadioButton formation1=new JRadioButton("3 Attackers, 3 MidFielders, 4 Defenders");
		formation1.setSelected(true);
		final JRadioButton formation2=new JRadioButton("3 Attackers, 4 MidFielders, 3 Defenders");
		final JRadioButton formation3=new JRadioButton("4 Attackers, 3 MidFielders, 3 Defenders");
		JLabel question2=new JLabel("Shall we proceed further?");
		
		
		formation1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				formation2.setSelected(false);
				
				formation3.setSelected(false);
				formation1.setSelected(true);
				teamFormationSelected=1;
				
				numAttackers=3;
				numMidFielders=3;
				numDefenders=4;
				
			}
			
		});
		
		formation2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				formation1.setSelected(false);
				formation3.setSelected(false);
				formation2.setSelected(true);
				teamFormationSelected=2;
				
				numAttackers=3;
				numMidFielders=4;
				numDefenders=3;
			}
			
		});
		
		formation3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				formation1.setSelected(false);
				formation2.setSelected(false);
				formation3.setSelected(true);
				teamFormationSelected=3;
				
				numAttackers=4;
				numMidFielders=3;
				numDefenders=3;
			}
			
		});
		
		
	
		confirmPanel.add(formation3);
		confirmPanel.add(question2);
		
		
		
		//////////////////////////
		//playSound();
		int answer = JOptionPane.showConfirmDialog(null, confirmPanel, "Team Formation",
	            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (answer == JOptionPane.OK_OPTION) {
			//this.setVisible(true);
			
			
			
			
        } 
		
		else if(answer==JOptionPane.NO_OPTION){
			System.exit(0);
		}
		
		else if(answer==JOptionPane.CLOSED_OPTION){
			System.exit(0);
		}
	}
	
}
