import java.awt.Color;
import java.awt.Graphics;

//holds the BodyPart class that the snake is made of
public class BodyPart {
	//creates initial variables that will be used to draw the squares
	private int xcoord, ycoord, width, height;
	//constructor that ... constructs a bodypart
	public BodyPart(int xcoord, int ycoord, int tileSize) {
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		width = tileSize;
		height = tileSize;
	}
	public void tick() {
		
	}
	//draws the body part, similar to Gamepanel.java
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(xcoord * width, ycoord * height, width, height);
	}
	//getters and setters
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
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
