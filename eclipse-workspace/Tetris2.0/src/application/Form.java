package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Form {

	/**
	 * @author Tomomi
	 * 
	 * Tetris uses 7 different forms, L, J, O, I, Z, S, and T, and each form consist of four rectangles.
	 *
	 */
	public Rectangle a;	
	public Rectangle b;
	public Rectangle c;
	public Rectangle d;
	private Color color;
	private Shape name;
	private int angle;	//angle of the shape. use this to keep track of the angle (1, 2, 3, 4) (O shape does not require angle)
	/**
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param name
	 */
	public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d, Shape name, int angle) {
		
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.name = name;
		this.angle = angle;
		
		switch (name) {
		case L:
			color = Color.DARKGOLDENROD;
			break;
		case J:
			color = Color.DARKKHAKI;
			break;
		case O:
			color = Color.DARKSEAGREEN;
			break;
		case I:
			color = Color.TAN;
			break;
		case Z:
			color = Color.INDIANRED;
			break;
		case S:
			color = Color.MAROON;
			break;
		case T:
			color = Color.OLIVEDRAB;
			break;

		}
		this.a.setFill(color);
		this.b.setFill(color);
		this.c.setFill(color);
		this.d.setFill(color);
	}
	
	public Shape getShape() {
		return this.name;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public int setAngle(int angle) {
		return this.angle = angle;
	}
	
}
