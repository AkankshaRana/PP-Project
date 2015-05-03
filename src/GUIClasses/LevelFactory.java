package GUIClasses;

public abstract class LevelFactory 
{

	abstract public void setLevelAndStart(FoosBallTabel tabel,String gameModeSelected,String gameTypeSelected);
	public abstract String getLevel();

}
