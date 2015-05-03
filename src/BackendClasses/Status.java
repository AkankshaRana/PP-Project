package BackendClasses;

import GUIClasses.Team1DisplayPanel;
import GUIClasses.Team2DisplayPanel;
import GUIClasses.TimerPanel;

public class Status {

	 Team1DisplayPanel team1Panel;
	 Team2DisplayPanel team2Panel;
	private TimerPanel timerPanel;
	private int team1Goals, team2Goals;
	
	private String level,player1Name, player2Name;
	private static Status status;
	
	private Status(Team1DisplayPanel team1Panel, Team2DisplayPanel team2Panel, TimerPanel timerPanel){
		this.team1Panel=team1Panel;
		this.team2Panel=team2Panel;
		this.timerPanel=timerPanel;
		this.team1Goals=0;
		this.team2Goals=0;
	}
	
	public static Status getInstance(Team1DisplayPanel team1Panel, Team2DisplayPanel team2Panel,TimerPanel timerPanel){
		status=new Status(team1Panel, team2Panel,timerPanel);
		return status;
	}

	public void setTeam1Score(){
		this.team1Goals++;
		this.team1Panel.incrementScore1();
	}
	
	public int getTeam1Score(){
		return this.team1Goals;
	}
	
	public void setTeam2Score(){
		this.team2Goals++;
		this.team2Panel.incrementScore2();
	}
	
	public int getTeam2Score(){
		return this.team2Goals;
	}

	
	public void setResult(){
		if(this.team1Goals>this.team2Goals){
			this.team1Panel.setResult1("Winner");
			this.team2Panel.setResult2("Loser");
		}
		else{
			this.team1Panel.setResult1("Loser");
			this.team2Panel.setResult2("Winner");
		}
		
	}
	
	public void setLevel(String level){
		this.level=level;
	this.timerPanel.setLevel(level);
	}
	
	public String getLevel(){
		return this.level;
	}
	
	public void setTeam1PlayerName(String name){
		this.player1Name=name;
		this.team1Panel.setPlayerName1(name);
	}
	
	public String getTeam1PlayerName(){
		return this.player1Name;
	}
	
	public void setTeam2PlayerName(String name){
		this.player2Name=name;
		this.team2Panel.setPlayerName2(name);
	}
	
	public String getTeam2PlayerName(){
		return this.player2Name;
	}
	
	public void setTimer(){
		
	}

	public int getTimer(){
		return 0;
	}
}
