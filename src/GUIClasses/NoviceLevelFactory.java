package GUIClasses;

public class NoviceLevelFactory extends LevelFactory {

	@Override
	public void setLevelAndStart(FoosBallTabel tabel, String gameModeSelected,String gameTypeSelected) {
		tabel.gameStart(this,gameModeSelected,gameTypeSelected);
		
	}

	@Override
	public String getLevel() {
		// TODO Auto-generated method stub
		return "Novice";
	}

}
