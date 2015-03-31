package TeamFormations;

public class TeamFactory {

	Team1 team1;
	Team2 team2;
	private static TeamFactory factoryInstance=new TeamFactory();
	
	private TeamFactory(){
		
	}
	
	public static TeamFactory getInstance(){
		return factoryInstance;
	}
	
	public void formTeam1(int numAttackers, int numDefenders, int numMidFielder, String teamType){
		this.team1.formTeam(numAttackers, numDefenders, numMidFielder, teamType);
	}
	
	public void formTeam2(int numAttackers, int numDefenders, int numMidFielder, String teamType){
		this.team2.formTeam(numAttackers, numDefenders, numMidFielder, teamType);
	}
	
	public Player[] getTeam1(){
		return this.team1.getTeam();
	}
	
	public Player[] getTeam2(){
		return this.team2.getTeam();
	}
	
}
