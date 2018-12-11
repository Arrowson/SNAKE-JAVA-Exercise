//allows dimesion to be set
import java.awt.Color;
import java.awt.Dimension;
//allows the graphics (lines and boxes) to exist
import java.awt.Graphics;
import java.awt.event.KeyEvent;
//allows the program to utilize input
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

//is the panel that is inside of the frame
import javax.swing.JPanel;
//start of class
public class Gamepanel extends JPanel implements Runnable, KeyListener {
	
	//Makes it so different classes can work together. If I called it with an incorrect
	//serial number, it would error out. Safety protocol?
	private static final long serialVersionUID = 1L;
	//sets width and height that cannot be changed.
	public static final int WIDTH = 500, HEIGHT = 500;
	
	//http://cs.lmu.edu/~ray/notes/javathreading/
	private Thread thread;
	
	//uninitialized to determine if program is running
	private boolean running;
	
	//rudimentary control structure
	private boolean right = true, left = false, Up = false, Down = false;
	
	//initializes a bodypart
	private BodyPart b;
	
	//creates a list of bodyparts that will be the snake
	private ArrayList<BodyPart> Snake;
	
	//creates the variables to be passed to the BodyPart constructor
	private int xcoord = 10, ycoord = 10, size = 5;
	
	//holds the number of times ticks is called
	private int ticks = 0;
	
	//instantiates a food object
	private Food food;
	
	//holds a list of foodies
	private ArrayList<Food> foods;
	
	//random variable
	private Random r;
	
	//creates the gamepanel with the desired dimensions
	public Gamepanel() {
		
		//allows interaction
		setFocusable(true);
		
		//description
		// https://stackoverflow.com/questions/1783793/java-difference-between-the-setpreferredsize-and-setsize-methods-in-compone
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		//listener interface for allowing keystrokes
		addKeyListener(this);
		
		//initializes the Snake
		Snake = new ArrayList<BodyPart>();
		
		//initializes a foodie
		foods = new ArrayList<Food>(); 
		
		//instantiates the random number
		r = new Random();
		
		//calls start
		start();
		
	}
	//starts the game
	public void start() {
		//sets running to true
		running = true;
		//instantiates the thread
		thread = new Thread(this);
		//starts the thread
		thread.start();
	}
	//ends the game
	public void stop() {
		//ends the game.
		running = false;
		try {
			/*
			 * If t is a Thread object whose thread is currently executing, 
			 * then t.join(); it causes the current thread to pause its execution
			 * until thread it join completes its execution.
			 */
			System.out.println("GAME OVER");
			System.out.println("FINAL SCORE...");
			System.out.println(Snake.size());
			thread.join();
		} catch (InterruptedException e) {
			//prints error
			e.printStackTrace();
		}
	}
	//determines the 'ticks' or time frame the game runs on
	public void tick() {
		//creates a new bodypart and adds it to the Snake
		if(Snake.size() == 0) {
			b = new BodyPart(xcoord, ycoord, 10);
			Snake.add(b);
		}

		//holds the number of times it's called.
		ticks++;
		//creates movement and ticks controls the speed 
		if(ticks > 250000) {
			if(right) xcoord++;
			if(left) xcoord--;
			if(Up) ycoord--;
			if(Down) ycoord++;
			
			//reset ticks ??
			ticks = 0;
			
			//creates the new bodypart, thus moving the snake
			b = new BodyPart(xcoord, ycoord, 10);
			
			//saves it to the snake
			Snake.add(b);
			
			//if the snake gets bigger than 5 blocks, remove one... ?
			if(Snake.size() > size) {
				Snake.remove(0);
			}
		}
		//gives a benchmark for the first foodie
		if(foods.size() == 0) {
			int xcoord = r.nextInt(49);
			int ycoord = r.nextInt(49); 
			
			food = new Food(xcoord, ycoord, 10);
			foods.add(food);
		}
		//if the snake hits a food, make snake longer
		for(int i = 0; i < foods.size(); i++) {
			if(xcoord == foods.get(i).getXcoord() && ycoord == foods.get(i).getYcoord()) {
				size++;
				foods.remove(i);
				i++;
			}
		}
		//if the snake is out of bounds, game over
		if(xcoord < 0 || xcoord > 49 || ycoord < 0 || ycoord >49) {
			stop();
		}
		
		//if snake runs into itself end
		for(int i = 0; i < Snake.size(); i++) {
			if(xcoord == Snake.get(i).getXcoord() && ycoord == Snake.get(i).getYcoord() && Snake.size() > 1) {
				//makes sure the snake isn't counting itself
				if(i != Snake.size()-1) {
					stop();
				}
			}
		}
	}
	//draws the lines so it's like a graph
	public void paint(Graphics g) {
		
		//clears rectangle at start
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		//sets default color of graphics
		g.setColor(Color.GREEN);
		//fills in the area with our color
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//sets the vertical lines
		for(int i = 0; i < WIDTH/10; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}
		//sets the horizontal lines
		for(int i = 0; i < HEIGHT/10; i++) {
			g.drawLine(0, i * 10, HEIGHT, i * 10);
		}
		//draws the pieces
		for(int i = 0; i < Snake.size(); i++) {
			Snake.get(i).draw(g);
		}
		//draws the food
		for(int i = 0; i < foods.size(); i++) {
			foods.get(i).draw(g);
		}
	}
	//automatically created run method created when implementing runnable above.
	public void run() {
		while(running) {
			tick();
			repaint();
		}
	}
	
	//determines if a key has been pressed and executes it.
	public void keyPressed(KeyEvent e) {
		//determines the key
		int key = e.getKeyCode();
		
		//if the key is Right arrow key and we aren't currently moving left, move right.
		if(key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			Up = false;
			Down = false;
		}
		
		//same but with left
		if(key == KeyEvent.VK_LEFT && !right) {
			left = true;
			Up = false;
			Down = false;
		}
		
		//same but up
		if(key == KeyEvent.VK_UP && !Down) {
			left = false;
			Up = true;
			right = false;
		}
		
		//same but with down
		if(key == KeyEvent.VK_DOWN && !Up) {
			left = false;
			Down = true;
			right = false;
		}
		
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
