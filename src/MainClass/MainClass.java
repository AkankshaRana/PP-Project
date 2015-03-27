package MainClass;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.print.Paper;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import BoardClasses.MyFrame;



public class MainClass {
 
    public static void main(String[] args) {
 
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyFrame();
                
            }
        });
    }
     
}