package TeamFormations;

public class Team2 {

	private PlayerFactory factory;
	public Attacker[] attackers;
	public Defender[] defenders;
	public MidFielder[] midFielders;
	public GoalKeeper goalKeeper;
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
		int maxX=960;
		int mid=maxX/2;
		int maxY=500;
		
		this.goalKeeper=(GoalKeeper)factory.getPlayer("GoalKeeper",mid+((4*mid)/5)-10, 240,180,300,teamType);
		this.team2Players[count]=this.goalKeeper;
		count++;
		
		int limitA=500/(numAttackers+1);
		
		for(int i=0;i<numAttackers;i++){
			this.attackers[i]=(Attacker)factory.getPlayer("Attacker",((3*mid)/5)-10,limitA*(i+1),limitA*(i),limitA*(i+2), teamType);
			this.team2Players[count]=this.attackers[i];
			count++;
		}
		int limitD=500/(numDefenders+1);
		for(int i=0;i<numDefenders;i++){
			this.defenders[i]=(Defender)factory.getPlayer("Defender",mid+((3*mid)/5)-10,limitD*(i+1),limitD*i,limitD*(i+2),teamType);
			this.team2Players[count]=this.defenders[i];
			count++;
		}
		int limitM=500/(numMidFielder+1);
		for(int i=0;i<numMidFielder;i++){
			this.midFielders[i]=(MidFielder)factory.getPlayer("MidFielder",(mid+(mid)/5)-10, limitM*(i+1),limitM*i,limitM*(i+2),teamType);
			this.team2Players[count]=this.midFielders[i];
			count++;
		}
		
	}
	
	public Player[] getTeam(){
		
		return this.team2Players;
	}
	
}
