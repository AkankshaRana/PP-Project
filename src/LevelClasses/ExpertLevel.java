package LevelClasses;

public class ExpertLevel implements Level{

	public String level;
	public int ballSpeed,playerSpeed;
	
	public ExpertLevel(){
		this.level="Expert";
		this.ballSpeed=20;
		this.playerSpeed=20;
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
