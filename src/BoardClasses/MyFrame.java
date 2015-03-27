package BoardClasses;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class MyFrame extends JFrame {
	//private JTextArea textArea;
    public Team1_Panel panel;
    public Team2_Panel panel2;
    public FoosBall_Tabel panel3;
     
    public MyFrame() {
        // Set the title
        super("FoosBall");
         
        // Set the layout. BorderLayout lets you
        // add components at the centre, north, south
        // east and west positions.
        setLayout(new BorderLayout());
         
        // Now we use our panel instead of a button
       // textArea = new JTextArea()
        //panel = new Team1_Panel();
       // panel4=new Team1_Panel();
        panel2 = new Team2_Panel();
        panel = new Team1_Panel(panel2);
        panel3 = new FoosBall_Tabel(panel,panel2);
         
        // Add the text area in the centre of the window.
       // add(textArea, BorderLayout.CENTER);
         
        // Add the panel to the left of the window (west)
        add(panel, BorderLayout.WEST);
      //  add()
        add(panel2, BorderLayout.EAST);
        //add(panel4,BorderLayout.CENTER);
        add(panel3, BorderLayout.SOUTH);
        
         
     
        // Make the app quit when we close the window.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
         
         
        // Set the window to 800 pixels wide and 600 high
        setSize(820, 600);
         
        // Make the window visible.
        setVisible(true);
    }
}
