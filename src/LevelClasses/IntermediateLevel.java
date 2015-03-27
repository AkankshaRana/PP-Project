package LevelClasses;

public class IntermediateLevel implements Level{

	public String level;
	public int ballSpeed,playerSpeed;
	
	public IntermediateLevel(){
		this.level="Intermediate";
		this.ballSpeed=10;
		this.playerSpeed=10;
	}
	
	@Override
	public void setLevel(String level) {
		this.level=level;
		
	}

	@Override
	public String getLevel() {
		
		return this.level;
	}

	@Override
	public int getBallSpeed() {
		
		return this.ballSpeed;
	}

	@Override
	public void setBallSpeed(int speed) {
		
		this.ballSpeed=speed;
	}

	@Override
	public void setPlayerSpeed(int speed) {
		this.playerSpeed=speed;
		
	}

	@Override
	public int getPlayerSpeed() {
		
		return this.playerSpeed;
	}
}
