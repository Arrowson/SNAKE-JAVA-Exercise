import java.awt.Color;
import java.awt.Graphics;

//holds the Food class that the snake is made of
public class Food {
	//creates initial variables that will be used to draw the squares
	private int xcoord, ycoord, width, height;
	//constructor that ... constructs a food
	public Food(int xcoord, int ycoord, int tileSize) {
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		width = tileSize;
		height = tileSize;
	}
	public void tick() {
		
	}
	//draws the food, similar to Gamepanel.java
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(xcoord * width, ycoord * height, width, height);
	}
	public int getXcoord() {
		return xcoord;
	}
	public void setXcoord(int xcoord) {
		this.xcoord = xcoord;
	}
	public int getYcoord() {
		return ycoord;
	}
	public void setYcoord(int ycoord) {
		this.ycoord = ycoord;
	}
	
}