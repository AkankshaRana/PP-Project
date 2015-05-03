package GUIClasses;

public class IntermediateLevelFactory extends LevelFactory {

	@Override
	public void setLevelAndStart(FoosBallTabel tabel, String gameModeSelected,String gameTypeSelected) {
		tabel.gameStart(this,gameModeSelected,gameTypeSelected);

		
	}

	@Override
	public String getLevel() {
		// TODO Auto-generated method stub
		return "Intermediate";
	}

}
