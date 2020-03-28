package application;

import java.util.Random;

import javafx.scene.shape.Rectangle;

public class Controller implements ControllerInterface {

	
	//public static final int MOVE = Tetris.MOVE;
	public static final int SIZE = Tetris.SIZE;
	public static int XMAX = Tetris.XMAX;
	public static int YMAX = Tetris.YMAX;
	public static int[][] MESH = Tetris.MESH;
	
	
	@Override
	public Form makeRectangle() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int num = rand.nextInt(105);
		
		Rectangle a = new Rectangle(SIZE-1, SIZE-1);
		Rectangle b = new Rectangle(SIZE-1, SIZE-1);
		Rectangle c = new Rectangle(SIZE-1, SIZE-1);
		Rectangle d = new Rectangle(SIZE-1, SIZE-1);
		Shape name = Shape.O;
		
		
		if (num < 15) {
			a.setX(XMAX/2 -SIZE);
			a.setY(0);
			b.setX(XMAX/2);
			b.setY(0);
			c.setX(XMAX/2 + SIZE);
			c.setY(0);
			d.setX(XMAX/2 + SIZE);
			d.setY(-SIZE);
			name = Shape.L;
		}
		else if (num < 30) {
			d.setX(XMAX/2 -SIZE);
			d.setY(-SIZE);
			c.setX(XMAX/2 -SIZE);
			c.setY(0);
			b.setX(XMAX/2);
			b.setY(0);
			a.setX(XMAX/2 + SIZE);
			a.setY(0);
			name = Shape.J;
		}
		else if (num < 45) {
			a.setX(XMAX/2 -SIZE);
			a.setY(-SIZE);
			b.setX(XMAX/2 -SIZE);
			b.setY(0);
			c.setX(XMAX/2);
			c.setY(0);
			d.setX(XMAX/2);
			d.setY(-SIZE);
			name = Shape.O;
		}
		else if (num < 60) {
			a.setX(XMAX/2 -SIZE);
			a.setY(-SIZE);
			b.setX(XMAX/2);
			b.setY(-SIZE);
			c.setX(XMAX/2 + SIZE);
			c.setY(-SIZE);
			d.setX(XMAX/2 + 2*SIZE);
			d.setY(-SIZE);
			name = Shape.I;
		}
		else if (num < 75) {
			a.setX(XMAX/2 -SIZE);
			a.setY(-SIZE);
			b.setX(XMAX/2);
			b.setY(-SIZE);
			c.setX(XMAX/2);
			c.setY(0);
			d.setX(XMAX/2 + SIZE);
			d.setY(0);
			name = Shape.Z;
		}
		else if (num < 90) {
			a.setX(XMAX/2 -SIZE);
			a.setY(0);
			b.setX(XMAX/2);
			b.setY(0);
			c.setX(XMAX/2);
			c.setY(-SIZE);
			d.setX(XMAX/2 + SIZE);
			d.setY(-SIZE);
			name = Shape.S;
		}
		else {
			a.setX(XMAX/2 -SIZE);
			a.setY(0);
			d.setX(XMAX/2);
			d.setY(-SIZE);
			b.setX(XMAX/2);
			b.setY(0);
			c.setX(XMAX/2 + SIZE);
			c.setY(0);
			name = Shape.T;
		}
		
		return new Form(a, b, c, d, name, 1);
	}
	

	@Override
	public void moveRight(Form form) {
		// TODO Auto-generated method stub	
		if (form.a.getX() + SIZE < XMAX && form.b.getX() + SIZE < XMAX && form.c.getX() + SIZE < XMAX && form.d.getX() + SIZE < XMAX)
		{
			form.a.setX(form.a.getX() + SIZE);
			form.b.setX(form.b.getX() + SIZE);
			form.c.setX(form.c.getX() + SIZE);
			form.d.setX(form.d.getX() + SIZE);
		}	
	}

	@Override
	public void moveLeft(Form form) {
		// TODO Auto-generated method stub
		if (form.a.getX() - SIZE >= 0 && form.b.getX() - SIZE >= 0 && form.c.getX() - SIZE >= 0 && form.d.getX() - SIZE >= 0)
		{
			form.a.setX(form.a.getX() - SIZE);
			form.b.setX(form.b.getX() - SIZE);
			form.c.setX(form.c.getX() - SIZE);
			form.d.setX(form.d.getX() - SIZE);
		}	
	}

	@Override
	public void turnRect(Form form) {
		// TODO Auto-generated method stub
		
		Shape name = form.getShape();
		int angle = form.getAngle();
		
		switch (name) {
		
		case L:
			switch(angle) {
			case 1:
				if (form.a.getX()+SIZE <= XMAX && form.c.getX()-SIZE >= 0 && form.c.getY()-SIZE < YMAX && form.d.getY()+SIZE*2 < YMAX)
				{
					form.a.setX(form.a.getX()+SIZE);
					form.a.setY(form.a.getY()-SIZE);
					form.c.setX(form.c.getX()-SIZE);
					form.c.setY(form.c.getY()+SIZE);
					form.d.setY(form.d.getY()+SIZE*2);	
					form.setAngle(2);				
				}
				break;
			case 2:
				if (form.a.getX()+SIZE < XMAX && form.a.getY()+SIZE < YMAX && form.c.getX()-SIZE >= 0 && form.c.getY()-SIZE < YMAX && form.d.getX()-SIZE*2 >= 0)
				{
					form.a.setX(form.a.getX()+SIZE);
					form.a.setY(form.a.getY()+SIZE);
					form.c.setX(form.c.getX()-SIZE);
					form.c.setY(form.c.getY()-SIZE);
					form.d.setX(form.d.getX()-SIZE*2);
					//form.d.setY(form.d.getY()+SIZE*2);
					form.setAngle(3);
				}
				break;
			case 3:
				
				form.a.setX(form.a.getX()-SIZE);
				form.a.setY(form.a.getY()+SIZE);
				form.c.setX(form.c.getX()+SIZE);
				form.c.setY(form.c.getY()-SIZE);
				form.d.setY(form.d.getY()-SIZE*2);	
				form.setAngle(4);
				break;
			case 4:
				if (form.d.getX()+SIZE*2 < XMAX)
				{
					form.a.setX(form.a.getX()-SIZE);
					form.a.setY(form.a.getY()-SIZE);
					form.c.setX(form.c.getX()+SIZE);
					form.c.setY(form.c.getY()+SIZE);
					form.d.setX(form.d.getX()+SIZE*2);
					//form.d.setY(form.d.getY()+SIZE*2);
					form.setAngle(1);
				}
				
				break;
			}
			break;
		case J:
			switch(angle) {
			case 1:
				if (form.d.getY()+SIZE*2 < YMAX)
				{
					form.a.setX(form.a.getX()-SIZE);
					form.a.setY(form.a.getY()+SIZE);
					form.c.setX(form.c.getX()+SIZE);
					form.c.setY(form.c.getY()-SIZE);
					form.d.setX(form.d.getX()+SIZE*2);	
					form.setAngle(2);
				}
				
				break;
			case 2:
				if (form.a.getX()-SIZE >=0)
				{
					form.a.setX(form.a.getX()-SIZE);
					form.a.setY(form.a.getY()-SIZE);
					form.c.setX(form.c.getX()+SIZE);
					form.c.setY(form.c.getY()+SIZE);
					form.d.setY(form.d.getY()+SIZE*2);
					//form.d.setY(form.d.getY()+SIZE*2);
					form.setAngle(3);
				}
				
				break;
			case 3:
				form.a.setX(form.a.getX()+SIZE);
				form.a.setY(form.a.getY()-SIZE);
				form.c.setX(form.c.getX()-SIZE);
				form.c.setY(form.c.getY()+SIZE);
				form.d.setX(form.d.getX()-SIZE*2);	
				form.setAngle(4);
				break;
			case 4:
				if (form.a.getX()+SIZE < XMAX)
				{
					form.a.setX(form.a.getX()+SIZE);
					form.a.setY(form.a.getY()+SIZE);
					form.c.setX(form.c.getX()-SIZE);
					form.c.setY(form.c.getY()-SIZE);
					form.d.setY(form.d.getY()-SIZE*2);
					//form.d.setY(form.d.getY()+SIZE*2);
					form.setAngle(1);
				}
				
				break;
			}
			break;
		case O:
				
			break;
		case I:
			
			switch(angle) {
			case 1:
				if (form.d.getY()+SIZE*2 < YMAX)
				{
					form.a.setX(form.a.getX()+SIZE);
					form.a.setY(form.a.getY()-SIZE);
					form.c.setX(form.c.getX()-SIZE);
					form.c.setY(form.c.getY()+SIZE);
					form.d.setX(form.d.getX()-SIZE*2);	
					form.d.setY(form.d.getY()+SIZE*2);
					form.setAngle(2);
				}
				
				break;
			case 2:
				if (form.d.getX()-SIZE*2 >= 0 && form.a.getX()+SIZE < XMAX)
				{
					form.a.setX(form.a.getX()+SIZE);
					form.a.setY(form.a.getY()+SIZE);
					form.c.setX(form.c.getX()-SIZE);
					form.c.setY(form.c.getY()-SIZE);
					form.d.setX(form.d.getX()-SIZE*2);
					form.d.setY(form.d.getY()-SIZE*2);
					form.setAngle(3);
				}
				
				break;
			case 3:
				if (form.a.getY()+SIZE < YMAX)
				{
					form.a.setX(form.a.getX()-SIZE);
					form.a.setY(form.a.getY()+SIZE);
					form.c.setX(form.c.getX()+SIZE);
					form.c.setY(form.c.getY()-SIZE);
					form.d.setX(form.d.getX()+SIZE*2);	
					form.d.setY(form.d.getY()-SIZE*2);
					form.setAngle(4);
				}
				
				break;
			case 4:
				if (form.a.getX()-SIZE >= 0 && form.d.getX()+SIZE*2 < XMAX)
				{
					form.a.setX(form.a.getX()-SIZE);
					form.a.setY(form.a.getY()-SIZE);
					form.c.setX(form.c.getX()+SIZE);
					form.c.setY(form.c.getY()+SIZE);
					form.d.setX(form.d.getX()+SIZE*2);	
					form.d.setY(form.d.getY()+SIZE*2);
					//form.d.setY(form.d.getY()+SIZE*2);
					form.setAngle(1);
				}
				
				break;
			}
			
			break;
		case Z:
			
			switch(angle) {
			case 1:
				
				form.a.setX(form.a.getX()+SIZE);
				form.a.setY(form.a.getY()-SIZE);
				form.c.setX(form.c.getX()-SIZE);
				form.c.setY(form.c.getY()-SIZE);
				form.d.setX(form.d.getX()-SIZE*2);	
				//form.d.setY(form.d.getY()+SIZE*2);
				form.setAngle(2);
				break;
			case 2:
				if (form.a.getX()+SIZE < XMAX)
				{
					form.a.setX(form.a.getX()+SIZE);
					form.a.setY(form.a.getY()+SIZE);
					form.c.setX(form.c.getX()+SIZE);
					form.c.setY(form.c.getY()-SIZE);
					//form.d.setX(form.d.getX()-SIZE*2);
					form.d.setY(form.d.getY()-SIZE*2);
					form.setAngle(3);
				}
				
				break;
			case 3:
				if (form.a.getY()+SIZE < YMAX)
				{
					form.a.setX(form.a.getX()-SIZE);
					form.a.setY(form.a.getY()+SIZE);
					form.c.setX(form.c.getX()+SIZE);
					form.c.setY(form.c.getY()+SIZE);
					form.d.setX(form.d.getX()+SIZE*2);	
					//form.d.setY(form.d.getY()-SIZE*2);
					form.setAngle(4);
				}
				
				break;
			case 4:
				if (form.a.getX()-SIZE >= 0)
				{
					form.a.setX(form.a.getX()-SIZE);
					form.a.setY(form.a.getY()-SIZE);
					form.c.setX(form.c.getX()-SIZE);
					form.c.setY(form.c.getY()+SIZE);
					//form.d.setX(form.d.getX()+SIZE*2);	
					//form.d.setY(form.d.getY()+SIZE*2);
					form.d.setY(form.d.getY()+SIZE*2);
					form.setAngle(1);
				}
				
				break;
			}
			break;	
		case S:
			
			switch(angle) {
			case 1:
				if (form.d.getY()+SIZE*2 < YMAX)
				{
					form.a.setX(form.a.getX()+SIZE);
					form.a.setY(form.a.getY()-SIZE);
					form.c.setX(form.c.getX()+SIZE);
					form.c.setY(form.c.getY()+SIZE);
					//form.d.setX(form.d.getX()-SIZE*2);	
					form.d.setY(form.d.getY()+SIZE*2);
					form.setAngle(2);
				}
				
				break;
			case 2:
				if ( form.d.getX()-SIZE*2 >= 0)
				{
					form.a.setX(form.a.getX()+SIZE);
					form.a.setY(form.a.getY()+SIZE);
					form.c.setX(form.c.getX()-SIZE);
					form.c.setY(form.c.getY()+SIZE);
					form.d.setX(form.d.getX()-SIZE*2);
					//form.d.setY(form.d.getY()-SIZE*2);
					form.setAngle(3);
				}
				
				break;
			case 3:
				
				form.a.setX(form.a.getX()-SIZE);
				form.a.setY(form.a.getY()+SIZE);
				form.c.setX(form.c.getX()-SIZE);
				form.c.setY(form.c.getY()-SIZE);
				//form.d.setX(form.d.getX()+SIZE*2);	
				form.d.setY(form.d.getY()-SIZE*2);
				form.setAngle(4);
				break;
			case 4:
				if(form.d.getX()+SIZE*2 < XMAX)
				{
					form.a.setX(form.a.getX()-SIZE);
					form.a.setY(form.a.getY()-SIZE);
					form.c.setX(form.c.getX()+SIZE);
					form.c.setY(form.c.getY()-SIZE);
					form.d.setX(form.d.getX()+SIZE*2);	
					//form.d.setY(form.d.getY()+SIZE*2);
					//form.d.setY(form.d.getY()+SIZE*2);
					form.setAngle(1);
				}
				
				break;
			}
			break;
		case T:
			
			switch(angle) {
			case 1:
				if (form.d.getX()+SIZE < XMAX && form.c.getY()+SIZE < YMAX)
				{
					form.a.setX(form.a.getX()+SIZE);
					form.a.setY(form.a.getY()-SIZE);
					form.c.setX(form.c.getX()-SIZE);
					form.c.setY(form.c.getY()+SIZE);
					form.d.setX(form.d.getX()+SIZE);	
					form.d.setY(form.d.getY()+SIZE);
					form.setAngle(2);
				}
				
				break;
			case 2:
				if (form.c.getX()-SIZE >= 0) {
					form.a.setX(form.a.getX()+SIZE);
					form.a.setY(form.a.getY()+SIZE);
					form.c.setX(form.c.getX()-SIZE);
					form.c.setY(form.c.getY()-SIZE);
					form.d.setX(form.d.getX()-SIZE);
					form.d.setY(form.d.getY()+SIZE);
					form.setAngle(3);
				}
				
				break;
			case 3:
				form.a.setX(form.a.getX()-SIZE);
				form.a.setY(form.a.getY()+SIZE);
				form.c.setX(form.c.getX()+SIZE);
				form.c.setY(form.c.getY()-SIZE);
				form.d.setX(form.d.getX()-SIZE);	
				form.d.setY(form.d.getY()-SIZE);
				form.setAngle(4);
				break;
			case 4:
				if (form.c.getX()+SIZE < XMAX)
				{
					form.a.setX(form.a.getX()-SIZE);
					form.a.setY(form.a.getY()-SIZE);
					form.c.setX(form.c.getX()+SIZE);
					form.c.setY(form.c.getY()+SIZE);
					form.d.setX(form.d.getX()+SIZE);	
					form.d.setY(form.d.getY()-SIZE);
					//form.d.setY(form.d.getY()+SIZE*2);
					form.setAngle(1);
				}
				
				break;
			}
			break;
		}
		
		
	}

	
	
	

	@Override
	public void moveFaster(Form form) {
		// TODO Auto-generated method stub
		if (form.a.getY() + SIZE*2 < YMAX && form.b.getY() + SIZE*2 < YMAX && form.c.getY() + SIZE*2 < YMAX && form.d.getY() + SIZE*2 < YMAX
				&& MESH[(int) (form.a.getX() / SIZE)][(int) form.a.getY() / SIZE + 2] != 1 
				&& MESH[(int) (form.b.getX() / SIZE)][(int) form.b.getY() / SIZE + 2] != 1
				&& MESH[(int) (form.c.getX() / SIZE)][(int) form.c.getY() / SIZE + 2] != 1
				&& MESH[(int) (form.d.getX() / SIZE)][(int) form.d.getY() / SIZE + 2] != 1)
		{
			form.a.setY(form.a.getY()+SIZE*2);
			form.b.setY(form.b.getY()+SIZE*2);
			form.c.setY(form.c.getY()+SIZE*2);
			form.d.setY(form.d.getY()+SIZE*2);
		}
		
	}

}
