package LevelClasses;

public class NoviceLevel implements Level{

	public String level;
	public int ballSpeed,playerSpeed;
	
	public NoviceLevel(){
		this.level="Novice";
		this.ballSpeed=5;
		this.playerSpeed=5;
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
