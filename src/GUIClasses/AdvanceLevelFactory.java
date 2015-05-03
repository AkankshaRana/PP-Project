package GUIClasses;

public class AdvanceLevelFactory extends LevelFactory {

	@Override
	public void setLevelAndStart(FoosBallTabel tabel,String gameModeSelected,String gameTypeSelected) {
		tabel.gameStart(this,gameModeSelected,gameTypeSelected);
		//tabel.add(team1Panel,BorderLayout.WEST);
		
	}

	@Override
	public String getLevel() {
		// TODO Auto-generated method stub
		return "Advance";
	}

}
