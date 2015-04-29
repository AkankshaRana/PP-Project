package TeamFormations;

public class TeamFactory {

	public Team1 team1;
	public Team2 team2;
	private static TeamFactory factoryInstance=new TeamFactory();
	
	private TeamFactory(){
		team1=new Team1();
		team2=new Team2();
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
