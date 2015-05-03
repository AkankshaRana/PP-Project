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
		int maxX=960;
		int mid=maxX/2;
		int maxY=500;
		//this.goalKeeper=new GoalKeeper(90, 190,120,260,teamType);
		this.goalKeeper=(GoalKeeper) factory.getPlayer("GoalKeeper", (mid/5)-10, 240,180,300, teamType);
		this.team1Players[count]=this.goalKeeper;
		count++;
		//int Region = 500/(numAttackers);
		int limitA=maxY/(numAttackers+1);
		for(int i=0;i<numAttackers;i++){
			this.attackers[i]=(Attacker) factory.getPlayer("Attacker",(mid+2*(mid)/5)-10 ,limitA*(i+1),limitA*(i),limitA*(i+2), teamType);//(530,160+j,0+k,340+j,teamType);
			this.team1Players[count]=this.attackers[i];
			count++;
		}
		int limitD=maxY/(numDefenders+1);
		for(int i=0;i<numDefenders;i++){
//			this.defenders[i]=(Defender)factory.getPlayer("Defender",170, 70+j,0+k,160+j,teamType);
			this.defenders[i]=(Defender)factory.getPlayer("Defender",((2*mid)/5)-10, limitD*(i+1),limitD*i,limitD*(i+2),teamType);
			this.team1Players[count]=this.defenders[i];
			count++;
		}
		int limitM=maxY/(numMidFielder+1);
		for(int i=0;i<numMidFielder;i++){
			//this.midFielders[i]=(MidFielder)factory.getPlayer("MidFielder",330, 70+j,0+k,160+j,teamType);
			this.midFielders[i]=(MidFielder)factory.getPlayer("MidFielder",((4*mid)/5)-10, limitM*(i+1),limitM*i,limitM*(i+2),teamType);
			this.team1Players[count]=this.midFielders[i];
			count++;
		}
		
	}
	
	public Player[] getTeam(){
		
		return this.team1Players;
	}
	
}
