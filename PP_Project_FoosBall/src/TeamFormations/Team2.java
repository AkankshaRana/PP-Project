package TeamFormations;

public class Team2 {

	private PlayerFactory factory;
	Attacker[] attackers;
	Defender[] defenders;
	MidFielder[] midFielders;
	GoalKeeper goalKeeper;
	Player[] team2Players;
	
	public Team2(){
		this.team2Players=new Player[11];
		factory=PlayerFactory.getIinstance();
	}
	
	public void formTeam(int numAttackers, int numDefenders, int numMidFielder, String teamType){
		int count=0;
		this.attackers=new Attacker[numAttackers];
		this.defenders=new Defender[numDefenders];
		this.midFielders=new MidFielder[numMidFielder];
		
		this.goalKeeper=(GoalKeeper)factory.getPlayer("GoalKeeper",692, 190,120,260,teamType);
		this.team2Players[count]=this.goalKeeper;
		count++;
		
		for(int i=0,j=0,k=0;i<numAttackers;i++,j+=90,k+=110){
			this.attackers[i]=(Attacker)factory.getPlayer("Attacker",250,160+j,0+k,340+j,teamType);
			this.team2Players[count]=this.attackers[i];
			count++;
		}
		
		for(int i=0,j=0,k=0;i<numDefenders;i++,j+=90,k+=110){
			this.defenders[i]=(Defender)factory.getPlayer("Defender",612, 70+j,0+k,160+90,teamType);
			this.team2Players[count]=this.defenders[i];
			count++;
		}
		
		for(int i=0,j=0,k=0;i<numMidFielder;i++,j+=90,k+=110){
			this.midFielders[i]=(MidFielder)factory.getPlayer("MidFielder",450, 70+j,0+k,160+j,teamType);
			this.team2Players[count]=this.midFielders[i];
			count++;
		}
		
	}
	
	public Player[] getTeam(){
		
		return this.team2Players;
	}
	
}
