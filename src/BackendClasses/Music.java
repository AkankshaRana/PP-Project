package BackendClasses;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Music implements Runnable{

	static String filePath;
	Thread threadInstance;
	public static Clip soundClip;
	private static Music classInstance=new Music("Toxin34-SlightJazz.wav");
	
	
	private Music(String path){
		this.filePath=path;
		this.threadInstance=new Thread(this);
		
	}
	
	
	@Override
	public void run() {
		try {
            
    		File soundFile = new File(Music.filePath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
            
             soundClip = AudioSystem.getClip();
            
            soundClip.open(audioInput);
            soundClip.start();
            
            
         } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         } catch (LineUnavailableException e) {
            e.printStackTrace();
         }
		
	}
	
	public static Music getInstance(){
		
		return classInstance;
		
	}
	
	public void setADifferentTrack(String path)
	{
		Music.filePath=path;
	}
	
	public void startMusic(){
		try{
		this.threadInstance.start();
		}
		catch(Exception e){
			System.out.println();
		}
	}
	
	public static void stopMusic(){
		//System.out.println("stoppin gthread.");
			soundClip.stop();
			soundClip.close();
		
	}
	
	public static void restart(){
		new Thread(new Runnable() {

	        @Override
	        public void run() {
	          //  Applet.newAudioClip(getClass().getResource(file)).play();
	        	try {
	                
	        		File soundFile = new File(filePath);
	                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
	                
	                 soundClip = AudioSystem.getClip();
	                
	                soundClip.open(audioInput);
	                soundClip.start();
	                
	                
	             } catch (UnsupportedAudioFileException e) {
	                e.printStackTrace();
	             } catch (IOException e) {
	                e.printStackTrace();
	             } catch (LineUnavailableException e) {
	                e.printStackTrace();
	             }
	        }
	    }).start();
	}
	
}

