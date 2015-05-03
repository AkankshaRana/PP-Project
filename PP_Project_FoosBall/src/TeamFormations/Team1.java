package TeamFormations;

public class Team1 {

	private PlayerFactory factory;
	public Attacker[] attackers;
	public Defender[] defenders;
	public MidFielder[] midFielders;
	public GoalKeeper goalKeeper;
	Player[] team1Players;
	
	public Team1(){
		this.team1Players=new Player[11];
		factory=PlayerFactory.getIinstance();
	}
	
	public void formTeam(int numAttackers, int numDefenders, int numMidFielder, String teamType){
		this.attackers=new Attacker[numAttackers];
		this.defenders=new Defender[numDefenders];
		this.midFielders=new MidFielder[numMidFielder];
		int count=0;
		
		//this.goalKeeper=new GoalKeeper(90, 190,120,260,teamType);
		this.goalKeeper=(GoalKeeper) factory.getPlayer("GoalKeeper", 90, 190,120,260, teamType);
		this.team1Players[count]=this.goalKeeper;
		count++;
		
		for(int i=0,j=0,k=0;i<numAttackers;i++,j+=90,k+=110){
			this.attackers[i]=(Attacker) factory.getPlayer("Attacker", 530,160+j,0+k,340+j, teamType);//(530,160+j,0+k,340+j,teamType);
			this.team1Players[count]=this.attackers[i];
			count++;
		}
		
		for(int i=0,j=0,k=0;i<numDefenders;i++,j+=90,k+=110){
			this.defenders[i]=(Defender)factory.getPlayer("Defender",170, 70+j,0+k,160+j,teamType);
			this.team1Players[count]=this.defenders[i];
			count++;
		}
		
		for(int i=0,j=0,k=0;i<numMidFielder;i++,j+=90,k+=110){
			this.midFielders[i]=(MidFielder)factory.getPlayer("MidFielder",330, 70+j,0+k,160+j,teamType);
			this.team1Players[count]=this.midFielders[i];
			count++;
		}
		
	}
	
	public Player[] getTeam(){
		
		return this.team1Players;
	}
	
}
