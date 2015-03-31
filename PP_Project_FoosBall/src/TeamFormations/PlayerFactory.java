package TeamFormations;

public class PlayerFactory {

	private static PlayerFactory factoryInstance= new PlayerFactory();
	private Player player;
	
	private PlayerFactory(){
		
	}
	
	public static PlayerFactory getIinstance(){
		return factoryInstance;
	}
	
	public Player getPlayer(String playerType, int initialX, int initialY,int minY,int maxY, String teamType){
		
		if(playerType.equalsIgnoreCase("Attacker")==true){
			this.player = new Attacker(initialX, initialY, minY, maxY, teamType);
		}
		
		else if(playerType.equalsIgnoreCase("MidFielder")==true){
			this.player = new MidFielder(initialX, initialY, minY, maxY, teamType);
		}
		
		else if(playerType.equalsIgnoreCase("GoalKeeper")==true){
			this.player = new GoalKeeper(initialX, initialY, minY, maxY, teamType);
		}
		
		else if(playerType.equalsIgnoreCase("Defender")==true){
			this.player = new Defender(initialX, initialY, minY, maxY, teamType);
		}
		
		return this.player;
		
	}
}
