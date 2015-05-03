package BackendClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class Database {
private static String DatabaseFile="FoosBallDatabase.txt";// – for storing the player win-score
    
    public static void updateDatabase(String name,int score)
    {
        
        
        try 
        {
            BufferedReader b_reader = new BufferedReader(new FileReader(DatabaseFile));
            String PlayerList;
            String data[]=new String[20];
            int flag=1;
            int i=0;
			while ((PlayerList = b_reader.readLine()) != null) 
                        {
                            if(PlayerList.contains(name))
                            {
                                flag=0;
                                data[i]=name+" "+score;
                            }
                            else
                                data[i]=PlayerList; 
                            i++;
			}
                        b_reader.close();
                        if(flag==1)
                        {
                            data[i]=name+" "+score;
                            i++;
                        }
                        PrintWriter out = new PrintWriter(new FileWriter(DatabaseFile));
                        for(int j=0;j<i;j++)
                             out.println(data[j]);
                        out.close();                        
        }
        catch (IOException e) {
			e.printStackTrace();
		} 
            
            
    }	 
	 public static void playSound() {
		    new Thread(new Runnable() {

		        @Override
		        public void run() {
		        	Clip soundClip;
		        	try {
		                
		        		File soundFile = new File("oddjazzy.wav");
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
