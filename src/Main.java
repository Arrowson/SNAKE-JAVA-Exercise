import javax.swing.JFrame;

public class Main {
	public Main() {
		/*
		 * A frame, implemented as an instance of the JFrame class, is a window 
		 *that has decorations such as a border, a title, and supports
		 *button components that close or iconify the window. Applications with a GUI usually 
		 *include at least one frame. 
		 */
		JFrame frame = new JFrame();
		//instantiates an object (gamepanel) of class Gamepanel.
		Gamepanel gamepanel = new Gamepanel();
		
		//adds the frame
		frame.add(gamepanel);
		//determines when it closes
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//title of the frame
		frame.setTitle("SNAKE");

		//sizes the frame correctly. Can also use setSize or setBounds, but this is preferred.
		frame.pack();
		//allows the frame to be visible...
		frame.setVisible(true);
		//makes the frame 'appear' in the center of the screen. Can set it so multiple frames don't overlap
		frame.setLocationRelativeTo(null);
	}
	//default constructor
	public static void main( String[] args) {
		//calls our Main constructor. Kinda a pseudo-adapter pattern
		new Main();
	}
}
